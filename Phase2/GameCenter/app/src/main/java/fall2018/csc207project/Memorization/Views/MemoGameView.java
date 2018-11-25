package fall2018.csc207project.Memorization.Views;

/**
 * Class represent the Memorization master game view.
 */
public interface MemoGameView {
    void flashButtonToRed(int pos, Integer delay);
    void flashButtonToBlue(int pos, Integer delay);
    void restoreButtonColor(int pos);
    void flashButtonToGreen(int pos, Integer delay);
    void updateScore(int score);
}
