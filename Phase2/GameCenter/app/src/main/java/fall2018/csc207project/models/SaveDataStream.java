package fall2018.csc207project.models;

import java.util.Map;

public interface SaveDataStream extends GlobalDataStream{
    Map<String, Map<String, Object>> getSaves();
    Map<String, Map<String, Object>> getAutoSaves();
}
