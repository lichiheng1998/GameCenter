package fall2018.csc207project.PushTheBox.Models;


import android.util.Range;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import fall2018.csc207project.R;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MapManagerTest {
    private HashMap<String, Object> levelInfo = new HashMap<>();
    private ArrayList<BgTile> bgElements;


    public void init(){
        bgElements = new ArrayList<>();
        ArrayList<Box> boxes = new ArrayList<>();
        Integer[] wallPos = {0,1,2,3,4,5,6,11,12,13,14,15,16,17};
        for (int i = 0; i < 18; i++){
            if (Arrays.asList(wallPos).contains(i)){
                bgElements.add(new BgTile("Wall"));
            }else if (i == 10){
                bgElements.add(new BgTile("Destination"));
            }else{
                bgElements.add(new BgTile("Floor"));
            }
        }
        boxes.add(new Box(8));
        levelInfo.put("Person", new Person(7));
        levelInfo.put("boxArrayList", boxes);
        levelInfo.put("map", new GameMap(6,3, bgElements));
    }

    @Test
    @Before
    public void textConstructor() {
        init();
        MapManager mapManager = new MapManager(0, 3, levelInfo);
        assertTrue("wrong person created",
                mapManager.person.equals(new Person(7)));
        assertEquals("wrong level", 0, mapManager.getLevel());
        assertEquals("wrong number of columns", 6, mapManager.getNumCol());
    }

    @Test
    public void testUnsolvedBoxSolved(){
        MapManager mapManager = new MapManager(0,0,levelInfo);
        assertFalse("game is unsolved yet", mapManager.boxSolved());
    }

    @Test
    public void testSolvedBoxSolved(){
        MapManager mapManager = new MapManager(0,0,levelInfo);
        mapManager.processPersonMovement(1);
        mapManager.processPersonMovement(1);
        assertTrue("game is solved", mapManager.boxSolved());
    }


    @Test
    public void testUnvalidMovementTowardsWall() {
        MapManager mapManager = new MapManager(0,0,levelInfo);
        String text = "movements towards the wall is invalid";
        assertFalse(text, mapManager.isValidMovement(-1));
        assertFalse(text, mapManager.isValidMovement(-6));
        assertFalse(text, mapManager.isValidMovement(6));
    }

    @Test
    public void processPersonPushBox() {
        MapManager mapManager = new MapManager(0,0,levelInfo);
        mapManager.processPersonMovement(1);
        assertTrue("the person should have pushed the box",
                mapManager.isBoxesMoved);
        assertEquals("the person didn't move or moved to wrong" +
                        "place", 8, mapManager.person.getPosition());
    }

    @Test
    public void testPersonMovementNoPushBox() {
        MapManager mapManager = new MapManager(0,0,levelInfo);
        mapManager.processPersonMovement(1);
        mapManager.processPersonMovement(-1);
        assertTrue("the box isn't pushed",
                !mapManager.isBoxesMoved);
        assertEquals("the person didn't move or moved to wrong" +
                "place", 7, mapManager.person.getPosition());
    }

    @Test
    public void testBoxArriveDestination(){
        MapManager mapManager = new MapManager(0,0,levelInfo);
        mapManager.processPersonMovement(1);
        mapManager.processPersonMovement(1);
        assertEquals("box should show that it is on a destination",
                R.drawable.winbox, mapManager.getBoxList().get(0).getImage());
    }

    @Test
    public void testNoUndoMadeCanProcessUndo() {
        MapManager mapManager = new MapManager(0,3,levelInfo);
        mapManager.processPersonMovement(1);
        mapManager.pushLastStep(1, mapManager.person.getPosition()+1);
        assertTrue("undo should be availabe since no undo is " +
                "done", mapManager.canProcessUndo(1));
        assertFalse("undo steps greater than limit",
                mapManager.canProcessUndo(10));
        assertFalse("requested undo steps greater than steps " +
                        "taken", mapManager.canProcessUndo(3));
    }

    @Test
    public void testProcessUndoMovement(){
        MapManager mapManager = new MapManager(0,3,levelInfo);
        mapManager.processPersonMovement(1);
        mapManager.processPersonMovement(1);
        mapManager.pushLastStep(1,9);
        mapManager.pushLastStep(1,10);
        mapManager.canProcessUndo(1);
        assertEquals("failed to undo 1 step", 8,
                mapManager.person.getPosition());
        assertEquals("box didn't undo with person", 9,
                mapManager.getBoxList().get(0).getPosition());
        mapManager.processPersonMovement(1);
        mapManager.pushLastStep(1,10);
        mapManager.canProcessUndo(2);
        assertEquals("undo 2 steps failed", 7,
                mapManager.person.getPosition());
        assertEquals("box didn't undo with person", 8,
                mapManager.getBoxList().get(0).getPosition());
    }


    @Test
    public void testGetTotalUndoTimes() {
        MapManager mapManager = new MapManager(0,3,levelInfo);
        mapManager.processPersonMovement(1);
        assertEquals("Total undo times should never change", 3,
                mapManager.getTotalUndoTimes());
    }

    @Test
    public void testGetTilesBg() {
        MapManager mapManager = new MapManager(0,0, levelInfo);
        Integer[] tileBg = mapManager.getTilesBg();
        for (int i = 0; i < 18; i++){
            Integer bg = bgElements.get(i).getBackground();
            assertEquals("Background on position " + i + "is incorrect", bg,
                    tileBg[i]);
        }
    }

    @Test
    public void testGetBoxList(){
        MapManager mapManager = new MapManager(0,0, levelInfo);
        ArrayList<Box> boxes = mapManager.getBoxList();
        assertTrue("wrong list of boxes",
                boxes.get(0).equals(new Box(8)));
    }
}