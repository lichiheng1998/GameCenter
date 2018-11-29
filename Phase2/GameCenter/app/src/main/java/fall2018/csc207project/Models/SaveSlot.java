package fall2018.csc207project.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * The class SaveSlot that implements Serializable
 */
public class SaveSlot implements Serializable{
    private Save[] saves;

    /**
     * Construct a new SaveSlot
     */
    SaveSlot(){
        saves = new Save[4];
    }

    /**
     * Construct a new SaveSlot by given a position and a data.
     *
     * @param position the slot you want to be save to
     * @param data the data to be save
     */
    public void saveToSlot(int position, Object data){
        if(position > 2){
            return;
        }
        saves[position] = new Save(new Date(), data);
    }

    /**
     * Get the saved data.
     *
     * @param position the slot you want to be save to
     * @return load the saved data
     */
    public Save readFromSlot(int position){
        if (position > 2) {
            return null;
        }
        return saves[position];
    }

    /**
     * AutoSave the data.
     *
     * @param data the data to be save
     */
    public void saveToAutoSave(Object data){
        saves[3] = new Save(new Date(), data);
    }

    /**
     * Get the auto-saved data.
     *
     * @return load the auto-saved data
     */
    public Save readFromAutoSave(){
        return saves[3];
    }
}
