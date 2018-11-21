package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fall2018.csc207project.PushTheBox.Models.Map;
import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.R;
import fall2018.csc207project.models.SaveManager;

public class BoxStartingActivity extends AppCompatActivity {
    private String currentUser;
    private SaveManager saveManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.box_starting);
        addNewGameButtonListener();
        addLoadButtonListener();
        addScoreButtonListener();
    }

    /**
     * Activate the start new game button.
     */
    private void addNewGameButtonListener(){
        Button newGameButton = findViewById(R.id.newBoxGame);
        newGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switchToBoxGame();
            }
        } );
    }

    /**
     * Activate the load auto save button.
     */
    private void addLoadButtonListener(){
        Button loadButton = findViewById(R.id.loadBoxGame);
        //TODO
    }

    /**
     * Activate the myScore button.
     */
    private void addScoreButtonListener(){
        Button myScoreButton = findViewById(R.id.boxScoreboard);
        //TODO
    }


    /**
     * Switch to the Push the Box game view.
     */
    private void switchToBoxGame(){
        Intent tmp = new Intent (this, BoxGameActivity.class);
        startActivity(tmp);
    }

//    /**
//     * Switch to the level selection view.
//     */
//    private void switchToSelectLevel(){
//        Intent tmp = new Intent (this, BoxLevelActivity.class);
//        startActivity(tmp);
//    }
//



}
