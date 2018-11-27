package fall2018.csc207project.SlidingTile.Models;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import fall2018.csc207project.models.ScoreManager;

/**
 * The class that is used to calculate the score, store and retrieve the score.
 */
public class SlidingTileScoreManager {

    private ScoreManager<TileScore> scoreManager;
    /**
     * Construct a new SlidingTileScoreManager by given a scoreManager
     *
     * @param scoreManager the score manager we need
     */
    public SlidingTileScoreManager(ScoreManager<TileScore> scoreManager) {
        this.scoreManager = scoreManager;
    }

    /**
     * Get the top ten TileScores by giving a context and a complexity.
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the top ten TileScores
     */
    public ArrayList<TileScore> getTopTenScores(Context context, int complexity){
        List<TileScore> scoreBoardScores = getSortedScores(context, complexity);
        ArrayList<TileScore> topTenScores = new ArrayList<>();
        if (scoreBoardScores.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                topTenScores.add(scoreBoardScores.get(i));
            }
        } else {
            topTenScores.addAll(scoreBoardScores);
        }
        return topTenScores;
    }

    /**
     * Get the sorted TileScore in reverse order
     * by giving a context and a complexity.
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the sorted TileScore by giving a context and a complexity
     */
    private ArrayList<TileScore> getSortedScores(Context context, int complexity) {
        List<TileScore> tileScores = scoreManager.getScoresOfGame(context);
        ArrayList<TileScore> scoreBoardScores = new ArrayList<>();
        for (TileScore tileScore : tileScores) {
            if (tileScore.complexity == complexity) {
                scoreBoardScores.add(tileScore);
            }
        }
        Collections.sort(scoreBoardScores);
        Collections.reverse(scoreBoardScores);
        return scoreBoardScores;
    }

    /**
     * Get the top three TileScores for all complexity
     * of the current user by giving a context.
     *
     * @param context the context of the app
     * @return the top three scores of the current user
     */
    public ArrayList<ArrayList<TileScore>> getUserTopThreeScores(Context context) {
        ArrayList<ArrayList<TileScore>> listsTop3Scores = new ArrayList<>();
        ArrayList<TileScore> top3For3x3 = getTop3ForComplexity(context, 3);
        ArrayList<TileScore> top3For4x4 = getTop3ForComplexity(context, 4);
        ArrayList<TileScore> top3For5x5 = getTop3ForComplexity(context, 5);
        listsTop3Scores.add(top3For3x3);
        listsTop3Scores.add(top3For4x4);
        listsTop3Scores.add(top3For5x5);
        return listsTop3Scores;
    }

    /**
     * Get the sorted TileScore in reverse order
     * for a given complexity of the current user by giving a context.
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the sorted TileScore for a given complexity
     */
    private ArrayList<TileScore> getUserSortedScores(Context context, int complexity) {
        List<TileScore> tileScores = scoreManager.getScoresOfUser(context);
        ArrayList<TileScore> scoresForComplexity = new ArrayList<>();
        for (TileScore tileScore :tileScores) {
            if (tileScore.complexity == complexity) {
                scoresForComplexity.add(tileScore);
            }
        }
        Collections.sort(scoresForComplexity);
        Collections.reverse(scoresForComplexity);
        return scoresForComplexity;
    }


    /**
     * Get the top three TileScore for a given complexity
     * of the current user by giving a context.
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the top three TileScore for a given complexity
     */
    private ArrayList<TileScore> getTop3ForComplexity(Context context, int complexity) {
        ArrayList<TileScore> scoresForComplexity = getUserSortedScores(context, complexity);
        ArrayList<TileScore> top3ForComplexity = new ArrayList<>();
        if (top3ForComplexity.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                top3ForComplexity.add(scoresForComplexity.get(i));
            }
        } else {
            top3ForComplexity.addAll(scoresForComplexity);
            for (int i = 0; i < 3 - scoresForComplexity.size(); i++) {
                top3ForComplexity.add(new TileScore(complexity, 1000, 1000));
            }
        }
        return top3ForComplexity;
    }
}
