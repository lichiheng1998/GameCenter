package fall2018.csc207project.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.*;

/**
 * The class ImageManager that manages the images
 */
public class ImageManager {

    /**
     * The current user.
     */
    private String user;

    /**
     * The .ava file
     */
    private static final String AVA_EXT = ".ava";

    /**
     * The .bg files
     */
    private static final String BG_EXT = ".bg";

    /**
     * Construct a new ImageManager by given a String.
     *
     * @param currentUser the current user playing the game
     */
    ImageManager(String currentUser){
        this.user = currentUser;
    }

    /**
     * Get a drawable of given Strings, Draw the Game maps.
     *
     * @param from where to start draw
     * @param to where to end draw
     * @return a drawable of given Strings.
     */
    private Drawable copyFile(String from, String to){
        try{
            Files.copy(Paths.get(from), Paths.get(to), REPLACE_EXISTING);
        } catch (IOException e){
            Log.e("Debug", "can not copy the file");
        }
        return Drawable.createFromPath(from);
    }

    /**
     * Save the background by given Uri and Context.
     *
     * @param imageUri the image uri
     * @param context the context of this app
     * @return the save of a Drawable background by given Uri and Context
     */
    public Drawable saveBackground(Uri imageUri, Context context){
        String path = getRealPathFromURI(imageUri, context);
        String newPath = context.getFilesDir() + "/" + user + BG_EXT;
        return copyFile(path, newPath);
    }

    /**
     * Get the background by given Uri and Context.
     *
     * @param context the context of this app
     * @return the load of a Drawable background by given Uri and Context
     */
    public Drawable getBackground(Context context){
        File file = new File(context.getFilesDir() + "/" + user + BG_EXT);
        if(!file.exists()){
            return null;
        }
        return Drawable.createFromPath(file.getAbsolutePath());
    }

    public Bitmap getBackgroundBitmap(Context context){
        File file = new File(context.getFilesDir() + "/" + user + BG_EXT);
        if(!file.exists()){
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    /**
     * Clear the whole game background.
     *
     * @param context the context of this app
     * @return true or false
     */
    public boolean clearBackground(Context context){
        File file = new File(context.getFilesDir() + "/" + user + BG_EXT);
        return file.delete();
    }

    /**
     * Save the avatar by given Uri and Context.
     *
     * @param imageUri the image uri
     * @param context the context of this app
     * @return the save of a Drawable avatar by given Uri and Context
     */
    public Drawable saveAvatar(Uri imageUri, Context context){
        String path = getRealPathFromURI(imageUri, context);
        String newPath = context.getFilesDir() + "/" + user + AVA_EXT;
        copyFile(path, newPath);
        return Drawable.createFromPath(path);
    }

    /**
     * Get the avatar by given Uri and Context.
     *
     * @param context the context of this app
     * @return the load of a Drawable avatar by given Uri and Context
     */
    public Drawable getAvatar(Context context){
        File file = new File(context.getFilesDir() + "/" + user + AVA_EXT);
        if(!file.exists()){
            return null;
        }
        return Drawable.createFromPath(file.getAbsolutePath());
    }

    /**
     * Get the real path from the content URI
     * taken the code from stack overflow url:
     * "https://stackoverflow.com/questions/17176518/set-background-of-layout-from-uri"
     *
     * @param contentURI the uri represents the data.
     * @return the real path represents the given uri.
     */
    private String getRealPathFromURI(Uri contentURI, Context context) {
        @SuppressLint("Recycle") Cursor cursor = context.getContentResolver()
                .query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is DropBox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

}
