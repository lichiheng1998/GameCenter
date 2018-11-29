package fall2018.csc207project.Models;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SaveManagerTest {
    @Mock
    private Context context;
    @Mock
    private SaveSlot saveSlot;
    @Mock
    private SaveDataStream dataStream;

    private SaveManager manager;

    @Before
    public void setUp(){
        Map<String, Map<String, SaveSlot>> map = new HashMap<>();
        MockitoAnnotations.initMocks(this);
        manager = new SaveManager(dataStream, "adam", "testGame");
        when(dataStream.getSaves(any(HashMap.class), eq(context))).thenReturn(map);
    }

    @Test
    public void shouldInitAndSave(){
        manager.saveToFile(saveSlot, context);
        verify(dataStream).saveSaves(any(Object.class), eq(context));
    }

    @Test
    public void shouldReadFromFile(){
        manager.readFromFile(context);
        verify(dataStream).getSaves(any(Object.class), eq(context));
    }
}
