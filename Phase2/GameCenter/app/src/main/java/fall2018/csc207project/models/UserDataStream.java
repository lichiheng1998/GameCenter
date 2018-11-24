package fall2018.csc207project.models;

import android.content.Context;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserDataStream {
    Object getAccountData(Object initData, Context context);
    Object getUserToGames(Object initData, Context context);
    void saveAccountData(Object data, Context context);
    void saveUserToGames(Object data, Context context);
}
