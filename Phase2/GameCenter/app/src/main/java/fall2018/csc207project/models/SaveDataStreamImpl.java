package fall2018.csc207project.models;

import android.content.Context;

public class SaveDataStreamImpl implements SaveDataStream{
    private static final String SAVEPATH = "Saves.ser";
    private GlobalDataStream dataStream;

    SaveDataStreamImpl(GlobalDataStream dataStream){
        this.dataStream = dataStream;
    }
    @Override
    public Object getSaves(Object initData, Context context) {
        return dataStream.getAndInit(initData, SAVEPATH, context);
    }

    @Override
    public void saveSaves(Object data, Context context){
        dataStream.saveGlobalData(data, SAVEPATH, context);
    }
}
