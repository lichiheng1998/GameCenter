package fall2018.csc207project.Memorization.Views;

import fall2018.csc207project.Memorization.Models.MemoManager;

/**
 * Class represent the Memorization master game view.
 */
public interface MemoGameView {

    /**
     * Make the buttons flashing by given color id, position and time delays between.
     *
     * @param pos the position to be flash
     * @param delay the amount of time between each flash
     * @param colorId the color of the flashing
     */
    void flashButtonToColor(int pos, Integer delay, int colorId);

    /**
     * Restore the color of the buttons
     *
     * @param pos the position of the button
     */
    void restoreButtonColor(int pos);

    /**
     * Updating the user's score.
     *
     * @param score the user's score.
     */
    void updateScore(int score);

    /**
     * Updating the user's life.
     *
     * @param newLife the amount of life to be update in int
     */
    void updateLife(int newLife);

    /**
     * Updating the current status.
     *
     * @param isActive a final boolean tells weather the Tiles are flashing or not
     */
    void updateStatus(boolean isActive);

    /**
     * Display a dialog when game is over
     *
     * @param score score of user
     * @param manager MemoManger that holds info of the game
     */
    void showGameOverDialog(int score, MemoManager manager);

    /**
     * Change the image of the button to indicate the hint is no more active.
     */
    void deActivateHint();
}
