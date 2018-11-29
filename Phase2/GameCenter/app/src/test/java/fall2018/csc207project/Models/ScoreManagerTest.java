package fall2018.csc207project.Models;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScoreManagerTest {
    @Mock
    private Context context;
    private Score score;
    private Score score2;
    List<Score> scores = new ArrayList<>();
    @Mock
    private ScoreCalculator<Score> calculator;
    @Mock
    private ScoreDataStream dataStream;

    private ScoreManager<Score> manager;

    @Before
    public void setUp(){
        score = new Score();
        score2 = new Score();
        score.game = "testGame";
        score.user = "testUser";
        score2.game = "testGame";
        score2.user = "testUser";
        scores.add(score);
        scores.add(score2);
        MockitoAnnotations.initMocks(this);
        this.manager = new ScoreManager<>(dataStream, "testGame", "testUser", calculator);
        when(dataStream.getScores(any(Object.class), eq(context))).thenReturn(scores);
    }

    @Test
    public void shouldSaveScore(){
        Score newScore = new Score();
        newScore.game = "testGame";
        newScore.user = "testUser";
        manager.saveScore(newScore, context);
        verify(dataStream).saveScores(scores, context);
    }

    @Test
    public void shouldGetScoreOfGame(){
        List<Score> retScores = manager.getScoresOfGame(context);
        for (int i = 0; i < retScores.size(); i++){
            assertEquals(scores.get(i), retScores.get(i));
        }
    }

    @Test
    public void shouldGetScoreOfUser(){
        List<Score> retScores = manager.getScoresOfUser(context);
        for (int i = 0; i < retScores.size(); i++){
            assertEquals(scores.get(i), retScores.get(i));
        }
    }

    @Test
    public void shouldGetCurrentUser(){
        assertEquals(manager.getCurrentUser(), "testUser");
    }
}
