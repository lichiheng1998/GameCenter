package fall2018.csc207project.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fall2018.csc207project.Models.UserManager;
import fall2018.csc207project.Views.LoginView;
import fall2018.csc207project.Views.SignUpView;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserPresenterTest {
    @Mock
    private LoginView lgView;
    @Mock
    private SignUpView suView;
    @Mock
    private UserManager manager;
    @Mock
    private Context context;
    @Mock
    private SharedPreferences shared;
    @Mock
    private SharedPreferences.Editor editor;

    @InjectMocks
    private UserPresenter lgPresenter;

    @InjectMocks
    private UserPresenter suPresenter;

    @Before
    public void setUp(){
        lgPresenter = new UserPresenterImpl(manager, lgView);
        suPresenter = new UserPresenterImpl(manager, suView);
        MockitoAnnotations.initMocks(this);
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(shared);
        when(shared.edit()).thenReturn(editor);
    }

    @Test
    public void shouldCallLocalCenterWhenSignUpSuccess(){
        String name = "test";
        String password = "test";
        when(manager.signUp(anyString(), anyString(), eq(context))).thenReturn(true);
        suPresenter.signUpUser(name, password, context);
        verify(suView, times(1)).localCenter();
    }

    @Test
    public void shouldToastFailureTextWhenSignUpFail(){
        String name = "test";
        String password = "test";
        when(manager.signUp(anyString(), anyString(), eq(context))).thenReturn(false);
        suPresenter.signUpUser(name, password, context);
        verify(suView, times(1)).makeSignUpFailedText();
    }

    @Test
    public void shouldCallLocalCenterWhenSignInSuccess(){
        String name = "test";
        String password = "test";
        when(manager.signIn(anyString(), anyString(), eq(context))).thenReturn(true);
        lgPresenter.loginUser(name, password, context);
        verify(lgView, times(1)).localCenter();
    }

    @Test
    public void shouldToastFailureTextWhenSignInFail(){
        String name = "test";
        String password = "test";
        when(manager.signIn(anyString(), anyString(), eq(context))).thenReturn(false);
        lgPresenter.loginUser(name, password, context);
        verify(lgView, times(1)).makeLoginFailedText();
    }
}
