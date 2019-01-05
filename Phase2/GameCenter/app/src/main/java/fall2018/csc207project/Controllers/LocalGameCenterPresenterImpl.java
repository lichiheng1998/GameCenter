package fall2018.csc207project.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.Map;

import fall2018.csc207project.NewModels.GlobalGameManager;
import fall2018.csc207project.NewModels.UserManager;
import fall2018.csc207project.Views.NavView;

/**
 * The class NavPresenter implements BasePresenter
 */
public class LocalGameCenterPresenterImpl implements LocalGameCenterPresenter,
        UserManager.OnUserProfileImageUpdated,  GlobalGameManager.GameReceiver,
        UserManager.OnGameListReady{

    /**
     * The NavView for the current user.
     */
    private NavView view;

    /**
     * The ImageManager to manage images.
     */
    private UserManager manager;

    private Map<String, StorageReference> gameCollection;
    private GlobalGameManager gameManager;

    /**
     * Construct a new NavPresenter by given a NavView and an ImageManager.
     *
     * @param view the NavView for the current user
     * @param manager the ImageManager to manage images
     */
    public LocalGameCenterPresenterImpl(NavView view, UserManager manager,
                                        GlobalGameManager gameManager){
        this.manager = manager;
        this.view = view;
        this.gameManager = gameManager;
    }


    @Override
    public void onResetClicked(Context context) {

    }

    @Override
    public void onBackgroundSelected(Uri imageUri, FirebaseStorage storage) {

    }

    @Override
    public void onAvatarSelected(Uri imageUri, FirebaseStorage storage) {
        manager.updateUserProfileImage(imageUri, this, storage);
    }

    @Override
    public void initializeView(FirebaseStorage storage) {
        view.showAvatar(manager.getUserProfileImage(storage));
        view.showUserName(manager.getUserNickName());
        gameManager.getGameCollection(this, storage);
    }

    @Override
    public void onLogOutClicked() {
        manager.logOut();
        view.finish();
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

    @Override
    public void onUserProfileImageUpdated(StorageReference imageUri) {
        if(imageUri != null){
            view.showAvatar(imageUri);
        }
    }

    @Override
    public void onGameCollectionReady(Map<String, StorageReference> gameCollection) {
        this.gameCollection = gameCollection;
        manager.getGameList(this, FirebaseFirestore.getInstance());
    }

    @Override
    public void onGameListReady(List<String> gameList) {
        view.prepareGameList(gameCollection, gameList);
    }
}
