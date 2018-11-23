package fall2018.csc207project.PushTheBox.Models;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LevelFactory {

    /**
     * The array list that stores all the background tile for the map
     */
    private List<BgTile> bgElements = new ArrayList<>();

    /**
     * The hash map that maps all the information of the game to its corresponding level
     */
    private SparseArray<Integer[][]> allLevels = new SparseArray<>();


    /**
     * The Hash Map that is in charge of mapping the elements for the game to their names.
     */
    private HashMap<String, Object> gameElements = new HashMap<>();

    /**
     * the array list of boxes in the game
     */
    private ArrayList<Box> boxArrayList = new ArrayList<>();

    /**
     * the total number of levels.
     */
    private int levelNum;

    /**
     * Create a new LevelFactory to initialize a game.
     */
    public LevelFactory(){
        initAllLevels();
        levelNum = allLevels.size();
    }


    /**
     * Get all the elements for the corresponding level to initialize a map.
     * @param level the level of the game ready to be played
     * @return Hash Map which maps the name of the needed element to the element
     */
    public HashMap<String, Object> getGameElements(int level){
        Integer[][] tmp = allLevels.get(level);
        int height  = tmp[0][0];
        int width = tmp[1][0];
        for(int i = 0; i < height * width; i++){
            if (i % width == 0 | i % width == (width - 1) | i < width | i >= ((height * width) - width)
                    | Arrays.asList(tmp[2]).contains(i)){
                bgElements.add(new BgTile(BgTile.WALLTYPE));
            } else if(Arrays.asList(tmp[4]).contains(i)){
                bgElements.add(new BgTile(BgTile.DESTTYPE));
            }else{
                bgElements.add(new BgTile(BgTile.FLOORTYPE));
            } if (Arrays.asList(tmp[3]).contains(i)){
                Box tmpBox = new Box(i);
                boxArrayList.add(tmpBox);
                if (Arrays.asList(tmp[4]).contains(i)){
                    tmpBox.arriveDestination();
                }
            }else if (Arrays.asList(tmp[5]).contains(i)){
                   gameElements.put("Person", new Person(i));
            }
        }
        gameElements.put("map", new GameMap(width, height, bgElements));
        gameElements.put("boxArrayList", boxArrayList);
        gameElements.put("bgElements", bgElements);
        return gameElements;
    }


    /**
     * Initialize the hash map which stores all the elements for each level of game.
     * */
    private void initAllLevels(){
        //the six arrays in the big array is the height, width, positions of walls
        //(excluding wall on edges), boxes, destination points, and the person, in order.
        Integer[][] levelOne = {{3},{5},{},{7},{8},{6}};
        allLevels.put(1, levelOne);

        Integer[][] levelTwo = {{5},{5},{},{12,17},{13,16},{6}};
        allLevels.put(2, levelTwo);

        Integer[][] levelThree = {{6},{6},{10,21},{14,15},{13,15},{7}};
        allLevels.put(3, levelThree);

        Integer[][] levelFour = {{6},{7},{12,23},{16,17},{24,26},{8}};
        allLevels.put(4, levelFour);

        Integer[][] levelFive = {{7},{7},{8,9,12,19,30},{18,25},{17,31},{15}};
        allLevels.put(5,levelFive);

        Integer[][] levelSix = {{7},{7},{8,12,17,36,40},{23,24,25},{24,31,38},{9}};
        allLevels.put(6, levelSix);

        Integer[][] levelSeven = {{6},{8},{13,14,35},{26,27,28},{20,21,37},{29}};
        allLevels.put(7,levelSeven);

        Integer[][] levelEight = {{8},{8},{9,13,14,19,27,29,45,46},{21,34,37},{10,34,38},{22}};
        allLevels.put(8,levelEight);
    }


    /**
     * Return the total number of levels exists
     * @return the total number of levels
     */
    public int getLevelAmount(){
        return levelNum;
    }
}
