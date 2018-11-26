package fall2018.csc207project.Memorization.Views;

import fall2018.csc207project.Memorization.Models.MemoManager;

/**
 * Class represent the Memorization master game view.
 */
public interface MemoGameView {
    void flashButtonToRed(int pos, Integer delay);
    void flashButtonToBlue(int pos, Integer delay);
    void restoreButtonColor(int pos);
    void flashButtonToGreen(int pos, Integer delay);
    void updateScore(int score);
    void showGameOverDialog(int score, MemoManager manager);
}
