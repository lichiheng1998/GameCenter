package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.PushTheBox.Models.BoxScore;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BoxScoreAdapterTest {
    private Context context  = mock(Context.class);
    private BoxScoreAdapter bsa;

    @Before
    public void ini(){
        List<BoxScore> scores = new ArrayList<>();
        scores.add(new BoxScore(1,3,10));
        scores.add(new BoxScore(2,3,15));
        scores.add(new BoxScore(3,3,30));
        bsa = new BoxScoreAdapter(context, scores);
    }

    @Test
    public void testGetCount(){
        assertEquals("wrong number of scores", 3, bsa.getCount());
    }

    @Test
    public void testFirstItem(){
        BoxScore bx1 = (BoxScore) bsa.getItem(0);
        assertTrue("got wrong item",
                (bx1.level == 1 && bx1.undoSteps == 3 && bx1.moveSteps == 10));
    }

    @Test
    public void testSecondItem(){
        BoxScore bx2 = (BoxScore) bsa.getItem(1);
        assertTrue("got wrong item",
                (bx2.level == 2 && bx2.undoSteps == 3 && bx2.moveSteps == 15));
    }

    @Test
    public void testThreeItem(){
        BoxScore bx3 = (BoxScore) bsa.getItem(2);
        assertTrue("got wrong item",
                (bx3.level == 3 && bx3.undoSteps == 3 && bx3.moveSteps == 30));
    }

    @Test
    public void testGetItemId(){
        long id = 1;
        assertEquals("got wrong id", 1 ,bsa.getItemId(1));
    }
}