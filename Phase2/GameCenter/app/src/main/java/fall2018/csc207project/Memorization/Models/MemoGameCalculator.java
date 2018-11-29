package fall2018.csc207project.Memorization.Models;

import fall2018.csc207project.Models.ScoreCalculator;

/**
 * the class MemoGameCalculator that implements ScoreCalculator<MemoScore>
 */
public class MemoGameCalculator implements ScoreCalculator<MemoScore> {

    @Override
    public int calculate(MemoScore scoreInfo) {
        int finalScore = (!scoreInfo.level)
                ? scoreInfo.scoreTotal * (10 + 5 * (scoreInfo.difficulty - 3))
                : scoreInfo.scoreTotal * (20 + 5 * (scoreInfo.difficulty - 3));
        return (finalScore > 0) ? finalScore : 0;
    }
}
