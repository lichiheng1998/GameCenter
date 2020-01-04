package fall2018.csc207project.Views;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.Arrays;
import java.util.List;

import fall2018.csc207project.NewModels.UserManager;
import fall2018.csc207project.R;

public class GlobalSignUpActivity extends AppCompatActivity implements UserManager.OnUserImageUpdated,
        UserManager.OnUserNickNameChanged, UserManager.OnGameReady{
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private EditText nickName;
    private ProgressBar progressBar;
    private UserManager manager;
    private FirebaseStorage storage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_signup);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        nickName = findViewById(R.id.nickname);
        progressBar = findViewById(R.id.loading_spinner);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Sign Up");
        setSupportActionBar(myToolbar);

        storage = FirebaseStorage.getInstance();
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

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            manager = new UserManager(mAuth);
                            manager.updateNickName(nickName, GlobalSignUpActivity.this);
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            DynamicToast.makeError(GlobalSignUpActivity.this, "Sign Up Failed!").show();
                        }
                    }
                });
    }

    public Uri getUriFromResourceId(int resId){
        Resources resources = GlobalSignUpActivity.this.getApplicationContext().getResources();
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(resId))
                .appendPath(resources.getResourceTypeName(resId))
                .appendPath(resources.getResourceEntryName(resId))
                .build();
        return uri;
    }

    @Override
    public void onUserNickNameChanged(String name) {
        if(name == null){
            DynamicToast.makeError(this, "Can't set nick name!");
        }
        manager.updateUserProfileImage(getUriFromResourceId(R.drawable.default_avatar),this, storage);
    }

    @Override
    public void onUserProfileImageUpdated(StorageReference imgRef) {
        if(imgRef == null){
            DynamicToast.makeError(this, "Can't set profile picture!");
        }
        manager.addGame(Arrays.asList("Memorization Master", "Push The Box"),
                GlobalSignUpActivity.this, FirebaseFirestore.getInstance());
    }

    @Override
    public void onGameReady(List<String> game, boolean isAdded) {
        if (game == null){
            DynamicToast.makeError(this, "Can't set default games!");
        }
        progressBar.setVisibility(View.INVISIBLE);
        finish();
    }
}
