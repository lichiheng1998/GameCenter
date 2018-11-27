package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc207project.PushTheBox.View.LevelView;

public class LevelPresenter {

    private LevelView view;

    public LevelPresenter(LevelView view, Context context){
        this.view = view;
    }

    public void undoStepsSetted(int undoStep){
        String text;
        if (undoStep == 3) {
            text = "The Total Undo Steps set to default value: 3";
        } else {
            text = "The Total Undo Steps set to: " + undoStep;
        }
        view.makeToastText(text);
    }

    public void acceptButtonClicked(String steps){
        int undoStep = Integer.parseInt(steps);
        String text = "Successfully set the Total Undo Steps to: " + undoStep;
        view.makeToastText(text);
    }
}
