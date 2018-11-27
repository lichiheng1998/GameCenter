package fall2018.csc207project.Models;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable{
    public String game;
    public String user;
    public int value;

    @Override
    public int compareTo(@NonNull Score score) {
        return value - score.value;
    }
}
