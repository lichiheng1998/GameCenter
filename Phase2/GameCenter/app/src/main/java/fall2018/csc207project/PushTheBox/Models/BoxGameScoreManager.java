package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc207project.Models.ScoreManager;

public class BoxGameScoreManager {

    private int level = 1;

    private ScoreManager<BoxScore> scoreManager;

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

    public ArrayList<BoxScore> getTopPlayers(Context context){
        ArrayList scores = getSortedScores(context);
        scores = filterByLevel(level,scores);
        return scores;
    }
//
//    /**
//     * Get the top three TileScores for all complexity
//     * of the current user by giving a context.
//     *
//     * @param context the context of the app
//     * @return the top three scores of the current user
//     */
//    public ArrayList<ArrayList<BoxScore>> getUserTopThreeScores(Context context) {
//        ArrayList<ArrayList<BoxScore>> listsTop3Scores = new ArrayList<>();
//        ArrayList<BoxScore> top3For3x3 = getTop3ForComplexity(context, 3);
//        ArrayList<BoxScore> top3For4x4 = getTop3ForComplexity(context, 4);
//        ArrayList<BoxScore> top3For5x5 = getTop3ForComplexity(context, 5);
//        listsTop3Scores.add(top3For3x3);
//        listsTop3Scores.add(top3For4x4);
//        listsTop3Scores.add(top3For5x5);
//        return listsTop3Scores;
//    }

    /**
     * Get the sorted TileScore in reverse order
     * for a given complexity of the current user by giving a context.
     *
     * @param context the context of the app
     * @param complexity the complexity the user chooses
     * @return the sorted TileScore for a given complexity
     */
//    private ArrayList<BoxScore> getUserSortedScores(Context context, int complexity) {
//        List<BoxScore> tileScores = scoreManager.getScoresOfUser(context);
//        ArrayList<BoxScore> scoresForComplexity = new ArrayList<>();
//        for (BoxScore tileScore :tileScores) {
//            if (tileScore.complexity == complexity) {
//                scoresForComplexity.add(tileScore);
//            }
//        }
//        Collections.sort(scoresForComplexity);
//        Collections.reverse(scoresForComplexity);
//        return scoresForComplexity;
//    }


    /**
     * Get the top three TileScore for a given complexity
     * of the current user by giving a context.
     *
     * @param context the context of the app
     * @return the top three TileScore for a given complexity
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

    public void setLevel(int level) {
        this.level = level;
    }

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
