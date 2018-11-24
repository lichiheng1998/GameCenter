package fall2018.csc207project.models;

import java.util.HashMap;
import java.util.Map;

import fall2018.csc207project.Memorization.Controllers.MemoStartingActivity;
import fall2018.csc207project.PushTheBox.Controllers.BoxStartingActivity;
import fall2018.csc207project.SlidingTile.Controllers.StartingActivity;
import fall2018.csc207project.R;

/**
 * The global configurations of the app. Those are shared data that shouldn't be changed.
 */
public class GlobalConfig {
    /**
     * Names of the games.
     */
    public static final String[] GAMELIST = {"SlidingTile", "Memorization Master", "Some Other Games2",
            "SlidingTile3", "Bejeweled4", "Some Other Games5", "SlidingTile6", "Bejeweled7",
            "Some Other Games8", "newGame", "Push The Box"};
    /**
     * Ids of the background resources of the games.
     */
    public static final Integer[] GAMEBG = {R.drawable.sliding_tile, R.drawable.bejeweled,
            R.drawable.sliding_tile, R.drawable.sliding_tile, R.drawable.bejeweled,
            R.drawable.sliding_tile, R.drawable.bejeweled, R.drawable.bejeweled,
            R.drawable.sliding_tile, R.drawable.global, R.drawable.global};

    /**
     * The Entries of the each games.
     */
    public static final Class[] GAMEENTRY = {
            StartingActivity.class, MemoStartingActivity.class, StartingActivity.class,
            StartingActivity.class, StartingActivity.class, StartingActivity.class,
            StartingActivity.class, StartingActivity.class, StartingActivity.class,
            StartingActivity.class, BoxStartingActivity.class
    };


    public static final Map<String, Integer> BGMAP = mapLists(GAMELIST, GAMEBG);

    public static final Map<String, Class> ENTRYMAP = mapLists(GAMELIST, GAMEENTRY);

    private static <T, V> Map<T, V> mapLists(T[] arr1, V[] arr2){
        Map<T, V> map = new HashMap<>();
        for (int i = 0; i < GAMELIST.length; i++) {
            map.put(arr1[i], arr2[i]);
        }
        return map;
    }
}
