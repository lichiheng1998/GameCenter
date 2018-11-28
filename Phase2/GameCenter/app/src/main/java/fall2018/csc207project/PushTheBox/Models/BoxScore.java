package fall2018.csc207project.PushTheBox.Models;

import fall2018.csc207project.Models.Score;

// Excluded from tests because it's a model class
public class BoxScore extends Score{

    /**
     * level of current game
     */
    public int level;

    /**
     * number of undoSteps of the specific game for a specific player in a specific complexity
     */
    public int undoSteps;

    /**
     * number of moveSteps executed for the specific game
     * for a specific player in a specific complexity
     */
    public int moveSteps;

    /**
     * Construct a new TileScore system by given
     * level, undoSteps, moveSteps
     *
     * @param level the level the user chooses
     * @param undoSteps the total undo steps the user used in Tile Game
     * @param moveSteps the total movement steps the user moves in Tile Game
     */
    public BoxScore(int level, int undoSteps, int moveSteps) {
        this.level = level;
        this.undoSteps = undoSteps;
        this.moveSteps = moveSteps;
        this.game = "PushBox";
    }
}
