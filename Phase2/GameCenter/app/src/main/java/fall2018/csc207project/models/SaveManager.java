package fall2018.csc207project.models;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class SaveManager {
    private String user;
    private String game;
    private SaveDataStream dataStream;

    public SaveManager(SaveDataStream dataStream, String user, String game){
        this.user = user;
        this.game = game;
        this.dataStream = dataStream;
    }

    private Map<String, Map<String, Object>> getGameToSaves(boolean isAutoSave, Context context){
        return (Map<String, Map<String,Object>>)
                dataStream.getSaves(new HashMap<String, Map<String, Object>>(), isAutoSave, context);
    }

    public void saveToSlot(Object save, boolean isAutoSave, Context context){
        Map<String, Map<String, Object>> saves = getGameToSaves(isAutoSave, context);
        if (!saves.containsKey(user)){
            saves.put(user, new HashMap<String, Object>());
        }
        saves.get(user).put(game, save);
        dataStream.saveSaves(saves, isAutoSave, context);
    }

    public Object readFromSlot(boolean isAutoSave, Context context){
        Map<String, Map<String, Object>> saves = getGameToSaves(isAutoSave, context);
        if (!saves.containsKey(user)){
            saves.put(user, new HashMap<String, Object>());
        }
        return saves.get(user).get(game);
    }
}
