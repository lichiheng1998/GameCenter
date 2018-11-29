package fall2018.csc207project.Controllers;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.Serializable;
import java.util.Date;

import fall2018.csc207project.Models.Save;
import fall2018.csc207project.Models.SaveManager;
import fall2018.csc207project.Models.SaveSlot;
import fall2018.csc207project.Views.ManageSaveView;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ManageSavePresenterTest {
    @Mock
    private SaveManager manager;
    @Mock
    private Context context;
    @Mock
    private ManageSaveView view;
    @Mock
    private SaveSlot saveSlot;

    private ManageSavePresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(manager.readFromFile(context)).thenReturn(saveSlot);
        presenter = new ManageSavePresenterImpl(view, manager, context);
    }

    @Test
    public void shouldSave(){
        Date date = mock(Date.class);
        Save save = new Save(date, mock(Object.class));
        when(saveSlot.readFromAutoSave()).thenReturn(save);
        when(date.toString()).thenReturn("test");
        presenter.onSaveButtonClicked(2, context);
        verify(manager).saveToFile(saveSlot, context);
        verify(view).showInfo(eq(2), anyString());
    }

    @Test
    public void shouldNotSave(){
        when(saveSlot.readFromAutoSave()).thenReturn(null);
        presenter.onSaveButtonClicked(2, context);
        verify(view).makeNotStartedText();
    }

    @Test
    public void shouldLoadSave(){
        Date date = mock(Date.class);
        Save save = new Save(date, mock(Serializable.class));
        when(saveSlot.readFromAutoSave()).thenReturn(save);
        when(saveSlot.readFromSlot(2)).thenReturn(save);
        presenter.onLoadButtonClicked(2);
        verify(view).launchGame(any(Serializable.class));
        presenter.onLoadButtonClicked(3);
        verify(view, times(2)).launchGame(any(Serializable.class));
    }

    @Test
    public void shouldNotLoadSave(){
        when(saveSlot.readFromAutoSave()).thenReturn(null);
        presenter.onLoadButtonClicked(2);
        verify(view, times(0)).launchGame(any(Serializable.class));
        presenter.onLoadButtonClicked(3);
        verify(view, times(0)).launchGame(any(Serializable.class));
    }

    @Test
    public void shouldInitView(){
        Date date = mock(Date.class);
        Save save = new Save(date, mock(Object.class));
        when(saveSlot.readFromAutoSave()).thenReturn(save);
        when(saveSlot.readFromSlot(anyInt())).thenReturn(save);
        when(date.toString()).thenReturn("test");
        presenter.initView();
        verify(view, times(4)).showInfo(anyInt(), eq("test"));
    }
}
