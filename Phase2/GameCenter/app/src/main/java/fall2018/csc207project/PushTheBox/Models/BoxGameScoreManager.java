package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc207project.Models.ScoreManager;

/**
 * The BoxGameScoreManager that manages the BoxGameScore
 */
public class BoxGameScoreManager {

    /**
     * the level of current game.
     */
    private int level = 1;

    /**
     * The ScoreManager<BoxScore> that manages the BoxScore.
     */
    private ScoreManager<BoxScore> scoreManager;

    /**
     * Construct a new BoxGameScoreManager by given a ScoreManager<BoxScore>
     *
     * @param scoreManager the ScoreManager<BoxScore> that manages the BoxScore
     */
    public BoxGameScoreManager(ScoreManager<BoxScore> scoreManager){
        this.scoreManager = scoreManager;
    }

    /**
     * Get the sorted BoxScore in order of ascend
     * by giving a context and a complexity.
     *
     * @param context the context of the app
     * @return the sorted BoxScore by giving a context
     */
    private List<BoxScore> getSortedScores(Context context) {
        List<BoxScore> scores = scoreManager.getScoresOfGame(context);
        Collections.sort(scores);
        Collections.reverse(scores);
        return scores;
    }

    /**
     * Get the top ten BoxScore for a given complexity
     * of the current game by giving a context.
     *
     * @param context the context of the app
     * @return the top ten BoxScore for a given complexity
     */
    public List<BoxScore> getTopPlayers(Context context){
        List<BoxScore> scores = getSortedScores(context);
        scores = filterByLevel(level, scores);
        return scores;
    }

    /**
     * Get the top three BoxScore for a given complexity
     * of the current user by giving a context.
     *
     * @param context the context of the app
     * @return the top three BoxScore for a given complexity
     */
    public ArrayList<BoxScore> getTopThreePerUser(Context context) {
        ArrayList<BoxScore> scores = (ArrayList<BoxScore>)scoreManager.getScoresOfUser(context);
        scores = filterByLevel(level, scores);
        Collections.sort(scores);
        Collections.reverse(scores);
        ArrayList<BoxScore> top3Score = new ArrayList<>();
        if (scores.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                top3Score.add(scores.get(i));
            }
        } else {
            top3Score.addAll(scores);
            for (int i = 0; i < 3 - scores.size(); i++) {
                top3Score.add(new BoxScore(level, 1000, 1000));
            }
        }
        return top3Score;
    }

    /**
     * Set the level of the game.
     *
     * @param level the game level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Create a filter for ListView by given an int and a List<BoxScore>.
     *
     * @param level the game level
     * @param scores the BoxScore for the user
     * @return the ArrayList<BoxScore> for the ListView
     */
    private ArrayList<BoxScore> filterByLevel(int level, List<BoxScore> scores){
        ArrayList<BoxScore> filterScores = new ArrayList<>();
        for (BoxScore score : scores){
            if (score.level == level){
                filterScores.add(score);
            }
        }
        return filterScores;
    }

}
