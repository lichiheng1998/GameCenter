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
    public String game = "Slidingtail";

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

    public TileScore(int complexity, String userName, int score){
        this.complexity = complexity;
        this.user = userName;
        this.value = score;
    }
    public TileScore(int complexity, String userName, int undoSteps, int moveSteps){
        this.complexity = complexity;
        this.user = userName;
        this.undoSteps = undoSteps;
        this.moveSteps = moveSteps;
    }
//
//    public boolean compareTo(TileScore score) {
//        return value >= score.value;
//    }

}
