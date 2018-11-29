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

public class SignInActivity extends AppCompatActivity implements LoginView{

    private UserPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserManager userManager = DatabaseUtil.getUserManager();
        presenter = new UserPresenterImpl(userManager, this);
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
                String userName = User.getText().toString();
                String password = Password.getText().toString();
                presenter.loginUser(userName, password, getApplicationContext());
            }
        });
    }

    public void makeLoginFailedText(){
        Toast.makeText(getApplicationContext(), "Login Failed",
                Toast.LENGTH_SHORT).show();
    }
    /**
     * Switch to the LocalGameCenterActivity view for the current user that just logs in.
     */
    public void localCenter(){
        Intent tmp = new Intent(this, LocalGameCenterActivity.class);
        startActivity(tmp);
    }
}
