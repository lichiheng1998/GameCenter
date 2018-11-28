package fall2018.csc207project.PushTheBox.Models;

import fall2018.csc207project.Models.ScoreCalculator;

/**
 * The class BoxGameCalculator that implements ScoreCalculator<BoxScore>.
 */
public class BoxGameCalculator implements ScoreCalculator<BoxScore> {

    @Override
    public int calculate(BoxScore scoreInfo) {
        int basicPoints = 50;
        int finalScore = scoreInfo.level * basicPoints
                         - 2 * scoreInfo.moveSteps - scoreInfo.undoSteps;
        return (finalScore > 0) ? finalScore : 0;
    }
}
