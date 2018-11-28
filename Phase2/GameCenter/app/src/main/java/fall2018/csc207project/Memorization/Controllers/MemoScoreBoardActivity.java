package fall2018.csc207project.Memorization.Controllers;

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
import fall2018.csc207project.Memorization.Models.MemoGameCalculator;
import fall2018.csc207project.Memorization.Models.MemoScore;
import fall2018.csc207project.Memorization.Models.MemoScoreManager;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ScoreManager;
import fall2018.csc207project.R;

public class MemoScoreBoardActivity extends AppCompatActivity {

    private MemoScoreManager scoreManager;
    private ArrayList<MemoScore> sortList = new ArrayList<>();
    private boolean level = false;
    private ListView scoreList;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        SharedPreferences shared = getSharedPreferences("GameData", Context.MODE_PRIVATE);

        String currentUser = shared.getString("currentUser", null);
        MemoGameCalculator calculator = new MemoGameCalculator();
        ScoreManager<MemoScore> globalScoreManager;
        globalScoreManager = DatabaseUtil.getScoreManager("Memo", currentUser, calculator);
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
     * addAcceptButton
     * that creates all Button Listener to this instance
     */
    public void addAcceptButton(){
        hardModeSwitchListener();
        Button acceptButton = findViewById(R.id.buttonAccept);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText acceptText = findViewById(R.id.acceptText);
                switch (acceptText.getText().toString()) {
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
