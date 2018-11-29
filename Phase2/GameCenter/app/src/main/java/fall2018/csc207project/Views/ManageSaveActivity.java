package fall2018.csc207project.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.Serializable;

import fall2018.csc207project.Controllers.ManageSavePresenter;
import fall2018.csc207project.Controllers.ManageSavePresenterImpl;
import fall2018.csc207project.R;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.GlobalConfig;
import fall2018.csc207project.Models.SaveManager;
/**
 * The activity that represents the page for managing saves.
 */
@SuppressWarnings("unchecked")
public class ManageSaveActivity extends AppCompatActivity implements ManageSaveView{
    private ManageSavePresenter presenter;
    /**
     * The entries of the game activities.
     */
    private Class<? extends Activity> entry;

    /**
     * The load and save button on the layout.
     */
    int[] saveIds = {R.id.save1, R.id.save2, R.id.save3};
    int[] fields = {R.id.field1, R.id.field2, R.id.field3, R.id.fieldAuto};
    int[] loadIds = {R.id.load1, R.id.load2, R.id.load3, R.id.loadAuto};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_manage);
        SharedPreferences shared = this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        String user = shared.getString("currentUser", null);
        String game = shared.getString("currentGame", null);
        SaveManager saveManager = DatabaseUtil.getSaveManager(user, game);
        presenter = new ManageSavePresenterImpl(this, saveManager, getApplicationContext());
        entry = (Class<? extends Activity>)GlobalConfig.GAME_MAP.get(game);
        setupSaveButtons();
        setupLoadButtons();
        presenter.initView();
    }

    /**
     * Setup the save button.
     */
    public void setupSaveButtons(){
        for (int i = 0; i < 3; i++){
            final int pos = i;
            findViewById(saveIds[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onSaveButtonClicked(pos, ManageSaveActivity.this.getApplicationContext());
                }
            });
        }
    }

    /**
     * Setup the load button.
     */
    public void setupLoadButtons(){
        for (int i = 0; i < 4; i++){
            final int pos = i;
            findViewById(loadIds[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onLoadButtonClicked(pos);
                }
            });
        }
    }

    @Override
    public void launchGame(Serializable data){
        Intent tmp = new Intent(ManageSaveActivity.this, entry);
        tmp.putExtra("save", data);
        startActivity(tmp);
        finish();
    }

    @Override
    public void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeNoSavedText() {
        Toast.makeText(this, "No Save!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeNotStartedText() {
        Toast.makeText(this, "Game is not started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInfo(int pos, String info){
        Button button = findViewById(fields[pos]);
        button.setText(info);
    }
}
