package fall2018.csc207project.PushTheBox.Controllers;

import fall2018.csc207project.PushTheBox.View.LevelView;

/**
 * The class LevelPresenter which interact with the level of the game.
 */
public class LevelPresenter {

    /**
     * The View of each level.
     */
    private LevelView view;

    /**
     * Text to be toasted.
     */
    public String text;

    /**
     * Construct a new LevelPresenter by given a LevelView.
     *
     * @param view the View of each level
     */
    public LevelPresenter(LevelView view){
        this.view = view;
    }

    public void setUndoSteps(int undoStep){
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
