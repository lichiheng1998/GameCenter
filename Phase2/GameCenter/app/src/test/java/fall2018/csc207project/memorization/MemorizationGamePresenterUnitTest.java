package fall2018.csc207project.Memorization;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.*;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Method;
import fall2018.csc207project.Memorization.Controllers.MemoGamePresenter;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Models.MemoTile;
import fall2018.csc207project.Memorization.Views.MemoGameView;
import fall2018.csc207project.Models.DataStream;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.Score;
import fall2018.csc207project.Models.ScoreCalculator;
import fall2018.csc207project.Models.ScoreDataStream;
import fall2018.csc207project.Models.ScoreManager;
import fall2018.csc207project.SlidingTile.Models.Tile;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemorizationGamePresenterUnitTest {

    private MemoGamePresenter presenter;

    private Context context = mock(Context.class);

    private MemoGameView view = mock(MemoGameView.class);

    private SharedPreferences shared = mock(SharedPreferences.class);

    private MemoManager manager = new MemoManager(3, 4, false);

    @Before
    public void setUp(){
        Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(shared);
        Mockito.when(shared.getString("currentUser", null)).thenReturn("Admin");
        FileOutputStream out = mock(FileOutputStream.class);
        try{
            when(context.openFileOutput("Saves.ser", Context.MODE_PRIVATE)).thenReturn(out);
        } catch
                (IOException e){}
        presenter = new MemoGamePresenter(view, context);
        presenter.setMemoManager(manager);
        presenter.startCycle();
    }

    @Test
    public void VerifyItemsTest(){
        //test getVerifyItems are all active tiles
        MemoTile temp = presenter.getVerifyItems();
        while(temp != null){
            assertEquals(1, temp.status);
            temp = presenter.getVerifyItems();
        }
    }

    @Test
    public void VerifyTest() {
        manager.updateActiveTiles();
        presenter.setVerifyIterator();
        MemoTile check = presenter.getVerifyItems();
        int checkId = check.getId();

        //Check if number of successTap increase when correct pos is verified
        presenter.verify(checkId, context);
        assertEquals(1, presenter.getSuccessTap());
        //Check if life decrease when false pos is verified
        presenter.verify(100, context);
        assertEquals(2, presenter.getLife());

    }

    @Test
    public void successFailTest() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Class memoGamePresenter = presenter.getClass();
        Method presenterFailed = memoGamePresenter.getDeclaredMethod("fail",
                int.class, Context.class);
        Method presenterSuccess = memoGamePresenter.getDeclaredMethod("success",
                int.class);
        presenterSuccess.setAccessible(true);
        presenterFailed.setAccessible(true);
        //Check if success increase the count
        presenterSuccess.invoke(presenter, 0);
        assertEquals(1, presenter.getSuccessTap());
        //Check if failed decrease life count
        presenterFailed.invoke(presenter, 1, context);
        assertEquals(2, presenter.getLife());
    }

    @Test
    public void shouldFlash()throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException{
        MemoTile tile = new MemoTile(3, MemoTile.TYPEACTIVE);
        MemoTile fakeTile = new MemoTile(3, MemoTile.TYPEFAKE);
        Class memoPresenter = presenter.getClass();
        Method flash = memoPresenter.getDeclaredMethod("flashMemoTile", MemoTile.class);
        flash.setAccessible(true);
        flash.invoke(presenter, tile);
        verify(view).flashButtonToColor(eq(3), anyInt(), eq(MemoTile.ACTIVECOLOR));
        flash.invoke(presenter, fakeTile);
        verify(view).flashButtonToColor(eq(3), anyInt(), eq(MemoTile.FAKECOLOR));
    }

    @Test
    public void tapTile() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, NoSuchFieldException {
        Class memoGamePresenter = presenter.getClass();

        Method tap = memoGamePresenter.getDeclaredMethod("onTapOnTile",
                Context.class, int.class);
        Field display = memoGamePresenter.getDeclaredField("isDisplaying");
        display.setAccessible(true);
        tap.setAccessible(true);

        //test that verify will not be invoke if displaying
        display.set(presenter, true);
        tap.invoke(presenter, context, 100);
        assertEquals(3, presenter.getLife());
        //test that verify will be invoke if not displaying
        display.set(presenter, false);
        tap.invoke(presenter, context, 100);
        assertEquals(2, presenter.getLife());
    }

    @Test
    public void gameOverBehaviors() throws NoSuchFieldException, IllegalAccessException{
        //game should not be over here
        Class memoGamePresenter = presenter.getClass();
        Field gameOver = memoGamePresenter.getDeclaredField("gameOver");
        gameOver.setAccessible(true);
        assertFalse(gameOver.getBoolean(presenter));
    }

    @Test
    public void shouldGameOver() throws Exception{
        ScoreManager manager = mock(ScoreManager.class);
        Class memoGamePresenter = presenter.getClass();
        Field life = memoGamePresenter.getDeclaredField("life");
        Field myManager = memoGamePresenter.getDeclaredField("scoreManager");
        myManager.setAccessible(true);
        myManager.set(presenter, manager);
        life.setAccessible(true);
        life.set(presenter, 0);
        Method failMethod = memoGamePresenter.getDeclaredMethod("fail", int.class, Context.class);
        failMethod.setAccessible(true);
        failMethod.invoke(presenter, 2, context);
        verify(view).showGameOverDialog(anyInt(),any(MemoManager.class));
    }

    @Test
    public void shouldShowHint() throws Exception{
        Field f1 = presenter.getClass().getDeclaredField("isDisplaying");
        f1.setAccessible(true);
        f1.set(presenter, false);
        presenter.onHintTap();
        verify(view).flashButtonToColor(anyInt(), anyInt(), anyInt());
        verify(view).deActivateHint();
    }
}
