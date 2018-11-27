package fall2018.csc207project.memorization;

import org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.Memorization.Models.MemoGameCalculator;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Models.MemoScore;
import fall2018.csc207project.Memorization.Models.MemoTile;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MemorizationModelTest {
    private MemoTile memo = new MemoTile(0, 1);
    private MemoScore testScoreHardMode = new MemoScore(4, true, 10);
    private MemoScore testScore = new MemoScore(4, false, 10);

    @Test
    @Before
    public void MemoTileConstructorTest(){
        MemoTile newMemo = new MemoTile(0, 0);
        assertEquals(0, memo.getId());
        assertEquals(1, memo.status);
        assertThat(newMemo, is(memo));
        assertThat(newMemo, not(new MemoTile(3, 0)));
    }

    @Test
    public void MemoScoreTest(){
        assertThat(testScore, not(testScoreHardMode));
        assertFalse(testScore.level);
        assertTrue(testScoreHardMode.level);
        assertEquals(10, testScore.scoreTotal);
        assertEquals(4, testScoreHardMode.difficulty);
    }

    @Test
    public void MemoGameCalculatorTest(){
        MemoGameCalculator cal = new MemoGameCalculator();
        int normalScore = cal.calculate(testScore);
        int hardScore = cal.calculate(testScoreHardMode);
        assertThat(hardScore, not(normalScore));
        assertEquals(normalScore, 150);
        assertEquals(hardScore, 250);
    }

    @Test
    public void MemoManagerConstructorTest(){
        //MemoManger test basic matching of fields in constructors
        MemoManager managerT = new MemoManager(3, 4, true);
        assertEquals(2, managerT.getCurComplexity());
        assertTrue(managerT.isLevel());
        assertEquals(1, managerT.getSequenceOrder().size());
    }

    @Test
    @After
    public void MemoManagerUpdateTilesTest(){
        MemoManager manager = new MemoManager(5, 5, false);
        //test update tiles
        for(int i = 0; i < 10; i++){
            manager.updateActiveTiles();
        }

        assertEquals(12, manager.getCurComplexity());
        int newSize = manager.getSequenceOrder().size();
        List memoList = manager.getSequenceOrder();
        assertEquals(11, newSize);

        //test every element in sequenceOrder is MemoTile
        //test if ids of tile are within bound
        for(int i = 0; i < newSize; i++){
            assertThat(memoList.get(i), instanceOf(MemoTile.class));
            MemoTile ti = (MemoTile) memoList.get(i);
            assertTrue(ti.getId() < manager.getSize());
        }
    }

}
