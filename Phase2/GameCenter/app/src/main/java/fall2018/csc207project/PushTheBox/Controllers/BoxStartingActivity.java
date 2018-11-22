package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fall2018.csc207project.PushTheBox.Models.Map;
import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.R;
import fall2018.csc207project.models.DatabaseUtil;
import fall2018.csc207project.models.SaveManager;

public class BoxStartingActivity extends AppCompatActivity {
    private String currentUser;
    private SaveManager saveManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        this.currentUser = sharedData.getString("currentUser", null);
        this.saveManager = DatabaseUtil.getSaveManager(currentUser,
                sharedData.getString("currentGame", null));

        setContentView(R.layout.box_starting);
        addNewGameButtonListener();
        addLoadButtonListener();
        //addScoreButtonListener();
    }

    /**
     * Activate the start new game button.
     */
    private void addNewGameButtonListener(){
        Button newGameButton = findViewById(R.id.newBoxGame);
        newGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switchToSelectLevel();
            }
        } );
    }

    /**
     * Activate the load auto save button.
     */
    private void addLoadButtonListener(){
        Button loadButton = findViewById(R.id.loadBoxGame);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(saveManager.readFromSlot(true, getApplicationContext()));
            }
        });
    }

    /**
     * Activate the myScore button.
     */
    private void addScoreButtonListener(){
        Button myScoreButton = findViewById(R.id.boxScoreboard);
        //TODO
    }


    /**
     * Switch to the the saved activity view to play the saved game.
     * @param save the saved mapManager to be restored.
     */
    private void switchToGame(Object save) {
        if (save == null){
            makeNoPlayedText();
        }else{
            MapManager mapManager = (MapManager)save;
            Intent tmp = new Intent (this, BoxGameActivity.class);
            tmp.putExtra("save", mapManager);
            startActivity(tmp);
        }
    }

    /**
     * Display that there is no game played yet.
     */
    private void makeNoPlayedText(){
        Toast.makeText(this, "You haven't play yet!",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the level selection view.
     */
    private void switchToSelectLevel(){
        Intent tmp = new Intent (this, LevelActivity.class);
        startActivity(tmp);
    }




}
