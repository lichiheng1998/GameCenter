package fall2018.csc207project.Controllers;

import android.content.Context;

/**
 * The interface UserPresenter to present this user.
 */
public interface UserPresenter {

    /**
     * Login to the Game Center by given the user's
     * name, password and the context of this game.
     *
     * @param name the current user's name
     * @param password the current user's password
     * @param context the context of this game
     */
    void loginUser(String name, String password, Context context);

    /**
     * SignUp to the Game Center by given the user's
     * name, password and the context of this game.
     *
     * Precondition: If there is no such user
     *
     * @param name the current user's name
     * @param password the current user's password
     * @param context the context of this game
     */
    void signUpUser(String name, String password, Context context);
}
