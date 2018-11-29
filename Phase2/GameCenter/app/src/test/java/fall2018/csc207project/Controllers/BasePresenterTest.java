package fall2018.csc207project.Controllers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fall2018.csc207project.Models.ImageManager;
import fall2018.csc207project.Views.NavView;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BasePresenterTest {
    @Mock
    private NavView view;
    @Mock
    private ImageManager manager;
    @Mock
    private Context context;
    @Mock
    private Drawable drawable;
    @Mock
    private Uri uri;

    @InjectMocks
    private BasePresenter basePresenter;

    @Before
    public void setUp(){
        basePresenter = new NavPresenter(view, manager);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInitAvatarAndBackGround(){
        when(manager.getAvatar(context)).thenReturn(drawable);
        when(manager.getBackground(context)).thenReturn(drawable);
        basePresenter.initializeView(context);
        verify(view, times(1)).showAvatar(drawable);
        verify(view, times(1)).showBackground(drawable);
    }

    @Test
    public void shouldNotInitAvatarAndBackGround(){
        when(manager.getAvatar(context)).thenReturn(null);
        when(manager.getBackground(context)).thenReturn(null);
        basePresenter.initializeView(context);
        verify(view, times(0)).showAvatar(drawable);
        verify(view, times(0)).showBackground(drawable);
    }

    @Test
    public void shouldSetAvatarAndBackGround(){
        when(manager.getAvatar(context)).thenReturn(drawable);
        when(manager.getBackground(context)).thenReturn(drawable);
        basePresenter.onAvatarSelected(uri, context);
        basePresenter.onBackgroundSelected(uri, context);
        verify(manager, times(1)).saveAvatar(uri, context);
        verify(manager, times(1)).saveBackground(uri, context);
    }

    @Test
    public void shouldResetBackground(){
        when(manager.clearBackground(context)).thenReturn(true);
        basePresenter.onResetClicked(context);
        verify(view, times(1)).showBackground(isNull(Drawable.class));
    }

    @Test
    public void shouldNotResetBackground(){
        basePresenter.onResetClicked(context);
        when(manager.getBackground(context)).thenReturn(null);
        verify(view, times(0)).showBackground(drawable);
    }
}
