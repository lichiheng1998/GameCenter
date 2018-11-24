package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.BoardManager;
import fall2018.csc207project.SlidingTile.Models.TileGameCalculator;
import fall2018.csc207project.SlidingTile.Models.TileScore;
import fall2018.csc207project.models.DataStream;
import fall2018.csc207project.models.DatabaseUtil;
import fall2018.csc207project.models.SaveManager;
import fall2018.csc207project.models.Score;
import fall2018.csc207project.models.ScoreCalculator;
import fall2018.csc207project.models.ScoreManager;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {
    private String currentUser;
    private SaveManager saveManager;
    private BoardManager boardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        this.currentUser = sharedData.getString("currentUser", null);
        this.saveManager = DatabaseUtil.getSaveManager(currentUser,
                sharedData.getString("currentGame", null));
        String currentGame = sharedData.getString("currentGame", null);
        ScoreManager<TileScore> scoreManager =
                DatabaseUtil.getScoreManager(currentGame, currentUser, new TileGameCalculator());
//        scoreManager.saveScore(new TileScore(0), getApplicationContext());
        Score score = scoreManager.getScoresOfUser(getApplicationContext()).get(0);
        Log.e("Test", "onCreate: " + "User: " + score.user + " " + score.game + " " + score.value);
        setContentView(R.layout.tile_game_starting);
        addStartButtonListener();
        addLoadButtonListener();
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
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        Button loadAutoButton = findViewById(R.id.LoadAuto);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(saveManager.readFromSlot(false, getApplicationContext()));
            }
        });
        loadAutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(saveManager.readFromSlot(true, getApplicationContext()));
            }
        });
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        final Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object save = saveManager.readFromSlot(true, getApplicationContext());
                if(save == null){
                    makeNotStartedText();
                } else {
                    saveManager.saveToSlot(save, false, getApplicationContext());
                    makeToastSavedText();
                }
            }
            });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game that has no saves.
     */
    private void makeNoSavedText() {
        Toast.makeText(this, "No Save!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that the game hasn't started yet.
     */
    private void makeNotStartedText() {
        Toast.makeText(this, "Game is not started!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the complexity selection view.
     */
    private void switchToComplexity() {
        Intent tmp = new Intent(this, ComplexityActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the GameActivity view to play the game.
     * @param save the save to be restored.
     */
    private void switchToGame(Object save) {
        if(save == null){
            makeNoSavedText();
        } else {
            boardManager = (BoardManager)save;
            Intent tmp = new Intent(this, GameActivity.class);
            tmp.putExtra("save", boardManager);
            startActivity(tmp);
        }
    }

    private void switchToScoreboard() {
//        Intent tmp = new Intent(this, Scoreboard.class);
//        tmp = tmp.putExtra("GlobalCenter", globalCenter);
//        startActivity(tmp);
    }
}
