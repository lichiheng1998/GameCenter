package fall2018.csc207project.Models;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The class that loads the data from internal storage into the memory.
 */
@SuppressWarnings("unchecked")
public class DataStream implements GlobalDataStream{

    private Object loadGlobalData(String path, Context context){
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

    @Override
    public void saveGlobalData(Object data, String path, Context context){
        try {
            ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(path,
                    Context.MODE_PRIVATE));
            out.writeObject(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getAndInit(Object initData, String path, Context context){
        Object data = loadGlobalData(path, context);
        if(data == null){
            saveGlobalData(initData, path, context);
            return initData;
        }
        return data;
    }
}
