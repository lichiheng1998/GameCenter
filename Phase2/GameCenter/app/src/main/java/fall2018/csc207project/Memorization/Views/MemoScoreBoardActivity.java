package fall2018.csc207project.Memorization.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fall2018.csc207project.Memorization.Controllers.MemoScoreBoardAdapter;
import fall2018.csc207project.Memorization.Models.MemoScore;
import fall2018.csc207project.Memorization.Models.MemoScoreManager;
import fall2018.csc207project.Models.CalculatorFactory;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ScoreCalculator;
import fall2018.csc207project.Models.ScoreManager;
import fall2018.csc207project.R;

/**
 * The MemoScoreBoardActivity that extends AppCompatActivity
 */
public class MemoScoreBoardActivity extends AppCompatActivity {

    /**
     * The MemoScoreManager that manage each MemoScoreManager
     */
    private MemoScoreManager scoreManager;

    /**
     * The sorted ArrayList<MemoScore> to be show on the top 10 ListView.
     */
    private ArrayList<MemoScore> sortList = new ArrayList<>();

    /**
     * Tells weather the level is on hard mode or crazy mode.
     */
    private boolean level = false;

    /**
     * The ListView of top 10 players for this game.
     */
    private ListView scoreList;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        SharedPreferences shared = getSharedPreferences("GameData", Context.MODE_PRIVATE);

        String currentUser = shared.getString("currentUser", null);
        String currentGame = shared.getString("currentGame", null);
        CalculatorFactory calculatorFactory = new CalculatorFactory();
        ScoreCalculator calculator =
                calculatorFactory.getCalculator("MemoCalculator");
        ScoreManager<MemoScore> globalScoreManager;
        globalScoreManager = DatabaseUtil.getScoreManager(currentGame, currentUser, calculator);
        scoreManager = new MemoScoreManager(globalScoreManager);

        setContentView(R.layout.memo_score_board);

        ((TextView)findViewById(R.id.memoCurrentPlayer)).setText(currentUser);

        scoreList = findViewById(R.id.memoScoreBoard);

        addAcceptButton();

        MemoScoreBoardAdapter adapter = new MemoScoreBoardAdapter(sortList, this);
        scoreList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * addAcceptButton that creates all Button Listener to this instance
     */
    public void addAcceptButton(){
        hardModeSwitchListener();
        Button acceptButton = findViewById(R.id.buttonAccept);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String acceptText = ((EditText) findViewById(R.id.acceptText)).getText().toString();
                switch (acceptText) {
                    case "3":
                        updateView(3, level);
                        break;
                    case "4":
                        updateView(4, level);
                        break;
                    case "5":
                        updateView(5, level);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),
                                "Wrong input numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * update the ListView in this instance
     *
     * @param difficulty the difficulty depends on the width of the game
     * @param level the level can be hard or crazy mode
     */
    public void updateView(int difficulty, boolean level){
        BaseAdapter adapter = new MemoScoreBoardAdapter(
                scoreManager.getTopTenScores(this, difficulty, level), this);
        scoreList.setAdapter(adapter);

        ArrayList<MemoScore> personalList
                = scoreManager.getUserTopThree(this, difficulty, level);
        ((TextView)findViewById(R.id.memoPlayerTop3x1))
                .setText(String.valueOf(personalList.get(0).value));
        ((TextView)findViewById(R.id.memoPlayerTop3x2))
                .setText(String.valueOf(personalList.get(1).value));
        ((TextView)findViewById(R.id.memoPlayerTop3x3))
                .setText(String.valueOf(personalList.get(2).value));
    }

    /**
     * Activate the hard mode Switch.
     */
    private void hardModeSwitchListener(){
        final Switch hardModeSwitch = findViewById(R.id.memoMode);
        hardModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    hardModeSwitch.setText("Crazy Mode");
                    level = true;
                } else {
                    hardModeSwitch.setText("Hard Mode");
                    level = false;
                }
            }
        });
    }
}
