package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fall2018.csc207project.Controllers.ManageSaveActivity;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.BoardManager;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {
    private String currentUser;
    private BoardManager boardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        this.currentUser = sharedData.getString("currentUser", null);
        String currentGame = sharedData.getString("currentGame", null);
        setContentView(R.layout.tile_game_starting);
        addStartButtonListener();
        addSaveButtonListener();
        addGlobalScoreButtonListener();
    }

    /**
     * Activate the start button.
     */

    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToComplexity();
            }
        });
    }

    /**
     * Activate the myScore button.
     */

    private void addGlobalScoreButtonListener() {
        Button myScoreButton = findViewById(R.id.global_score_button);
        myScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();;
            }
        });
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button loadButton = findViewById(R.id.SaveButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tmp = new Intent(StartingActivity.this, ManageSaveActivity.class);
                startActivity(tmp);
            }
        });
    }

    /**
     * Switch to the complexity selection view.
     */
    private void switchToComplexity() {
        Intent tmp = new Intent(this, ComplexityActivity.class);
        startActivity(tmp);
    }

    private void switchToScoreboard() {
        Intent tmp = new Intent(this, ScoreBoardActivity.class);
        startActivity(tmp);
    }
}
