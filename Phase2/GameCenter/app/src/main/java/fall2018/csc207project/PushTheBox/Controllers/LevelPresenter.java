package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc207project.PushTheBox.View.LevelView;

public class LevelPresenter {

    private LevelView view;

    /**
     * Text to be toasted.
     */
    public String text;


    public LevelPresenter(LevelView view){
        this.view = view;
    }

    public void undoStepsSetted(int undoStep){
        if (undoStep == 3) {
            text = "The Total Undo Steps set to default value: 3";
        } else {
            text = "The Total Undo Steps set to: " + undoStep;
        }
        makeToast();
    }

    public void acceptButtonClicked(String steps){
        int undoStep = Integer.parseInt(steps);
        text = "Successfully set the Total Undo Steps to: " + undoStep;
        makeToast();
    }

    private void makeToast(){
        view.makeToastText(text);
    }
}
