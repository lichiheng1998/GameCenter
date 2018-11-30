package fall2018.csc207project.Controllers;

import android.content.Context;

/**
 * The interface ManageSavePresenter that manage the game saves.
 */
public interface ManageSavePresenter {

    /**
     * Activate the save button by given the save button's position
     * and the context of the app.
     *
     * @param pos the save button's position
     * @param context the context of the app
     */
    void onSaveButtonClicked(int pos, Context context);

    /**
     * Activate the load button by given the load button's position
     * and the context of the app.
     *
     * @param pos the load button's position
     */
    void onLoadButtonClicked(int pos);

    /**
     * Initialize the View.
     */
    void initView();
}
