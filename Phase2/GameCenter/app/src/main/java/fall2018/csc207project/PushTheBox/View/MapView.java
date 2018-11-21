package fall2018.csc207project.PushTheBox.View;

import fall2018.csc207project.PushTheBox.Models.MapManager;

public interface MapView {
    void showNumberPicker();
    void makeToastNoUndoTimesLeftText();
    void display();
    void updateMap(MapManager mapManager);
}
