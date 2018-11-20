package fall2018.csc207project.Memorization.controllers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fall2018.csc207project.R;

public class MemorizationGameActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_game_starting);
        addNewGameButtonListener();
        addMyTopScoresButtonListener();
        addTopPlayersButtonListener();
    }


    private void addNewGameButtonListener(){
        Button newGameButton = findViewById(R.id.newMemogame);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToBoardsSize();
            }
        });
    }

    private void switchToBoardsSize() {
    }

    private void addMyTopScoresButtonListener(){}

    private void addTopPlayersButtonListener() {
    }
}
