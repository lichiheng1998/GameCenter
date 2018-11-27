package fall2018.csc207project.Models;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
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
 *
 */
public class ImageManager {
    private String user;
    private static final String AVAEXT = ".ava";
    private static final String BGEXT = ".bg";

    public ImageManager(String currentUser){
        this.user = currentUser;
    }

    public Drawable copyFile(String from, String to){
        try{
            Files.copy(Paths.get(from), Paths.get(to), REPLACE_EXISTING);
        } catch (IOException e){
            Log.e("Debug", "can not copy the file");
        }
        return Drawable.createFromPath(from);
    }
    public Drawable saveBackground(Uri imageUri, Context context){
        String path = getRealPathFromURI(imageUri, context);
        String newPath = context.getFilesDir() + "/" + user +BGEXT;
        return copyFile(path, newPath);
    }

    public Drawable getBackground(Context context){
        File file = new File(context.getFilesDir() + "/" + user + BGEXT);
        if(!file.exists()){
            return null;
        }
        return Drawable.createFromPath(file.getAbsolutePath());
    }

    public boolean clearBackground(Context context){
        File file = new File(context.getFilesDir() + "/" + user + BGEXT);
        return file.delete();
    }

    public Drawable saveAvatar(Uri imageUri, Context context){
        String path = getRealPathFromURI(imageUri, context);
        String newPath = context.getFilesDir() + "/" + user + AVAEXT;
        copyFile(path, newPath);
        return Drawable.createFromPath(path);
    }

    public Drawable getAvatar(Context context){
        File file = new File(context.getFilesDir() + "/" + user + AVAEXT);
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
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    /**
     * Adapted from "https://stackoverflow.com/questions/11932805/cropping-circular-area-from-bitmap-in-android"
     * Cut the image into circle shape.
     * @param bitmap bitmap to be cut.
     */
    public static Bitmap getRoundedBitmap(Bitmap bitmap) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final int len = Math.min(width, height);
        final Bitmap outputBitmap = Bitmap.createBitmap(len, len, Bitmap.Config.ARGB_8888);
        final Path path = new Path();
        path.addCircle(
                (float)(width / 2)
                , (float)(height / 2)
                , (float) Math.min((width / 2), (height / 2))
                , Path.Direction.CCW);

        final Canvas canvas = new Canvas(outputBitmap);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 0, 0, null);
        return outputBitmap;
    }
}
