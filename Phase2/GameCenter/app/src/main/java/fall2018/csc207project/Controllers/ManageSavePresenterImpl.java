package fall2018.csc207project.Controllers;

import android.content.Context;

import java.io.Serializable;

import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.Save;
import fall2018.csc207project.Models.SaveManager;
import fall2018.csc207project.Models.SaveSlot;
import fall2018.csc207project.Views.ManageSaveView;

public class ManageSavePresenterImpl implements ManageSavePresenter{
    private ManageSaveView view;
    private SaveSlot saveSlot;
    private SaveManager saveManager;

    public ManageSavePresenterImpl(ManageSaveView view, SaveManager manager, Context context){
        this.view = view;
        saveManager = manager;
        saveSlot = saveManager.readFromFile(context);
    }
    @Override
    public void onSaveButtonClicked(int pos, Context context) {
        Save save = saveSlot.readFromAutoSave();
        if (save == null){
            view.makeNotStartedText();
            return;
        }
        view.makeToastSavedText();
        saveSlot.saveToSlot(pos, save.data);
        saveManager.saveToFile(saveSlot, context);
        view.showInfo(pos, save.date.toString());
    }

    @Override
    public void onLoadButtonClicked(int pos) {
        Save save = pos == 3 ? saveSlot.readFromAutoSave():saveSlot.readFromSlot(pos);
        if (save == null) {
            view.makeNoSavedText();
            return;
        }
        view.launchGame((Serializable) save.data);
    }

    @Override
    public void initView() {
        for (int i = 0; i < 4; i++){
            Save save = i == 3 ? saveSlot.readFromAutoSave() : saveSlot.readFromSlot(i);
            if (save != null){
                view.showInfo(i, save.date.toString());
            }
        }
    }
}
