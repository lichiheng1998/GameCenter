package fall2018.csc207project.models;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("unchecked")
/**
 * The class that loads the data from internal storage into the memory.
 */
public class DataStream implements GlobalDataStream{

    /**
     * Load form the given path.
     * @param path the path of the file to be loaded
     * @return the deserialize object.
     */
    public Object loadGlobalData(String path, Context context){
        Object object = null;
        File file = new File(context.getFilesDir() + "/" + path);
        if (!file.exists()) {
            return null;
        }
        try {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput(path));
            object = in.readObject();
            in.close();
        } catch (ClassNotFoundException e) {
            Log.e("setup global center", "Class not found: " + e.toString());
        } catch (IOException e) {
            Log.e("setup global center", "IOException: " + e.toString());
        }
        return object;
    }

    /**
     * Save to the given path.
     * @param path the path of the file to be saved.
     * @param data the object to be saved.
     */
    public void saveGlobalData(Object data, String path, Context context){
        try {
            ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(path,
                    Context.MODE_PRIVATE));
            out.writeObject(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the data in given path. If not exists, initialize with the initData, and save to the path.
     * @param path the path of the file to be loaded.
     * @param initData the data used to initialize.
     * @return the loaded data.
     */
    public Object getAndInit(Object initData, String path, Context context){
        Object data = loadGlobalData(path, context);
        if(data == null){
            saveGlobalData(initData, path, context);
            return initData;
        }
        return data;
    }
}
