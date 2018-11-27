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

public class SignUpActivity extends AppCompatActivity {

    private String userName = "";

    private String password = "";

    private UserManager userManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        userManager = DatabaseUtil.getUserManager();
        addSignUpButtonListener();
        addCancelButtonListener();
    }

    /**
     * Activate the cancel button
     */
    private void addCancelButtonListener(){
        Button cancelButton = findViewById(R.id.cancel_signup);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Activate the signUp button.
     */
    private void addSignUpButtonListener() {
        Button loginButton = findViewById(R.id.signup_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText User = findViewById(R.id.signup_username);
                EditText Password = findViewById(R.id.signup_password);
                userName = User.getText().toString();
                password = Password.getText().toString();
                if (userManager.signUp(userName, password, getApplicationContext())){
                    SharedPreferences sharedPref =
                            SignUpActivity.this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("currentUser", userName);
                    editor.commit();
                    localCenter();
                }else{
                    Toast.makeText(getApplicationContext(), "SignUp Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Switch to the LocalGameCenterActivity view for the current user that just signs up.
     */
    private void localCenter(){
        Intent tmp = new Intent(this, LocalGameCenterActivity.class);
        startActivity(tmp);
    }
}