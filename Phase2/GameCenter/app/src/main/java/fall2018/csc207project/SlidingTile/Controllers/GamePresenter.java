package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.widget.Button;

import java.util.List;
import java.util.Observer;

import fall2018.csc207project.SlidingTile.Models.BoardManager;

public interface GamePresenter extends Observer{
    void onUndoButtonClicked(int step);
    void onUndoTextClicked();
    void onTapOnTile(Context context, int position);
    void setBoardManager(BoardManager boardManager);
    List<Button> getButtonList(final Context context);
}
