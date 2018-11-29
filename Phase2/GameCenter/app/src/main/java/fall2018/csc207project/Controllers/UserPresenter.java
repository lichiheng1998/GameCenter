package fall2018.csc207project.Controllers;

import android.content.Context;

public interface UserPresenter {
    void loginUser(String name, String password, Context context);
    void signUpUser(String name, String password, Context context);
}
