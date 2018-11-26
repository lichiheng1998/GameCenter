package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ShareActionProvider;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc207project.PushTheBox.Models.LevelFactory;
import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.PushTheBox.View.BoxGameActivity;
import fall2018.csc207project.PushTheBox.View.MapView;
import fall2018.csc207project.models.DatabaseUtil;
import fall2018.csc207project.models.SaveManager;
import fall2018.csc207project.models.SaveSlot;

public class BoxGamePresenter implements Observer {
    /**
     * The map manager.
     */
    private MapManager mapManager;

    /**
     * The save manager.
     */
    private SaveManager saveManager;

    /**
     * Current user playing the game.
     */
    private String currentUser;

    /**
     * The view of the game.
     */
    private MapView view;

    /**
     * The controller which controls the movements of person and boxes.
     */
    MovementController movementController;

    /**
     * The save slot of the current user.
     */
    private SaveSlot saveSlot;

    /**
     * A new box game presenter.
     * @param view
     * @param context
     */
    public BoxGamePresenter(MapView view, Context context){
        this.view = view;
        SharedPreferences shared = context.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        currentUser = shared.getString("currentUser", null);
        String currentGame = shared.getString("currentGame", null);
        saveManager = DatabaseUtil.getSaveManager(currentUser, currentGame);
        saveSlot = saveManager.readFromFile(context);
        movementController = new MovementController();
    }

    /**
     * Set up the map manager of this presenter
     * @param mapManager of this presenter
     */
    public void setMapManager(MapManager mapManager){
        this.mapManager = mapManager;
        mapManager.subscribe(this);
        movementController.setMapManager(mapManager);
    }

    /**
     * When one of the arrow buttons is clicked, let the person process movement and update the map.
     * @param direction direction chosen
     */
    public void arrowButtonClicked(Context context, String direction){
        if (movementController.processTapMovement(context, direction)){
            view.levelComplete();
        }
        saveSlot.saveToAutoSave(mapManager);
        saveManager.saveToFile(saveSlot, context);
    }

    /**
     * When undo button is clicked, process undo.
     * @param step the number of undo steps.
     */
    public void onUndoButtonClicked(int step){
        if(!mapManager.canProcessUndo(step)) {
            view.makeToastNoUndoTimesLeftText();
        }
    }

    /**
     * When undo text is clicked, show number picker for user to choose undo steps.
     */
    public void onUndoTextClicked(){
        view.showNumberPicker();
    }

    /**
     * Updates notified to observer. Calls the View to update the map.
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        view.updateMap(mapManager);
    }
}
