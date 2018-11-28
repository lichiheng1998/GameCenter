package fall2018.csc207project.PushTheBox.Controllers;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Controllers.SlidingTileScoreBoardAdapter;
import fall2018.csc207project.SlidingTile.Models.SlidingTileScoreManager;
import fall2018.csc207project.SlidingTile.Models.TileGameCalculator;
import fall2018.csc207project.SlidingTile.Models.TileScore;

public class BoxScoreBoardActivity extends AppCompatActivity {


    private SlidingTileScoreManager scoreManager;
    private ArrayList sortList;
    private ArrayList<ArrayList<TileScore>> personalList;
    private String currentUser;
    private ListView scoreList;

    protected void Oncreate(Bundle savedInstance){
        super.onCreate(savedInstance);
//
//        SharedPreferences shared = this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
//        currentUser = shared.getString("currentUser", null);
//        TileGameCalculator calculator = new TileGameCalculator();
//        ScoreManager<TileScore> globalScoreManager;
//        globalScoreManager = DatabaseUtil.getScoreManager("SlidingTile", this.currentUser, calculator);
//        this.scoreManager = new SlidingTileScoreManager(globalScoreManager);
//        setContentView(R.layout.box_score_board);
//        this.scoreList = this.findViewById(R.id.scoreBoard);
//        switchToLevel3();
//        this.personalList = scoreManager.getUserTopThreeScores(this);
//        setTexts();
//        createButton();
//        SlidingTileScoreBoardAdapter adapter = new SlidingTileScoreBoardAdapter(sortList, this);
//        this.scoreList.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

    }

}
