package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.widget.Button;
import java.util.List;
import java.util.Observer;
import fall2018.csc207project.SlidingTile.Models.BoardManager;

/**
 * The interface GamePresenter extends Observer which Presenter the Current Game.
 */
public interface GamePresenter extends Observer{

    /**
     * Set the Undo Button by the given steps want to be undo.
     *
     * @param step steps want to be undo
     */
    void onUndoButtonClicked(int step);

    /**
     * Set the Undo Text.
     */
    void onUndoTextClicked();

    /**
     * Deal with the the position of the Tile that the user tapped on.
     *
     * @param context the context of this app
     * @param position the position of the Tile that the user tapped on
     */
    void onTapOnTile(Context context, int position);

    /**
     * Set the BoardManager by given the BoardManager that manages the Board.
     *
     * @param boardManager the BoardManager that manages the Board
     */
    void setBoardManager(BoardManager boardManager);

    /**
     * Get the List of Tile Buttons for this game.
     *
     * @param context the context of this app
     * @return the List of Tile Buttons for this game
     */
    List<Button> getButtonList(final Context context);
}
