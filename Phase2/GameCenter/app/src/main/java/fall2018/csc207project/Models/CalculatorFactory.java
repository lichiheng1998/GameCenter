package fall2018.csc207project.Models;

import fall2018.csc207project.Memorization.Models.MemoGameCalculator;
import fall2018.csc207project.PushTheBox.Models.BoxGameCalculator;
import fall2018.csc207project.SlidingTile.Models.TileGameCalculator;

/**
 *
 */
@SuppressWarnings("unchecked")
public class CalculatorFactory {

    /**
     *
     *
     * @param calculatorType
     * @return
     */
    public <T extends Score> ScoreCalculator<T> getCalculator(String calculatorType) {
        if (calculatorType == null) {
            return null;
        } else if (calculatorType.equals("MemoCalculator")) {
            return (ScoreCalculator<T>) new MemoGameCalculator();
        } else if (calculatorType.equals("BoxCalculator")) {
            return (ScoreCalculator<T>) new BoxGameCalculator();
        } else if (calculatorType.equals("SlidingTileCalculator")) {
            return (ScoreCalculator<T>) new TileGameCalculator();
        } else {
            return null;
        }
    }
}
