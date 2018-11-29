package fall2018.csc207project.SlidingTile.Models;

import fall2018.csc207project.Models.ScoreCalculator;

/**
 * THe class TileGameCalculator that implements ScoreCalculator<TileScore>
 */
public class TileGameCalculator implements ScoreCalculator<TileScore> {
    @Override
    public int calculate(TileScore scoreInfo) {
        int maxScore = 1000;
        int finalScore = 0;
        if (scoreInfo.complexity == 3) {
            finalScore = maxScore - 7 * scoreInfo.moveSteps - scoreInfo.undoSteps;
        } else if (scoreInfo.complexity == 4) {
            finalScore = maxScore - 2 * scoreInfo.moveSteps - scoreInfo.undoSteps;
        } else if (scoreInfo.complexity == 5) {
            finalScore = maxScore - scoreInfo.moveSteps - scoreInfo.undoSteps;
        }
        return (finalScore > 0) ? finalScore : 0;
    }
}
