package fall2018.csc207project.PushTheBox.Models;

import java.io.Serializable;

import fall2018.csc207project.R;

/**
 * Tile with a background image, and combination of tiles make a map.
 */
public class BgTile implements Serializable {

    /**
     * The image id of this bgTile.
     */
    private final int BACKGROUND;

    /**
     * Whether if this tile is a destination point.
     */
    private final Boolean WINNABLE;

    /**
     * Whether if this tile is a wall.
     */
    private final Boolean STANDABLE;

    public BgTile(String bgType){
        if(bgType.equals("Wall")){
            BACKGROUND = R.drawable.wall;
            WINNABLE = false;
            STANDABLE = false;
        }else if (bgType.equals("Floor")){
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
    public Boolean isStandable(){
        return STANDABLE;
    }

    /**
     * Return whether if this tile is a destination point of a box.
     * @return whether if this tile is a destinaiton point
     */
    public Boolean isWinnable(){
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
