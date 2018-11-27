package fall2018.csc207project.Models;

import android.content.Context;

public interface UserDataStream {
    Object getAccountData(Object initData, Context context);
    Object getUserToGames(Object initData, Context context);
    void saveAccountData(Object data, Context context);
    void saveUserToGames(Object data, Context context);
}
