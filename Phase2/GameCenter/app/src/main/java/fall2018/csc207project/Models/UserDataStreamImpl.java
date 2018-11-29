package fall2018.csc207project.Models;

import android.content.Context;

/**
 * The class UserDataStreamImpl that implements UserDataStream
 */
public class UserDataStreamImpl implements UserDataStream {

    /**
     * The path to save the AccountData.
     */
    private static final String ACCOUNT_DATA_PATH = "AccountData.ser";

    /**
     * The path to save the UserData.
     */
    private static final String USER_TO_GAMES = "UserToGames.ser";

    /**
     * The GlobalDataStream helps to save.
     */
    private GlobalDataStream dataStream;

    /**
     * Construct a new UserDataStreamImpl by given a GlobalDataStream.
     *
     * @param dataStream the GlobalDataStream helps to save
     */
    UserDataStreamImpl(GlobalDataStream dataStream){
        this.dataStream = dataStream;
    }

    @Override
    public Object getAccountData(Object initData, Context context) {
        return dataStream.getAndInit(initData, ACCOUNT_DATA_PATH, context);
    }

    @Override
    public void saveAccountData(Object data, Context context){
        dataStream.saveGlobalData(data, ACCOUNT_DATA_PATH, context);
    }

    @Override
    public Object getUserToGames(Object initData, Context context) {
        return dataStream.getAndInit(initData, USER_TO_GAMES, context);
    }

    @Override
    public void saveUserToGames(Object data, Context context){
        dataStream.saveGlobalData(data, USER_TO_GAMES, context);
    }
}
