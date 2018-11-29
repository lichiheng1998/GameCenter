package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import fall2018.csc207project.Models.ScoreManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoxGameScoreManagerTest {
    private Context context = mock(Context.class);
    private BoxGameScoreManager manager;
    private ArrayList<BoxScore> scores = new ArrayList<>();

    @Before
    public void init(){
        scores.add(new BoxScore(1,1,20));
        scores.add(new BoxScore(1,1,10));
        scores.add(new BoxScore(1,1,30));
        scores.add(new BoxScore(1,1,40));
        ScoreManager<BoxScore> scoreManager = mock(ScoreManager.class);
        when(scoreManager.getScoresOfGame(context)).thenReturn(scores);
        when(scoreManager.getScoresOfUser(context)).thenReturn(scores);
        manager = new BoxGameScoreManager(scoreManager);
    }

    @Test
    public void testGetFirstTopPlayer(){
        BoxScore bs1 = scores.get(3);
        BoxScore bs2 = manager.getTopPlayers(context).get(0);
        assertEquals("got wrong first top player",bs1.level, bs2.level);
        assertEquals("got wrong first top player",bs1.moveSteps, bs2.moveSteps);
        assertEquals("got wrong first top player",bs1.undoSteps, bs2.undoSteps);
    }

    @Test
    public void testGetSecondTopPlayer(){
        BoxScore bs1 = scores.get(2);
        BoxScore bs2 = manager.getTopPlayers(context).get(1);
        assertEquals("got wrong second top player",bs1.level, bs2.level);
        assertEquals("got wrong second top player",bs1.moveSteps, bs2.moveSteps);
        assertEquals("got wrong second top player",bs1.undoSteps, bs2.undoSteps);
    }


    @Test
    public void testGetFirstOfTopThreePerUser(){
        BoxScore bs1 = scores.get(3);
        BoxScore bs2 = manager.getTopThreePerUser(context).get(0);
        assertEquals("got wrong first top three player",bs1.level, bs2.level);
        assertEquals("got wrong first top three player",bs1.moveSteps, bs2.moveSteps);
        assertEquals("got wrong first top three player",bs1.undoSteps, bs2.undoSteps);
    }

    @Test
    public void testGetLastOfTopThreePerUser(){
        BoxScore bs1 = scores.get(1);
        BoxScore bs2 = manager.getTopThreePerUser(context).get(2);
        assertEquals("got wrong last top three player",bs1.level, bs2.level);
        assertEquals("got wrong last top three player",bs1.moveSteps, bs2.moveSteps);
        assertEquals("got wrong last top three player",bs1.undoSteps, bs2.undoSteps);
    }

}