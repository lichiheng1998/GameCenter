package fall2018.csc207project.Memorization.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import fall2018.csc207project.R;

public class MemoComplxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_complexity);
        addL2ButtonListener();
        addL3ButtonListener();
        addL4ButtonListener();

    }

    private void addL4ButtonListener() {}

    private void addL3ButtonListener() {}

    private void addL2ButtonListener() {}
}
