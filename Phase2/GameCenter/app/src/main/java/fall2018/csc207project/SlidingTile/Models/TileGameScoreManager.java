package fall2018.csc207project.SlidingTile.Models;

public class TileGameScoreManager {

    private int complexity;
    private String currrentUser;

    TileGameScoreManager(int complexity, String currrentUser){
        this.complexity = complexity;
        this.currrentUser = currrentUser;
    }
    public getHighestScore(){
        this.getScorebyUser(this.currrentUser);
    }
}
