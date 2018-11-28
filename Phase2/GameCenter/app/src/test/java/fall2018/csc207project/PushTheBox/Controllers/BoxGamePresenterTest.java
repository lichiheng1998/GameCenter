package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Observer;

import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.PushTheBox.View.MapView;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BoxGamePresenterTest {
//    private Context context = mock(Context.class);
//    private MapView view = mock(MapView.class);
////    private BoxGamePresenter presenter;
//    @Mock
//    private BoxGamePresenter presenter;
//
//
//    public void init(){
//        SharedPreferences shared = mock(SharedPreferences.class);
////        when(context.getSharedPreferences("GameData",Context.MODE_PRIVATE))
////                .thenReturn(shared);
//
//        doNothing().when(view).makeInvalidMovementText();
//        doNothing().when(view).levelComplete();
//        doNothing().when(view).display();
//        doNothing().when(view).makeToastNoUndoTimesLeftText();
//        doNothing().when(view).showNumberPicker();
//
//        presenter = spy(new BoxGamePresenter(view, context));
//        doNothing().when(presenter).setUpSaveManager(context);
//    }
//
//
//
//    @Test
//    public void setMapManager() {
//
//        MapManager mapManager = mock(MapManager.class);
//        doNothing().when(mapManager).subscribe(any(Observer.class));
//        doNothing().when(presenter).setMapManager(mapManager);
//        presenter.setMapManager(mapManager);
//        verify(mapManager, times(1)).subscribe(
//                any(Observer.class));
////        assertTrue(true);
//
//
//    }
//
//    @Test
//    public void arrowButtonClicked() {
//    }
//
//    @Test
//    public void onUndoButtonClicked() {
//    }
//
//    @Test
//    public void onUndoTextClicked() {
//    }
//
//    @Test
//    public void update() {
//    }
}