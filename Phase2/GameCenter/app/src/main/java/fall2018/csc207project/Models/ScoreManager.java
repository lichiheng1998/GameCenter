package fall2018.csc207project.Models;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that is used to calculate the score, store and retrieve the score.
 */
@SuppressWarnings("unchecked")
public class ScoreManager <T extends Score>{

    /**
     * The current game that is now operating.
     */
    private String currentGame;

    /**
     * The current user that is now operating.
     */
    private String currentUser;

    /**
     * the score data needed for operating.
     */
    private ScoreDataStream dataStream;

    /**
     * the type of score calculator needed for calculate the current game.
     */
    private ScoreCalculator calculator;

    /**
     * Construct a new SlidingTileScoreManager
     * by given a dataStream, a user, and a score calculator.
     *
     * @param dataStream the score dataStream
     * @param game the current game
     * @param user the current user
     * @param calculator a score calculator
     */
    public ScoreManager(ScoreDataStream dataStream, String game, String user,
                        ScoreCalculator<T> calculator){
        this.currentGame = game;
        this.currentUser = user;
        this.dataStream = dataStream;
        this.calculator = calculator;
    }

    /**
     * Get all scores in the database;
     * @param context the context of the app.
     * @return the list of scores.
     */
    private List<Score> getGameToScore(Context context){
        return (List<Score>)
                dataStream.getScores(new ArrayList<Score>(), context);
    }

    /**
     * Get all scores of the game;
     * @param context the context of the app
     * @return the list of scores.
     */
    public List<T> getScoresOfGame(Context context){
        List<Score> scores = getGameToScore(context);
        List<T> retArray = new ArrayList<>();
        for (Score score : scores){
            if(score.game.equals(currentGame)){
                       retArray.add((T)score);
            }
        }
        return retArray;
    }

    /**
     * Get all scores of the game of the user;
     * @param context the context of the app
     * @return the list of scores.
     */
    public List<T> getScoresOfUser(Context context){
        List<T> scores = getScoresOfGame(context);
        List<T> retArray = new ArrayList<>();
        for (T score: scores){
            if(score.user.equals(currentUser)){
                retArray.add(score);
            }
        }
        return retArray;
    }

    /**
     * Get all scores of the game of the user;
     * @param context the context of the app.
     * @param score the score that is saving.
     */
    public void saveScore(T score, Context context){
        List<Score> scores = getGameToScore(context);
        score.value = calculator.calculate(score);
        score.user = currentUser;
        score.game = currentGame;
        scores.add(score);
        dataStream.saveScores(scores, context);
    }

    /**
     * get the current user that is now operating.
     *
     * @return the current user that is now operating
     */
    public String getCurrentUser() {
        return currentUser;
    }
}
