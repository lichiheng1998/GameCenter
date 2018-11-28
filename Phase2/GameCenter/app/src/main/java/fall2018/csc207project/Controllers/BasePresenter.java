package fall2018.csc207project.Controllers;

import android.content.Context;
import android.net.Uri;

/**
 * The interface BasePresenter
 */
public interface BasePresenter {

    /**
     * Select the background by given a Uri and a Context.
     *
     * @param imageUri the image get that selected
     * @param context the context of the app
     */
    void onBackgroundSelected(Uri imageUri, Context context);

    /**
     * Select the avatar by given a Uri and a Context.
     *
     * @param imageUri the image get that selected
     * @param context the context of the app
     */
    void onAvatarSelected(Uri imageUri, Context context);

    /**
     * Reset the clicked by given a Context.
     *
     * @param context the context of the app
     */
    void onResetClicked(Context context);

    /**
     * Initialize the view by given a Context.
     *
     * @param context the context of the app
     */
    void initializeView(Context context);
}
