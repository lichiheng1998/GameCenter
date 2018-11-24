package fall2018.csc207project.models;

import android.content.Context;

import java.util.List;
import java.util.Map;

public interface ScoreDataStream {
    Object getScores(Object initData, Context context);
    void saveScores(Object data, Context context);
}
