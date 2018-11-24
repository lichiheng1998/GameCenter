package fall2018.csc207project.Memorization.Models;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MemosManager implements Iterable<MemoTile>{
    private int curComplexity;
    private List<MemoTile> sequenceOrder;

    MemosManager(){
        // Set the current complexity to be 1.
        curComplexity = 1;
        sequenceOrder = new ArrayList<>();
        // Update the active tiles.
        updateActiveTiles();
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
        // Generate some Tiles with random ids.
        for (int count = 0; count < curComplexity; count++) {
            randomID = ThreadLocalRandom.current().nextInt(curComplexity);
            sequenceOrder.add(new MemoTile(randomID, MemoTile.TYPEACTIVE));
        }
        curComplexity += 1;
    }
}
