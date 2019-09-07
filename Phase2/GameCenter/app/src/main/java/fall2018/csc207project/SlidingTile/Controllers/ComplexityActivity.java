package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.BoardManager;
import fall2018.csc207project.SlidingTile.Views.GameActivity;

/**
 * The class ComplexityActivity setting for SlidingTileGame activity
 * extends AppCompatActivity.
 */
public class ComplexityActivity extends AppCompatActivity {

    /**
     * the total undo steps for user to use in this game.
     */
    private int undoStep;

    /**
     * The complexity of the SlidingTileGame can be 3x3, 4x4 or 5x5
     */
    private int complexity;

    @Override
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
        Button loginButton = findViewById(R.id.SetUndoButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText UndoSteps = findViewById(R.id.TimesToUndo);
                String steps = UndoSteps.getText().toString();
                undoStep = Integer.parseInt(steps);
                Toast.makeText(getApplicationContext(),
                        "Successfully set the Total Undo Steps to: " + undoStep,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        tmp.putExtra("save", new BoardManager(complexity, undoStep));
        if (undoStep == 3) {
            Toast.makeText(getApplicationContext(),
                    "The Total Undo Steps set to default value: 3",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "The Total Undo Steps set to: " + undoStep,
                    Toast.LENGTH_SHORT).show();
        }
        startActivity(tmp);
        finish();
    }
}
