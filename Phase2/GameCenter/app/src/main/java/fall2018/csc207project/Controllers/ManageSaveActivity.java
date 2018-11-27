package fall2018.csc207project.Controllers;

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
import fall2018.csc207project.R;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.GlobalConfig;
import fall2018.csc207project.Models.Save;
import fall2018.csc207project.Models.SaveManager;
import fall2018.csc207project.Models.SaveSlot;
/**
 * The activity that represents the page for managing saves.
 */
@SuppressWarnings("unchecked")
public class ManageSaveActivity extends AppCompatActivity{
    private SaveManager saveManager;
    private String user;
    private String game;
    /**
     * The save slot that stores all save of the user.
     */
    private SaveSlot saveSlot;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_manage);
        SharedPreferences shared = this.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        user = shared.getString("currentUser", null);
        game = shared.getString("currentGame", null);
        saveManager = DatabaseUtil.getSaveManager(user, game);
        saveSlot = saveManager.readFromFile(getApplicationContext());
        entry = (Class<? extends Activity>)GlobalConfig.GAMEMAP.get(game);
        setupSaveButtons();
        setupLoadButtons();
        setupInfos();
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
                    Save save = saveSlot.readFromAutoSave();
                    if (save == null){
                        makeNotStartedText();
                        return;
                    }
                    makeToastSavedText();
                    saveSlot.saveToSlot(pos, save.data);
                    saveManager.saveToFile(saveSlot, ManageSaveActivity.this.getApplicationContext());
                    setupInfos();
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
                    Save save = pos == 3 ? saveSlot.readFromAutoSave():saveSlot.readFromSlot(pos);
                    if (save == null) {
                        makeNoSavedText();
                        return;
                    }
                    Intent tmp = new Intent(ManageSaveActivity.this, entry);
                    tmp.putExtra("save", (Serializable) save.data);
                    startActivity(tmp);
                    finish();
                }
            });
        }
    }

    public void setupInfos(){
        for (int i = 0; i < 4; i++){
            Button button = findViewById(fields[i]);
            Save save = i == 3 ? saveSlot.readFromAutoSave() : saveSlot.readFromSlot(i);
            if (save != null){
                button.setText(save.date.toString());
            }
        }
    }


    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game that has no saves.
     */
    private void makeNoSavedText() {
        Toast.makeText(this, "No Save!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that the game hasn't started yet.
     */
    private void makeNotStartedText() {
        Toast.makeText(this, "Game is not started!", Toast.LENGTH_SHORT).show();
    }
}


