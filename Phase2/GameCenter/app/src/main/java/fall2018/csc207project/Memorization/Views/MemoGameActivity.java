package fall2018.csc207project.Memorization.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import fall2018.csc207project.Memorization.Controllers.GamePresenter;
import fall2018.csc207project.Memorization.Controllers.MemoGamePresenter;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ImageManager;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Controllers.CustomAdapter;

public class MemoGameActivity extends AppCompatActivity implements MemoGameView,
        GameOverDialogFragment.GameOverDialogListener,
        GameStartDialogFragment.StartDialogListener {

    /**
     * Represent the board view of the game.
     */
    private GridView boardView;
    /**
     * List of buttons on the board.
     */
    private List<Button> memoButtons;
    /**
     * The game logic presenter that control this view.
     */
    private GamePresenter presenter;
    /**
     * Width and height of the board.
     */
    private int columnWidth;
    private int columnHeight;

    /**
     * The TextView that show on screen.
     */
    private TextView score, life, status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_main);
        setBackGround();
        score = findViewById(R.id.Score);
        life = findViewById(R.id.life);
        status = findViewById(R.id.status);
        presenter = new MemoGamePresenter(this, getApplicationContext());
        MemoManager memoManager = (MemoManager) getIntent().getSerializableExtra("save");
        presenter.setMemoManager(memoManager);
        setupButtons(memoManager.getSize());
        setupHintButtonListener();
        setupGridView(memoManager);
        DialogFragment dialog = new GameStartDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "StartDialog");
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
                    presenter.onTapOnTile(getApplicationContext(), (int)button.getTag());
                }
            });
            setButtonColor(button, android.R.color.white);
            button.getBackground().setAlpha(150);
            memoButtons.add(button);
        }
    }

    /**
     * Initialize the buttons base on the capacity of the board.
     * @param memoManager the memoManager used to initialize the grid view.
     */
    private void setupGridView(final MemoManager memoManager) {
        boardView = findViewById(R.id.mapGrid);
        boardView.setNumColumns(memoManager.width);
        boardView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        boardView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = boardView.getMeasuredWidth();
                        int displayHeight = boardView.getMeasuredHeight();

                        columnWidth = displayWidth / memoManager.width;
                        columnHeight = displayHeight / memoManager.height;
                        boardView.setAdapter(new CustomAdapter(memoButtons, columnWidth, columnHeight));
                    }
                });
    }

    /**
     * Flash the button to red and un-flash it after the given delay.
     * @param pos the position of the button to flash.
     * @param delay the time of the delay.
     */
    private void unFlashButton(final int pos, int delay){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() { restoreButtonColor(pos);
            }
        }, delay);
    }

    /**
     * Flash the button to the given color un-flash it after the given delay.
     * @param pos the position of the button to flash.
     * @param delay the time of the delay.
     * @param colorId the current color.
     */
    public void flashButtonToColor(int pos, Integer delay, int colorId){
        setButtonColor(memoButtons.get(pos), colorId);
        if (delay != null){
            unFlashButton(pos, delay);
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

    /**
     * Updating the life by given a new amount of life in int.
     *
     * @param newLife the new amount of life in int
     */
    public void updateLife(int newLife){
        life.setText(String.valueOf(newLife));
    }

    /**
     * Updating the current status.
     *
     * @param isActive a final boolean tells weather the Tiles are flashing or not
     */
    public void updateStatus(final boolean isActive){
        runOnUiThread(new Runnable() {
            @Override
            @SuppressLint("SetTextI18n")
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

    /**
     * Display a dialog when game is over
     *
     * @param score score of user
     * @param manager MemoManger that holds info of the game
     */
    @Override
    public void showGameOverDialog(int score, MemoManager manager){
        FragmentManager fm = getSupportFragmentManager();
        GameOverDialogFragment fragment = GameOverDialogFragment.newInstance(score, manager);
        fragment.show(fm, "fragment_edit_name");
    }

    /**
     * Setup the background to the user's predefined background.
     */
    private void setBackGround(){
        SharedPreferences shared = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        String currentUser = shared.getString("currentUser", null);
        ImageManager manager = DatabaseUtil.getImageManager(currentUser);
        Drawable bg = manager.getBackground(getApplicationContext());
        if (bg != null){
            findViewById(R.id.memoGame).setBackground(bg);
        }
    }

    private void setupHintButtonListener(){
        findViewById(R.id.hintButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onHintTap();
            }
        });
    }

    public void deActivateHint(){
        ((ImageButton)findViewById(R.id.hintButton)).setImageResource(android.R.drawable.btn_star_big_off);
    }
    /**
     * Finish when user choose to exit
     */
    @Override
    public void onRefresh() {
        finish();
    }

    @Override
    public void onButtonClicked() {
        presenter.startCycle();
    }
}
