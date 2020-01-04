package fall2018.csc207project.Views;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Map;

/**
 * The Nav that display on screen.
 */
public interface NavView {

    /**
     * Display the background by pass in a Drawable.
     *
     * @param drawable a drawable tells the info of display
     */
    void showBackground(StorageReference imgRef);

    void showAvatar(StorageReference imgRef);

    void showUserName(String userName);

    void prepareGameList(Map<String, StorageReference> gameCollection, List<String> gameList);

    void finish();
}
