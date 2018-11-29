package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;
import android.content.res.AssetManager;
import android.renderscript.ScriptGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LevelFactoryTest {
    HashMap<String, Object> result;
    private GameMap map;

    @Before
    public void getGameElementForLevel1() {
        Context context = mock(Context.class);
        LevelFactory levelFactory = spy(new LevelFactory(context));
        AssetManager assetManager = mock(AssetManager.class);
        when(context.getAssets()).thenReturn(assetManager);
        doReturn("3-5--7-8-6").when(levelFactory).readLine(assetManager);
        when(levelFactory.getGameElements(1)).thenCallRealMethod();
        result = levelFactory.getGameElements(1);
        map = (GameMap) result.get("map");


    }

    @Test
    public void testPerson(){
        assertTrue("wrong person for level 1",
                ((Person)result.get("Person")).equals(new Person(6)));
    }

    @Test
    public void testBoxArrayList(){
        assertEquals("wrong box for level 1", 7,
                ((ArrayList<Box>)result.get("boxArrayList")).get(0).getPosition());
    }

    @Test
    public void testBgTiles(){
        Integer[] wallPos = {0,1,2,3,4,5,9,10,11,12,13,14};
        for (int i = 0; i < 15; i++){
            if (Arrays.asList(wallPos).contains(i)){
                assertTrue("Tile on position " + i + " should be a wall",
                        map.tileIsWall(i));
            }else if (i == 8){
                assertTrue("Tile on position " + i + " should be a destination",
                        map.tileIsDestination(i));
            }else{
                assertTrue("Tile on position "+ i + " should be a floor",
                        (!map.tileIsDestination(i) && !map.tileIsWall(i)));
            }
        }
    }

    @Test
    public void testDimensions(){
        assertEquals("wrong height", 3, map.NUM_ROW);
        assertEquals("wrong width", 5, map.NUM_COL);
    }
}