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

    public MemoManager(int width, int height){
        this.width = width;
        this.height = height;
        // Set the current complexity to be 1.
        curComplexity = 10;
        sequenceOrder = new ArrayList<>();
        // Update the active tiles.
        updateActiveTiles();
    }

    public int getSize(){
        return width * height;
    }

    public int getCurComplexity() {
        return curComplexity;
    }

    @NonNull
    @Override
    public Iterator<MemoTile> iterator() {
        return sequenceOrder.iterator();
    }

    public void updateActiveTiles(){
        sequenceOrder.clear();
        int randomID;
        int bound = getSize();
        // Generate some Tiles with random ids.
        for (int count = 0; count < curComplexity; count++) {
            randomID = ThreadLocalRandom.current().nextInt(bound);
            sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEACTIVE));
        }
        curComplexity += 1;
    }
}
