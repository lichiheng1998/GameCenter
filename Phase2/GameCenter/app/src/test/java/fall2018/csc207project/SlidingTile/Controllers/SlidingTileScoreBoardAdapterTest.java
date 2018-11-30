package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.SlidingTile.Models.Tile;
import fall2018.csc207project.SlidingTile.Models.TileScore;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SlidingTileScoreBoardAdapterTest {
    private Context context = mock(Context.class);
    private SlidingTileScoreBoardAdapter sba;

    @Before
    public void setup(){
        List<TileScore> scores = new ArrayList<>();
        scores.add(new TileScore(3,3,10));
        scores.add(new TileScore(3,3,15));
        scores.add(new TileScore(3,3,30));
        scores.get(0).user = "n";
        scores.get(0).value = 1;
        sba = new SlidingTileScoreBoardAdapter(scores, context);
    }

    @Test
    public void testGetCount(){
        assertEquals("wrong number of items", 3, sba.getCount());
    }

    @Test
    public void testFirstItem(){
        String[] result = (String[]) sba.getItem(0);
        assertEquals("got wrong item", "n", result[0]);
        assertEquals("got wrong item", "1", result[1]);
    }


    @Test
    public void testGetItemId(){
        long id = 1;
        assertEquals("got wrong id", 1 ,sba.getItemId(1));
    }
}