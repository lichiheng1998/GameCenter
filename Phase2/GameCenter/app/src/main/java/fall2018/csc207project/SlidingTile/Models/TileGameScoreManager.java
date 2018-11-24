package fall2018.csc207project.SlidingTile.Models;

import java.util.ArrayList;

import fall2018.csc207project.models.ScoreCalculator;
import fall2018.csc207project.models.ScoreDataStream;
import fall2018.csc207project.models.ScoreManager;

/**
 * The class been used to calculate the score, store and retrieve the score specific to
 * SlidingTail Game.
 */

public class TileGameScoreManager extends ScoreManager {


    /**
    * complexity of the TIleGame
    */
    private int complexity;

    TileGameScoreManager(String userName, int complexity, ScoreDataStream dataStream,
                         ScoreCalculator<TileScore> calculator){
        super(dataStream, "SlidingTile", userName, calculator);
        this.complexity = complexity;
    }

    /**
     * get the highest score for a user for a specific level from existing database
     * @param userName: the username of current player
     * @param complexity : the complexity of the current game for current player
     * @return a TileScore.
    * */
    public TileScore getHighestbyUserLevel (String userName, int complexity){
        return null;
    }

    /**
     * @param userName: the username of current player
     * @return a TileScore.
     * get highest 10 scores for a given complexity */
    public ArrayList<TileScore> getHighestTenbyLevel (String userName, int complexity){
        return null;
    }

}
