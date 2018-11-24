package fall2018.csc207project.SlidingTile.Models;

import fall2018.csc207project.models.Score;

public class TileScore extends Score{


    /**
     * complexity of current game
    */
    public int complexity;

    /**
     * name of current game
     */
    public final String game = "SlidingTilesGame";

    /**
     * name of the current player
     */
    public String user;

    /**
     * score of the specific game for a specific player in a specific complexity
     */
    public int value;

    /**
     * number of undoSteps of the specific game for a specific player in a specific complexity
     */
    public int undoSteps;

    /**
     * number of moveSteps executed for the specific game for a specific player in a specific complexity
     */
    public int moveSteps;

//    public TileScore(int complexity, String userName, int score){
//        this.complexity = complexity;
//        this.user = userName;
//        this.value = score;
//    }
    public TileScore(String userName, int complexity,  int undoSteps, int moveSteps){
        this.user = userName;
        this.complexity = complexity;
        this.undoSteps = undoSteps;
        this.moveSteps = moveSteps;
    }

//
//    public boolean compareTo(TileScore score) {
//        return value >= score.value;
//    }

}
