package fall2018.csc207project.Memorization.Models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MemoManager implements Iterable<MemoTile>, Serializable{
    /**
     * width of the memo board
     */
    public final int width;
    /**
     * height of memo board
     */
    public final int height;
    /**
     * current number of memoTile output
     */
    private int curComplexity;
    /**
     * list of all memo tiles
     */
    private List<MemoTile> sequenceOrder;

    /**
     * hard or normal game level
     */
    private boolean level;

    /**
     * initialize a new MemoManger
     * @param width width of the game board
     * @param height height of the game board
     */
    public MemoManager(int width, int height, boolean level){
        this.width = width;
        this.height = height;
        this.level = level;
        // Set the current complexity to be 1.
        curComplexity = 1;
        sequenceOrder = new ArrayList<>();
        // Update the active tiles.
        updateActiveTiles();

    }

    /**
     * return total number of memo tiles of the game board
     * @return total number of memo tiles on the board
     */
    public int getSize(){
        return width * height;
    }

    /**
     * give the current number of memo tiles to memorize
     * @return current number of memo tiles for user to memorize
     */
    public int getCurComplexity() {
        return curComplexity;
    }

    /**
     * iterator of MemoTile
     * @return MemoTile iterator
     */
    @NonNull
    @Override
    public Iterator<MemoTile> iterator() {
        return sequenceOrder.iterator();
    }

    /**
     * regenerate random active memoTiles to update sequenceOrder
     * different generate method based on value of level
     */
    public void updateActiveTiles(){
        int randomID;
        int bound = getSize();
        // Generate some Tiles with random ids.
        if(level){
            sequenceOrder.clear();
            for (int count = 0; count < curComplexity; count++) {
                randomID = ThreadLocalRandom.current().nextInt(bound);
                int fake = ThreadLocalRandom.current().nextInt(10);
                if(fake > 2){
                    sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEACTIVE));
                } else {
                    sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEFAKE));
                }
            }
        } else {
            randomID = ThreadLocalRandom.current().nextInt(bound);
            sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEACTIVE));
        }

        curComplexity += 1;
    }

    public MemoManager getNewInstance(){
        return new MemoManager(width, height, level);
    }

}
