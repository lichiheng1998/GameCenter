package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;

import java.util.Observer;

import fall2018.csc207project.PushTheBox.Models.MapManager;

/**
 * The interface GamePresenter that extends Observer
 */
public interface GamePresenter extends Observer{

    /**
     * Set up the map manager of this presenter.
     *
     * @param mapManager of this presenter
     */
    void setMapManager(MapManager mapManager);

    /**
     * When one of the arrow buttons is clicked, let the person process movement and update the map.
     *
     * @param context the context of the app
     * @param direction direction chosen
     */
    void arrowButtonClicked(Context context, String direction);

    /**
     * When undo button is clicked, process undo.
     *
     * @param step the number of undo steps
     */
    void onUndoButtonClicked(int step);

    /**
     * When undo text is clicked, show number picker for user to choose undo steps.
     */
    void onUndoTextClicked();

    /**
     * Set up the current user's score.
     *
     * @param context the context of the app
     */
    void saveScores(Context context);
}
