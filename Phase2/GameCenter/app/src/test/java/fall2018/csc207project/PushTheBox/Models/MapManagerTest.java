package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MapManagerTest {
    private Context context = Mockito.mock(Context.class);

    @Test
    public void testInitLevel2Game() {
        MapManager mapManager = new MapManager(2, 3, context);
        assertEquals("initial map is unsolved", mapManager.boxSolved(), false);
        assertEquals("initial map cannot process undo",
                mapManager.canProcessUndo(1), false);
        assertEquals("total undo times should not change",
                mapManager.getTotalUndoTimes(), 3);
        assertEquals("this is a level 2 game", mapManager.getLevel(), 2);
        assertEquals("the number of columns for level 2 is 5",
                mapManager.getNumCol(), 5);
    }

    @Test
    public void isValidMovement() {
    }

    @Test
    public void processPersonMovement() {
    }

    @Test
    public void pushLastStep() {
    }

    @Test
    public void canProcessUndo() {
    }

    @Test
    public void getTotalUndoTimes() {
    }

    @Test
    public void getLevel() {
    }

    @Test
    public void subscribe() {
    }

    @Test
    public void getNumCol() {
    }

    @Test
    public void getTilesBg() {
    }

    @Test
    public void getPersonPosToImage() {
    }

    @Test
    public void getBoxPosToImage() {
    }
}