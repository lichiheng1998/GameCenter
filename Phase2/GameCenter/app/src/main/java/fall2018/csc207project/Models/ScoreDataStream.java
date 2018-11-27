package fall2018.csc207project.Models;

import android.content.Context;

public interface ScoreDataStream {
    Object getScores(Object initData, Context context);
    void saveScores(Object data, Context context);
}
