package fall2018.csc207project.Controllers;

import android.app.Activity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface GlobalPresenter {
    void firebaseAuthWithGoogle(GoogleSignInAccount acct, Activity activity);
    boolean isLoggedIn();
}
