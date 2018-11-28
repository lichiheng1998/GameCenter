package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;
import android.util.SparseIntArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observer;
import java.util.HashMap;
import java.util.Stack;

import fall2018.csc207project.Models.DatabaseUtil;

/**
 * Manages a map, including the movements of the person and boxes on the map, and checks for a win.
 */
public class MapManager implements Serializable {
    /**
     * The map to be managed.
     */
    public GameMap map;

    /**
     * The person who pushes the box on the map.
     */
    public Person person;

    /**
     * The list of box to be pushed on the map.
     */
    private ArrayList<Box> boxArrayList = new ArrayList<>();

    /**
     * The level of the game chosen by user.
     */
    private int level;

    /**
     * The counting of total steps moved.
     */
    private int totalMoveSteps;

    /**
     * The counting of total steps undid.
     */
    private int totalUndoSteps;

    /**
     * The number of undo available left.
     */
    private int undoTimes;
    private final int totalUndoTimes;

    /**
     * Stores all the movements taken.
     */
    private Stack<int[]> stackOfMovements;

    /**
     * Whether the boxes are moved.
     */
    public boolean isBoxesMoved = false;

    /**
     * Initialize a new manager to manage a new map.
     */
    public MapManager(int level, int undoTimes, HashMap<String, Object> levelInfo){
        this.level = level;
        this.undoTimes = undoTimes;
        totalUndoTimes = undoTimes;
        this.stackOfMovements = new Stack<>();
        createGameByLevel(levelInfo);
    }

    /**
     * Returns whether all the boxes are pushed to a destination point.
     * @return whether all the boxes are pushed to a destination point
     */
    public Boolean boxSolved(){
        for (Box box: boxArrayList) {
            // if there exist boxes on not destination point, the game is not solved.
            if(!map.getBgElements().get(box.getPosition()).isWinnable()){
                return false;
            }
        }
        return true;
    }

    /**
     * Return the box on this position if exist
     * @param position position which the box may occur
     * @return the box on this position. If there does not exist such box, return null
     */
    private Box boxAtPos(int position){
        for (Box box: boxArrayList) {
            if (box.getPosition() == position){
                return box;
            }
        }
        return null;
    }

    /**
     * Check if changing the position into new position is a valid movement.
     * Movement is only valid when no wall and no box on next step OR when there is a box
     * on next step but no box nor wall on next next step.
     * @param posChange the changing in position to get new position
     */
    public Boolean isValidMovement(int posChange){
        int newPosition = person.getPosition() + posChange;
        return (!map.tileIsWall(newPosition) && boxAtPos(newPosition) == null)
                || (boxAtPos(newPosition) != null
                && !map.tileIsWall(newPosition + posChange)
                && boxAtPos(newPosition + posChange) == null);
    }

    /**
     * Process person's movement.
     * @param posChange the changing in position to get new position
     */
    public void processPersonMovement(int posChange){
        isBoxesMoved = false;
        int newPosition = person.getPosition() + posChange;
        // There may be a box on next step, so the person will push the box.
        if (boxAtPos(newPosition)!=null){
            processBoxMovement(newPosition, newPosition + posChange);
        }
        person.walk(posChange);
        totalMoveSteps++;
    }

    /**
     * Process the movement of the box on the map.
     * The image of the box will turn into winning image if it arrives at a destination point.
     * @param position current position of the box
     * @param newPosition new position for the box
     */
    private void processBoxMovement(int position, int newPosition){
        Objects.requireNonNull(boxAtPos(position)).move(newPosition);
        if (map.tileIsDestination(newPosition)){
            Objects.requireNonNull(boxAtPos(newPosition)).arriveDestination();
        }else{
            Objects.requireNonNull(boxAtPos(newPosition)).leaveDestination();
        }
        isBoxesMoved = true;
    }

    public void pushLastStep(int personOldPos, int boxNewPos){
        stackOfMovements.push(new int[]{personOldPos, boxNewPos});
    }

    /**
     * Process the undo movement by the given steps.
     * int[2] of {old position of person, new position of box}
     *
     * @param steps the steps you want to go back.
     */
    private void processUndoMovement(int steps) {
        for (int i = 0; i < steps; i++) {
            int[] poppedItem = stackOfMovements.pop();
            int currentPersonPos = person.getPosition();
            if (poppedItem[1] == -1) {
                person.walk(-poppedItem[0]);
            } else {
                processBoxMovement(poppedItem[1], currentPersonPos);
                person.walk(-poppedItem[0]);
            }
        }
        undoTimes--;
        totalUndoSteps++;
    }

    /**
     * Whether it is available to process the given number of undo steps
     * @param step the number of steps requested for undo
     * @return if the request of undo is available
     */
    public boolean canProcessUndo(int step){
        if (undoTimes == 0 || step > stackOfMovements.size()){
            return false;
        } else {
            processUndoMovement(step);
            return true;
        }
    }

    /**
     * Return the total number of undo times to be used.
     * @return total number of undo times
     */
    public int getTotalUndoTimes(){
        return totalUndoTimes;
    }

    public int getTotalMoveSteps(){
        return totalMoveSteps;
    }

    /**
     * Initialize the game elements by the chosen game level.
     */
    private void createGameByLevel(HashMap<String, Object> levelInfo){
        person = (Person) levelInfo.get("Person");
        boxArrayList = (ArrayList<Box>) levelInfo.get("boxArrayList");
        map = (GameMap) levelInfo.get("map");
    }

    /**
     * Return the level of the game.
     * @return the level of the game
     */
    public int getLevel(){
        return level;
    }

    /**
     * Set the observer.
     * @param o the observer
     */
    public void subscribe(Observer o){
        person.addObserver(o);
    }

    /**
     * Return the number of columns
     * @return number of columns
     */
    public int getNumCol(){
        return map.getNumCol();
    }


    /**
     * Get the array of the background id of all tiles which forms the map.
     * @return the array of the background ids
     */
    public Integer[] getTilesBg(){
        Integer[] tileBgs = new Integer[map.getBgElements().size()];
        for (int i = 0; i < tileBgs.length; i++ ){
            tileBgs[i] = map.getBgElements().get(i).getBackground();
        }
        return tileBgs;
    }

    /**
     * Get all the boxes' position mapping with the image id of each box
     * @return boxes' position mapping with the image id of each box
     */
    public ArrayList<Box> getBoxList(){
        return boxArrayList;
    }
}
