package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ScoreManager;
import fall2018.csc207project.PushTheBox.Models.BoxGameCalculator;
import fall2018.csc207project.PushTheBox.Models.BoxScore;
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
        int posChange = getPosChange(direction);
        if (mapManager.isValidMovement(posChange)) {
            mapManager.processPersonMovement(posChange);
            int boxNewPos = mapManager.person.getPosition() + posChange;
            if (!mapManager.isBoxesMoved) {
                mapManager.pushLastStep(posChange, -1);
            } else {
                mapManager.pushLastStep(posChange, boxNewPos);
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

    /**
     * Get the value that represents the change in position.
     * @param direction the string representing direciton(eg."up", "down", "right", "left")
     * @return the value that represents the change in position
     */
    private int getPosChange(String direction){
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
        return posChange;
    }
}
