package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import fall2018.csc207project.SlidingTile.Models.BoardManager;

/**
 * The MovementController that process each movement that create by the current user.
 */
public class MovementController {

    /**
     * The BoardManager that manages the Board.
     */
    private BoardManager boardManager = null;

    /**
     * construct a new MovementController
     */
    MovementController() {}

    /**
     * Set up the BoardManager by given a BoardManager
     *
     * @param boardManager the BoardManager that manages the Map
     */
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Process tap movement and return whether the game is completed or not.
     *
     * @param position the direction going to move
     * @return whether the game is completed or not
     */
    public boolean processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {
            boardManager.pushLastStep();
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
            Log.e("debug", "processTapMovement: enter the if statement");
            return true;
        }
        Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        Log.e("debug", "processTapMovement:not enter the if statement");
        return true;
    }
}
