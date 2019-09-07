package fall2018.csc207project.Controllers;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;
import java.util.List;

import fall2018.csc207project.Models.GlobalConfig;
import fall2018.csc207project.NewModels.UserManager;
import fall2018.csc207project.R;
import fall2018.csc207project.Views.GlobalView;

public class GlobalPresenterImpl implements GlobalPresenter, UserManager.OnUserProfileImageUpdated,
        UserManager.OnGameReady{

    private FirebaseAuth mAuth;
    private GlobalView view;
    private UserManager manager;
    private FirebaseFirestore database;

    public GlobalPresenterImpl(GlobalView view, FirebaseFirestore database){
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
        this.database = database;
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, final FirebaseStorage storage,
                                       final Activity activity) {
        view.updateProgressBar(true);
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            manager = new UserManager(GlobalPresenterImpl.this.mAuth);
                            manager.updateUserProfileImage(getUriFromResourceId(R.drawable.default_avatar, activity),
                                    GlobalPresenterImpl.this, storage);
                        } else {
                            view.loginWithGoogleFailed();
                        }
                        view.updateProgressBar(false);
                    }
                });
    }

    @Override
    public boolean isLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    public Uri getUriFromResourceId(int resId, Context context){
        Resources resources = context.getResources();
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(resId))
                .appendPath(resources.getResourceTypeName(resId))
                .appendPath(resources.getResourceEntryName(resId))
                .build();
        return uri;
    }

    @Override
    public void onUserProfileImageUpdated(StorageReference name) {
        manager.addGame(Arrays.asList(GlobalConfig.GAME_LIST), this, database);
    }

    @Override
    public void onGameReady(List<String> game, boolean isAdded) {
        view.switchToGameCenter();
    }
}
