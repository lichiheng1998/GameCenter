package fall2018.csc207project.PushTheBox.Controllers;

import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import fall2018.csc207project.R;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyViewHolderTest {
    private MyViewHolder viewHold;
    private TextView userName = mock(TextView.class);
    private TextView userScore = mock(TextView.class);

    @Before
    public void init(){
        View base = mock(View.class);
        when(base.findViewById(R.id.boxScoreBoardUser)).thenReturn(userName);
        when(base.findViewById(R.id.boxScoreBoardScore)).thenReturn(userScore);
        viewHold = new MyViewHolder(base);
    }

    @Test
    public void testConstructor(){
        assertEquals("wrong username", userName, viewHold.userName);
        assertEquals("wrong score", userScore, viewHold.userScore);
    }

}