package fall2018.csc207project.Views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import fall2018.csc207project.R;

public class GlobalSignInActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_signin);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading_spinner);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Sign In");
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();
        setBackButtonListener();
        setSignUpButtonListener();
    }

    private void setBackButtonListener(){
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
            }
        });
    }

    private void setSignUpButtonListener(){
        findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithFireBase(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void signInWithFireBase(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            DynamicToast.makeError(GlobalSignInActivity.this, "Please Input Correct Info!").show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            finish();
                        } else {
                            DynamicToast.makeError(GlobalSignInActivity.this, "Account Info Incorrect!").show();
                        }
                    }
                });
    }
}
