package fall2018.csc207project.Memorization.Views;

import java.util.List;

import fall2018.csc207project.Memorization.Models.MemoTile;

public interface MemoGameView {
    void updateButtonToRed(int pos);
    void restoreButtonColor();
    void updateButtonToGreen(int pos);
    void updateScore(int score);
}
