package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;

import java.util.Observer;

import fall2018.csc207project.PushTheBox.Models.MapManager;

public interface GamePresenter extends Observer{
    void setMapManager(MapManager mapManager);
    void arrowButtonClicked(Context context, String direction);
    void onUndoButtonClicked(int step);
    void onUndoTextClicked();
}
