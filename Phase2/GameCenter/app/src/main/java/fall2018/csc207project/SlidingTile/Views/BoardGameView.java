package fall2018.csc207project.SlidingTile.Views;

/**
 * The interface BoardGameView that make the BoardGame shows on screen.
 */
public interface BoardGameView {

    /**
     *
     */
    void showNumberPicker();
    void makeToastNoUndoTimesLeftText();
    void display();
    void swapButtons(int pos1, int pos2);
}
