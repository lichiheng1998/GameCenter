package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import fall2018.csc207project.Models.ScoreManager;

@SuppressWarnings("unchecked")
public class BoxGameScoreManager {

    /**
     * The game's level.
     */
    private int level = 1;

    /**
     * The ScoreManager<BoxScore> helps to mange the BoxScore.
     */
    private ScoreManager<BoxScore> scoreManager;

    /**
     * Construct a new BoxGameScoreManager that takes a ScoreManager<BoxScore>.
     *
     * @param scoreManager the ScoreManager<BoxScore> helps to mange the BoxScore
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
    private ArrayList<BoxScore> getSortedScores(Context context) {
        ArrayList<BoxScore> scores = (ArrayList)scoreManager.getScoresOfGame(context);
        Collections.sort(scores);
        Collections.reverse(scores);
        return scores;
    }

    /**
     * Get the top ten BoxScore for a given complexity
     * of the current user by giving a context.
     *
     * @param context the context of the app
     * @return the top ten BoxScore for a given complexity
     */
    public ArrayList<BoxScore> getTopPlayers(Context context){
        ArrayList scores = getSortedScores(context);
        scores = filterByLevel(level,scores);
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
        ArrayList<BoxScore> scores = (ArrayList)scoreManager.getScoresOfUser(context);
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
     * @param level the level of the game
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get the ArrayList<BoxScore> for the Top 10 Players ListView.
     *
     * @param level the level of the game
     * @param scores the ArrayList<BoxScore>
     * @return the ArrayList<BoxScore> for the Top 10 Player ListView
     */
    private ArrayList<BoxScore> filterByLevel(int level, ArrayList<BoxScore> scores){
        ArrayList<BoxScore> filterScores = new ArrayList();
        for (BoxScore score : scores){
            if (score.level == level){
                filterScores.add(score);
            }
        }
        return filterScores;
    }

}
