package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;

import fall2018.csc207project.PushTheBox.Models.Box;
import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.PushTheBox.Models.Person;
import fall2018.csc207project.R;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MapAdapterTest {
    private Context context  = mock(Context.class);
    private int wall = R.drawable.wall;
    private int floor = R.drawable.floor;
    private int destination = R.drawable.destination;
    private Integer[] tileBgs = {wall, wall, wall, wall, floor, wall, wall, wall, wall};;

    @Test
    public void testConstructor(){
        MapAdapter mapAdapter = new MapAdapter(tileBgs, 3, context);
        assertEquals("wrong number of tiles", mapAdapter.getCount(), 9);
        assertEquals("wrong item", mapAdapter.getItem(4), floor);
        assertEquals("wrong item", mapAdapter.getItem(3), wall);
        assertEquals("wrong id", mapAdapter.getItemId(3), 3);
    }

    @Test
    public void testSetPerson(){
        MapAdapter mapAdapter = new MapAdapter(tileBgs, 3, context);
        Person person = new Person(4);
        mapAdapter.setPerson(person);
        assertEquals("element on positio 4 should be the person",
                mapAdapter.getItem(4), R.drawable.person);
    }

    @Test
    public void testSetBoxesList(){
        MapAdapter mapAdapter = new MapAdapter(tileBgs, 3, context);
        ArrayList<Box> boxes = new ArrayList<>();
        boxes.add(new Box(4));
        mapAdapter.setBoxesList(boxes);
        assertEquals("element on positio 4 should be the box",
                mapAdapter.getItem(4), R.drawable.box);
    }

}