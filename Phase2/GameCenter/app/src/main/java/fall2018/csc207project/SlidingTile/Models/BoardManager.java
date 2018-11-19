package fall2018.csc207project.SlidingTile.Models;

import android.content.Context;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.Stack;

import fall2018.csc207project.R;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager implements Serializable{

    /**
     * The board being managed.
     */
    private Board board;

    private Stack<Integer> stackOfMovements;

    private int complexity;

    private int undoTimes;

    private int totalSteps;

    private List<Tile> tileList;

    /**
     * Manage a new shuffled board.
     */
    public BoardManager(int dim, int undoTimes) {
        this.complexity = dim;
        this.undoTimes = undoTimes;
        totalSteps = 0;
        stackOfMovements = new Stack<>();
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = dim * dim;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        do {
            Collections.shuffle(tiles);
            this.tileList = tiles;
        } while (!isSolvable());
        this.board = new Board(tiles);
    }

    /**
     * Returns a int[] array of inversion information with
     * {total number of inversions, blank tile's position}
     *
     * @return a int[] array of inversion information with
     *
     * From: https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     */
    private int[] getInversionsInfo() {
        int inversions = 0;
        int blankPosition = 0;
        ArrayList<Integer> integerList = new ArrayList<>();
        for (int index = 0; index < tileList.size(); index++) {
            if (tileList.get(index).getId() != Math.pow(complexity, 2)) {
                integerList.add(tileList.get(index).getId());
            } else {
                blankPosition = index;
            }
        }
        for (int i = 0; i < integerList.size(); i++) {
            for (int j = i + 1; j < integerList.size(); j++) {
                if (integerList.get(i) > integerList.get(j)) {
                    inversions++;
                }
            }
        }
        return new int[] {inversions, blankPosition};
    }

    /**
     * Return whether the given 3x3, 4x4 or 5x5
     * sliding tile board is solvable.
     *
     * @return whether the given 3x3, 4x4 or 5x5
     * sliding tile board is solvable
     *
     * From: https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     */
    private boolean isSolvable() {
        int[] array = getInversionsInfo();
        int inversions = array[0];
        int row = complexity;
        int rowBlankTile = array[1] / row;
        return ((row % 2 == 1) && (inversions % 2 == 0))
                || ((row % 2 == 0) && ((rowBlankTile % 2 == 0) == !(inversions % 2 == 0)));
    }

    /**
     * Return whether the tiles are in row-major order.
     *
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
     *
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
     *
     * @param position the position
     */
    public void touchMove(int position) {
        this.totalSteps += 1;
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
    }

    /**
     * Return the blank tile's position.
     *
     * @return the blank tile's position
     */
    private int findBlinkTilePosition() {
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
     *
     * @param steps the steps you want to go back.
     */
    private void processUndoMovement(int steps) {
        for (int i = 0; i < steps; i++) {
            touchMove(stackOfMovements.pop());
        }
        totalSteps++;
        undoTimes--;
    }

    public void pushLastStep(){
        stackOfMovements.push(findBlinkTilePosition());
    }
    /**
     * Return the total steps you did in each undo.
     *
     * @return the total steps you did in each undo
     */
    public int getTotalSteps() {
        return totalSteps;
    }

    public int getComplexity(){
        return complexity;
    }

    public void subscribe(Observer o){
        board.addObserver(o);
    }

    public boolean undo(int step){
        if (undoTimes == 0 || step > stackOfMovements.size()){
            return false;
        }
        processUndoMovement(step);
        return true;
    }

    public List<Button> getButtonList(Context context){
        List<Button> tileButtons = new ArrayList<>();
        for (int row = 0; row != this.complexity; row++) {
            for (int col = 0; col != this.complexity; col++) {
                Button tmp = new Button(context);
                Tile tmpTile = board.getTile(row, col);
                if (tmpTile.getId() != board.numTiles()) {
                    tmp.setTextSize(40);
                    tmp.setText(Integer.toString(tmpTile.getId()));
                }
                tmp.setBackgroundResource(R.drawable.tile);
                tileButtons.add(tmp);
            }
        }
        return tileButtons;
    }
}
