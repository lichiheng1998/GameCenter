package fall2018.csc207project.Views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.Controllers.GameListViewAdapter;
import fall2018.csc207project.Models.GlobalConfig;
import fall2018.csc207project.NewModels.UserManager;
import fall2018.csc207project.R;

/**
 * The activity that allows the user to add or remove game.
 */
public class GameListActivity extends AppCompatActivity implements UserManager.OnGameReady, UserManager.OnGameListReady {

    /**
     * The user manager to manage each user.
     */
    private UserManager userManager;
    private FirebaseFirestore storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager(FirebaseAuth.getInstance());
        storage = FirebaseFirestore.getInstance();
        setContentView(R.layout.game_list);
        userManager.getGameList(this, storage);
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
                List<String> games = new ArrayList<>();
                games.add(game);
                if(isChecked){
                    userManager.addGame(games, GameListActivity.this, storage);
                } else {
                    userManager.removeGame(game, GameListActivity.this, storage);
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
//    private void prepareGameList(){
//        BaseAdapter adapter = new GameListViewAdapter(this, GlobalConfig.GAME_LIST,
//                userManager.getGames(getApplicationContext()), getSwitchListener());
//        ListView gameList = findViewById(R.id.game_list);
//        gameList.setAdapter(adapter);
//    }

    @Override
    public void onGameReady(List<String> game, boolean isAdded) {
        if(isAdded) {
            Toast.makeText(this, "Game added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Game deleted", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onGameListReady(List<String> gameList) {
        BaseAdapter adapter = new GameListViewAdapter(this, GlobalConfig.GAME_LIST, gameList,
                getSwitchListener());
        ListView list = findViewById(R.id.game_list);
        list.setAdapter(adapter);
    }
}
