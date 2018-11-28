package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MapManagerTest {
    private HashMap<String, Object> levelInfo = new HashMap<>();


    public void init(){
        List<BgTile> bgElements = new ArrayList<>();
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
        assertEquals("wrong level", mapManager.getLevel(), 0);
        assertEquals("wrong number of columns", mapManager.getNumCol(),
                6);
    }

    @Test
    public void testUnsolvedBoxSolved(){
        MapManager mapManager = new MapManager(0,0,levelInfo);
        assertFalse("game is unsolved yet", mapManager.boxSolved());
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
                        "place", mapManager.person.getPosition(), 8);
    }

    @Test
    public void processPersonMovementNoPushBox() {
        MapManager mapManager = new MapManager(0,0,levelInfo);
        mapManager.processPersonMovement(1);
        mapManager.processPersonMovement(-1);
        assertTrue("the box isn't pushed",
                !mapManager.isBoxesMoved);
        assertEquals("the person didn't move or moved to wrong" +
                "place", mapManager.person.getPosition(), 7);
    }

//    @Test
//    public void pushLastStep() {
//    }

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

//    @Test
//    public void getTotalUndoTimes() {
//    }
//
//    @Test
//    public void getTilesBg() {
//    }
//
//    @Test
//    public void getPersonPosToImage() {
//    }
//
//    @Test
//    public void getBoxPosToImage() {
//    }
//
//    @Test
//    @After
//    public void testSolvedBoxSolved(){
//    }
}