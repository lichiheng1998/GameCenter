package fall2018.csc207project.Models;

import java.util.HashMap;
import java.util.Map;

import fall2018.csc207project.Memorization.Views.MemoGameActivity;
import fall2018.csc207project.Memorization.Controllers.MemoStartingActivity;
import fall2018.csc207project.PushTheBox.View.BoxGameActivity;
import fall2018.csc207project.PushTheBox.Controllers.BoxStartingActivity;
import fall2018.csc207project.SlidingTile.Controllers.StartingActivity;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Views.GameActivity;

/**
 * The global configurations of the app. Those are shared data that shouldn't be changed.
 */
public class GlobalConfig {
    /**
     * Names of the games.
     */
    public static final String[] GAMELIST = {"SlidingTile", "Memorization Master", "Push The Box"};
    /**
     * Ids of the background resources of the games.
     */
    private static final Integer[] GAMEBG = {R.drawable.sliding_tile, R.drawable.memopick, R.drawable.global};

    /**
     * The Entries of the each games.
     */
    private static final Class[] GAMEENTRY = {
            StartingActivity.class, MemoStartingActivity.class, BoxStartingActivity.class
    };

    /**
     * The Entries of the each games.
     */
    private static final Class[] GAMESTART = {
            GameActivity.class, MemoGameActivity.class, BoxGameActivity.class
    };

    public static final Map<String, Integer> BGMAP = mapLists(GAMELIST, GAMEBG);

    public static final Map<String, Class> ENTRYMAP = mapLists(GAMELIST, GAMEENTRY);

    public static final Map<String, Class> GAMEMAP = mapLists(GAMELIST, GAMESTART);

    private static <T, V> Map<T, V> mapLists(T[] arr1, V[] arr2){
        Map<T, V> map = new HashMap<>();
        for (int i = 0; i < GAMELIST.length; i++) {
            map.put(arr1[i], arr2[i]);
        }
        return map;
    }
}
