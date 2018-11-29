package fall2018.csc207project.PushTheBox.Models;

import org.junit.Test;

import fall2018.csc207project.R;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void walkToLeft() {
        Person person = new Person(5);
        person.walk(-1);
        assertEquals("the person is not facing left when moved to left",
                R.drawable.lperson, person.getImage());
        assertEquals("the person walked to a wrong position when going leftwards",
                4, person.getPosition());
    }

    @Test
    public void walkToRight() {
        Person person = new Person(5);
        person.walk(1);
        assertEquals("the person is not facing right when moved to right",
                R.drawable.rperson, person.getImage());
        assertEquals("the person walked to a wrong position when going rightwards",
                6,
                person.getPosition());
    }

    @Test
    public void walkToUp() {
        Person person = new Person(5);
        person.walk(-3);
        assertEquals("the person is not facing up when moved upwards",
                R.drawable.uperson, person.getImage());
        assertEquals("the person walked to a wrong position when going upwards",
                2, person.getPosition());
    }

    @Test
    public void walkToDown() {
        Person person = new Person(5);
        person.walk(3);
        assertEquals("the person is not facing down when moved downwards",
                R.drawable.person, person.getImage());
        assertEquals("the person walked to a wrong position when going downwards",
                8, person.getPosition());
    }

    @Test
    public void walkToInvalidPosition() {
        Person person = new Person(5);
        person.walk(-6);
        assertEquals("position after change is invalid so positon should not" +
                        "change", 5, person.getPosition());
    }
}