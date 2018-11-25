package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.BoardManager;
import fall2018.csc207project.SlidingTile.Models.Tile;
import fall2018.csc207project.SlidingTile.Views.BoardGameView;
import fall2018.csc207project.models.DatabaseUtil;
import fall2018.csc207project.models.SaveManager;

public class BoardGamePresenter implements Observer {
    /**
     * The movement processing logic
     */
    private MovementController movementController;
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

    public BoardGamePresenter(BoardGameView view, Context context){
        this.view = view;
        SharedPreferences shared = context.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        currentUser = shared.getString("currentUser", null);
        String currentGame = shared.getString("currentGame", null);
        saveManager = DatabaseUtil.getSaveManager(currentUser, currentGame);
        this.movementController = new MovementController();
    }


    public void onUndoButtonClicked(int step){
        if(!boardManager.undo(step)) {
            view.makeToastNoUndoTimesLeftText();
        }
    }

    public void onUndoTextClicked(){
        view.showNumberPicker();
    }

    public void onTapOnTile(Context context, int position){
        if (movementController.processTapMovement(context, position)){
            saveManager.saveToSlot(boardManager, true, context);
        }
    }

    public void setBoardManager(BoardManager boardManager){
        this.boardManager = boardManager;
        this.movementController.setBoardManager(boardManager);
        boardManager.subscribe(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        int[] arr = (int[]) arg;
        // arr[0] = 0 means that it is a change of position of two tiles
        // arr[0] = 1 means that it is a change of the last tile because of completion
        if (arr[0] == 0){
            view.swapButtons(arr[1], arr[2]);
        } else {

        }
    }

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
            tmp.setBackgroundResource(R.drawable.tile);
            tileButtons.add(tmp);
            count++;
        }
        return tileButtons;
    }
}
