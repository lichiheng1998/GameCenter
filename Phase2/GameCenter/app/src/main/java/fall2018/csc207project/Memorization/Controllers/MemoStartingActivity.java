package fall2018.csc207project.Memorization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import fall2018.csc207project.R;

/**
 * Class represent the StartingActivity of the memorization master game.
 */
public class MemoStartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_game_starting);
        addNewGameButtonListener();
        addMyTpoScoreButtonListener();
    }

    /**
     * Setup the listener for the new game button.
     */
    private void addNewGameButtonListener(){
        Button newGameButton = findViewById(R.id.newMemogame);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToBoardsSize();
            }
        });
    }

    /**
     * Setup the listener for the switch to board size button.
     */
    private void switchToBoardsSize() {
        Intent tmp = new Intent(this, MemoComplexActivity.class);
        startActivity(tmp);
    }

    /**
     * Setup the listener for the switch to board size button.
     */
    private void addMyTpoScoreButtonListener(){
        Button myScoreButton = findViewById(R.id.memogameScoreboard);
        myScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Switch the current slide to the ScoreBoard slide.
     */
    private void switchToScoreboard() {
        Intent tmp = new Intent(this, MemoScoreBoardActivity.class);
        startActivity(tmp);
    }
}
