package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.SlidingTileScoreManager;
import fall2018.csc207project.SlidingTile.Models.TileGameCalculator;
import fall2018.csc207project.SlidingTile.Models.TileScore;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ScoreManager;

public class ScoreBoardActivity extends AppCompatActivity {

    private SlidingTileScoreManager scoreManager;
    private ArrayList<TileScore> sortList = new ArrayList<>();
    private ArrayList<ArrayList<TileScore>> personalList = new ArrayList<>();
    private String currentUser;
    private ListView scoreList;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        SharedPreferences shared = this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        currentUser = shared.getString("currentUser", null);
        TileGameCalculator calculator = new TileGameCalculator();
        ScoreManager <TileScore> globalScoreManager;
        globalScoreManager = DatabaseUtil.getScoreManager("SlidingTile", this.currentUser, calculator);
        this.scoreManager = new SlidingTileScoreManager(globalScoreManager);
        setContentView(R.layout.tile_game_score_board);
        this.scoreList = this.findViewById(R.id.scoreBoard);
        createButton();
        this.personalList = scoreManager.getUserTopThreeScores(this);
        setTexts();
        createButton();
        SlidingTileScoreBoardAdapter adapter = new SlidingTileScoreBoardAdapter(sortList, this);
        this.scoreList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * createButton
     * that creates all Button Listener to this instance
     */
    public void createButton(){
        /*
      the Button Instances Instance
     */
        Button threeTimeThree = findViewById(R.id.button3x3);
        threeTimeThree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ScoreBoardActivity.this.updateListView(3);
            }
        });
        Button fourTimeFour = findViewById(R.id.button4x4);
        fourTimeFour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ScoreBoardActivity.this.updateListView(4);
            }
        });
        Button fiveTimeFive = findViewById(R.id.button5x5);
        fiveTimeFive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ScoreBoardActivity.this.updateListView(5);
            }
        });
    }

    /**
     * update the ListView in this instance
     *
     */
    public void updateListView(int level){
        BaseAdapter adapter = new SlidingTileScoreBoardAdapter(scoreManager.getTopTenScores(this, level), this);
        scoreList.setAdapter(adapter);
    }

    public void setTexts(){
        ((TextView)findViewById(R.id.currentPlayer)).setText(currentUser);
        ((TextView)findViewById(R.id.playerscore3x1)).setText(String.valueOf(this.personalList.get(0).get(0).value));
        ((TextView)findViewById(R.id.playerscore3x2)).setText(String.valueOf(this.personalList.get(0).get(1).value));
        ((TextView)findViewById(R.id.playerscore3x3)).setText(String.valueOf(this.personalList.get(0).get(2).value));
        ((TextView)findViewById(R.id.playerscore4x1)).setText(String.valueOf(this.personalList.get(1).get(0).value));
        ((TextView)findViewById(R.id.playerscore4x2)).setText(String.valueOf(this.personalList.get(1).get(1).value));
        ((TextView)findViewById(R.id.playerscore4x3)).setText(String.valueOf(this.personalList.get(1).get(2).value));
        ((TextView)findViewById(R.id.playerscore5x1)).setText(String.valueOf(this.personalList.get(2).get(0).value));
        ((TextView)findViewById(R.id.playerscore5x2)).setText(String.valueOf(this.personalList.get(2).get(1).value));
        ((TextView)findViewById(R.id.playerscore5x3)).setText(String.valueOf(this.personalList.get(2).get(2).value));
    }
}