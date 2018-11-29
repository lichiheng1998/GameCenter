package fall2018.csc207project.Views;

import android.graphics.drawable.Drawable;

/**
 * The Nav that display on screen.
 */
public interface NavView {

    /**
     * Display the background by pass in a Drawable.
     *
     * @param drawable a drawable tells the info of display
     */
    void showBackground(Drawable drawable);

    /**
     * Display the avatar by pass in a Drawable.
     *
     * @param drawable a drawable tells the info of display
     */
    void showAvatar(Drawable drawable);
}
