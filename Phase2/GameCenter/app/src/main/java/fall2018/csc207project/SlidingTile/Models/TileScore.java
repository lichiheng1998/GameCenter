package fall2018.csc207project.SlidingTile.Models;

import fall2018.csc207project.models.Score;

public class TileScore extends Score{


    public int complexity;
    public String game = "Slidetail";
    public String user;
    public int value;

    TileScore(int complexity){
        this.complexity = complexity;
    }

}
