package fall2018.csc207project.PushTheBox.Models;

import java.util.ArrayList;
import java.util.Observer;


/**
 * Manages a map, including the movements of the person and boxes on the map, and checks for a win.
 */
public class MapManager {
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
     * The counting of steps taken.
     */
    public int stepsTaken;

    /**
     * Initialize a new manager to manage a new map.
     */
    public MapManager(int level){
        this.level = level;
        createGameByLevel();//TODO
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
        if ((!map.tileIsWall(newPosition) && boxAtPos(newPosition) == null)
                || (boxAtPos(newPosition)!=null
                && !map.tileIsWall(newPosition + posChange)
                && boxAtPos(newPosition + posChange) == null)){
            return true;
        }
        return false;
    }

    /**
     * Process person's movement.
     * @param posChange the changing in position to get new position
     */
    public void processPersonMovement(int posChange){
        int newPosition = person.getPosition() + posChange;
        // There may be a box on next step, so the person will push the box.
        if (boxAtPos(newPosition)!=null){
            processBoxMovement(newPosition, newPosition + posChange);
        }
        person.walk(posChange);
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

    /**
     * Initialize the game elements by the chosen game level.
     */
    private void createGameByLevel(){
        //TODO
        bgElements = new ArrayList<>();
        for (int i=0; i < 5*5;i++){
            if(i<=5 | i==9 | i==10 | i==14 | i==15 | i >= 19){
                bgElements.add(new BgTile("Wall"));
            }else if (i==13 | i==16){
                bgElements.add(new BgTile("Destination"));
            }else{
                bgElements.add(new BgTile("Floor"));
            }
        }
        map = new Map(5,5,bgElements);
        person = new Person(6);
        boxArrayList.add(new Box(12));
        boxArrayList.add(new Box(17));

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
        Integer[] tileBgs = new Integer[bgElements.size()];
        for (int i =0; i <  tileBgs.length; i++){
            tileBgs[i] = bgElements.get(i).getBackground();
        }
        tileBgs[person.getPosition()] = person.getImage();
        for (Box box: boxArrayList) {
            tileBgs[box.getPosition()] = box.getImage();
        }
        return tileBgs;
    }
}
