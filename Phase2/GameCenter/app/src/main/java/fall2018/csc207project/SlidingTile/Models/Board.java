package fall2018.csc207project.SlidingTile.Models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable,Iterable<Tile> {

    /**
     * The number of rows.
     */
     final int NUM_ROWS;

    /**
     * The number of rows.
     */
    final int NUM_COLS;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        int size = tiles.size();
        Iterator<Tile> iter = tiles.iterator();
        this.NUM_ROWS = (int) Math.sqrt(size);
        this.NUM_COLS = this.NUM_ROWS;
        this.tiles = new Tile[NUM_ROWS][NUM_COLS];
        for (int r = 0; r != NUM_ROWS; r++) {
            for (int c = 0; c != NUM_COLS; c++) {
                this.tiles[r][c] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    int numTiles() {
        return NUM_COLS*NUM_ROWS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
        int[] swapId = {0, row1 * NUM_COLS + col1, row2 * NUM_ROWS + col2};
        setChanged();
        notifyObservers(swapId);
    }

    /**
     * When the board is completed, all tiles will be displayed including the last blank tile.
     */
    public void completeBoard(){
        int[] arr = {1, NUM_COLS, NUM_ROWS};
        setChanged();
        notifyObservers(arr);
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }
    /**
     * Inner class that implements Iterable for Board
     * @return a Iterator<Tile>
    **/
    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new Iterator<Tile>() {
            int Index = 0;
            @Override
            /*
            * the implementation of hasNext for iterator*/
            public boolean hasNext() {
                return Index < NUM_ROWS*NUM_COLS;
            }

            /*
            * the implemenation of next method for iterator*/

            public Tile next() {
                if (hasNext()) {
                    int RowIndex = Index / NUM_COLS;
                    int ColIndex = Index % NUM_COLS;
                    ++Index;
                    return tiles[RowIndex][ColIndex];
                }
                else{
                    return null;
                }
            }
        };
    }
}
