package fall2018.csc207project.PushTheBox.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fall2018.csc207project.Models.CalculatorFactory;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ScoreCalculator;
import fall2018.csc207project.Models.ScoreManager;
import fall2018.csc207project.PushTheBox.Controllers.BoxScoreAdapter;
import fall2018.csc207project.PushTheBox.Models.BoxGameScoreManager;
import fall2018.csc207project.PushTheBox.Models.BoxScore;
import fall2018.csc207project.R;

/**
 * The class BoxScoreBoardActivity that extends AppCompatActivity
 * and implements AdapterView.OnItemSelectedListener
 * Excluded from tests because it's a view class.
 */
public class BoxScoreBoardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * The BoxGameScoreManager that manage the BoxGameScore.
     */
    private BoxGameScoreManager scoreManager;

    /**
     * The ArrayList<BoxScore> that shows all the BoxScores of current user.
     */
    private ArrayList<BoxScore> personalList;

    /**
     * The current user's name.
     */
    private String currentUser;

    /**
     * The Top 10 Score board.
     */
    private ListView listView;

    /**
     * The level of the current game.
     */
    private String level = "1";

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.box_score_board);
        SharedPreferences shared = this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        currentUser = shared.getString("currentUser", null);
        String currentGame = shared.getString("currentGame", null);
        CalculatorFactory calculatorFactory = new CalculatorFactory();
        ScoreCalculator calculator = calculatorFactory.getCalculator("BoxCalculator");
        ScoreManager<BoxScore> globalScoreManager;
        globalScoreManager = DatabaseUtil.getScoreManager(currentGame, currentUser, calculator);
        scoreManager = new BoxGameScoreManager(globalScoreManager);
        scoreManager.setLevel(1);
        listView = this.findViewById(R.id.boxScoreBoard);
        personalList = scoreManager.getTopThreePerUser(this);
        setSpinnerListenerChange();
        setViewAdapter();
    }

    /**
     * Set up the Spinner of the score board.
     */
    public void setSpinnerListenerChange(){
        Spinner spinner = findViewById(R.id.boxSpinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        scoreManager.setLevel(Integer.parseInt(level));
    }

    /**
     * Set up the texts that need to be show on score board.
     */
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

    @Override
    public void onNothingSelected(AdapterView<?> arg) {
        level = "1";
    }

    /**
     * set the ListView of the top 10 players score board.
     */
    public void setViewAdapter(){
        ArrayList<BoxScore> sortList = scoreManager.getTopPlayers(this);
        BoxScoreAdapter adapter = new BoxScoreAdapter(this, sortList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

