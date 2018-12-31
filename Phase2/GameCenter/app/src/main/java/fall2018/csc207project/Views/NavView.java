package fall2018.csc207project.Views;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.firebase.storage.StorageReference;

/**
 * The Nav that display on screen.
 */
public interface NavView {

    /**
     * Display the background by pass in a Drawable.
     *
     * @param drawable a drawable tells the info of display
     */
    void showBackground(Drawable drawable);

    void showAvatar(StorageReference imgRef);

    void showAvatar(Uri imgRef);
}
