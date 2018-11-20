package fall2018.csc207project.models;

import android.content.Context;

public interface SaveDataStream {
    Object getSaves(Object initData, boolean isAutoSave, Context context);
    void saveSaves(Object data, boolean isAutoSave, Context context);
}
