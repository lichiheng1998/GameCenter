package fall2018.csc207project.Controllers;

import android.content.Context;
import android.net.Uri;

import fall2018.csc207project.Models.ImageManager;

public interface BasePresenter {
    void onBackgroundSelected(Uri imageUri, Context context);
    void onAvatarSelected(Uri imageUri, Context context);
    void onResetClicked(Context context);
    void initializeView(Context context);
}
