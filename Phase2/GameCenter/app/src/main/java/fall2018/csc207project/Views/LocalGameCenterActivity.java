package fall2018.csc207project.Views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fall2018.csc207project.Controllers.BasePresenter;
import fall2018.csc207project.Controllers.GameCenterListViewAdapter;
import fall2018.csc207project.Controllers.GameListActivity;
import fall2018.csc207project.Controllers.NavPresenter;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.GlobalConfig;
import fall2018.csc207project.Models.UserManager;
import fall2018.csc207project.R;

/**
 * The activity that allows the user choose the game to play and choose whether they want to add
 * or remove game.
 */
public class LocalGameCenterActivity extends AppCompatActivity implements NavView{
    private UserManager userManager;
    private BaseAdapter adapter;
    private List<String> gameList;
    private BasePresenter presenter;
    private NavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_game_center);
        navigationView = findViewById(R.id.nav_view);
        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        String currentUser = sharedData.getString("currentUser", null);
        showUserName(currentUser);
        this.userManager = DatabaseUtil.getUserManager(currentUser);
        presenter = new NavPresenter(this, DatabaseUtil.getImageManager(currentUser));
        presenter.initializeView(getApplicationContext());
        gameList = userManager.getGames(getApplicationContext());
        prepareGameList();
        addAddGameButtonListener();
        setupNavListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameList.clear();
        gameList.addAll(userManager.getGames(getApplicationContext()));
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
        this.adapter = new GameCenterListViewAdapter(this, gameList,
                GlobalConfig.BG_MAP);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String game = ((TextView)v.findViewById(R.id.gameName)).getText().toString();
                launchGame(game);
            }
        });
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
        Class gameActivity = GlobalConfig.ENTRY_MAP.get(game);
        Intent tmp = new Intent(this, gameActivity);
        startActivity(tmp);
    }

    private void setupNavListener(){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch (id){
                            case R.id.nav_avatar:
                                openGallery(0);
                                break;
                            case R.id.nav_gallery:
                                openGallery(1);
                                break;
                            case R.id.nav_clear:
                                presenter.onResetClicked(getApplicationContext());
                                break;
                            case R.id.nav_logout:
                                finish();
                                break;
                        }
                        return true;
                    }
                });
    }

    /**
     * Taking the code from the youtube video url: "https://www.youtube.com/watch?v=OPnusBmMQTw"
     * Let the user choose the background image from its gallery.
     */
    private void openGallery(int requestCode){
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, requestCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the background after user selecting the picture from gallery.
     * @param data returned data
     * @param requestCode given request code
     * @param resultCode returned result code
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if(requestCode == 1){
                presenter.onBackgroundSelected(data.getData(), getApplicationContext());
                return;
            }
            presenter.onAvatarSelected(data.getData(), getApplicationContext());
        }
    }

    @Override
    public void showBackground(Drawable drawable) {
        navigationView.getHeaderView(0).setBackground(drawable);
    }

    @Override
    public void showAvatar(Drawable drawable) {
        ((ImageView)navigationView.getHeaderView(0).findViewById(R.id.avatar)).setImageDrawable(drawable);
    }

    private void showUserName(String userName){
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.username)).setText(userName);
    }

    /**
     * Request the permission to access the picture from the gallery.
     * Taken the code from the stackoverflow url: "https://stackoverflow.com/questions/39866869/how-to-ask-permission-to-access-gallery-on-android-m/39866945"
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, requestCode);
            } else {
                Toast.makeText(this, "Not granted permission", Toast.LENGTH_SHORT).show();
            }
    }
}

