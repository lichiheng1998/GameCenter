package fall2018.csc207project.Memorization.Models;

import fall2018.csc207project.Models.Score;

public class MemoScore extends Score{

    /**
     * the difficulty is depends on height of the game board.
     */
    public int difficulty;

    /**
     * hard or normal game level
     */
    public boolean level;

    /**
     * the total score of this board
     */
    public int scoreTotal;

    /**
     * Construct a new TileScore system
     * by given width, level and scoreTotal.
     *
     * @param width the width of the game board
     * @param level hard or normal for this game
     * @param scoreTotal the total score for this game
     */
    public MemoScore(int width, boolean level, int scoreTotal) {
        this.difficulty = width;
        this.level = level;
        this.scoreTotal = scoreTotal;
    }
}
