package fall2018.csc207project.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameCenterListViewAdapterTest {
    private GameCenterListViewAdapter adapter;
    @Mock
    private Context context;

    @Mock
    private List<String> gameList;

    @Mock
    private Map<String, Integer> BGMAP;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        adapter = new GameCenterListViewAdapter(context, gameList, BGMAP);
        when(gameList.get(anyInt())).thenReturn("testGame");
        when(BGMAP.get(anyString())).thenReturn(5);
        when(adapter.getContext()).thenReturn(context);
//        when(LayoutInflater.from(context).inflate(anyInt(), any(ViewGroup.class), eq(false))).thenReturn()

    }

    @Test
    public void shouldSetView(){
        View view = adapter.getView(3, null, mock(ViewGroup.class));
        GameCenterListViewAdapter.MyViewHolder holder = (GameCenterListViewAdapter.MyViewHolder)view.getTag();
        assertEquals(holder.textView.getText(), "testGame");
    }

}
