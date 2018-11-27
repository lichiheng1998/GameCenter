package fall2018.csc207project.Models;

import android.content.Context;

public interface SaveDataStream {
    Object getSaves(Object initData, Context context);
    void saveSaves(Object data, Context context);
}
