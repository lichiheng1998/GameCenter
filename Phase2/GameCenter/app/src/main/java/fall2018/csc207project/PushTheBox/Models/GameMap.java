package fall2018.csc207project.PushTheBox.Models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * The map where a person will push the boxes to destinations.
 */
public class GameMap implements Serializable {

    /**
     * The list of background tiles.
     */
    private List<BgTile> bgElements = new ArrayList<>();

    /**
     * The total number of rows of the map grid.
     */
    final int NUM_ROW;

    /**
     * The total number of columns of the map grid.
     */
    final int NUM_COL;

    /**
     * A new map in row*col size.
     * @param numRow the total number of rows
     * @param numCol the total number of columns
     * @param bgElements the list of elements that are the background tiles
     */
    public GameMap(int numCol, int numRow, List<BgTile> bgElements){
        this.NUM_ROW = numRow;
        this.NUM_COL = numCol;
        this.bgElements = bgElements;
    }

    /**
     * Returns whether the tile with given tile ID is a wall
     * @param tileId the ID of the tile to be evaluated
     * @return whether the tile with tile's ID is a wall
     */
    public Boolean tileIsWall(int tileId){
        if(!bgElements.get(tileId).isStandable()){
            return true;
        }else{
            return false;
        }
    }


    public Boolean tileAtDestination(int tileId){
        if (bgElements.get(tileId).isWinnable()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the background tile with given tile ID
     * @param tileId the ID of the tile looking for
     * @return the bgTile with given tile ID
     */
    public BgTile getTileAtId(int tileId){
        return bgElements.get(tileId);
    }

    /**
     * Returns the number of columns of the map.
     * @return the total number of columns
     */
    public int getNumCol(){
        return NUM_COL;
    }

    /**
     * Returns the number of rows of the map.
     * @return the total number of rows
     */
    public int getNumRow(){
        return NUM_ROW;
    }
}
