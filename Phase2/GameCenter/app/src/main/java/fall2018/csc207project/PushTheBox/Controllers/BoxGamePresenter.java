package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ShareActionProvider;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.PushTheBox.View.MapView;
import fall2018.csc207project.models.DatabaseUtil;
import fall2018.csc207project.models.SaveManager;

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
            saveManager.saveToSlot(mapManager, true, context);
        }
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
