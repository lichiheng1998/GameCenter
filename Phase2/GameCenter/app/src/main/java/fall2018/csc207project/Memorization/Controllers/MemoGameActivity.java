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

import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Views.MemoGameView;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Controllers.CustomAdapter;

public class MemoGameActivity extends AppCompatActivity implements MemoGameView {

    private GridView boardview;
    private List<Button> memoButtons;
    private GamePresenter presenter;
    private Button filledButton;
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
        presenter.start();
    }

    private void setupButtons(int size){
        memoButtons = new ArrayList<>();
        for(int count = 0; count < size; count++){
            final Button button = new Button(getApplicationContext());
            button.setTag(count);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    presenter.onTapOnTile((int)button.getTag());
                    Log.e("test", "onClick: " );
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

    public void updateButtonWithColor(int pos, int color) {
        if(filledButton != null){
            if(filledButton == memoButtons.get(0))
                setButtonColor(memoButtons.get(0), android.R.color.white);
            restoreButtonColor();
        }
        Log.e("number of buttons", "updateButtonWithColor: " + String.valueOf(memoButtons.size()));
        filledButton = memoButtons.get(pos);
        setButtonColor(filledButton, color);
        ViewCompat.setBackgroundTintList(filledButton, ContextCompat.getColorStateList(this,
                color));
    }

    @Override
    public void updateButtonToRed(int pos){
        updateButtonWithColor(pos, android.R.color.holo_red_dark);
    }
    @Override
    public void updateButtonToGreen(int pos){
        updateButtonWithColor(pos, android.R.color.holo_green_dark);
    }
    @Override
    public void restoreButtonColor(){
        setButtonColor(filledButton, android.R.color.white);
    }
    private void setButtonColor(Button button, int color){
        ViewCompat.setBackgroundTintList(button, ContextCompat.getColorStateList(this,
                color));
    }
    @Override
    public void updateScore(int score) {

    }
}
