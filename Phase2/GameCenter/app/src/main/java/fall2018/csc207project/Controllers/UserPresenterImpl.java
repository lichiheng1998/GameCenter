package fall2018.csc207project.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import fall2018.csc207project.Models.UserManager;
import fall2018.csc207project.Views.LoginView;
import fall2018.csc207project.Views.SignUpView;

public class UserPresenterImpl implements UserPresenter {
    private UserManager userManager;
    private SignUpView signupView;
    private LoginView loginView;

    public UserPresenterImpl(UserManager manager, SignUpView view){
        userManager = manager;
        signupView = view;
    }

    public UserPresenterImpl(UserManager manager, LoginView view){
        userManager = manager;
        loginView = view;
    }

    @Override
    public void loginUser(String name, String password, Context context) {
        if(userManager.signIn(name, password, context)){
            SharedPreferences sharedPref =
                    context.getSharedPreferences("GameData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("currentUser", name);
            editor.commit();
            loginView.localCenter();
        }else{
            loginView.makeLoginFailedText();
        }
    }

    @Override
    public void signUpUser(String name, String password, Context context) {
        if (userManager.signUp(name, password, context)){
            SharedPreferences sharedPref =
                    context.getSharedPreferences("GameData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("currentUser", name);
            editor.commit();
            signupView.localCenter();
        }else{
            signupView.makeSignUpFailedText();
        }
    }
}
