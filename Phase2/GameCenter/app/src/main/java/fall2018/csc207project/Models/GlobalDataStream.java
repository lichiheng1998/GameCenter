package fall2018.csc207project.Models;

import android.content.Context;

/**
 * The interface GlobalDataStream for reading and writing files.
 */
public interface GlobalDataStream {

    /**
     * Save to the given path.
     * @param path the path of the file to be saved.
     * @param data the object to be saved.
     */
    void saveGlobalData(Object data, String path, Context context);

    /**
     * Get the data in given path. If not exists, initialize with the initData, and save to the path.
     * @param path the path of the file to be loaded.
     * @param initData the data used to initialize.
     * @return the loaded data.
     */
    Object getAndInit(Object initData, String path, Context context);
}
