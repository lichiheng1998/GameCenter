package fall2018.csc207project.Memorization.Controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Views.MemoGameView;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Controllers.CustomAdapter;

public class MemoGameActivity extends AppCompatActivity implements MemoGameView {

    private GridView boardview;
    private List<Button> memoButtons;
    private GamePresenter presenter;
    private int columnWidth;
    private int columnHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MemoGamePresenter(this);
        MemoManager memoManager = (MemoManager) getIntent().getSerializableExtra("save");
        presenter.setMemoManager(memoManager);
        setContentView(R.layout.memo_main);
        setupButtons(memoManager.getSize());
        setupGridView(memoManager);
        presenter.startCycle();
    }

    private void setupButtons(int size){
        memoButtons = new ArrayList<>();
        for(int count = 0; count < size; count++){
            final Button button = new Button(getApplicationContext());
            button.setTag(count);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    presenter.onTapOnTile((int)button.getTag());
                }
            });
            setButtonColor(button, android.R.color.white);
            memoButtons.add(button);
        }
    }

    private void setupGridView(final MemoManager memoManager) {
        boardview = findViewById(R.id.mapGrid);
        boardview.setNumColumns(memoManager.width);
        boardview.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        boardview.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = boardview.getMeasuredWidth();
                        int displayHeight = boardview.getMeasuredHeight();

                        columnWidth = displayWidth / memoManager.width;
                        columnHeight = displayHeight / memoManager.height;
                        display();
                    }
                });
    }

    public void display(){
        boardview.setAdapter(new CustomAdapter(memoButtons, columnWidth, columnHeight));
    }

    @Override
    public void flashButtonToRed(int pos, Integer delay){
        setButtonColor(memoButtons.get(pos), android.R.color.holo_red_dark);
        if (delay != null){
            unflashButton(pos, delay);
        }
    }

    private void unflashButton(final int pos, int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() { restoreButtonColor(pos);
            }
        }, delay);
    }

    @Override
    public void flashButtonToGreen(int pos, Integer delay){
        setButtonColor(memoButtons.get(pos), android.R.color.holo_green_dark);
        if (delay != null){
            unflashButton(pos, delay);
        }
    }

    @Override
    public void restoreButtonColor(int pos){
        setButtonColor(memoButtons.get(pos), android.R.color.white);
    }
    private void setButtonColor(Button button, int color){
        ViewCompat.setBackgroundTintList(button, ContextCompat.getColorStateList(this,
                color));
    }
    @Override
    public void updateScore(int score) {

    }
}
