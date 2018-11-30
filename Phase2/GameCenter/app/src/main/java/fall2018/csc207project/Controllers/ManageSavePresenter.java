package fall2018.csc207project.Controllers;

import android.content.Context;

/**
 * The inter
 */
public interface ManageSavePresenter {
    void onSaveButtonClicked(int pos, Context context);
    void onLoadButtonClicked(int pos);
    void initView();
}
