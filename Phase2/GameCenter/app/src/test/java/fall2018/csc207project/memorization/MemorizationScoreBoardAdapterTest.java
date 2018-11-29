
package fall2018.csc207project.memorization;

import android.content.Context;

import fall2018.csc207project.Memorization.Controllers.MemoScoreBoardAdapter;
import fall2018.csc207project.Memorization.Models.MemoScore;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class MemorizationScoreBoardAdapterTest {
    private Context context  = mock(Context.class);
    private MemoScoreBoardAdapter memoScoreAdapter;

    List<MemoScore> scores = new ArrayList<>();

    @Before
    public void ini(){
        MemoScore firstScore = new MemoScore(1,true,0);
        firstScore.user = "placeHolder";
        firstScore.value = 666;
        scores.add(firstScore);
        scores.add(new MemoScore(1,true,0));
        scores.add(new MemoScore(2,true,15));
        scores.add(new MemoScore(3,true,30));
        scores.add(new MemoScore(4,true,45));
        memoScoreAdapter = new MemoScoreBoardAdapter(scores, context);
    }

    @Test
    public void testGetCount(){
        assertEquals( 5, memoScoreAdapter.getCount());
    }

    @Test
    public void testGetItem(){
        int position =  1;
        long pos=  memoScoreAdapter.getItemId(position);
        assertEquals(pos, position);
    }

    @Test
    public void testGetItemObject(){
        String [] obj = (String[]) memoScoreAdapter.getItem(0);
        assertEquals("placeHolder", obj[0]);
        assertEquals("666", obj[1]);
    }
}
