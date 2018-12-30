package fall2018.csc207project.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import fall2018.csc207project.R;

public class GlobalSignUpActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private EditText nickName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_signup);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        nickName = findViewById(R.id.nickname);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Sign Up");
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
        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpWithFireBase(email.getText().toString(), password.getText().toString(),
                        nickName.getText().toString());
            }
        });
    }

    private void signUpWithFireBase(String email, String password, final String nickName){
        if(email.isEmpty() || password.isEmpty() || nickName.isEmpty()){
            DynamicToast.makeError(GlobalSignUpActivity.this, "Please Input Correct Info!").show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nickName).build();

                            user.updateProfile(profileUpdates);
                            finish();
                        } else {
                            DynamicToast.makeError(GlobalSignUpActivity.this, "Sign Up Failed!").show();
                        }
                    }
                });
    }
}