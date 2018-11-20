package fall2018.csc207project.models;

import java.util.List;
import java.util.Map;

public class ScoreManager {
    private Map<String, List<Score>> userToScores;
    private Map<String, List<Score>> gameToScores;

    public ScoreManager(ScoreDataStream dataStream){
        userToScores = dataStream.getUserToScores();
        gameToScores = dataStream.getGameToScores();
    }
    public Map getScorebyUser(String Name){
        return null;

    }
    public Map<String, Score> getScores (){
        return null;
    }
}
