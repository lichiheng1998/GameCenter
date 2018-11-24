package fall2018.csc207project.Memorization.controllers;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CursorAdapter;

import java.util.List;

import fall2018.csc207project.Memorization.model.BoardStatus;
import fall2018.csc207project.Memorization.model.MemoBoard;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Controllers.CustomAdapter;
import fall2018.csc207project.SlidingTile.Views.GestureDetectGridView;

public class MemoGameActivity extends AppCompatActivity {

    private List<Button> memoButtons;
    private GestureDetectGridView boardview;
    private int columnWidth;
    private int columnHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemoBoard memoBoard = (MemoBoard) getIntent().getSerializableExtra("save");
        BoardStatus boardStatus = new BoardStatus(memoBoard);
        this.memoButtons = memoBoard.initializeButtons(this);
        setContentView(R.layout.memo_main);
        setUpGridView(memoBoard);
    }

    private void setUpGridView(MemoBoard memoBoard) {
        final int dim = memoBoard.getBoardSize();
        boardview = findViewById(R.id.grid2);
        boardview.setNumColumns(dim);
        boardview.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        boardview.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = boardview.getMeasuredWidth();
                        int displayHeight = boardview.getMeasuredHeight();

                        columnWidth = displayWidth / dim;
                        columnHeight = displayHeight / dim;
                        display();
                    }
                });

    }

    public void display(){
        boardview.setAdapter(new CustomAdapter(memoButtons, columnWidth, columnHeight));
    }


}
