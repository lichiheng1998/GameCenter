package fall2018.csc207project.Models;

import android.content.Context;

public class ScoreDataStreamImpl implements ScoreDataStream{
    private static final String GAME_TO_SCORES_PATH = "GameToScores.ser";
    private GlobalDataStream dataStream;

    ScoreDataStreamImpl(GlobalDataStream dataStream){
        this.dataStream = dataStream;
    }

    @Override
    public Object getScores(Object initData, Context context){
        return dataStream.getAndInit(initData, GAME_TO_SCORES_PATH, context);
    }

    @Override
    public void saveScores(Object data, Context context) {
        dataStream.saveGlobalData(data, GAME_TO_SCORES_PATH, context);
    }
}
