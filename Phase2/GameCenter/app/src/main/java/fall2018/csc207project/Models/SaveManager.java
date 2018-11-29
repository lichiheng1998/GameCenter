package fall2018.csc207project.Models;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * The SaveManager to manage the saves.
 */
@SuppressWarnings("unchecked")
public class SaveManager {

    /**
     * The user's name.
     */
    private String user;

    /**
     * The game's name.
     */
    private String game;

    /**
     * The SaveDataStream to save the data.
     */
    private SaveDataStream dataStream;

    /**
     * Construct a new SaveManager to manage the saves
     *
     * @param dataStream the SaveDataStream to save the data
     * @param user the user's name
     * @param game the game's name
     */
    SaveManager(SaveDataStream dataStream, String user, String game){
        this.user = user;
        this.game = game;
        this.dataStream = dataStream;
    }

    /**
     * Returns a slot of save in Map<String, Map<String, SaveSlot>>.
     *
     * @param context the context of this app
     * @return a slot of save in Map<String, Map<String, SaveSlot>>
     */
    private Map<String, Map<String, SaveSlot>> getSaveSlots(Context context){
        return (Map<String, Map<String, SaveSlot>>)
                dataStream.getSaves(new HashMap<String, Map<String, SaveSlot>>(), context);
    }

    /**
     * Save to the given slot.
     *
     * @param save the SaveSlot
     * @param context the context of this app
     */
    public void saveToFile(SaveSlot save, Context context){
        Map<String, Map<String, SaveSlot>> saves = getSaveSlots(context);
        initialize(saves);
        saves.get(user).put(game, save);
        dataStream.saveSaves(saves, context);
    }

    /**
     * Get a SaveSlot where you want to load the save.
     *
     * @param context the context of this app
     * @return a SaveSlot where you want to load the save
     */
    public SaveSlot readFromFile(Context context){
        Map<String, Map<String, SaveSlot>> saves = getSaveSlots(context);
        initialize(saves);
        return saves.get(user).get(game);
    }

    /**
     * Initialize a save.
     *
     * @param saves a Map<String, Map<String, SaveSlot>> of save info
     */
    private void initialize(Map<String, Map<String, SaveSlot>> saves){
        if(!saves.containsKey(user)){
            saves.put(user, new HashMap<String, SaveSlot>());
        }
        if(!saves.get(user).containsKey(game)){
            saves.get(user).put(game, new SaveSlot());
        }
    }
}
