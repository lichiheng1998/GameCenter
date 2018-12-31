package fall2018.csc207project.Views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.List;

import fall2018.csc207project.Controllers.GameCenterListViewAdapter;
import fall2018.csc207project.Controllers.LocalGameCenterPresenter;
import fall2018.csc207project.Controllers.LocalGameCenterPresenterImpl;
import fall2018.csc207project.Models.GlideApp;
import fall2018.csc207project.Models.GlobalConfig;
import fall2018.csc207project.NewModels.UserManager;
import fall2018.csc207project.R;

/**
 * The activity that allows the user choose the game to play and choose whether they want to add
 * or remove game.
 */
public class LocalGameCenterActivity extends AppCompatActivity implements NavView{

    private Uri mCropImageUri;
    private FirebaseStorage storage;

    /**
     * The BaseAdapter to interact with the ListView.
     */
    private BaseAdapter adapter;

    /**
     * The List of String represent all games.
     */
    private List<String> gameList;

    /**
     * The BasePresenter to interact with the GameActivities.
     */
    private LocalGameCenterPresenter presenter;

    /**
     * The NavigationView to navigate the View.
     */
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_game_center);
        navigationView = findViewById(R.id.nav_view);
//        SharedPreferences sharedData = getSharedPreferences("GameData", Context.MODE_PRIVATE);
//        String currentUser = sharedData.getString("currentUser", null);
//        showUserName(currentUser);
//        this.userManager = DatabaseUtil.getUserManager(currentUser);
        storage = FirebaseStorage.getInstance();
        presenter = new LocalGameCenterPresenterImpl(this,
                new UserManager(FirebaseAuth.getInstance()));
        presenter.initializeView(storage);
//        gameList = userManager.getGames(getApplicationContext());
//        prepareGameList();
//        addAddGameButtonListener();
        setupNavListener();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        gameList.clear();
//        gameList.addAll(userManager.getGames(getApplicationContext()));
//        this.adapter.notifyDataSetChanged();
//    }

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
    @SuppressLint("ApplySharedPref")
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

    /**
     * Set the Nav.
     */
    private void setupNavListener(){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch (id){
                            case R.id.nav_avatar:
                                CropImage.startPickImageActivity(LocalGameCenterActivity.this);
                                break;
//                            case R.id.nav_gallery:
//                                openGallery(1);
//                                break;
                            case R.id.nav_clear:
                                presenter.onResetClicked(getApplicationContext());
                                break;
                            case R.id.nav_logout:
                                logout();
                                finish();
                                break;
                        }
                        return true;
                    }
                });
    }


    /**
     * When user click logout, return the globle start page.
     */
    private void logout(){
        Intent tmp = new Intent(this, GlobalActivity.class);
        startActivity(tmp);
    }

    /**
     * Set the background after user selecting the picture from gallery.
     * @param data returned data
     * @param requestCode given request code
     * @param resultCode returned result code
     */
    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result of pick image chooser
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},   CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                // no permissions required or already granted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                presenter.onAvatarSelected(resultUri, storage);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public void showBackground(Drawable drawable) {

    }

    @Override
    public void showAvatar(StorageReference imgRef) {
        Glide.with(this /* context */)
                .load(imgRef)
                .into((ImageView)navigationView.getHeaderView(0).findViewById(R.id.avatar));
    }

    @Override
    public void showAvatar(Uri imgRef) {
        Log.e("test", "showAvatar: it's shown");
        GlideApp.with(this /* context */)
                .load(imgRef)
                .into((ImageView)navigationView.getHeaderView(0).findViewById(R.id.avatar));
    }
    /**
     * Display the user's name.
     *
     * @param userName the given user's name
     */
    private void showUserName(String userName){
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.username)).setText(userName);
    }

    /**
     * Request the permission to access the picture from the gallery.
     * Taken the code from the stack-overflow url: "https://stackoverflow.com/questions/39866869/how-to-ask-permission-to-access-gallery-on-android-m/39866945"
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CropImage.startPickImageActivity(this);
            } else {
                Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // required permissions granted, start crop image activity
                startCropImageActivity(mCropImageUri);
            } else {
                Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setAspectRatio(1,1)
                .setFixAspectRatio(true)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(this);
    }
}

