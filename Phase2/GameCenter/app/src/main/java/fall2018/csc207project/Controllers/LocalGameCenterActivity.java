package fall2018.csc207project.Controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.models.DataStream;
import fall2018.csc207project.models.GlobalConfig;
import fall2018.csc207project.models.SaveManager;
import fall2018.csc207project.models.UserManager;
import fall2018.csc207project.R;

/**
 * The activity that allows the user choose the game to play and choose whether they want to add
 * or remove game.
 */
public class LocalGameCenterActivity extends AppCompatActivity {

    private String currentUser;
    private UserManager userManager;
    private BaseAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_game_center);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        this.currentUser = sharedData.getString("currentUser", null);
        this.userManager = new UserManager(DataStream.getInstance(), currentUser,this);
        prepareGameList();
        addAddGameButtonListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.adapter.notifyDataSetChanged();
    }

    /**
     * Activate the add button.
     */
    private void addAddGameButtonListener() {
        Button addGameButton = findViewById(R.id.add_game);
        addGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchGameList();
            }
        });
    }

    /**
     * Switch to the GameListActivity view to add or remove.
     */
    private void switchGameList(){
        Intent tmp = new Intent(this, GameListActivity.class);
        startActivity(tmp);
    }

    /**
     * Prepare the game list.
     */
    private void prepareGameList(){
        ListView listView = findViewById(R.id.gameList);
        this.adapter = new GameCenterListViewAdapter(this, userManager.getGames(),
                GlobalConfig.BGMAP);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String game = ((TextView)v.findViewById(R.id.gameName)).getText().toString();
                launchGame(game);
            }
        });
        listView.setAdapter(adapter);
    }

    /**
     * Launch the game with the given game name.
     * @param game the name of the current game.
     */
    private void launchGame(String game){
        SharedPreferences sharedPref =
                this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("currentGame", game);
        editor.commit();
        Class gameActivity = GlobalConfig.ENTRYMAP.get(game);
        Intent tmp = new Intent(this, gameActivity);
        startActivity(tmp);
    }
}

