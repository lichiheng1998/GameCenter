package fall2018.csc207project.SlidingTile.Controllers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CustomAdapterTest {
    private CustomAdapter adapter;
    private Button button = mock(Button.class);

    private void init(){
        List<Button> buttons;
        buttons = new ArrayList<>();
        buttons.add(button);
        adapter = new CustomAdapter(buttons, 1, 1);
    }

    @Test
    @Before
    public void testGetCount() {
        init();
        assertEquals("wrong number of buttons",
                1, adapter.getCount());
    }

    @Test
    public void getItem() {
        assertEquals("got the wrong item",
                button, adapter.getItem(0));
    }

    @Test
    public void getItemId() {
        assertEquals("got wrong id", 0, adapter.getItemId(0));
    }

    @Test
    public void getView() {
        View view  = button;
        ViewGroup parent = mock(ViewGroup.class);
        assertEquals("got the wrong view",
                button, adapter.getView(0, view, parent));
    }
}