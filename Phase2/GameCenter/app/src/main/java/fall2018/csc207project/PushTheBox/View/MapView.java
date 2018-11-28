package fall2018.csc207project.PushTheBox.View;

import fall2018.csc207project.PushTheBox.Models.MapManager;

/**
 *  The interface MapView sets the BoxGame Map on screen.
 */
public interface MapView {

    /**
     * Show the number picker dialog.
     */
    void showNumberPicker();

    /**
     * Display that a game has No Undo Times Left.
     */
    void makeToastNoUndoTimesLeftText();

    /**
     * Display that a game has Invalid Movement.
     */
    void makeInvalidMovementText();

    /**
     * Display the map on the grid view.
     */
    void display();

    /**
     * Update the map with information taken from mapManager.
     *
     * @param mapManager the mapManager with updated info
     */
    void updateMap(MapManager mapManager);

    /**
     * Display the alert dialog when level is completed.
     */
    void levelComplete();
}
