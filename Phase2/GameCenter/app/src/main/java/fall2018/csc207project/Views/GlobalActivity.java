package fall2018.csc207project.Views;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import fall2018.csc207project.Controllers.GlobalPresenter;
import fall2018.csc207project.Controllers.GlobalPresenterImpl;
import fall2018.csc207project.R;

/**
 * The initial activity after user run the app
 */
public class GlobalActivity extends AppCompatActivity implements GlobalView{

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
        presenter = new GlobalPresenterImpl(this);
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
                presenter.firebaseAuthWithGoogle(account, this);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                loginWithGoogleFailed();
            }
        }
    }

    @Override
    public void loginWithGoogleFailed() {
        Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchToGameCenter(){
        Toast.makeText(this, "GameStarted!", Toast.LENGTH_SHORT).show();
    }
}