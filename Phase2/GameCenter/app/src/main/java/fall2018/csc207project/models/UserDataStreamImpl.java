package fall2018.csc207project.models;

import android.content.Context;

public class UserDataStreamImpl implements UserDataStream{
    private static final String ACCOUNTDATAPATH = "AccountData.ser";
    private static final String USERTOGAMES = "UserToGames.ser";
    private GlobalDataStream dataStream;

    public UserDataStreamImpl(GlobalDataStream dataStream){
        this.dataStream = dataStream;
    }

    @Override
    public Object getAccountData(Object initData, Context context) {
        return dataStream.getAndInit(initData, ACCOUNTDATAPATH, context);
    }

    @Override
    public void saveAccountData(Object data, Context context){
        dataStream.saveGlobalData(data, ACCOUNTDATAPATH, context);
    }

    @Override
    public Object getUserToGames(Object initData, Context context) {
        return dataStream.getAndInit(initData, USERTOGAMES, context);
    }

    @Override
    public void saveUserToGames(Object data, Context context){
        dataStream.saveGlobalData(data, USERTOGAMES, context);
    }
}
