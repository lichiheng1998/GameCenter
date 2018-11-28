package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The class LevelFactory that implements Serializable.
 */
public class LevelFactory implements Serializable {

    /**
     * The context of this app.
     */
    private Context context;

    /**
     * The HashMap<String, Object> that contains all the game elements.
     */
    private HashMap<String, Object> gameElements = new HashMap<>();

    /**
     * The BufferedReader for read file.
     */
    private BufferedReader reader;

    /**
     * Create a new LevelFactory to initialize a game.
     */
    public LevelFactory(Context context){
        this.context = context;
    }


    /**
     * Get all the elements for the corresponding level to initialize a map.
     * @param level the level of the game ready to be played
     * @return Hash Map which maps the name of the needed element to the element
     */
    public HashMap<String, Object> getGameElements(int level){
        List<BgTile> bgElements = new ArrayList<>();
        ArrayList<Box> boxArrayList = new ArrayList<>();
        Integer[][] tmp = getInfoFromFile(level);
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
        gameElements.put("map", new GameMap(width, height, bgElements));
        gameElements.put("boxArrayList", boxArrayList);
        return gameElements;
    }


    /**
     * Initialize the hash map which stores all the elements for each level of game.
     * */
    private Integer[][] getInfoFromFile(int level) {
        Integer[][] levelInfo = new Integer[6][];
        AssetManager assetManager = context.getAssets();
        String nextLine;
        int countLevel = 0;
        while ((nextLine = readLine(assetManager)) != null && countLevel != level) {
            countLevel++;
            if (countLevel == level) {
                String[] nextLevel = nextLine.split("-");
                for (int i = 0; i < nextLevel.length; i++) {
                    levelInfo[i] = strArrToIntArr(nextLevel[i].split(","));
                }
            }
        }
        return levelInfo;
    }

    /**
     * Get a string of what is read from the file.
     *
     * @param assetManager the AssetManager for this game
     * @return a string of what is read from the file
     */
    public String readLine(AssetManager assetManager){
        String result = "";
        try {
            if (reader == null) {
                reader = new BufferedReader(new InputStreamReader(
                        assetManager.open("box_levels.txt")));
            }
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Convert an array of string representation of integers into an array of integers.
     * @param strArr an array of string representation of integers
     * @return the array of integers
     */
    private Integer[] strArrToIntArr(String[] strArr){
        Integer[] intArr = new Integer[strArr.length];
        if (!strArr[0].equals("")) {
            for (int i = 0; i < strArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }
        }
        return intArr;
    }
}