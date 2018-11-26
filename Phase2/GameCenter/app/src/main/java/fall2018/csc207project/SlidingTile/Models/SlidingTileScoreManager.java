package fall2018.csc207project.SlidingTile.Models;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
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
    public Stack<TileScore> getTopTenScores(Context context, int complexity){
        List<TileScore> scoreBoardScores = getSortedScores(context, complexity);
        Stack<TileScore> topTenScores = new Stack<>();
        if (scoreBoardScores.size() >= 10) {
            topTenScores.addAll(scoreBoardScores.size() - 11, scoreBoardScores);
        } else {
            for (int i = 0; i < 10 - scoreBoardScores.size(); i++) {
                topTenScores.add(null);
            }
            topTenScores.addAll(scoreBoardScores);
        }
        return topTenScores;
    }

    /**
     * Get the sorted TileScore by giving a context and a complexity.
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the sorted TileScore by giving a context and a complexity
     */
    private List<TileScore> getSortedScores(Context context, int complexity) {
        List<TileScore> tileScores = scoreManager.getScoresOfGame(context);
        List<TileScore> scoreBoardScores = new ArrayList<>();
        for (TileScore tileScore : tileScores) {
            if (tileScore.complexity == complexity) {
                scoreBoardScores.add(tileScore);
            }
        }
        Collections.sort(scoreBoardScores);
        return scoreBoardScores;
    }

    /**
     * Get the top three TileScores for all complexity
     * of the current user by giving a context.
     *
     * @param context the context of the app
     * @return the top three scores of the current user
     */
    public List<Stack<TileScore>> getUserTopThreeScores(Context context) {
        List<Stack<TileScore>> listsTop3Scores = new ArrayList<>();
        Stack<TileScore> top3For3x3 = getTop3ForComplexity(context, 3);
        Stack<TileScore> top3For4x4 = getTop3ForComplexity(context, 4);
        Stack<TileScore> top3For5x5 = getTop3ForComplexity(context, 5);
        listsTop3Scores.add(top3For3x3);
        listsTop3Scores.add(top3For4x4);
        listsTop3Scores.add(top3For5x5);
        return listsTop3Scores;
    }

    /**
     * Get the sorted TileScore for a given complexity
     * of the current user by giving a context.
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the sorted TileScore for a given complexity
     */
    private List<TileScore> getUserSortedScores(Context context, int complexity) {
        List<TileScore> tileScores = scoreManager.getScoresOfUser(context);
        List<TileScore> scoresForComplexity = new ArrayList<>();
        for (TileScore tileScore :tileScores) {
            if (tileScore.complexity == complexity) {
                scoresForComplexity.add(tileScore);
            }
        }
        Collections.sort(scoresForComplexity);
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
    private Stack<TileScore> getTop3ForComplexity(Context context, int complexity) {
        List<TileScore> scoresForComplexity = getUserSortedScores(context, complexity);
        Stack<TileScore> top3ForComplexity = new Stack<>();
        if (top3ForComplexity.size() >= 3) {
            top3ForComplexity.addAll(scoresForComplexity.size() - 4, scoresForComplexity);
        } else {
            for (int i = 0; i < 3 - scoresForComplexity.size(); i++) {
                top3ForComplexity.add(null);
            }
            top3ForComplexity.addAll(scoresForComplexity);
        }
        return top3ForComplexity;
    }
}
