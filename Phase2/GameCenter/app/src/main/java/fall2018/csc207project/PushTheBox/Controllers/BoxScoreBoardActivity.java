package fall2018.csc207project.PushTheBox.Controllers;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import fall2018.csc207project.PushTheBox.Models.BoxGameScoreManager;
import fall2018.csc207project.R;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ScoreManager;
import fall2018.csc207project.PushTheBox.Models.BoxGameCalculator;
import fall2018.csc207project.PushTheBox.Models.BoxScore;
import fall2018.csc207project.SlidingTile.Controllers.SlidingTileScoreBoardAdapter;
import fall2018.csc207project.SlidingTile.Models.SlidingTileScoreManager;
import fall2018.csc207project.SlidingTile.Models.TileGameCalculator;
import fall2018.csc207project.SlidingTile.Models.TileScore;

public class BoxScoreBoardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private BoxGameScoreManager scoreManager;
    private ArrayList<BoxScore> sortList;
    private ArrayList<BoxScore> personalList;
    private String currentUser;
    private ListView listView;

    private String level = "1";

    private Spinner spinner;

    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.box_score_board);



        SharedPreferences shared = this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        currentUser = shared.getString("currentUser", null);
        BoxGameCalculator calculator = new BoxGameCalculator();
        ScoreManager<BoxScore> globalScoreManager;
        globalScoreManager = DatabaseUtil.getScoreManager("PushBox", currentUser, calculator);
        scoreManager = new BoxGameScoreManager(globalScoreManager);
        scoreManager.setLevel(1);
        listView = this.findViewById(R.id.boxScoreBoard);
        personalList = scoreManager.getTopThreePerUser(this);
        setSpinnerListenerChange();
        setViewAdapter();
    }

    public void setSpinnerListenerChange(){
        spinner = findViewById(R.id.boxSpinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        scoreManager.setLevel(Integer.parseInt(level));
    }

    private void setTexts(){
        personalList = scoreManager.getTopThreePerUser(this);
        ((TextView)findViewById(R.id.boxPlayerName)).setText(currentUser);
        ((TextView)findViewById(R.id.boxTopOne)).setText(String.valueOf(personalList.get(0).value));
        ((TextView)findViewById(R.id.boxTopTwo)).setText(String.valueOf(personalList.get(1).value));
        ((TextView)findViewById(R.id.boxTopThree)).setText(String.valueOf(personalList.get(2).value));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        level = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "current Selected Level: " + level, Toast.LENGTH_SHORT).show();
        scoreManager.setLevel(Integer.parseInt(level));
        setTexts();
        setViewAdapter();
    }
    public void onNothingSelected(AdapterView<?> arg) {
        level = "1";
    }

    public void setViewAdapter(){
        sortList = scoreManager.getTopPlayers(this);
        BoxScoreAdapter adapter = new BoxScoreAdapter(this, sortList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
