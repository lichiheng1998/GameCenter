package fall2018.csc207project.Models;

import java.util.HashMap;
import java.util.Map;

import fall2018.csc207project.Memorization.Views.MemoGameActivity;
import fall2018.csc207project.Memorization.Controllers.MemoStartingActivity;
import fall2018.csc207project.PushTheBox.View.BoxGameActivity;
import fall2018.csc207project.PushTheBox.View.BoxStartingActivity;
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
    public static final String[] GAME_LIST = {"SlidingTile", "Memorization Master", "Push The Box"};

    /**
     * Ids of the background resources of the games.
     */
    private static final Integer[] GAME_BG
            = {R.drawable.sliding_tile, R.drawable.memopick, R.drawable.global};

    /**
     * The entries of the each games.
     */
    private static final Class[] GAME_ENTRY = {
            StartingActivity.class, MemoStartingActivity.class, BoxStartingActivity.class
    };

    /**
     * The starts of the each games.
     */
    private static final Class[] GAME_START = {
            GameActivity.class, MemoGameActivity.class, BoxGameActivity.class
    };

    /**
     * The background for the Games.
     */
    public static final Map<String, Integer> BG_MAP = mapLists(GAME_LIST, GAME_BG);

    /**
     * The entry for the Games.
     */
    public static final Map<String, Class> ENTRY_MAP = mapLists(GAME_LIST, GAME_ENTRY);

    /**
     * The Games' Maps
     */
    public static final Map<String, Class> GAME_MAP = mapLists(GAME_LIST, GAME_START);

    /**
     * Returns a Map<T, V>.
     *
     * @param arr1 the array of T
     * @param arr2 the array of V
     * @param <T> the class T
     * @param <V> the class V
     * @return a Map<T, V>
     */
    private static <T, V> Map<T, V> mapLists(T[] arr1, V[] arr2){
        Map<T, V> map = new HashMap<>();
        for (int i = 0; i < GAME_LIST.length; i++) {
            map.put(arr1[i], arr2[i]);
        }
        return map;
    }
}
