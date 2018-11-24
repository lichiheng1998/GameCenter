package fall2018.csc207project.PushTheBox.Models;

import java.io.Serializable;

import fall2018.csc207project.R;

/**
 * Tile with a background image, and combination of tiles make a map.
 */
public class BgTile implements Serializable {
    public static final String WALLTYPE =  "Wall";
    public static final String FLOORTYPE =  "Floor";
    public static final String DESTTYPE =  "Destination";

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
    private final boolean STANDABLE;

    public BgTile(String bgType){
        if(bgType.equals(WALLTYPE)){
            BACKGROUND = R.drawable.wall;
            WINNABLE = false;
            STANDABLE = false;
        }else if (bgType.equals(FLOORTYPE)){
            BACKGROUND = R.drawable.floor;
            WINNABLE = false;
            STANDABLE = true;
        }else{
            BACKGROUND = R.drawable.destination;
            WINNABLE = true;
            STANDABLE = true;
        }
    }

    /**
     * Return whether if the person or the boxes can stand on this tile.
     * @return whether if this tile is standable
     */
    public boolean isStandable(){
        return STANDABLE;
    }

    /**
     * Return whether if this tile is a destination point of a box.
     * @return whether if this tile is a destinaiton point
     */
    public boolean isWinnable(){
        return WINNABLE;
    }

    /**
     * Return the background image id of this tile.
     * @return the background image id
     */
    public int getBackground(){
        return BACKGROUND;
    }
}
