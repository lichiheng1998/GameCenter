package fall2018.csc207project.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * The class Score that implements Comparable<Score> and Serializable
 */
public class Score implements Comparable<Score>, Serializable {

    /**
     * The game's name.
     */
    public String game;

    /**
     * The user's name.
     */
    public String user;

    /**
     * The user's total score.
     */
    public int value;

    @Override
    public int compareTo(@NonNull Score score) {
        return value - score.value;
    }
}
