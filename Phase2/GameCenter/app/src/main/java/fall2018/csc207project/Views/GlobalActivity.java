package fall2018.csc207project.Views;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityOptionsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import fall2018.csc207project.Controllers.GlobalPresenter;
import fall2018.csc207project.Controllers.GlobalPresenterImpl;
import fall2018.csc207project.R;

/**
 * The initial activity after user run the app
 */
public class GlobalActivity extends AppCompatActivity implements GlobalView{

    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;
    private GlobalPresenter presenter;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(presenter.isLoggedIn()){
            switchToGameCenter();
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global);

        progressBar = findViewById(R.id.loading_spinner);

        presenter = new GlobalPresenterImpl(this, FirebaseFirestore.getInstance());
        // Initialize Firebase Auth
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        setGoogleButtonListener();
        setSignUpButtonListener();
        setSignInButtonListener();
    }
    public void setGoogleButtonListener(){
        findViewById(R.id.googleSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    public void setSignUpButtonListener(){
        findViewById(R.id.SignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(GlobalActivity.this, findViewById(R.id.logoImage), "logo");
                Intent tmp = new Intent(GlobalActivity.this, GlobalSignUpActivity.class);
                startActivity(tmp, options.toBundle());
            }
        });
    }

    public void setSignInButtonListener(){
        findViewById(R.id.SignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(GlobalActivity.this, findViewById(R.id.logoImage), "logo");
                Intent tmp = new Intent(GlobalActivity.this, GlobalSignInActivity.class);
                startActivity(tmp, options.toBundle());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.firebaseAuthWithGoogle(account, FirebaseStorage.getInstance(), this);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                loginWithGoogleFailed();
            }
        }
    }

    @Override
    public void loginWithGoogleFailed() {
        DynamicToast.makeError(this, getResources().getString(R.string.login_failed)).show();
    }

    @Override
    public void switchToGameCenter(){
        DynamicToast.make(this, "Game Started!").show();
        Intent tmp = new Intent(this, LocalGameCenterActivity.class);
        startActivity(tmp);
    }

    @Override
    public void updateProgressBar(boolean visibility) {
        int code = visibility ? View.VISIBLE : View.INVISIBLE;
        progressBar.setVisibility(code);
    }
}