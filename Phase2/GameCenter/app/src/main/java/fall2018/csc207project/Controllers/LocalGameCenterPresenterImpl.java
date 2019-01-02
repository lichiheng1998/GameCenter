package fall2018.csc207project.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import fall2018.csc207project.NewModels.UserManager;
import fall2018.csc207project.Views.NavView;

/**
 * The class NavPresenter implements BasePresenter
 */
public class LocalGameCenterPresenterImpl implements LocalGameCenterPresenter{

    /**
     * The NavView for the current user.
     */
    private NavView view;

    /**
     * The ImageManager to manage images.
     */
    private UserManager manager;

    /**
     * Construct a new NavPresenter by given a NavView and an ImageManager.
     *
     * @param view the NavView for the current user
     * @param manager the ImageManager to manage images
     */
    public LocalGameCenterPresenterImpl(NavView view, UserManager manager){
        this.manager = manager;
        this.view = view;
    }


    @Override
    public void onResetClicked(Context context) {

    }

    @Override
    public void onBackgroundSelected(Uri imageUri, FirebaseStorage storage) {

    }

    @Override
    public void onAvatarSelected(Uri imageUri, FirebaseStorage storage) {
        view.showAvatar(imageUri);
        manager.updateUserProfileImage(imageUri, storage);
    }

    @Override
    public void initializeView(FirebaseStorage storage) {
        view.showAvatar(manager.getUserProfileImage(storage));
        view.showUserName(manager.getUserNickName());
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
}
