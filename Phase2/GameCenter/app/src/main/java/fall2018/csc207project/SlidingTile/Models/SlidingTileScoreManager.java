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
     * Get the top ten TileScores by giving a context and a complexity
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the top ten TileScores
     */
    public List<TileScore> getTopTenScoreBoard(Context context, int complexity){
        List<TileScore> tileScores = getScoresOfGame(context);
        List<TileScore> scoreBoardScores = new ArrayList<>();
        for (TileScore tileScore : tileScores) {
            if (tileScore.complexity == complexity) {
                scoreBoardScores.add(tileScore);
            }
        }

        Collections.sort(scoreBoardScores);

        List<TileScore> topTenScoreBoard = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topTenScoreBoard.add(scoreBoardScores.get(i));
        }

        return topTenScoreBoard;
    }
}
