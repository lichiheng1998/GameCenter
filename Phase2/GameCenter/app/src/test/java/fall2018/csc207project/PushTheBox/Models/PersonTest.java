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
                person.getImage(), R.drawable.lperson);
        assertEquals("the person walked to a wrong position when going leftwards",
                person.getPosition(), 4);
    }

    @Test
    public void walkToRight() {
        Person person = new Person(5);
        person.walk(1);
        assertEquals("the person is not facing right when moved to right",
                person.getImage(), R.drawable.rperson);
        assertEquals("the person walked to a wrong position when going rightwards",
                person.getPosition(), 6);
    }

    @Test
    public void walkToUp() {
        Person person = new Person(5);
        person.walk(-3);
        assertEquals("the person is not facing up when moved upwards",
                person.getImage(), R.drawable.uperson);
        assertEquals("the person walked to a wrong position when going upwards",
                person.getPosition(), 2);
    }

    @Test
    public void walkToDown() {
        Person person = new Person(5);
        person.walk(3);
        assertEquals("the person is not facing down when moved downwards",
                person.getImage(), R.drawable.person);
        assertEquals("the person walked to a wrong position when going downwards",
                person.getPosition(), 8);
    }

    @Test
    public void walkToInvalidPosition() {
        Person person = new Person(5);
        person.walk(-6);
        assertEquals("position after change is invalid so posiiton should not" +
                        "change", person.getPosition(), 5);
    }
}