package fall2018.csc207project.models;
import java.io.Serializable;
import java.util.Date;

public class SaveSlot implements Serializable{
    private Save[] saves;

    public SaveSlot(){
        saves = new Save[4];
    }

    public boolean saveToSlot(int position, Object data){
        if(position > 2){
            return false;
        }
        saves[position] = new Save(new Date(), data);
        return true;
    }

    public Save readFromSlot(int position){
        if (position > 2) {
            return null;
        }
        return saves[position];
    }

    public void saveToAutoSave(Object data){
        saves[3] = new Save(new Date(), data);
    }

    public Save readFromAutoSave(){
        return saves[3];
    }
}
