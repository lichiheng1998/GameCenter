package fall2018.csc207project.Models;

/**
 * The interface ScoreCalculator<T extends Score> calculate score for different games
 *
 * @param <T> class that extends Score
 */
public interface ScoreCalculator<T extends Score>{

    /**
     *@param scoreInfo the method will calculate the numeric value of the given record.
     */
    int calculate(T scoreInfo);
}
