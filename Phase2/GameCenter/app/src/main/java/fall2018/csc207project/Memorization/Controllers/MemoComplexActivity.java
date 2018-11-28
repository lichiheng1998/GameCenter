package fall2018.csc207project.Memorization.Controllers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Views.MemoGameActivity;
import fall2018.csc207project.R;

/**
 * Class represents the memorization master activity that user can select the complexity of the board.
 */
public class MemoComplexActivity extends AppCompatActivity {

    /**
     * The width of the game can be 3, 4 or 5
     */
    private int width;

    /**
     * The height of the game can be 4 or 5
     */
    private int height;

    /**
     * The level of the game mode which can be hard or crazy.
     */
    private boolean level = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_complexity);
        addL3ButtonListener();
        addL4ButtonListener();
        addL5ButtonListener();
        hardModeSwitchListener();

    }

    /**
     * Active the button listener that change the board complexity to 5 * 5.
     */
    private void addL5ButtonListener() {
        Button L4Button = findViewById(R.id.memo4);
        L4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = 5;
                height = 5;
                switchToMemoGame();
            }
        });
    }

    /**
     * Active the button listener that change the board complexity to 4 * 3.
     */
    private void addL4ButtonListener() {
        Button L3Button = findViewById(R.id.memo3);
        L3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = 4;
                height = 5;
                switchToMemoGame();
            }
        });
    }

    /**
     * Active the button listener that change the board complexity to 3 * 3.
     */
    private void addL3ButtonListener() {
        Button L2Button = findViewById(R.id.memo2);
        L2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = 3;
                height = 4;
                switchToMemoGame();
            }
        });

    }

    /**
     * Active the hard mode Switch listener that set the game to hard or crazy mode.
     */
    private void hardModeSwitchListener(){
        final Switch hardModeSwitch = findViewById(R.id.mode);
        hardModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
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

    /**
     * Active the button listener that start game.
     */
    private void switchToMemoGame() {
        Intent tmp = new Intent(this, MemoGameActivity.class);
        tmp.putExtra("save", new MemoManager(width, height, level));
        startActivity(tmp);
        finish();
    }
}
