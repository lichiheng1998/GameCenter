package fall2018.csc207project.Controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

/**
 * The interface BasePresenter
 */
public interface LocalGameCenterPresenter {

    /**
     * Select the background by given a Uri and a Context.
     *
     * @param imageUri the image get that selected
     * @param context the context of the app
     */
    void onBackgroundSelected(Uri imageUri, FirebaseStorage storage);


    void onAvatarSelected(Uri imageUri, FirebaseStorage storage);

    /**
     * Reset the clicked by given a Context.
     *
     * @param context the context of the app
     */
    void onResetClicked(Context context);

    void onLogOutClicked();

    void initializeView(FirebaseStorage storage);

    void onFabClicked(String game, FirebaseFirestore database);
}
