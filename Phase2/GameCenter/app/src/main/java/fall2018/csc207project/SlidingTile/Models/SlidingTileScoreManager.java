package fall2018.csc207project.SlidingTile.Models;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.models.ScoreCalculator;
import fall2018.csc207project.models.ScoreDataStream;
import fall2018.csc207project.models.ScoreManager;

public class SlidingTileScoreManager extends ScoreManager<TileScore>{
    public SlidingTileScoreManager(ScoreDataStream dataStream,
                            String user, ScoreCalculator<TileScore> calculator) {
        super(dataStream, "SlidingTilesGame", user, calculator);
    }

    public List<TileScore> filter(ArrayList<TileScore> filterList, int filterLevel){
        List res = new ArrayList();
        for (TileScore score: filterList){
            if (score.complexity == filterLevel ) {
                res.add(score);
            }
        }
        return res;
    }
}
