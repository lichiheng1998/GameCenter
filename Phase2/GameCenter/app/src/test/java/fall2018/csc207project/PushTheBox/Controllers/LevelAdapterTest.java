package fall2018.csc207project.PushTheBox.Controllers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LevelAdapterTest {

    private LevelAdapter levelAdapter;
    private Button button = mock(Button.class);

    private void init(){
        List<Button> levelButtons;
        levelButtons = new ArrayList<>();
        levelButtons.add(button);
        levelAdapter = new LevelAdapter(levelButtons);
    }

    @Test
    @Before
    public void testGetCount() {
        init();
        assertEquals("wrong number of buttons",
                1, levelAdapter.getCount());
    }

    @Test
    public void getItem() {
        assertEquals("got the wrong item",
                button, levelAdapter.getItem(0));
    }

    @Test
    public void getItemId() {
        assertEquals("got wrong id", 0, levelAdapter.getItemId(0));
    }

    @Test
    public void getView() {
        View view  = button;
        ViewGroup parent = mock(ViewGroup.class);
        assertEquals("got the wrong view",
                button, levelAdapter.getView(0, view, parent));
    }
}