package fall2018.csc207project.SlidingTile.Models;

import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.Stack;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager implements Serializable, Iterable<Tile>{
    /**
     * The board being managed.
     */
    public Board board;
    /**
     * the stack of all movements taken
     */
    private Stack<Integer> stackOfMovements;
    /**
     * The complexity of game managed
     */
    private int complexity;
    /**
     * the undo times left
     */
    private int undoTimes;
    /**
     * The counting of total steps moved.
     */
    private int totalMoveSteps;

    /**
     * The counting of total steps undid.
     */
    private int totalUndoSteps;

    /**
     * Manage a new shuffled board.
     * @param dim the dimension of board
     * @param undoTimes the undo times initially
     */
    public BoardManager(int dim, int undoTimes) {
        this.complexity = dim;
        this.undoTimes = undoTimes;
        stackOfMovements = new Stack<>();
        this.board = this.shuffledBoard(dim);
    }

    /**
     * Create the board with shuffling
     * @param dim the dimension(complexity) of the board
     */
    private Board shuffledBoard(int dim){
        if (dim == 3) {
            return (new SlidingTileGameShuffler()).shuffle(dim, 81);
        } else if (dim == 4) {
            return (new SlidingTileGameShuffler()).shuffle(dim, 256);
        } else {
            return (new SlidingTileGameShuffler()).shuffle(dim, 625);
        }
    }


    /**
     * The iterator.
     * @return the iterator
     */
    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return board.iterator();
    }

    /**
     * Return whether the tiles are in row-major order.
     * @return whether the tiles are in row-major order
     */
    public boolean puzzleSolved() {
        boolean solved = true;
        int count = 1;
        Iterator<Tile> newIterator = board.iterator();
        while(newIterator.hasNext() && solved){
            solved = newIterator.next().getId() == count++;
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    public boolean isValidTap(int position) {
        int row = position / board.NUM_COLS;
        int col = position % board.NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     * @param position the position
     */
    public void touchMove(int position) {
        int row = position / board.NUM_ROWS;
        int col = position % board.NUM_COLS;
        int blankId = board.numTiles();
        int rowBlank=0,colBlank=0;
        Iterator<Tile> newIterator = board.iterator();
        for (int i = 0; i < blankId; ++i){
            Tile temp = newIterator.next();
            if (temp.getId() == blankId) {
                rowBlank = i / board.NUM_COLS;
                colBlank = i % board.NUM_COLS;
            }
        }
        board.swapTiles(row,col,rowBlank,colBlank);
        totalMoveSteps++;
    }

    /**
     * Return the blank tile's position.
     * @return the blank tile's position
     */
    private int findBlankTilePosition() {
        int blankId = board.numTiles();
        Iterator<Tile> iterator = board.iterator();
        int blankPosition = 0;
        for (int i = 0; i < blankId; ++i){
            Tile temp = iterator.next();
            if (temp.getId() == blankId) {
                blankPosition = i;
            }
        }
        return blankPosition;
    }

    /**
     * Process the undo movement by the given steps.
     * @param steps the steps you want to go back.
     */
    private void processUndoMovement(int steps) {
        for (int i = 0; i < steps; i++) {
            touchMove(stackOfMovements.pop());
            totalMoveSteps--;
        }
        totalUndoSteps++;
        undoTimes--;
    }

    /**
     * Add last step into record
     */
    public void pushLastStep(){
        stackOfMovements.push(findBlankTilePosition());
    }

    /**
     * Return the total steps you did.
     * @return the total steps you did
     */
    public int getTotalMoveSteps() {
        return totalMoveSteps;
    }

    /**
     * Return the total steps you undo.
     * @return the total steps you moved
     */
    public int getTotalUndoSteps() {
        return totalUndoSteps;
    }

    /**
     * Get the complexity(dimension) of this board
     * @return the complexity(dimension)
     */
    public int getComplexity(){
        return complexity;
    }

    /**
     * Assign observer to board
     * @param o the observer of the board
     */
    public void subscribe(Observer o){
        board.addObserver(o);
    }

    /**
     * Undo the amount of steps if valid. Return whether undo is made
     * @param step the amount of steps to undo
     * @return whether undo is made
     */
    public boolean undo(int step){
        if (undoTimes == 0 || step > stackOfMovements.size()){
            return false;
        }
        processUndoMovement(step);
        return true;
    }
}
