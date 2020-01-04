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
import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.List;
import java.util.Map;

import fall2018.csc207project.Controllers.GameCenterListViewAdapter;
import fall2018.csc207project.Controllers.LocalGameCenterPresenter;
import fall2018.csc207project.Controllers.LocalGameCenterPresenterImpl;
import fall2018.csc207project.Models.GlideApp;
import fall2018.csc207project.Models.GlobalConfig;
import fall2018.csc207project.NewModels.GlobalGameManager;
import fall2018.csc207project.NewModels.UserManager;
import fall2018.csc207project.R;

/**
 * The activity that allows the user choose the game to play and choose whether they want to add
 * or remove game.
 */
public class LocalGameCenterActivity extends AppCompatActivity implements NavView{

    private Uri mCropImageUri;
    private FirebaseStorage storage;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private ImageView avatar;
    private ImageView header;
    private boolean visibility;
    private boolean avatarIsDisplayed;
    private AvatarHandler avatarHandler;
    private FloatingActionButton fab;

    private Menu collapsedMenu;
    private boolean appBarExpanded = true;
    private List<String> gameList;

    private Toolbar toolbar;

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

        avatarHandler = new AvatarHandler(this);
        visibility = false;
        avatarIsDisplayed = true;
        toolbar = findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout = findViewById(R.id.appbar);
        fab = findViewById(R.id.fab);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        navigationView = findViewById(R.id.nav_view);
        avatar = findViewById(R.id.avatar);
        header = findViewById(R.id.header);
        storage = FirebaseStorage.getInstance();

        GlobalGameManager gameManager =  new GlobalGameManager(FirebaseFirestore.getInstance());
        presenter = new LocalGameCenterPresenterImpl(this,
                new UserManager(FirebaseAuth.getInstance()), gameManager);

        presenter.initializeView(storage);

        setupNavListener();
        setupAppbarListener();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 200) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                }
                updateAvatar(appBarExpanded);
            }
        });
    }

    private void updateAvatar(boolean isExpanded){
        if(isExpanded && !avatarIsDisplayed){
            avatar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_up_fast));
            avatar.setVisibility(View.VISIBLE);
            avatarIsDisplayed = !avatarIsDisplayed;
        }
        else if(!isExpanded && avatarIsDisplayed){
            avatar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_down_fast));
            avatar.setVisibility(View.INVISIBLE);
            avatarIsDisplayed = !avatarIsDisplayed;
        }
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
    public void prepareGameList(Map<String, StorageReference> gameCollection, List<String> gameList){
        recyclerView = findViewById(R.id.scrollableview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final GameCenterListViewAdapter adapter = new GameCenterListViewAdapter(gameList, gameCollection);
        adapter.setOnLongPressListener(new RecyclerViewOnLongPressListener() {
            @Override
            public void onLongPress(View view, int position) {
                visibility = !visibility;
                adapter.updateVisibility(visibility);
            }
        });
        adapter.setFabListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onFabClicked((String)view.getTag(), FirebaseFirestore.getInstance());
                adapter.remove((String)view.getTag());
            }
        });
        recyclerView.setAdapter(adapter);
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
    private void setupAppbarListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchGameList();
            }
        });
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
                            case R.id.nav_gallery:
                                avatarHandler.openGallery();
                                break;
                            case R.id.nav_clear:
                                presenter.onResetClicked(getApplicationContext());
                                break;
                            case R.id.nav_logout:
                                presenter.onLogOutClicked();
                                break;
                        }
                        return true;
                    }
                });
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
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        avatarHandler.handleAvatarRequest(requestCode, resultCode, data);
        if (requestCode == AvatarHandler.PICK_BGIMAGE_REQUEST_CODE){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},  AvatarHandler.PICK_BGIMAGE_PERMISSION_REQUEST_CODE);
            } else {
                Log.d("hi", "Everything is good.");
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri resultUri = result.getUri();
            presenter.onAvatarSelected(resultUri, storage);
        }
    }

    @Override
    public void showBackground(final StorageReference imgRef) {
        GlideApp.with(this)
                .load(imgRef)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(header);
    }

    @Override
    public void showAvatar(final StorageReference imgRef) {
        GlideApp.with(this)
                .load(imgRef)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .transition(GenericTransitionOptions.with(R.anim.scale_up))
                .into(avatar);
    }


    /**
     * Display the user's name.
     *
     * @param userName the given user's name
     */
    @Override
    public void showUserName(String userName){
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.username)).setText(userName);
    }

    /**
     * Request the permission to access the picture from the gallery.
     * Taken the code from the stack-overflow url: "https://stackoverflow.com/questions/39866869/how-to-ask-permission-to-access-gallery-on-android-m/39866945"
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        avatarHandler.handleAvatarPermission(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null
                && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed
            collapsedMenu.add("Add")
                    .setIcon(R.drawable .ic_action_add)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                presenter.onLogOutClicked();
                return true;
            case R.id.action_settings:
                return true;
        }
        if (item.getTitle() == "Add") {
            Toast.makeText(this, "clicked add", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.initializeView(storage);
    }
}

