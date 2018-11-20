package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.BoardManager;

/**
 * The setting for SlidingTileGame activity.
 */
public class ComplexityActivity extends AppCompatActivity {

    private int undoStep;
    private int complexity;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        undoStep = 3;
        complexity = 3;
        setContentView(R.layout.complexity);
        addL3ButtonListener();
        addL4ButtonListener();
        addL5ButtonListener();
        addAcceptButtonListener();
    }

    /**
     * Activate the 3 x 3 button.
     */
    public void addL3ButtonListener(){
        Button l3Button = findViewById(R.id.button_3);
        l3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complexity = 3;
                switchToGame();
            }
        });
    }

    /**
     * Activate the 4 x 4 button.
     */
    public void addL4ButtonListener(){
        Button l3Button = findViewById(R.id.button_4);
        l3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complexity = 4;
                switchToGame();
            }
        });
    }

    /**
     * Activate the 5 x 5 button.
     */
    public void addL5ButtonListener(){
        Button l3Button = findViewById(R.id.button_5);
        l3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complexity = 5;
                switchToGame();
            }
        });
    }
    /**
     * Activate the undo steps setting button.
     */
    private void addAcceptButtonListener() {
        Button loginButton = findViewById(R.id.set_undo_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText UndoSteps = findViewById(R.id.TimesToUndo);
                String steps = UndoSteps.getText().toString();
                if (!steps.equals("")){
                    undoStep = Integer.parseInt(steps);
                    Toast.makeText(getApplicationContext(), "Successful setting",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter Something",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        tmp.putExtra("save", new BoardManager(complexity, undoStep));
        startActivity(tmp);
        finish();
    }
}
