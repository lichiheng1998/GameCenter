package fall2018.csc207project.models;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserDataStream extends GlobalDataStream{
    Map<String, String> getAccountData();
    Map<String, List<String>> getUserToGames();
}
