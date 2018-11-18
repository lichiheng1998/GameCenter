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
    private Context context;
    private SaveDataStream dataStream;

    public SaveManager(SaveDataStream dataStream, String user, String game, Context context){
        this.user = user;
        this.game = game;
        this.context = context;
        this.dataStream = dataStream;
        gameToSaves = dataStream.getSaves();
        gameToAutoSaves = dataStream.getAutoSaves();
        initStructure(gameToSaves);
        initStructure(gameToAutoSaves);
        dataStream.saveGlobalData(context);
    }

    private void initStructure(Map<String, Map<String, Object>> struct){
        if (!struct.containsKey(user)){
            struct.put(user, new HashMap<String, Object>());
        }
    }

    public void saveToSlot(Object save, boolean isAutoSave){
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
