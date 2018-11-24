package fall2018.csc207project.SlidingTile.Views;

public interface BoardGameView {
    void showNumberPicker();
    void makeToastNoUndoTimesLeftText();
    void display();
    void swapButtons(int pos1, int pos2);
}
