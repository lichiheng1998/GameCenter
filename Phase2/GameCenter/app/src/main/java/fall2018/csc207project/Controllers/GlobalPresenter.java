package fall2018.csc207project.Controllers;

import android.app.Activity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.storage.FirebaseStorage;

public interface GlobalPresenter {
    void firebaseAuthWithGoogle(GoogleSignInAccount acct, final FirebaseStorage storage,
                                final Activity activity);
    boolean isLoggedIn();
}
