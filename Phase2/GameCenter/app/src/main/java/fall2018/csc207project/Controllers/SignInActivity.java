package fall2018.csc207project.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.UserManager;
import fall2018.csc207project.R;
import fall2018.csc207project.Views.LocalGameCenterActivity;

/**
 * The class SignInActivity that extends AppCompatActivity
 */
public class SignInActivity extends AppCompatActivity {

    /**
     * The user's name that need to sign in to the Game Center.
     */
    private String userName = "";

    /**
     * The user's password that need to sign in to the Game Center.
     */
    private String password = "";

    /**
     * The UserManager to manage the users.
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = DatabaseUtil.getUserManager();
        setContentView(R.layout.sign_in);
        addLoginButtonListener();
        addCancelButtonListener();
    }

    /**
     * Activate the cancel button
     */
    private void addCancelButtonListener(){
        Button cancelButton = findViewById(R.id.cancel_signin);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Activate the login button.
     */
    private void addLoginButtonListener() {
        Button loginButton = findViewById(R.id.signin_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText User = findViewById(R.id.signin_username);
                EditText Password = findViewById(R.id.signin_password);
                userName = User.getText().toString();
                password = Password.getText().toString();
                if(userManager.signIn(userName, password, getApplicationContext())){
                    SharedPreferences sharedPref =
                            SignInActivity.this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("currentUser", userName);
                    editor.apply();
                    localCenter();
                }else{
                    Toast.makeText(getApplicationContext(), "Login Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Switch to the LocalGameCenterActivity view for the current user that just logs in.
     */
    private void localCenter(){
        Intent tmp = new Intent(this, LocalGameCenterActivity.class);
        startActivity(tmp);
    }
}
