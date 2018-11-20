package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc207project.PushTheBox.Models.MapManager;

public class MovementController {

    private MapManager mapManager;

    public MovementController(){}


    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public boolean processTapMovement(Context context, String direction) {
        int posChange;
        if (direction.equals("left")){
            posChange = -1;
        } else if(direction.equals("up")){
            posChange = - mapManager.getNumCol();
        }else if (direction.equals("right")){
            posChange = 1;
        }else{
            posChange = mapManager.getNumCol();
        }

        if (mapManager.isValidMovement(posChange)) {
            mapManager.processPersonMovement(posChange);
            if (mapManager.boxSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        Toast.makeText(context, "Invalid Movement", Toast.LENGTH_SHORT).show();
        return true;
    }
}
