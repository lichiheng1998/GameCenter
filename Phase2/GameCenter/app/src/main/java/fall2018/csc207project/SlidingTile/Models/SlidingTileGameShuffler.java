package fall2018.csc207project.SlidingTile.Models;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.List;

/**
 * A final class SlidingTileGameShuffler with implements Shuffler.
 */
public final class SlidingTileGameShuffler implements Shuffler{

    /**
     * process multiple shuffles on a given board
     * with the dimension of the board (which is in {3, 4, 5})
     * and number of time want to shuffle.
     *
     * @param dim dimension of the board (which is in {3, 4, 5})
     * @param numTimesToShuffle the number of times want to shuffle the board
     * @return a shuffled board
     */
    @Override
    public Board shuffle(int dim, int numTimesToShuffle) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = dim * dim;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        Board boardToShuffle = new Board(tiles);
        for (int i = 0; i < numTimesToShuffle; i++) {
            processSingleShuffle(boardToShuffle);
        }
        return boardToShuffle;
    }

    /**
     * process a Single Shuffle on a given board.
     *
     * @param boardToShuffle the board that need to process a single shuffle
     */
    @Override
    public void processSingleShuffle(Board boardToShuffle) {
        ArrayList<int[]> validDirections = getValidDirections(boardToShuffle);
        Random randomGenerator = new Random();
        int chosenIndex = randomGenerator.nextInt(validDirections.size());
        int[] chosenCoordinate = validDirections.get(chosenIndex);
        int[] blankTileCoordinate = getBlankTileCoordinate(boardToShuffle);
        boardToShuffle.swapTiles(blankTileCoordinate[0], blankTileCoordinate[1],
                                      chosenCoordinate[0], chosenCoordinate[1]);
    }

    /**
     * Get the valid directions in an ArrayList<int[]> around the blank tile
     * by given a Board of tiles.
     *
     * Post-condition: 2 less equal than ArrayList<int[]> length less equal than 4
     *
     * @param board the board will get processed
     * @return the valid directions in an ArrayList<int[]>
     */
    private ArrayList<int[]> getValidDirections(Board board) {
        int[] blankTileCoordinate = getBlankTileCoordinate(board);
        int blankRow = blankTileCoordinate[0];
        int blankColumn = blankTileCoordinate[1];
        ArrayList<int[]> validDirections = new ArrayList<>();
        if (blankRow - 1 >= 0) {
            validDirections.add(new int[]{blankRow - 1, blankColumn});
        }
        if (blankRow + 1 < board.NUM_ROWS) {
            validDirections.add(new int[]{blankRow + 1, blankColumn});
        }
        if (blankColumn - 1 >= 0) {
            validDirections.add(new int[]{blankRow, blankColumn - 1});
        }
        if (blankColumn + 1 < board.NUM_COLS) {
            validDirections.add(new int[]{blankRow, blankColumn + 1});
        }
        return validDirections;
    }

    /**
     * Get the coordinate of the Blank Tile in Array of integer {BlankRow, BlankColumn}
     * by given a Board of tiles.
     *
     * @param board the board will get processed
     * @return the coordinate of the Blank Tile
     *         in Array of integer {row, column}
     */
    private int[] getBlankTileCoordinate(Board board) {
        int blankId = board.numTiles();
        Iterator<Tile> iterator = board.iterator();
        int blankPosition = 0;
        for (int i = 0; i < blankId; ++i){
            Tile temp = iterator.next();
            if (temp.getId() == blankId) {
                blankPosition = i;
            }
        }
        return new int[] {blankPosition/board.NUM_ROWS,
                          blankPosition%board.NUM_COLS};
    }
}
