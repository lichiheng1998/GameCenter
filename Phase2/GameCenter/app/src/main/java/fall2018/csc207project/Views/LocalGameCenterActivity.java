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
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.Arrays;
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
    private boolean visibility;
    private boolean avatarIsDisplayed;

    private Menu collapsedMenu;
    private boolean appBarExpanded = true;
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

        visibility = false;
        avatarIsDisplayed = true;
        final Toolbar toolbar = findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout = findViewById(R.id.appbar);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        navigationView = findViewById(R.id.nav_view);
        avatar = findViewById(R.id.avatar);

        storage = FirebaseStorage.getInstance();

        GlobalGameManager gameManager =  new GlobalGameManager(FirebaseFirestore.getInstance());
        presenter = new LocalGameCenterPresenterImpl(this,
                new UserManager(FirebaseAuth.getInstance()), gameManager);

        presenter.initializeView(storage);

        setupNavListener();

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
            Log.e("blah", "updateAvatar: hide");
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
                                presenter.onLogOutClicked();
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
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        if (item.getTitle() == "Add") {
            Toast.makeText(this, "clicked add", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}

