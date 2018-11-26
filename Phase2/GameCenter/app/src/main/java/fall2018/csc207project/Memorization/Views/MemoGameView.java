package fall2018.csc207project.Memorization.Views;

import fall2018.csc207project.Memorization.Models.MemoManager;

/**
 * Class represent the Memorization master game view.
 */
public interface MemoGameView {
    void flashButtonToColor(int pos, Integer delay, int colorId);
    void restoreButtonColor(int pos);
    void updateScore(int score);
    void updateLife(int newLife);
    void updateStatus(boolean isActive);
    void showGameOverDialog(int score, MemoManager manager);
}
