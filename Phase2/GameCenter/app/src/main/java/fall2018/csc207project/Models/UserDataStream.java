package fall2018.csc207project.Models;

import android.content.Context;

/**
 * The interface UserDataStream to help save the game's User data.
 */
public interface UserDataStream {

    /**
     * Load from the saved Account.
     *
     * @param initData the data that want to be load from
     * @param context the context of this app
     * @return load from the saved Account
     */
    Object getAccountData(Object initData, Context context);

    /**
     * Load from the saved User.
     *
     * @param initData the data that want to be load from
     * @param context the context of this app
     * @return load from the saved User
     */
    Object getUserToGames(Object initData, Context context);

    /**
     * Save the given Account data.
     *
     * @param data the data that want to be save to
     * @param context the context of this app
     */
    void saveAccountData(Object data, Context context);

    /**
     * Save the given User data.
     *
     * @param data the data that want to be save to
     * @param context the context of this app
     */
    void saveUserToGames(Object data, Context context);
}
