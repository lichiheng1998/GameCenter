package fall2018.csc207project.Models;

import android.content.Context;

/**
 * The class SaveDataStreamImpl that implements SaveDataStream
 */
public class SaveDataStreamImpl implements SaveDataStream {

    /**
     * The directory to save the game.
     */
    private static final String SAVE_PATH = "Saves.ser";

    /**
     * The GlobalDataStream to save the game.
     */
    private GlobalDataStream dataStream;

    /**
     * Construct a SaveDataStreamImpl to save game
     *
     * @param dataStream the GlobalDataStream to save the game
     */
    SaveDataStreamImpl(GlobalDataStream dataStream){
        this.dataStream = dataStream;
    }

    @Override
    public Object getSaves(Object initData, Context context) {
        return dataStream.getAndInit(initData, SAVE_PATH, context);
    }

    @Override
    public void saveSaves(Object data, Context context){
        dataStream.saveGlobalData(data, SAVE_PATH, context);
    }
}
