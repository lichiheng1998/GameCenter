package fall2018.csc207project.Models;

import android.content.Context;

/**
 * The interface ScoreDataStream to help save the game's Score data.
 */
public interface ScoreDataStream {

    /**
     * Load from the saved Score.
     *
     * @param initData the data that want to be load from
     * @param context the context of this app
     * @return load from the saved Score
     */
    Object getScores(Object initData, Context context);

    /**
     * Save the given Score data.
     *
     * @param data the data that want to be save to
     * @param context the context of this app
     */
    void saveScores(Object data, Context context);
}
