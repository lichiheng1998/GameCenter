package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc207project.Models.SaveManager;
import fall2018.csc207project.Models.SaveSlot;
import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.PushTheBox.View.MapView;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BoxGamePresenterTest {
    private Context context = mock(Context.class);
    private BoxGamePresenter presenter;
    private MapManager mapManager = mock(MapManager.class);
    private MapView view = mock(MapView.class);

    @Before
    public void init(){
        SharedPreferences shared = mock(SharedPreferences.class);
        when(context.getSharedPreferences("GameData", Context.MODE_PRIVATE)).thenReturn(shared);
        when(shared.getString("currentUser", null)).thenReturn("n");
        when(shared.getString("currentGame", null)).thenReturn("pushTheBox");
        FileOutputStream out = mock(FileOutputStream.class);
        try {
            when(context.openFileOutput("Saves.ser", Context.MODE_PRIVATE)).thenReturn(out);
        }catch(IOException e){ }//do nothing
        presenter = spy(new BoxGamePresenter(view, context));
    }

    @Test
    public void testSetMapManager(){
        doNothing().when(mapManager).subscribe(presenter);

        presenter.setMapManager(mapManager);
        verify(mapManager, times(1)).subscribe(presenter);
    }


////    @Test
////    public void testArrowButtonClicked(){
////        doNothing().when(view).makeInvalidMovementText();
////
////        MapManager mapManager = mock(MapManager.class);
////        when(mapManager.isValidMovement(1)).thenReturn(false);
////        presenter.setMapManager(mapManager);
////        presenter.arrowButtonClicked(context, "right");
////        verify(view, times(1)).makeInvalidMovementText();
////    }

    @Test
    public void testOnUndoTextClicked() {
        doNothing().when(view).showNumberPicker();
        presenter.onUndoTextClicked();
        verify(view, times(1)).showNumberPicker();

    }

    @Test
    public void onUndoButtonClicked() {
        when(mapManager.canProcessUndo(1)).thenReturn(false);
        doNothing().when(view).makeInvalidMovementText();
        presenter.setMapManager(mapManager);
        presenter.onUndoButtonClicked(1);
        verify(view, times(1)).makeToastNoUndoTimesLeftText();
    }


    @Test
    public void update() {
        doNothing().when(view).updateMap(mapManager);
        presenter.setMapManager(mapManager);
        presenter.update(mock(Observable.class), new Object());
        verify(view, times(1)).updateMap(mapManager);
    }
}