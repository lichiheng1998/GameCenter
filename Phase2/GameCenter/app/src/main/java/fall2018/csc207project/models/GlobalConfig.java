package fall2018.csc207project.models;

import java.util.HashMap;
import java.util.Map;

import fall2018.csc207project.SlidingTile.Controllers.StartingActivity;
import fall2018.csc207project.R;

public class GlobalConfig {
    public static final String[] GAMELIST = {"SlidingTile", "Bejeweled", "Some Other Games2",
            "SlidingTile3", "Bejeweled4", "Some Other Games5", "SlidingTile6", "Bejeweled7",
            "Some Other Games8", "newGame"};

    public static final Integer[] GAMEBG = {R.drawable.sliding_tile, R.drawable.bejeweled,
            R.drawable.sliding_tile, R.drawable.sliding_tile, R.drawable.bejeweled,
            R.drawable.sliding_tile, R.drawable.bejeweled, R.drawable.bejeweled,
            R.drawable.sliding_tile, R.drawable.global};

    public static final Class[] gameEntry = {
            StartingActivity.class, StartingActivity.class, StartingActivity.class,
            StartingActivity.class, StartingActivity.class, StartingActivity.class,
            StartingActivity.class, StartingActivity.class, StartingActivity.class,
            StartingActivity.class
    };


    public static final Map<String, Integer> BGMAP = mapLists(GAMELIST, GAMEBG);

    public static final Map<String, Class> ENTRYMAP = mapLists(GAMELIST, gameEntry);

    private static <T, V> Map<T, V> mapLists(T[] arr1, V[] arr2){
        Map<T, V> map = new HashMap<>();
        for (int i = 0; i < GAMELIST.length; i++) {
            map.put(arr1[i], arr2[i]);
        }
        return map;
    }
}
