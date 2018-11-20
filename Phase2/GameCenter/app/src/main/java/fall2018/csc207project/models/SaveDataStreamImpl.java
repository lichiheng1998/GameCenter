package fall2018.csc207project.models;

import android.content.Context;

public class SaveDataStreamImpl implements SaveDataStream{
    private static final String SAVEPATH = "Saves.ser";
    private static final String AUTOSAVEPATH = "AutoSaves.ser";
    private GlobalDataStream dataStream;

    public SaveDataStreamImpl(GlobalDataStream dataStream){
        this.dataStream = dataStream;
    }
    @Override
    public Object getSaves(Object initData, boolean isAutoSave, Context context) {
        if (isAutoSave) {
            return dataStream.getAndInit(initData, AUTOSAVEPATH, context);
        }
        return dataStream.getAndInit(initData, SAVEPATH, context);
    }

    @Override
    public void saveSaves(Object data, boolean isAutoSave, Context context){
        if (isAutoSave) {
            dataStream.saveGlobalData(data, AUTOSAVEPATH, context);
        }
        dataStream.saveGlobalData(data, SAVEPATH, context);
    }
}
