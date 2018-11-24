package fall2018.csc207project.models;

/**
 * The utility class used to initialize the UserManager, SaveManager, and ScoreManager.
 */
public class DatabaseUtil {
    /**
     * @param user the user to be managed.
     * @return new instance of the UserManager.
     */
    public static UserManager getUserManager(String user){
        return new UserManager(new UserDataStreamImpl(new DataStream()), user);
    }

    public static UserManager getUserManager(){
        return new UserManager(new UserDataStreamImpl(new DataStream()));
    }
    /**
     * @param user,game the user and game slot to be saved to or read from.
     */
    public static SaveManager getSaveManager(String user, String game) {
        return new SaveManager(new SaveDataStreamImpl(new DataStream()), user, game);
    }
    /**
     * @param user,game the ScoreManager will read or write to the given user's and game's Score
     * Database
     */
    public static <T extends Score> ScoreManager<T>
    getScoreManager(String game, String user, ScoreCalculator<T> calculator) {
        return new ScoreManager<>(new ScoreDataStreamImpl(new DataStream()), game, user, calculator);
    }
}
