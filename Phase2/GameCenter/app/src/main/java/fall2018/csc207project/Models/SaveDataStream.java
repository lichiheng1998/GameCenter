package fall2018.csc207project.Models;

import android.content.Context;

/**
 * The interface SaveDataStream to save a game data.
 */
public interface SaveDataStream {

    /**
     * Load from the save.
     *
     * @param initData the data that want to be load from
     * @param context the context of this app
     * @return load from the save
     */
    Object getSaves(Object initData, Context context);

    /**
     * Save the given data.
     *
     * @param data the data that want to be save to
     * @param context the context of this app
     */
    void saveSaves(Object data, Context context);
}
