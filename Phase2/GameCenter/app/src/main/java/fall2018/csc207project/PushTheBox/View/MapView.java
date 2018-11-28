package fall2018.csc207project.PushTheBox.View;

import fall2018.csc207project.PushTheBox.Models.MapManager;

public interface MapView {
    void showNumberPicker();
    void makeToastNoUndoTimesLeftText();
    void makeInvalidMovementText();
    void display();
    void updateMap(MapManager mapManager);
    void levelComplete();
}
