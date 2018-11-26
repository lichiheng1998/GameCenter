package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.SlidingTileScoreManager;
import fall2018.csc207project.SlidingTile.Models.TileGameCalculator;
import fall2018.csc207project.SlidingTile.Models.TileScore;
import fall2018.csc207project.models.DatabaseUtil;
import fall2018.csc207project.models.ScoreManager;

public class ScoreBoardActivity extends AppCompatActivity {

    private SlidingTileScoreManager scoreManager;
    private ArrayList<TileScore> sortList;
    private TileScore [][] personalList;
    private String currentUser;
    private TileGameCalculator calculator;
    private ListView scoreList;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        SharedPreferences shared = this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        currentUser = shared.getString("currentUser", null);
        calculator = new TileGameCalculator();
        ScoreManager <TileScore> globalScoreManager;
        globalScoreManager = DatabaseUtil.getScoreManager("SlidingTail", this.currentUser, calculator);
        this.scoreManager = new SlidingTileScoreManager(globalScoreManager);
        setContentView(R.layout.tile_game_score_board);
        switchToLevel3();
        addButtonListeners();
    }

    public void addButtonListeners(){
        add3ButtonListener();
        add4ButtonListener();
        add5ButtonListener();
    }
    public void setTexts(){
        ((TextView)findViewById(R.id.currentPlayer)).setText(currentUser);
        ((TextView)findViewById(R.id.playerscore3x1)).setText(this.personalList[0][0].value +"");
        ((TextView)findViewById(R.id.playerscore3x2)).setText(this.personalList[0][1].value +"");
        ((TextView)findViewById(R.id.playerscore3x3)).setText(this.personalList[0][2].value +"");
        ((TextView)findViewById(R.id.playerscore4x1)).setText(this.personalList[1][0].value +"");
        ((TextView)findViewById(R.id.playerscore4x2)).setText(this.personalList[1][1].value +"");
        ((TextView)findViewById(R.id.playerscore4x3)).setText(this.personalList[1][2].value +"");
        ((TextView)findViewById(R.id.playerscore5x1)).setText(this.personalList[2][0].value +"");
        ((TextView)findViewById(R.id.playerscore5x2)).setText(this.personalList[2][1].value +"");
        ((TextView)findViewById(R.id.playerscore5x3)).setText(this.personalList[2][2].value +"");


    }

    public void add3ButtonListener (){
        Button Level3Listener = findViewById(R.id.button3x3);
        Level3Listener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLevel3();
            }
        });
    }

    public void add4ButtonListener() {
        Button Level4Listener = findViewById(R.id.button4x4);
        Level4Listener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLevel4();
            }
        });
    }
    public void add5ButtonListener(){
        Button Level3Listener = findViewById(R.id.button5x5);
        Level3Listener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLevel5();
            }
        });
    }
    public void switchToLevel3(){
        this.sortList = (ArrayList)scoreManager.getTopTenScores(this, 3);
        this.personalList = scoreManager.getUserTopThreeScores(this);
        setTexts();
//        SlidingTileScoreBoardAdapter adapter = new SlidingTileScoreBoardAdapter(sortList, this);
//        this.scoreList.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }
    public void switchToLevel4(){
        this.sortList = (ArrayList)scoreManager.getTopTenScores(this, 4);
        this.personalList = scoreManager.getUserTopThreeScores(this);
        setTexts();
//        SlidingTileScoreBoardAdapter adapter = new SlidingTileScoreBoardAdapter(sortList, this);
//        this.scoreList.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

    }
    public void switchToLevel5(){
        this.sortList = (ArrayList)scoreManager.getTopTenScores(this, 5);
        this.personalList = scoreManager.getUserTopThreeScores(this);
        setTexts();
//        SlidingTileScoreBoardAdapter adapter = new SlidingTileScoreBoardAdapter(sortList, this);
//        this.scoreList.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

    }
}
