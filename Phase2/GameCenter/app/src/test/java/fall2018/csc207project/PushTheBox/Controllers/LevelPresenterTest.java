package fall2018.csc207project.PushTheBox.Controllers;

import org.junit.Test;

import java.util.logging.Level;

import fall2018.csc207project.PushTheBox.View.LevelView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LevelPresenterTest {
    LevelView view = mock(LevelView.class);
    private LevelPresenter levelPresenter = new LevelPresenter(view);

    @Test
    public void undoStepsSetted() {
        levelPresenter.undoStepsSetted(100);
        assertTrue("wrong message for undostep 100", (levelPresenter.text).equals(
                        "The Total Undo Steps set to: 100"));
        levelPresenter.undoStepsSetted(3);
        assertTrue("wrong message for undostep 3", (levelPresenter.text).equals(
                "The Total Undo Steps set to default value: 3"));
    }

    @Test
    public void acceptButtonClicked() {
        levelPresenter.acceptButtonClicked("3");
        assertTrue("wrong message setting undo steps 3",
                (levelPresenter.text).equals(
                "Successfully set the Total Undo Steps to: 3"));
    }
}