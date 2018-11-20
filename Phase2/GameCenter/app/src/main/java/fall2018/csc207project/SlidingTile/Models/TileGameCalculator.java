package fall2018.csc207project.SlidingTile.Models;

import fall2018.csc207project.models.ScoreCalculator;

public class TileGameCalculator implements ScoreCalculator<TileScore> {
    @Override
    public int calculate(TileScore scoreInfo) {
        return 1000;
    }
}
