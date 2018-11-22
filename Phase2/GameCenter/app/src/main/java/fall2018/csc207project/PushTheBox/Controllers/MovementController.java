package fall2018.csc207project.PushTheBox.Controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import fall2018.csc207project.PushTheBox.Models.MapManager;

public class MovementController {

    private MapManager mapManager;

    public MovementController(){}


    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    /**
     * Process tap movement and return whether the game is completed or not.
     * @param context context
     * @param direction the direction going to
     * @return whether the game is completed or not
     */
    public boolean processTapMovement(Context context, String direction) {
        int posChange;
        switch (direction) {
            case "left":
                posChange = -1;
                break;
            case "up":
                posChange = -mapManager.getNumCol();
                break;
            case "right":
                posChange = 1;
                break;
            default:
                posChange = mapManager.getNumCol();
                break;
        }

        if (mapManager.isValidMovement(posChange)) {
            int personOldPos = mapManager.person.getPosition();
            mapManager.processPersonMovement(posChange);
            int boxNewPos = mapManager.person.getPosition() + posChange;
            if (!mapManager.isBoxesMoved) {
                System.out.println(true);
                mapManager.pushLastStep(personOldPos, -1);
            } else {
                System.out.println(false);
                mapManager.pushLastStep(personOldPos, boxNewPos);
            }
            if (mapManager.boxSolved()) {
                return true;
            }else{
                return false;
            }
        }
        Toast.makeText(context, "Invalid Movement", Toast.LENGTH_SHORT).show();
        return false;
    }
}
