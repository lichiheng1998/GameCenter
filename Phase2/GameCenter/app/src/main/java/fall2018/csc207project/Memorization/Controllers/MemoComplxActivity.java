package fall2018.csc207project.Memorization.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.R;

public class MemoComplxActivity extends AppCompatActivity {
    private int width;
    private int height;
    private boolean level=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_complexity);
        addL3ButtonListener();
        addL4ButtonListener();
        addL5ButtonListener();
        hardModeSwitchListener();

    }

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

    private void hardModeSwitchListener(){
        Switch hardModeSwitch = findViewById(R.id.hardmode);
        hardModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    level = true;
                } else {
                    level = false;
                }
            }
        });
    }

    private void switchToMemoGame() {
        Intent tmp = new Intent(this, MemoGameActivity.class);
        tmp.putExtra("save", new MemoManager(width, height, level));
        startActivity(tmp);
        finish();
    }
}
