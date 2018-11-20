package fall2018.csc207project.models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class UserManager {
    private UserDataStream dataStream;
    private String currentUser;

    public UserManager(UserDataStream dataStream, String user){
        this(dataStream);
        this.currentUser = user;
    }

    public UserManager(UserDataStream dataStream){
        this.dataStream = dataStream;
    }

    /**
     * Get the mapping of user to password
     */
    private Map<String, String> getUserToPassword(Context context){
        return (Map<String, String>) dataStream.getAccountData(new HashMap<String, String>(), context);
    }

    /**
     * Get the mapping of user to game list
     */
    private Map<String, List<String>> getUserToGames(Context context){
        return (Map<String, List<String>>) dataStream.getUserToGames(new HashMap<String, List<String>>(), context);
    }

    /** Attempts to create a new account using the username and password provided.
     *  Returns true upon a successfully created new account. Also, logs them in.
     *  Returns false otherwise.
     */
    public boolean signUp(String username, String password, Context context){
        Map<String, String> userToPassword = getUserToPassword(context);
        Map<String, List<String>> userToGames = getUserToGames(context);

        if (username.equals("") || password.equals(""))
            return false;
        if (userToPassword.containsKey(username)){
            return false;
        } else {
            userToPassword.put(username, password);
            userToGames.put(username, new ArrayList<String>());
            dataStream.saveAccountData(userToPassword, context);
            dataStream.saveUserToGames(userToGames, context);
            return true;
        }
    }
    /** Attempts a login to the system using credentials username and password.
     *  Returns true upon a successful sign in. Returns false otherwise.
     *
     * @param username: String
     * @param password: String
     * @return Boolean
     */
    public boolean signIn(String username, String password, Context context){
        Map<String, String> userToPassword = getUserToPassword(context);
        return userToPassword.containsKey(username) && userToPassword.get(username).equals(password);
    }

    /** Add the game to the user's game list
     *
     * @param game: String
     * @return Boolean
     */
    public boolean addGame(String game, Context context){
        Map<String, List<String>> userToGames = getUserToGames(context);
        List<String> userGames = userToGames.get(currentUser);
        boolean result = !userGames.contains(game) && userGames.add(game);
        if(result){
            dataStream.saveUserToGames(userToGames, context);
        }
        return result;
    }

    /** Remove the game from the user's game list
     *
     * @param game: String
     * @return Boolean
     */
    public boolean removeGame(String game, Context context){
        Map<String, List<String>> userToGames = getUserToGames(context);
        boolean result = userToGames.get(currentUser).remove(game);
        if(result){
            dataStream.saveUserToGames(userToGames, context);
        }
        return result;
    }

    /** Get the user's game list
     * @return Boolean
     */
    public List<String> getGames(Context context){
        // Not leaking the original data collection.
        return getUserToGames(context).get(currentUser);
    }
}
