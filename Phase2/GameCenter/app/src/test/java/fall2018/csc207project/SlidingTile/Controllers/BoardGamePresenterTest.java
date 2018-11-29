package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;

import fall2018.csc207project.SlidingTile.Models.BoardManager;
import fall2018.csc207project.SlidingTile.Views.BoardGameView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BoardGamePresenterTest {
    private Context context = mock(Context.class);
    private BoardGamePresenter presenter;
    private BoardManager manager = mock(BoardManager.class);
    private BoardGameView view = mock(BoardGameView.class);

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
        presenter = spy(new BoardGamePresenter(view, context));
    }


    @Test
    public void onUndoButtonClicked() {
        presenter.setBoardManager(manager);
        doNothing().when(view).makeToastNoUndoTimesLeftText();
        when(manager.undo(5)).thenReturn(true);
        presenter.onUndoButtonClicked(5);
        verify(view, times(0)).makeToastNoUndoTimesLeftText();
        presenter.onUndoButtonClicked(3);
        when(manager.undo(3)).thenReturn(false);
        verify(view, times(1)).makeToastNoUndoTimesLeftText();
    }

    @Test
    public void onUndoTextClicked() {
        doNothing().when(view).showNumberPicker();
        presenter.onUndoTextClicked();
        verify(view, times(1)).showNumberPicker();
    }

//    @Test
//    public void onTapOnTile() {
//        presenter.setBoardManager(manager);
//        when(manager.isValidTap(1)).thenReturn(true);
//        doNothing().when(manager).pushLastStep();
//        doNothing().when(manager).touchMove(1);
//        when(manager.puzzleSolved()).thenReturn(false);
//
//        presenter.onTapOnTile(context, 1);
//        verify(manager, times(1)).touchMove(1);
//    }

    @Test
    public void setBoardManager() {
        doNothing().when(manager).subscribe(presenter);
        presenter.setBoardManager(manager);
        verify(manager, times(1)).subscribe(presenter);
    }

    @Test
    public void update() {
        int[] id = {0,1};
        doNothing().when(view).swapButtons(0,1);
        presenter.update(mock(Observable.class), id);
        verify(view, times(1)).swapButtons(0,1);
    }

//    @Test
//    public void getButtonList() {
//    }
}