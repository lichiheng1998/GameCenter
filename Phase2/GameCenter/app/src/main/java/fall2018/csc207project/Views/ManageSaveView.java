package fall2018.csc207project.Views;

import java.io.Serializable;

/**
 * Display the ManageSave on screen.
 */
public interface ManageSaveView {

    /**
     * Show the Info on screen.
     *
     * @param pos the position to display
     * @param info the display info
     */
    void showInfo(int pos, String info);

    /**
     * Display that a game was saved successfully.
     */
    void makeToastSavedText();

    /**
     * Display that a game that has no saves.
     */
    void makeNoSavedText();

    /**
     * Display that the game hasn't started yet.
     */
    void makeNotStartedText();

    /**
     * Launch the game with the given data.
     * @param data the data to be sent to the game.
     */
    void launchGame(Serializable data);
}
