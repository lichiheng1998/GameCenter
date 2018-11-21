package fall2018.csc207project.PushTheBox.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import fall2018.csc207project.PushTheBox.Models.BgTile;
import fall2018.csc207project.PushTheBox.Models.Box;
import fall2018.csc207project.PushTheBox.Models.Map;
import fall2018.csc207project.PushTheBox.Models.Person;

public class LevelFactory {

    /**
     * The array list that stores all the background tile for the map
     */
    private ArrayList<BgTile> bgElements = new ArrayList<>();

    /**
     * The hash map that maps all the information of the game to its corresponding level
     */
    private HashMap<Integer, Integer[][]> allLevels = new HashMap<>();

    /**
     * The Hash Map that is in charge of mapping the elements for the game to their names.
     */
    private HashMap<String, Object> gameElements = new HashMap<>();

    /**
     * the array list of boxes in the game
     */
    private ArrayList<Box> boxArrayList = new ArrayList<>();

    /**
     * Create a new LevelFactory to initialize a game.
     */
    public LevelFactory(){
        initAllLevels();
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
                bgElements.add(new BgTile("Wall"));
            } else if(Arrays.asList(tmp[4]).contains(i)){
                bgElements.add(new BgTile("Destination"));
            }else{
                bgElements.add(new BgTile("Floor"));
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
        gameElements.put("map", new Map(width, height, bgElements));
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

//        Integer[][] levelFour = {{6},{7},{12,23},{16,17},{24,26},{8}};
//        allLevels.put(4, levelFour);
    }
}
