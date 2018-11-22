package fall2018.csc207project.PushTheBox.Models;

import android.util.SparseIntArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observer;
import java.util.HashMap;
import java.util.Stack;

import fall2018.csc207project.PushTheBox.Controllers.LevelFactory;

/**
 * Manages a map, including the movements of the person and boxes on the map, and checks for a win.
 */
public class MapManager implements Serializable {
    /**
     * The map to be managed.
     */
    public Map map;

    /**
     * The person who pushes the box on the map.
     */
    public Person person;

    /**
     * The list of box to be pushed on the map.
     */
    public ArrayList<Box> boxArrayList = new ArrayList<>();

    /**
     * The level of the game chosen by user.
     */
    private int level;

    /**
     * The list of all background tiles.
     */
    private ArrayList<BgTile> bgElements;

    /**
     * The Hashmap which stores the initial information of the game.
     */
    private HashMap<String, Object> levelInfo = new HashMap<>();

    /**
     * The array that stores all the image id that will be displayed on the view.
     */
    private Integer[] tileBgs;

    /**
     * The counting of steps taken.
     */
    private int totalSteps;

    /**
     * The number of undo available left.
     */
    private int undoTimes;

    /**
     * The total number of undo times.
     */
    private final int totalUndoTimes;

    private Stack<int[]> stackOfMovements;

    /**
     * Initialize a new manager to manage a new map.
     */
    public MapManager(int level, int undoTimes){
        this.level = level;
        createGameByLevel();
        initTileBg();
        this.undoTimes = undoTimes;
        totalUndoTimes = undoTimes;
        this.totalSteps = 0;
        this.stackOfMovements = new Stack<>();
    }

    /**
     * Returns whether all the boxes are pushed to a destination point.
     * @return whether all the boxes are pushed to a destination point
     */
    public Boolean boxSolved(){
        for (Box box: boxArrayList) {
            // if there exist boxes on not destination point, the game is not solved.
            if(!bgElements.get(box.getPosition()).isWinnable()){
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
    public Box boxAtPos(int position){
        for (Box box: boxArrayList) {
            if (box.getPosition() == position){
                return box;
            }
        }
        return null;
    }

    /**
     * Check if changing the position into new position is a valid movement.
     * Movement is only valid when there is no wall and no box on next step OR when there is a box
     * on next step but no box nor wall on next next step.
     *
     * @param posChange the changing in position to get new position
     */
    public Boolean isValidMovement(int posChange){
        int newPosition = person.getPosition() + posChange;
        return (!map.tileIsWall(newPosition) && boxAtPos(newPosition) == null)
                || (boxAtPos(newPosition) != null
                && !map.tileIsWall(newPosition + posChange)
                && boxAtPos(newPosition + posChange) == null);
    }

    public boolean isBoxesMoved = false;

    /**
     * Process person's movement.
     * @param posChange the changing in position to get new position
     */
    public void processPersonMovement(int posChange){
        isBoxesMoved = false;
        int newPosition = person.getPosition() + posChange;
        // There may be a box on next step, so the person will push the box.
        if (boxAtPos(newPosition)!=null){
            isBoxesMoved = true;
            processBoxMovement(newPosition, newPosition + posChange);
        }
        person.walk(posChange);
        totalSteps++;
    }

    /**
     * Process the movement of the box on the map.
     * The image of the box will turn into winning image if it arrives at a destination point.
     * @param position current position of the box
     * @param newPosition new position for the box
     */
    private void processBoxMovement(int position, int newPosition){
        boxAtPos(position).move(newPosition);
        if (map.tileAtDestination(newPosition)){
            boxAtPos(newPosition).arriveDestination();
        }else{
            boxAtPos(newPosition).leaveDestination();
        }
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
                person.walk(-poppedItem[0]);
                processBoxMovement(poppedItem[1], currentPersonPos);
            }
        }
        undoTimes--;
    }

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

    /**
     * Return the total steps you did.
     *
     * @return the total steps you did
     */
    public int getTotalSteps() {
        return totalSteps;
    }

    /**
     * Initialize the game elements by the chosen game level.
     */
    private void createGameByLevel(){
        LevelFactory levelFactory = new LevelFactory();
        levelInfo = levelFactory.getGameElements(level);
        bgElements = (ArrayList<BgTile>) levelInfo.get("bgElements");
        person = (Person) levelInfo.get("Person");
        boxArrayList = (ArrayList<Box>) levelInfo.get("boxArrayList");
        map = (Map) levelInfo.get("map");
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
     * Return the number of rows
     * @return number of rows
     */
    public int getNumRow(){
        return map.getNumRow();
    }

    /**
     * Get the array of the background id of all tiles which forms the map.
     * @return the array of the background ids
     */
    public Integer[] getTilesBg(){
        return tileBgs;
    }


    /**
     * Initialize the array that stores all the image Id of tiles on the map.
     */
    private void initTileBg(){
        tileBgs = new Integer[bgElements.size()];
        for (int i = 0; i < tileBgs.length; i++ ){
            tileBgs[i] = bgElements.get(i).getBackground();
        }
    }


    public SparseIntArray getPersonPosToImage(){
        SparseIntArray personPosToImage = new SparseIntArray();
        personPosToImage.append(person.getPosition(), person.getImage());
        return personPosToImage;
    }

    public SparseIntArray getBoxPosToImage(){
        SparseIntArray boxes = new SparseIntArray();
        for (Box box : boxArrayList){
            boxes.append(box.getPosition(), box.getImage());
        }
        return boxes;
    }
}
