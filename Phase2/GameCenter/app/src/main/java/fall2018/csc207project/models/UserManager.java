package fall2018.csc207project.models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserManager {
    private Map<String, List<String>> userToGames;
    private Map<String, String> userToPassword;
    private Context context;
    private UserDataStream dataStream;
    private String currentUser;

    public UserManager(UserDataStream dataStream, String user, Context context){
        this(dataStream, context);
        this.currentUser = user;
    }

    public UserManager(UserDataStream dataStream, Context context){
        this.dataStream = dataStream;
        this.context = context;
        this.userToPassword = dataStream.getAccountData();
        this.userToGames = dataStream.getUserToGames();
    }

    /** Attempts to create a new account using the username and password provided.
     *  Returns true upon a successfully created new account. Also, logs them in.
     *  Returns false otherwise.
     */
    public Boolean signUp(String username, String password){
        if (username.equals("") || password.equals(""))
            return false;
        if (userToPassword.containsKey(username)){
            return false;
        } else {
            userToPassword.put(username, password);
            userToGames.put(username, new ArrayList<String>());
            dataStream.saveGlobalData(context);
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
    public Boolean signIn(String username, String password){
        return userToPassword.containsKey(username) && userToPassword.get(username).equals(password);
    }

    /** Add the game to the user's game list
     *
     * @param game: String
     * @return Boolean
     */
    public Boolean addGame(String game){
        List<String> userGames = userToGames.get(currentUser);
        boolean result = !userGames.contains(game) && userGames.add(game);
        if(result){
            dataStream.saveGlobalData(context);
        }
        return result;
    }

    /** Remove the game from the user's game list
     *
     * @param game: String
     * @return Boolean
     */
    public Boolean removeGame(String game){
        boolean result = userToGames.get(currentUser).remove(game);
        if(result){
            dataStream.saveGlobalData(context);
        }
        return result;
    }

    /** Get the user's game list
     * @return Boolean
     */
    public List<String> getGames(){
        // Not leaking the original data collection.
        return userToGames.get(currentUser);
    }
}
