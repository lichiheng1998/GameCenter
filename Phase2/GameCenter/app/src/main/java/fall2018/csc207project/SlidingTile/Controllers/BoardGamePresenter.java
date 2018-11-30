package fall2018.csc207project.SlidingTile.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import fall2018.csc207project.Models.CalculatorFactory;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ImageManager;
import fall2018.csc207project.Models.SaveManager;
import fall2018.csc207project.Models.SaveSlot;
import fall2018.csc207project.Models.ScoreCalculator;
import fall2018.csc207project.Models.ScoreManager;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.BoardManager;
import fall2018.csc207project.SlidingTile.Models.Tile;
import fall2018.csc207project.SlidingTile.Models.TileScore;
import fall2018.csc207project.SlidingTile.Views.BoardGameView;

/**
 * The class BoardGamePresenter that implements GamePresenter.
 */
public class BoardGamePresenter implements GamePresenter {
    /**
     * The movement processing logic
     */
    private MovementController movementController;
    /**
     * The image manager.
     */
    private ImageManager imageManager;
    /**
     * The board manager.
     */
    private BoardManager boardManager;
    /**
     * The manager that manage the save files.
     */
    private SaveManager saveManager;
    /**
     * The current user.
     */
    private String currentUser;
    /**
     * Process the Abstract game view.
     */
    private BoardGameView view;
    /**
     * The save slot of the current user.
     */
    private SaveSlot saveSlot;

    private boolean isSolved = false;

    /**
     * Construct a new BoardGamePresenter
     * by given a BoardGameView, and a Context.
     *
     * @param view the BoardGame's View
     * @param context the context of this app
     */
    public BoardGamePresenter(BoardGameView view, Context context){
        this.view = view;
        SharedPreferences shared = context.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        currentUser = shared.getString("currentUser", null);
        String currentGame = shared.getString("currentGame", null);
        saveManager = DatabaseUtil.getSaveManager(currentUser, currentGame);
        imageManager = DatabaseUtil.getImageManager(currentUser);
        saveSlot = saveManager.readFromFile(context);
        this.movementController = new MovementController();
    }

    /**
     * Makes toast showing no undo times left if step of undo is invalid
     * @param step steps want to be undo
     */
    @Override
    public void onUndoButtonClicked(int step){
        if(!boardManager.undo(step)) {
            view.makeToastNoUndoTimesLeftText();
        }
    }

    /**
     * Show pickering when user wants do edit undo steps
     */
    @Override
    public void onUndoTextClicked(){
        view.showNumberPicker();
    }

    /**
     * Process movements when tile is clicked
     * @param context the context of this app
     * @param position the position of the Tile that the user tapped on
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onTapOnTile(Context context, int position){
        if (movementController.processTapMovement(context, position)){
            saveSlot.saveToAutoSave(boardManager);
            saveManager.saveToFile(saveSlot, context);
            if (boardManager.puzzleSolved() && !isSolved){
                TileScore score = new TileScore(boardManager.getComplexity()
                        , boardManager.getTotalUndoSteps(), boardManager.getTotalMoveSteps());
                CalculatorFactory calculatorFactory = new CalculatorFactory();
                ScoreCalculator calculator = calculatorFactory
                        .getCalculator("SlidingTileCalculator");
                ScoreManager<TileScore> scoreManager
                        = DatabaseUtil.getScoreManager("SlidingTile", currentUser,calculator);
                scoreManager.saveScore(score, context);
                isSolved = true;
            }
        }
    }

    /**
     * Set the board manager.
     * @param boardManager the BoardManager that manages the Board
     */
    @Override
    public void setBoardManager(BoardManager boardManager){
        this.boardManager = boardManager;
        this.movementController.setBoardManager(boardManager);
        boardManager.subscribe(this);
    }

    /**
     * Update the list of buttons.
     * @param o the observed board
     * @param arg the int array which contains the two buttons to be swapped
     */
    @Override
    public void update(Observable o, Object arg) {
        int[] arr = (int[]) arg;
        view.swapButtons(arr[0], arr[1]);
    }

    /**
     * Get the list of buttons(which are tiles)
     * @param context the context of this app
     * @return the list of buttons
     */
    @SuppressLint("SetTextI18n")
    public List<Button> getButtonList(final Context context){
        int complexity = boardManager.getComplexity();
        List<Button> tileButtons = new ArrayList<>();
        int count = 0;
        for(Tile tmpTile: boardManager){
            final int pos = count;
            Button tmp = new Button(context);
            if (tmpTile.getId() != complexity * complexity) {
                tmp.setText(Integer.toString(tmpTile.getId()));
            }
            tmp.setTextSize(40);
            tmp.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    onTapOnTile(context, pos);
                }
            });
            Bitmap bg = imageManager.getBackgroundBitmap(context);
            setBackground(tmp, tmpTile, bg, context);
            tileButtons.add(tmp);
            count++;
        }
        return tileButtons;
    }

    private void setBackground(Button button, Tile tile, Bitmap bg, Context context){
        if(bg == null){
            button.setBackgroundResource(R.drawable.tile);
        } else {
            button.setBackground(tile.getBackground(context, bg, boardManager.getComplexity()));
        }
    }
}
