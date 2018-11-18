package fall2018.csc207project.models;

import java.util.List;
import java.util.Map;

public interface ScoreDataStream extends GlobalDataStream{
    Map<String, List<Score>> getUserToScores();
    Map<String, List<Score>> getGameToScores();
}
