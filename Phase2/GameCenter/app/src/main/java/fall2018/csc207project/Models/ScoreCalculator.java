package fall2018.csc207project.Models;

public interface ScoreCalculator<T extends Score>{
    /**
     *@param scoreInfo the method will calculate the numeric value of the given record.
     */
    int calculate(T scoreInfo);
}
