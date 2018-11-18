package fall2018.csc207project.models;

import android.content.Context;

public interface GlobalDataStream {
    void saveGlobalData(Context context);
    void loadGlobalData(Context context);
}
