package fall2018.csc207project.SlidingTile.Models;

import fall2018.csc207project.Models.Score;

public class TileScore extends Score{

    /**
     * complexity of current game
    */
    public int complexity;

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
     * complexity, undoSteps, moveSteps
     *
     * @param complexity the complexity the user chooses
     * @param undoSteps the total undo steps the user used in Tile Game
     * @param moveSteps the total movement steps the user moves in Tile Game
     */
    public TileScore( int complexity, int undoSteps, int moveSteps){
        this.complexity = complexity;
        this.undoSteps = undoSteps;
        this.moveSteps = moveSteps;
        this.game = "SlidingTile";
    }
    public String toString(){
        return new String(complexity + user+value);
    }
}
