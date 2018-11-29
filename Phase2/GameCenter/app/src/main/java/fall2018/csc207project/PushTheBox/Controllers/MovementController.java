package fall2018.csc207project.PushTheBox.Controllers;

import fall2018.csc207project.PushTheBox.Models.MapManager;

/**
 * The MovementController that process each movement that create by the current user.
 */
public class MovementController {

    /**
     * The MapManager that manages the Map.
     */
    private MapManager mapManager;

    /**
     * Construct a new MovementController.
     */
    MovementController(){}

    /**
     * Set up the MapManager by given a MapManager
     *
     * @param mapManager the MapManager that manages the Map
     */
    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    /**
     * Process tap movement and return whether the game is completed or not.
     *
     * @param direction the direction going to move
     * @return whether the game is completed or not
     */
    public boolean processTapMovement(String direction) {
        int posChange = getPosChange(direction);
        if (mapManager.isValidMovement(posChange)) {
            mapManager.processPersonMovement(posChange);
            int boxNewPos = mapManager.person.getPosition() + posChange;
            if (!mapManager.isBoxesMoved) {
                mapManager.pushLastStep(posChange, -1);
            } else {
                mapManager.pushLastStep(posChange, boxNewPos);
            }
            return true;
        }
        return false;
    }

    /**
     * Get the value that represents the change in position.
     *
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
