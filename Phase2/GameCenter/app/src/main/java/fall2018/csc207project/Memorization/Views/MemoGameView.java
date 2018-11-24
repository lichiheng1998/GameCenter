package fall2018.csc207project.Memorization.Views;

import java.util.List;

import fall2018.csc207project.Memorization.Models.MemoTile;

public interface MemoGameView {
    void flashButtonToRed(int pos, Integer delay);
    void restoreButtonColor(int pos);
    void flashButtonToGreen(int pos, Integer delay);
    void updateScore(int score);
}
