package fall2018.csc207project.Memorization.Models;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import fall2018.csc207project.Models.ScoreManager;

/**
 * The class MemoScoreManager
 * that is used to calculate the score, store and retrieve the score.
 */
public class MemoScoreManager {

    /**
     * The ScoreManager to manage the MemoScore.
     */
    private ScoreManager<MemoScore> scoreManager;

    /**
     * Construct a new MemoScoreManager by given a scoreManager.
     *
     * @param scoreManager the MemoScores manager we need
     */
    public MemoScoreManager(ScoreManager<MemoScore> scoreManager) {
        this.scoreManager = scoreManager;
    }

    /**
     * Get the top ten MemoScores by giving a context, a difficulty and a level.
     *
     * @param context the context of the app
     * @param difficulty the difficulty the user chooses
     * @param level the mode level for hard or easy
     * @return the top ten MemoScores of all players in this game
     */
    public ArrayList<MemoScore> getTopTenScores(Context context, int difficulty, boolean level){
        List<MemoScore> scoreBoardScores
                = getSortedScores(scoreManager.getScoresOfGame(context), difficulty, level);
        ArrayList<MemoScore> topTenScores = new ArrayList<>();
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
     * Get the top three MemoScores for all level of difficulties and modes.
     *
     * @param context the context of the app
     * @param difficulty the difficulty the user chooses
     * @param level the mode level for hard or easy
     * @return the top three MemoScores for all level of difficulties and modes
     */
    public ArrayList<MemoScore> getUserTopThree(Context context, int difficulty, boolean level) {
        ArrayList<MemoScore> scoresForComplexity
                = getSortedScores(scoreManager.getScoresOfUser(context), difficulty, level);
        ArrayList<MemoScore> top3ForComplexity = new ArrayList<>();
        if (top3ForComplexity.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                top3ForComplexity.add(scoresForComplexity.get(i));
            }
        } else {
            top3ForComplexity.addAll(scoresForComplexity);
            for (int i = 0; i < 3 - scoresForComplexity.size(); i++) {
                top3ForComplexity.add(new MemoScore(difficulty, level, 0));
            }
        }
        return top3ForComplexity;
    }

    /**
     * Get the sorted MemoScores in reverse order
     * by giving a List of MemoScores, a difficulty and a level mode.
     *
     * @param memoScores the List of MemoScores want to be sort
     * @param difficulty the difficulty the user chooses
     * @param level the mode level for hard or easy
     * @return the sorted MemoScores by giving a List of MemoScore, a difficulty and a level mode
     */
    private ArrayList<MemoScore> getSortedScores(List<MemoScore> memoScores, int difficulty, boolean level) {
        ArrayList<MemoScore> scoreBoardScores = new ArrayList<>();
        for (MemoScore memoScore : memoScores) {
            if (memoScore.difficulty == difficulty && memoScore.level == level) {
                scoreBoardScores.add(memoScore);
            }
        }
        Collections.sort(scoreBoardScores);
        Collections.reverse(scoreBoardScores);
        return scoreBoardScores;
    }
}
