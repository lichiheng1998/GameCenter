package fall2018.csc207project.Models;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserManagerTest {

    private UserManager manager;
    @Mock
    private UserDataStream userDataStream;

    @Mock
    private Context context;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Map<String, List<String>> userToGames = new HashMap<>();
        Map<String, String> userToPassword = new HashMap<>();
        userToPassword.put("adam", "112233");
        manager = new UserManager(userDataStream, "adam");
        List<String> games = new ArrayList<>();
        games.add("a");
        games.add("b");
        userToGames.put("adam", games);
        when(userDataStream.getUserToGames(any(HashMap.class), eq(context))).thenReturn(userToGames);
        when(userDataStream.getAccountData(any(HashMap.class), eq(context))).thenReturn(userToPassword);
    }

    @Test
    public void shouldGetGame(){
        assertEquals(manager.getGames(context).get(0), "a");
    }

    @Test
    public void shouldAddGame(){
        manager.addGame("c", context);
        verify(userDataStream).saveUserToGames(any(Object.class), eq(context));
    }

    @Test
    public void shouldRemoveGame(){
        manager.removeGame("a", context);
        verify(userDataStream).saveUserToGames(any(Object.class), eq(context));
    }

    @Test
    public void shouldSignIn(){
        assertTrue(manager.signIn("adam", "112233", context));
        assertFalse(manager.signIn("adam", "11223", context));
    }


    @Test
    public void shouldSignUp(){
        assertFalse(manager.signUp("adam", "112233", context));
        assertTrue(manager.signUp("adama", "11223", context));
        assertFalse(manager.signUp("", "", context));
    }
}
