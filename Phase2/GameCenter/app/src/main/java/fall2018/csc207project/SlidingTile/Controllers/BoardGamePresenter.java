package fall2018.csc207project.SlidingTile.Controllers;

import java.util.Observable;
import java.util.Observer;

import fall2018.csc207project.SlidingTile.Models.BoardManager;
import fall2018.csc207project.SlidingTile.Views.BoardGameView;
import fall2018.csc207project.models.SaveManager;

public class BoardGamePresenter implements Observer {
    /**
     * The board manager.
     */
    private BoardManager boardManager;
    /**
     * The manager that manage the save files.
     */
    private SaveManager saveManager;
    /**
     * The board manager.
     */
    private String currentUser;
    private BoardGameView view;

    public BoardGamePresenter(BoardGameView view){
        this.view = view;
    }


    public void onUndoButtonClicked(int step){
        if(!boardManager.undo(step)) {
            view.makeToastNoUndoTimesLeftText();
        }
    }

    public void onUndoTextClicked(){
        view.showNumberPicker();
    }

    public void setBoardManager(BoardManager boardManager){
        this.boardManager = boardManager;
        boardManager.subscribe(this);
    }

    public void setSaveManager(SaveManager saveManager){
        this.saveManager = saveManager;
    }

    public void setCurrentUser(String user){
        this.currentUser = user;
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
        saveManager.saveToSlot(boardManager,true);
    }
}
