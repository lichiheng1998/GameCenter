package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.TileGameCalculator;
import fall2018.csc207project.SlidingTile.Models.TileScore;
import fall2018.csc207project.models.DatabaseUtil;
import fall2018.csc207project.models.ScoreManager;

public class ScoreBoardActivity extends AppCompatActivity {

    private ScoreManager scoreManager;
    private ArrayList<TileScore> sortList;
    private String currentUser;
    private TileGameCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        SharedPreferences shared = this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        currentUser = shared.getString("currentUser", null);
        calculator = new TileGameCalculator();
        DatabaseUtil.getScoreManager("SlidingTail", this.currentUser, calculator);
        setContentView(R.layout.tile_game_score_board);
        add3ButtonListener();
        add4ButtonListener();
        add5ButtonListener();
    }

    public void add3ButtonListener (){
        Button Level3Listener = findViewById(R.id.button3x3);
        Level3Listener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                switchToLevel3();
            }
        });
    }

    public void add4ButtonListener() {
        Button Level4Listener = findViewById(R.id.button4x4);
//        Level4Listener.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToLevel4();
//            }
//        });
    }
    public void add5ButtonListener(){
        Button Level3Listener = findViewById(R.id.button5x5);
//        Level3Listener.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToLevel5();
//            }
//        });
    }
}
