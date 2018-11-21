package fall2018.csc207project.SlidingTile.Models;

import java.util.ArrayList;

import fall2018.csc207project.models.ScoreDataStream;
import fall2018.csc207project.models.ScoreManager;

public class TileGameScoreManager extends ScoreManager {

    private int complexity;
    private String game = "SlidingTail";

    TileGameScoreManager(String userName, int complexity, ScoreDataStream dataStream, String user,
                         ScoreCalculator<T> calculator){
        super(dataStream, "SlidingTile", user, calculator);
        this.complexity = complexity;
    }
    public TileScore getHighestbyUserLevel3 (String userName){
        return null;
    }
    public TileScore getHighestbyUserLevel4 (String userName){
        return null;
    }
    public TileScore getHighestbyUserLevel5 (String userName){
        return null;
    }
    public TileScore getHighestbyUserLevel6 (String userName){
        return null;
    }
    public ArrayList<TileScore> getHighestTenbyLevel3 (String userName){
        return null;
    }
    public ArrayList<TileScore> getHighestTenbyLevel4 (String userName){
        return null;
    }
    public ArrayList<TileScore> getHighestTenbyLevel5 (String userName){
        return null;
    }
    public ArrayList<TileScore> getHighestTenbyLevel6 (String userName){
        return null;
    }
}
