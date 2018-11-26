package fall2018.csc207project.Memorization.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc207project.Memorization.Controllers.GamePresenter;
import fall2018.csc207project.Memorization.Controllers.MemoGamePresenter;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Views.MemoGameView;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Controllers.CustomAdapter;

public class MemoGameActivity extends AppCompatActivity implements MemoGameView,
        GameOverDialogFragment.GameOverDialogListener {

    /**
     * Represent the board view of the game.
     */
    private GridView boardview;
    /**
     * List of buttons on the board.
     */
    private List<Button> memoButtons;
    /**
     * The game logic persenter that control this view.
     */
    private GamePresenter presenter;
    /**
     * Width and height of the board.
     */
    private int columnWidth;
    private int columnHeight;
    private boolean isActive;

    private TextView score, life, status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_main);
        score = findViewById(R.id.Score);
        life = findViewById(R.id.life);
        status = findViewById(R.id.status);
        presenter = new MemoGamePresenter(this);
        isActive = true;
        MemoManager memoManager = (MemoManager) getIntent().getSerializableExtra("save");
        presenter.setMemoManager(memoManager);
        setupButtons(memoManager.getSize());
        setupGridView(memoManager);
        presenter.startCycle();
    }

    /**
     * Initialize the buttons base on the capacity of the board.
     * @param size the capacity.
     */
    private void setupButtons(int size){
        memoButtons = new ArrayList<>();
        for(int count = 0; count < size; count++){
            final Button button = new Button(getApplicationContext());
            button.setTag(count);
            button.setText(String.valueOf(count));
            button.setTextSize(40);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    presenter.onTapOnTile((int)button.getTag());
                }
            });
            setButtonColor(button, android.R.color.white);
            memoButtons.add(button);
        }
    }

    /**
     * Initialize the buttons base on the capacity of the board.
     * @param memoManager the memoManager used to initialize the grid view.
     */
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
                        boardview.setAdapter(new CustomAdapter(memoButtons, columnWidth, columnHeight));
                    }
                });
    }

    /**
     * Flash the button to red and unflash it after the given delay.
     * @param pos the position of the button to flash.
     * @param delay the time of the delay.
     */
    private void unflashButton(final int pos, int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() { restoreButtonColor(pos);
            }
        }, delay);
    }

    /**
     * Flash the button to the given color unflash it after the given delay.
     * @param pos the position of the button to flash.
     * @param delay the time of the delay.
     * @param colorId the current color.
     */
    public void flashButtonToColor(int pos, Integer delay, int colorId){
        setButtonColor(memoButtons.get(pos), colorId);
        if (delay != null){
            unflashButton(pos, delay);
        }
    }

    /**
     * @param pos the position of the button to be restored to white.
     */
    @Override
    public void restoreButtonColor(int pos){
        setButtonColor(memoButtons.get(pos), android.R.color.white);
    }

    /**
     * @param button the button that will change color.
     * @param color the color to be set.
     */
    private void setButtonColor(Button button, int color){
        ViewCompat.setBackgroundTintList(button, ContextCompat.getColorStateList(this,
                color));
    }

    /**
     * Update the score to be displayed.
     * @param newScore the score to be displayed.
     */
    @Override
    public void updateScore(int newScore) {
        score.setText(String.valueOf(newScore));
    }

    public void updateLife(int newLife){
        life.setText(String.valueOf(newLife));
    }

    public void updateStatus(final boolean isActive){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isActive){
                    status.setText("Displaying");
                    status.setTextColor(Color.BLUE);
                } else {
                    status.setText("Your Turn!");
                    status.setTextColor(Color.GREEN);
                }
            }
        });

    }

    @Override
    public void showGameOverDialog(int score, MemoManager manager){
        FragmentManager fm = getSupportFragmentManager();
        GameOverDialogFragment fragment = GameOverDialogFragment.newInstance(score, manager);
        fragment.show(fm, "fragment_edit_name");
    }

    @Override
    public void onRefresh() {
        finish();
    }
}
