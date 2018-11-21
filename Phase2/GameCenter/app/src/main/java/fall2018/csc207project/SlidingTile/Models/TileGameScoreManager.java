package fall2018.csc207project.SlidingTile.Models;

import java.util.ArrayList;

import fall2018.csc207project.models.ScoreCalculator;
import fall2018.csc207project.models.ScoreDataStream;
import fall2018.csc207project.models.ScoreManager;

public class TileGameScoreManager extends ScoreManager {

    private int complexity;
    private String game = "SlidingTail";

    TileGameScoreManager(String userName, int complexity, ScoreDataStream dataStream,
                         ScoreCalculator<TileScore> calculator){
        super(dataStream, "SlidingTile", userName, calculator);
        this.complexity = complexity;
    }
    public TileScore getHighestbyUserLevel (String userName, int complexity){
        return null;
    }

    public ArrayList<TileScore> getHighestTenbyLevel3 (String userName, int complexity){
        return null;
    }

}
