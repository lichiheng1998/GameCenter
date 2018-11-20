package fall2018.csc207project.Memorization.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class BoardStatus{
    private int curComplexity=0;
    private int indexPointer=0;
    private ArrayList<Integer> sequenceOrder = new ArrayList<Integer>();
    private int randomPoll;
    private Iterator checks = sequenceOrder.iterator();


    BoardStatus(MemoBoard bd){
        int size = bd.getBoardSize();
        this.randomPoll = size * size;
    }

    public int getCurComplexity() {
        return curComplexity;
    }

    public int getIndexPointer(){
        return indexPointer;
    }

    public int nextCheck(){
        if (checks.hasNext()){
            indexPointer += 1;
            return (Integer) checks.next();
        } else{
            return -1;
        }
    }

    public ArrayList getLightupList(){
        int next = ThreadLocalRandom.current().nextInt(1,randomPoll+1);
        sequenceOrder.add(next);
        curComplexity += 1;
        checks = sequenceOrder.iterator();
        return sequenceOrder;
    }

}
