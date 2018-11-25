package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import fall2018.csc207project.SlidingTile.Models.BoardManager;

public class MovementController {

    private BoardManager boardManager = null;

    public MovementController() {
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

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
