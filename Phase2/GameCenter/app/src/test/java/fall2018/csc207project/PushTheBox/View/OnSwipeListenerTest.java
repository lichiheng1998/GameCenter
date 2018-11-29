package fall2018.csc207project.PushTheBox.View;

import android.view.MotionEvent;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OnSwipeListenerTest {

    @Test
    public void onFling() {
        OnSwipeListener osl = new OnSwipeListener() {
            @Override
            public void onSwipe(int dir) { }
        };
        MotionEvent e1 = mock(MotionEvent.class);
        MotionEvent e2 = mock(MotionEvent.class);
        when(e1.getX()).thenReturn(0.02f);
        when(e1.getY()).thenReturn(0.02f);
        when(e2.getX()).thenReturn(0.2f);
        when(e2.getY()).thenReturn(1.2f);
        assertEquals(true, osl.onFling(e1, e2, 1f, 1f));
    }
}