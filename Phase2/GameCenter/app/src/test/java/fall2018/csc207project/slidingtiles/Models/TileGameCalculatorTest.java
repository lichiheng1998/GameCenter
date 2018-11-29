package fall2018.csc207project.slidingtiles.Models;

import org.junit.Test;

import fall2018.csc207project.SlidingTile.Models.TileGameCalculator;
import fall2018.csc207project.SlidingTile.Models.TileScore;

import static org.junit.Assert.assertTrue;

public class TileGameCalculatorTest {
    @Test
    public void testCalculate() {
        TileGameCalculator tileGameCalculator = new TileGameCalculator();
        TileScore tileScore3x0 = new TileScore(3, 1000,1000);
        TileScore tileScore3x1 = new TileScore(3, 5,30);
        TileScore tileScore4x0 = new TileScore(4, 1000,1000);
        TileScore tileScore4x1 = new TileScore(4, 5,30);
        TileScore tileScore5x0 = new TileScore(5, 1000,1000);
        TileScore tileScore5x1 = new TileScore(5, 5,30);
        int result0 = tileGameCalculator.calculate(tileScore3x0);
        int result1 = tileGameCalculator.calculate(tileScore3x1);
        int result2 = tileGameCalculator.calculate(tileScore4x0);
        int result3 = tileGameCalculator.calculate(tileScore4x1);
        int result4 = tileGameCalculator.calculate(tileScore5x0);
        int result5 = tileGameCalculator.calculate(tileScore5x1);
        assertTrue(result0 == 0);
        assertTrue(result1 == 785);
        assertTrue(result2 == 0);
        assertTrue(result3 == 935);
        assertTrue(result4 == 0);
        assertTrue(result5 == 965);
    }
}
