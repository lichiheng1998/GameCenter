package fall2018.csc207project.Memorization;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.Memorization.Controllers.MemoGamePresenter;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Views.MemoGameView;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemorizationGamePresenterUnitTest {

    private MemoGamePresenter presenter;

    private Context context = Mockito.mock(Context.class);

    private MemoGameView view = Mockito.mock(MemoGameView.class);

    private SharedPreferences shared = Mockito.mock(SharedPreferences.class);


    private MemoManager manager = new MemoManager(3, 4, false);

    private List testSequence;

    @Before
    public void setUp(){
        Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(shared);
        Mockito.when(shared.getString("currentUser", null)).thenReturn("Admin");
        presenter = new MemoGamePresenter(view, context);
        for(int i = 0; i < 10; i++){
            manager.updateActiveTiles();
        }
        presenter.setMemoManager(manager);
        presenter.setVerifyIterator();
        presenter.setNextToVerify();
        testSequence = manager.getSequenceOrder();
    }

    @Test
    public void MemoGamePresenterTest(){

        //test getVerifyItems are all active tiles
        assertEquals(1, presenter.getVerifyItems().status);

    }

}
