package fall2018.csc207project.Memorization;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.Memorization.Controllers.MemoGamePresenter;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Views.MemoGameView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemorizationGamePresenterUnitTest {
    @InjectMocks
    private MemoGamePresenter presenter;
    @Mock
    private Context context;
    @Mock
    private MemoGameView view;

    private SharedPreferences shared;

    private MemoManager manager = new MemoManager(3, 4, false);

    private List testSequence;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
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
