package fall2018.csc207project.PushTheBox.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoxGameCalculatorTest {

    @Test
    public void calculate() {
        BoxScore score= new BoxScore (1, 1000, 1000);
        BoxGameCalculator calculator = new BoxGameCalculator();
        int res = calculator.calculate(score);
        assertTrue(res == 0);
    }
}