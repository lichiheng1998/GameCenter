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
import java.util.Map;

public class LevelFactory implements Serializable {

    /**
     * The hash map that maps all the information of the game to its corresponding level
     */
    private Map<Integer, Integer[][]> allLevels = new HashMap<>();

    /**
     * the total number of levels.
     */
    private int levelNum;

    /**
     * Create a new LevelFactory to initialize a game.
     */
    public LevelFactory(Context context){
        initAllLevels(context);
        levelNum = allLevels.size();
    }


    /**
     * Get all the elements for the corresponding level to initialize a map.
     * @param level the level of the game ready to be played
     * @return Hash Map which maps the name of the needed element to the element
     */
    public HashMap<String, Object> getGameElements(int level){
        HashMap<String, Object> gameElements = new HashMap<>();
        List<BgTile> bgElements = new ArrayList<>();
        ArrayList<Box> boxArrayList = new ArrayList<>();
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
    private void initAllLevels(Context context){
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    assetManager.open("box_levels.txt")));
            String nextLine = reader.readLine();
            int countLevel = 1;
            while ((nextLine = reader.readLine()) != null){
                String[] nextLevel = nextLine.split("-");
                Integer[][] levelInfo = new Integer[nextLevel.length][];
                for (int i = 0; i < nextLevel.length; i++){
                    levelInfo[i] = strArrToIntArr(nextLevel[i].split(","));
                }
                allLevels.put(countLevel, levelInfo);
                countLevel++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Integer[] strArrToIntArr(String[] strArr){
        Integer[] intArr = new Integer[strArr.length];
        if (!strArr[0].equals("")) {
            for (int i = 0; i < strArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }
        }
        return intArr;
    }


    /**
     * Return the total number of levels exists
     * @return the total number of levels
     */
    public int getLevelAmount(){
        return levelNum;
    }
}
