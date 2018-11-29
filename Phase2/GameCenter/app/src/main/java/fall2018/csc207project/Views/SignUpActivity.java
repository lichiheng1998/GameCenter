package fall2018.csc207project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fall2018.csc207project.Controllers.UserPresenter;
import fall2018.csc207project.Controllers.UserPresenterImpl;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.UserManager;
import fall2018.csc207project.R;

/**
 * The class SignUpActivity that extends AppCompatActivity and implements SignUpView.
 */
public class SignUpActivity extends AppCompatActivity implements SignUpView{

    /**
     * The UserPresenter that interact with SignUpActivity
     */
    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        UserManager userManager = DatabaseUtil.getUserManager();
        presenter = new UserPresenterImpl(userManager, this);
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
                String userName = User.getText().toString();
                String password = Password.getText().toString();
                presenter.signUpUser(userName, password, getApplicationContext()); }
        });
    }

    @Override
    public void makeSignUpFailedText(){
        Toast.makeText(getApplicationContext(), "SignUp Failed",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void localCenter(){
        Intent tmp = new Intent(this, LocalGameCenterActivity.class);
        startActivity(tmp);
    }
}