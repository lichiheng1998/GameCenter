package fall2018.csc207project.PushTheBox.Models;

import java.io.Serializable;

import fall2018.csc207project.R;

/**
 * Tile with a background image, and combination of tiles make a map.
 * Excluded from tests because it's a model class
 */
public class BgTile implements Serializable {
    /**
     * The strings which should be given to constructor to verify the type of bgTile.
     */
    private static final String WALL_TYPE =  "Wall";
    private static final String FLOOR_TYPE =  "Floor";

    /**
     * The image id of this bgTile.
     */
    private final int BACKGROUND;

    /**
     * Whether if this tile is a destination point.
     */
    private final boolean WINNABLE;

    /**
     * Whether if this tile is a wall.
     */
    private final boolean STAND_ABLE;

    /**
     * Construct a new BgTile by given a String.
     *
     * @param bgType the String tells the bg's type
     */
    public BgTile(String bgType){
        switch (bgType) {
            case WALL_TYPE:
                BACKGROUND = R.drawable.wall;
                WINNABLE = false;
                STAND_ABLE = false;
                break;
            case FLOOR_TYPE:
                BACKGROUND = R.drawable.floor;
                WINNABLE = false;
                STAND_ABLE = true;
                break;
            default:
                BACKGROUND = R.drawable.destination;
                WINNABLE = true;
                STAND_ABLE = true;
                break;
        }
    }

    /**
     * Return whether if the person or the boxes can stand on this tile.
     *
     * @return whether if this tile that can be stand
     */
    public boolean canBeStand(){
        return STAND_ABLE;
    }

    /**
     * Return whether if this tile is a destination point of a box.
     *
     * @return whether if this tile is a destination point
     */
    public boolean isWinnable(){
        return WINNABLE;
    }

    /**
     * Return the background image id of this tile.
     *
     * @return the background image id
     */
    public int getBackground(){
        return BACKGROUND;
    }
}
