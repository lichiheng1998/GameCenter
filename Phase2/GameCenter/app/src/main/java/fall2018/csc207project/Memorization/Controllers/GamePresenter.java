package fall2018.csc207project.Memorization.Controllers;

import android.content.Context;

import fall2018.csc207project.Memorization.Models.MemoManager;

/**
 * Class represents the game logic present.
 */
public interface GamePresenter {
    /**
     * Process the tap input from the user.
     * @param context
     * @param position the position of the button being tap.
     */
    void onTapOnTile(Context context, int position);
    /**
     * Initialize the MemoManager in the presenter.
     * @param memoManager the manager to be set up.
     */
    void setMemoManager(MemoManager memoManager);
    /**
     * Start a new game cycle.
     */
    void startCycle();
}
