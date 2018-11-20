package fall2018.csc207project.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fall2018.csc207project.R;
import fall2018.csc207project.models.DataStream;
import fall2018.csc207project.models.GlobalDataStream;

/**
 * The initial activity after user run the app
 */
public class GlobalActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global);
        addSignInButtonListener();
        addSignUPButtonListener();
    }
    /**
     * Switch to the SignIn view to sign in.
     */
    public void switchToSignIn() {
        Intent tmp = new Intent(this, SignInActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the SignUp view to sign up.
     */
    public void switchToSignUp() {
        Intent tmp = new Intent(this, SignUpActivity.class);
        startActivity(tmp);
    }

    /**
     * Activate the signIn button.
     */
    private void addSignInButtonListener() {
        Button startButton = findViewById(R.id.signIn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignIn();
            }
        });
    }

    /**
     * Activate the signUp button.
     */
    private void addSignUPButtonListener() {
        Button startButton = findViewById(R.id.signUp);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignUp();
            }
        });
    }
}