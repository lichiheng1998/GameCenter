package fall2018.csc207project.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;

import fall2018.csc207project.models.DataStream;
import fall2018.csc207project.models.GlobalConfig;
import fall2018.csc207project.models.UserManager;
import fall2018.csc207project.R;

/**
 * The activity that allows the user to add or remove game.
 */
public class GameListActivity extends AppCompatActivity {
    private UserManager userManager;
    private String currentUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        this.currentUser = sharedData.getString("currentUser", null);
        userManager = new UserManager(DataStream.getInstance(), currentUser);
        setContentView(R.layout.game_list);
        prepareGameList();
        addBackButtonListener();
    }

    /**
     * Setup the listener for add or remove game.
     */
    CompoundButton.OnCheckedChangeListener getSwitchListener(){
        return new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                String game = view.getText().toString();
                if(isChecked){
                    userManager.addGame(game, getApplicationContext());
                } else {
                    userManager.removeGame(game, getApplicationContext());
                }
            }
        };
    }

    /**
     * Activate the back button.
     */
    private void addBackButtonListener() {
        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Map the data to the game list view.
     */
    private void prepareGameList(){
        BaseAdapter adapter = new GameListViewAdapter(this, GlobalConfig.GAMELIST,
                userManager.getGames(), getSwitchListener());
        ListView gameList = findViewById(R.id.game_list);
        gameList.setAdapter(adapter);
    }
}
