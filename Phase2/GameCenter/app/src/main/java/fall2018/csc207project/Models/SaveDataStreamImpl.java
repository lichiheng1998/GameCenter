package fall2018.csc207project.Models;

import android.content.Context;

public class SaveDataStreamImpl implements SaveDataStream{
    private static final String SAVE_PATH = "Saves.ser";
    private GlobalDataStream dataStream;

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
