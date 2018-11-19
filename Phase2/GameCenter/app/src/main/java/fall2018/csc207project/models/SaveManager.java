package fall2018.csc207project.models;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class SaveManager {
    private String user;
    private String game;
    private Map<String, Map<String, Object>> gameToSaves;
    private Map<String, Map<String, Object>> gameToAutoSaves;
    private SaveDataStream dataStream;

    public SaveManager(SaveDataStream dataStream, String user, String game, Context context){
        this.user = user;
        this.game = game;
        this.dataStream = dataStream;
        gameToSaves = dataStream.getSaves();
        gameToAutoSaves = dataStream.getAutoSaves();
        initStructure(gameToSaves, context);
        initStructure(gameToAutoSaves, context);

    }

    private void initStructure(Map<String, Map<String, Object>> struct, Context context){
        if (!struct.containsKey(user)){
            struct.put(user, new HashMap<String, Object>());
            dataStream.saveGlobalData(context);
        }
    }

    public void saveToSlot(Object save, boolean isAutoSave, Context context){
        if (isAutoSave){
            gameToAutoSaves.get(user).put(game, save);
        } else {
            gameToSaves.get(user).put(game, save);
        }
        Log.d("debug", "saveToSlot: Is saved！");
        dataStream.saveGlobalData(context);
    }

    public Object readFromSlot(boolean isAutoSave){
        if (isAutoSave){
            return gameToAutoSaves.get(user).get(game);
        }
        return gameToSaves.get(user).get(game);
    }
}
