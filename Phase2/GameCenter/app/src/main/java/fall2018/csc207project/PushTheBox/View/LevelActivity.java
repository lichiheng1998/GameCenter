package fall2018.csc207project.PushTheBox.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import fall2018.csc207project.PushTheBox.Controllers.LevelAdapter;
import fall2018.csc207project.PushTheBox.Controllers.LevelPresenter;
import fall2018.csc207project.R;

/**
 * The class LevelActivity that extends AppCompatActivity and implements LevelView.
 */
public class LevelActivity extends AppCompatActivity implements LevelView {

    /**
     * The grid view for level buttons.
     */
    GridView gridView;

    /**
     * The list of buttons for levels.
     */
    private List<Button> levelButtons = new ArrayList<>();

    /**
     * number of steps for undo.
     */
    private int undoStep;

    /**
     * Presenter of level page.
     */
    private LevelPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.presenter = new LevelPresenter(this);
        undoStep = 3;
        setContentView(R.layout.box_levels);
        gridView = findViewById(R.id.boxLevelGrid);
        createLevelButtons();
        addAcceptButtonListener();
        display();
    }

    /**
     * Create all the buttons with numbers indicating levels.
     */
    @SuppressLint("SetTextI18n")
    public void createLevelButtons(){
        int buttonColor = android.graphics.Color.argb(255, 168, 193,164);
        for (int i = 0; i < 9; i++){
            Button tmp = new Button(getApplicationContext());
            tmp.setTextSize(40);
            tmp.setText(Integer.toString(i+1));
            tmp.setBackgroundColor(buttonColor);
            tmp.setStateListAnimator(null);
            tmp.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    int level = Integer.parseInt(button.getText().toString());
                    switchToGame(level);
                }
            });
            levelButtons.add(tmp);
        }
    }

    /**
     * Activate the undo steps setting button.
     */
    private void addAcceptButtonListener() {
        Button loginButton = findViewById(R.id.SetUndoButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText UndoSteps = findViewById(R.id.SetUndoTimes);
                String steps = UndoSteps.getText().toString();
                presenter.acceptButtonClicked(steps);
            }
        });
    }

    /**
     * Switch to game with given level.
     *
     * @param level level of game to start
     */
    private void switchToGame(int level) {
        Intent tmp = new Intent(this, BoxGameActivity.class);
        tmp.putExtra("undoStep",undoStep);
        tmp.putExtra("level",level);
        presenter.setUndoSteps(undoStep);
        startActivity(tmp);
        finish();
    }

    @Override
    public void makeToastText(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Display the grid with all levels.
     */
    public void display(){
        gridView.setAdapter(new LevelAdapter(levelButtons));
    }
}
