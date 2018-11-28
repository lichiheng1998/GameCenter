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
     * the total score of this board
     */
    private int scoreTotal;

    /**
     * initialize a new MemoManger
     *
     * @param width width of the game board
     * @param height height of the game board
     * @param level hard or normal for this game
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
     * boolean value represent if game is in crazy mode
     * @return whether the game in crazy mode
     */
    public boolean isLevel() {
        return level;
    }

    /**
     * return the list of MemoTile Displayed
     * @return list of MemoTile displayed
     */
    public List<MemoTile> getSequenceOrder() {
        return sequenceOrder;
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
     * Generate random position under the bound of current complexity.
     * @param bound the upper bound of the id generated.
     */
    private void shuffleSequence(int bound){
        sequenceOrder.clear();
        int randomID = ThreadLocalRandom.current().nextInt(bound);
        sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEACTIVE));
        for (int count = 1; count < curComplexity; count++) {
            randomID = ThreadLocalRandom.current().nextInt(bound);
            int fake = ThreadLocalRandom.current().nextInt(10);
            if(fake > 2){
                sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEACTIVE));
            } else {
                sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEFAKE));
            }
        }
    }
    /**
     * regenerate random active memoTiles to update sequenceOrder
     * different generate method based on value of level
     */
    public void updateActiveTiles(){

        int bound = getSize();
        // Generate some Tiles with random ids.
        if(level){
            shuffleSequence(bound);
        } else {
            int randomID = ThreadLocalRandom.current().nextInt(bound);
            sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEACTIVE));
        }

        curComplexity += 1;
    }

    /**
     * Get a new MemoManager by this.width, this.height and this.level
     *
     * @return a new MemoManager by this.width, this.height and this.level
     */
    public MemoManager getNewInstance(){
        return new MemoManager(width, height, level);
    }

    /**
     * Set the current total score to the new total score.
     *
     * @param scoreTotal the new total score want to be set to
     */
    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    /**
     * Get the current total score for this MemoManager.
     *
     * @return the current total score for this MemoManager
     */
    public int getScoreTotal() {
        return scoreTotal;
    }

    /**
     * Get the current height for this MemoManager.
     *
     * @return the current height for this MemoManager
     */
    public int getHeightDifficulty() {
        return height;
    }
}
