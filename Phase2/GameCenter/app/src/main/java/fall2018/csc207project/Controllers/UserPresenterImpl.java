package fall2018.csc207project.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import fall2018.csc207project.Models.UserManager;
import fall2018.csc207project.Views.LoginView;
import fall2018.csc207project.Views.SignUpView;

/**
 * The UserPresenterImpl implements UserPresenter
 */
public class UserPresenterImpl implements UserPresenter {

    /**
     * The UserManager that manages the user.
     */
    private UserManager userManager;

    /**
     * The SignUpView that show the SignUp screen.
     */
    private SignUpView signupView;

    /**
     * The LoginView that show the Login screen.
     */
    private LoginView loginView;

    /**
     * Construct a new UserPresenterImpl
     * by given a UserManager and a SignUpView.
     *
     * @param manager the UserManager that manages the user
     * @param view the SignUpView that show the SignUp screen
     */
    public UserPresenterImpl(UserManager manager, SignUpView view){
        userManager = manager;
        signupView = view;
    }

    /**
     * Construct a new UserPresenterImpl
     * by given a UserManager and a LoginView.
     *
     * @param manager the UserManager that manages the user
     * @param view the LoginView that show the Login screen
     */
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
            editor.apply();
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
            editor.apply();
            signupView.localCenter();
        }else{
            signupView.makeSignUpFailedText();
        }
    }
}
