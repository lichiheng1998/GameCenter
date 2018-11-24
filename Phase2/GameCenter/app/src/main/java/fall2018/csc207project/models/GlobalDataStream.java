package fall2018.csc207project.models;

import android.content.Context;

public interface GlobalDataStream {
    void saveGlobalData(Object data, String path, Context context);
    Object loadGlobalData(String path, Context context);
    Object getAndInit(Object initData, String path, Context context);
}
