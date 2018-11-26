package fall2018.csc207project.SlidingTile.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc207project.models.ScoreCalculator;
import fall2018.csc207project.models.ScoreDataStream;
import fall2018.csc207project.models.ScoreManager;

public class SlidingTileScoreManager extends ScoreManager<TileScore>{
    public SlidingTileScoreManager(ScoreDataStream dataStream,
                            String user, ScoreCalculator<TileScore> calculator) {
        super(dataStream, "SlidingTilesGame", user, calculator);
    }

    /**
     * Get the top ten TileScores by giving a context and a complexity.
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the top ten TileScores
     */
    public List<TileScore> getTopTenScores(Context context, int complexity){
        List<TileScore> tileScores = getScoresOfGame(context);
        List<TileScore> scoreBoardScores = new ArrayList<>();
        for (TileScore tileScore : tileScores) {
            if (tileScore.complexity == complexity) {
                scoreBoardScores.add(tileScore);
            }
        }
        Collections.sort(scoreBoardScores);

        List<TileScore> topTenScores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topTenScores.add(scoreBoardScores.get(i));
        }
        return topTenScores;
    }

    /**
     * Get the top three TileScores for all complexity
     * of the current user by giving a context.
     *
     * @param context the context of the app
     * @return the top three scores of the current user
     */
    public TileScore[][] getUserTopThreeScores(Context context) {
        List<TileScore> tileScores = getScoresOfUser(context);
        List<TileScore> scoresFor3x3 = new ArrayList<>();
        List<TileScore> scoresFor4x4 = new ArrayList<>();
        List<TileScore> scoresFor5x5 = new ArrayList<>();
        for (TileScore tileScore :tileScores) {
            if (tileScore.complexity == 3) {
                scoresFor3x3.add(tileScore);
            } else if (tileScore.complexity == 4) {
                scoresFor4x4.add(tileScore);
            } else if (tileScore.complexity == 5) {
                scoresFor5x5.add(tileScore);
            }
        }
        Collections.sort(scoresFor3x3);
        Collections.sort(scoresFor4x4);
        Collections.sort(scoresFor5x5);
        return new TileScore[][] {{scoresFor3x3.get(0), scoresFor3x3.get(1), scoresFor3x3.get(2)},
                                  {scoresFor4x4.get(0), scoresFor4x4.get(1), scoresFor4x4.get(2)},
                                  {scoresFor5x5.get(0), scoresFor5x5.get(1), scoresFor5x5.get(2)}};
    }
}
