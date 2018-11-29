package fall2018.csc207project.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.GlobalConfig;
import fall2018.csc207project.Models.UserManager;
import fall2018.csc207project.R;

/**
 * The activity that allows the user to add or remove game.
 */
public class GameListActivity extends AppCompatActivity {

    /**
     * The user manager to manage each user.
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        String currentUser = sharedData.getString("currentUser", null);
        userManager = DatabaseUtil.getUserManager(currentUser);
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
        BaseAdapter adapter = new GameListViewAdapter(this, GlobalConfig.GAME_LIST,
                userManager.getGames(getApplicationContext()), getSwitchListener());
        ListView gameList = findViewById(R.id.game_list);
        gameList.setAdapter(adapter);
    }
}
