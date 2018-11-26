package fall2018.csc207project.PushTheBox.Models;

import fall2018.csc207project.models.ScoreCalculator;

public class BoxGameCalculator implements ScoreCalculator<BoxScore> {

    @Override
    public int calculate(BoxScore scoreInfo) {
        int basicPoints = 50;
        int finalScore = scoreInfo.level * basicPoints
                         - 2 * scoreInfo.moveSteps - scoreInfo.undoSteps;
        if (finalScore > 0){
            return finalScore;
        }
        else {
            return 0;
        }
    }
}
