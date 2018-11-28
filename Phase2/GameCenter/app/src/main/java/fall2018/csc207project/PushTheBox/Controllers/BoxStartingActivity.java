package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import fall2018.csc207project.Controllers.ManageSaveActivity;
import fall2018.csc207project.PushTheBox.View.LevelActivity;
import fall2018.csc207project.R;

// excluded from tests because it's a view class
public class BoxStartingActivity extends AppCompatActivity {
    private String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        this.currentUser = sharedData.getString("currentUser", null);

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
                Intent tmp = new Intent(BoxStartingActivity.this, ManageSaveActivity.class);
                startActivity(tmp);
            }
        });
    }

    /**
     * Activate the myScore button.
     */
    private void addScoreButtonListener(){
        Button scoreButton = findViewById(R.id.boxScoreboard);
        scoreButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent tmp = new Intent(BoxStartingActivity.this, BoxScoreBoardActivity.class);
                startActivity(tmp);
            }
        });
    }

    /**
     * Switch to the level selection view.
     */
    private void switchToSelectLevel(){
        Intent tmp = new Intent (this, LevelActivity.class);
        startActivity(tmp);
    }
}
