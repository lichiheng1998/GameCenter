package fall2018.csc207project.Memorization.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fall2018.csc207project.Memorization.model.MemoBoard;
import fall2018.csc207project.R;

public class MemoComplxActivity extends AppCompatActivity {
    private int complexity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_complexity);
        addL2ButtonListener();
        addL3ButtonListener();
        addL4ButtonListener();

    }

    private void addL4ButtonListener() {
        Button L4Button = findViewById(R.id.memo4);
        L4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complexity = 4;
                switchToMemoGame();
            }
        });
    }

    private void addL3ButtonListener() {
        Button L3Button = findViewById(R.id.memo3);
        L3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complexity = 3;
                switchToMemoGame();
            }
        });
    }

    private void addL2ButtonListener() {
        Button L2Button = findViewById(R.id.memo2);
        L2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complexity = 2;
                switchToMemoGame();
            }
        });

    }

    private void switchToMemoGame() {
        Intent tmp = new Intent(this, MemoGameActivity.class);
        tmp.putExtra("save", new MemoBoard(complexity));
        startActivity(tmp);
        finish();
    }
}
