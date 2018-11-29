package fall2018.csc207project.Memorization;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import java.lang.reflect.*;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Method;
import fall2018.csc207project.Memorization.Controllers.MemoGamePresenter;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Models.MemoTile;
import fall2018.csc207project.Memorization.Views.MemoGameView;
import fall2018.csc207project.SlidingTile.Models.Tile;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemorizationGamePresenterUnitTest {

    private MemoGamePresenter presenter;

    private Context context = Mockito.mock(Context.class);

    private MemoGameView view = Mockito.mock(MemoGameView.class);

    private SharedPreferences shared = Mockito.mock(SharedPreferences.class);


    private MemoManager manager = new MemoManager(3, 4, false);

    @Before
    public void setUp(){
        Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(shared);
        Mockito.when(shared.getString("currentUser", null)).thenReturn("Admin");
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
        Class memoPresenter = presenter.getClass();
        Method presenterFailed = memoPresenter.getDeclaredMethod("fail",
                int.class, Context.class);
        Method presenterSuccess = memoPresenter.getDeclaredMethod("success",
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
}
