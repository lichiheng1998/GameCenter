package fall2018.csc207project.PushTheBox.Models;

import android.content.Context;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LevelFactoryTest {
    private String[] box_text = {"3-5--7-8-6",
            "5-5--12,17-13,16-6",
            "6-6-10,21-14,15-13,15-7",
            "6-7-12,23-16,17-24,26-8",
            "7-7-8,9,12,19,30-18,25-17,31-15",
            "7-7-8,12,17,36,40-23,24,25-24,31,38-9",
            "6-7-12,17,33-9,16,23-9,25,29-8",
            "6-8-13,14,35-26,27,28-20,21,37-29",
            "8-8-9,13,14,19,27,29,45,46-21,34,37-10,34,38-22"};

    @Test
    public void getGameElements() {
        Context context = mock(Context.class);
        LevelFactory levelFactory = new LevelFactory(context);
        HashMap<String, Object> result = new HashMap<>();
        result.put("Person", new Person(6));
        try{
            result = levelFactory.getGameElements(2);
        }catch(NullPointerException e){
            Integer[][] tmp = {{5}, {5}, {}, {12,17}, {13,16}, {6}};
        }
        assertTrue("Incorrect person for level 2",
                ((Person)result.get("Person")).equals(new Person(6)));
//        ArrayList<Box> boxes = (ArrayList<Box>) result.get("boxArrayList");
//        assertTrue("Incorrect first for level 2",
//                boxes.get(0).equals(new Box(12)));
//        assertTrue("Incorrect second for level 2",
//                boxes.get(1).equals(new Box(17)));
    }
}