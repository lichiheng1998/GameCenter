package fall2018.csc207project.models;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
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

    private Map<String, Map<String, SaveSlot>> getSaveSlots(Context context){
        return (Map<String, Map<String, SaveSlot>>)
                dataStream.getSaves(new HashMap<String, Map<String, SaveSlot>>(), context);
    }

    public void saveToFile(SaveSlot save, Context context){
        Map<String, Map<String, SaveSlot>> saves = getSaveSlots(context);
        initialize(saves);
        saves.get(user).put(game, save);
        dataStream.saveSaves(saves, context);
    }

    public SaveSlot readFromFile(Context context){
        Map<String, Map<String, SaveSlot>> saves = getSaveSlots(context);
        initialize(saves);
        return saves.get(user).get(game);
    }

    private void initialize(Map<String, Map<String, SaveSlot>> saves){
        if(!saves.containsKey(user)){
            saves.put(user, new HashMap<String, SaveSlot>());
        }
        if(!saves.get(user).containsKey(game)){
            saves.get(user).put(game, new SaveSlot());
        }
    }
}
