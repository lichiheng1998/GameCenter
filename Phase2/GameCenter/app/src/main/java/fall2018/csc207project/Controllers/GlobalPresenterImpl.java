package fall2018.csc207project.Controllers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import fall2018.csc207project.Views.GlobalView;

public class GlobalPresenterImpl implements GlobalPresenter{

    private FirebaseAuth mAuth;
    private GlobalView view;

    public GlobalPresenterImpl(GlobalView view){
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, Activity activity) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            view.switchToGameCenter();
                        } else {
                            view.loginWithGoogleFailed();
                        }
                    }
                });
    }

    @Override
    public boolean isLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }
}
