package fall2018.csc207project.models;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class DataStream implements SaveDataStream, ScoreDataStream, UserDataStream{
    private static final String PATH = "GlobalSave.ser";
    private Map<String, Object> globalData;
    private static DataStream single_instance;

    private DataStream(){
    }

    public void loadGlobalData(Context context){
        File file = new File(context.getFilesDir() + "/" + PATH);
        if (!file.exists()) {
            initializeGlobalData();
            saveGlobalData(context);
            return;
        }
        try {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput(PATH));
            this.globalData = (Map<String, Object>)in.readObject();
            in.close();
        } catch (ClassNotFoundException e) {
            Log.e("setup global center", "Class not found: " + e.toString());
        } catch (IOException e) {
            Log.e("setup global center", "IOException: " + e.toString());
        }
    }

    public void saveGlobalData(Context context){
        try {
            ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(PATH,
                    Context.MODE_PRIVATE));
            out.writeObject(this.globalData);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Map<String, Object>> getSaves() {
        return (Map<String, Map<String, Object>>)globalData.get("Saves");
    }

    @Override
    public Map<String, Map<String, Object>> getAutoSaves() {
        return (Map<String, Map<String, Object>>)globalData.get("AutoSaves");
    }

    @Override
    public Map<String, List<Score>> getUserToScores() {
        return (Map<String, List<Score>>)globalData.get("UserToScores");
    }

    @Override
    public Map<String, List<Score>> getGameToScores() {
        return (Map<String, List<Score>>)globalData.get("GameToScores");
    }

    @Override
    public Map<String, String> getAccountData() {
        return (Map<String, String>)globalData.get("AccountData");
    }

    @Override
    public Map<String, List<String>> getUserToGames() {
        return (Map<String, List<String>>)globalData.get("UserToGames");
    }

    private void initializeGlobalData(){
        Map<String, Object> data = new HashMap<>();
        data.put("Saves", new HashMap<String, Map<String, Object>>());
        data.put("AutoSaves", new HashMap<String, Map<String, Object>>());
        data.put("UserToScores", new HashMap<String, List<Score>>());
        data.put("GameToScores", new HashMap<String, List<Score>>());
        data.put("AccountData", new HashMap<String, String>());
        data.put("UserToGames", new HashMap<String, List<String>>());
        this.globalData = data;
    }
    public static DataStream getInstance()
    {
        if (single_instance == null){
            single_instance = new DataStream();
        }
        return single_instance;
    }
}
