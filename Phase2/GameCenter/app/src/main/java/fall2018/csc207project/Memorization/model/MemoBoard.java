package fall2018.csc207project.Memorization.model;

import android.content.Context;
import android.widget.Button;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemoBoard implements Serializable {
    private int boardSize;
    private Memo[][] memos;

    public MemoBoard(int boardSize) {
        this.boardSize = boardSize;
        int dim = this.boardSize;
        this.memos = new Memo[dim][dim];
        int num = 0;
        for (int r = 0; r != dim; r++) {
            for (int c = 0; c != dim; c++) {
                memos[r][c] = new Memo(num + 1);
            }
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public Memo[][] getMemos() {
        return memos;
    }

    public List<Button> initializeButtons(Context context){
        List<Button> memoButtons = new ArrayList<>();
        for (int r = 0; r != boardSize; r++) {
            for (int c = 0; c != boardSize; c++) {
                Button temp = new Button(context);
                temp.setTextSize(35);
                temp.setText(Integer.toString(memos[r][c].getId()));
                memoButtons.add(temp);
            }
        }
        return memoButtons;
    }
}