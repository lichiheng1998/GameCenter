package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;

import fall2018.csc207project.SlidingTile.Models.BoardManager;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovementControllerTest {
    private BoardManager manager = mock(BoardManager.class);
    private Context context = mock(Context.class);

    @Before
    public void init(){
        doNothing().when(manager).pushLastStep();
        doNothing().when(manager).touchMove(1);
        when(manager.puzzleSolved()).thenReturn(false);
    }

    @Test
    public void testProcessValidTapMovement(){
        when(manager.isValidTap(1)).thenReturn(true);
        MovementController mc = new MovementController();
        mc.setBoardManager(manager);
        assertTrue("process must return true",
                mc.processTapMovement(context, 1));
        verify(manager, times(1)).pushLastStep();
        verify(manager, times(1)).touchMove(1);
    }
}