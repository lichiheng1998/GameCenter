package fall2018.csc207project.PushTheBox.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc207project.PushTheBox.Controllers.GamePresenter;
import fall2018.csc207project.PushTheBox.Controllers.MapAdapter;
import fall2018.csc207project.PushTheBox.Models.LevelFactory;
import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Views.NumberPickerDialog;

/**
 * The game activity for Push The Box.
 * Excluded from tests because it's a view class
 */
public class BoxGameActivity extends AppCompatActivity implements MapView{

    /**
     * The column's dimension.
     */
    private static int columnDim;

    /**
     * EditText for the undo.
     */
    private EditText undoText;

    /**
     * The presenter of the map.
     */
    private GamePresenter presenter;

    /**
     * The grid view.
     */
    private SwipeDetectGridView gridView;

    /**
     * The adapter for grid view.
     */
    MapAdapter mapAdapter;

    /**
     * The level of current game.
     */
    private int level;

    /**
     * The total undo times to be used.
     */
    private int totalUndoTimes;

    /**
     * The alert dialog which will be displayed when game completed.
     */
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.presenter = new BoxGamePresenter(this,getApplicationContext());
        setContentView(R.layout.box_gaming);
        setupMapManager();
        undoText = findViewById(R.id.StepsToUndo);
        addUndoButtonListener();
        addStepInputListener();
        addResetButtonListener();
    }

    /**
     * Set up the mapManager, level, and total undo times.
     */
    private void setupMapManager(){
        MapManager mapManager;
        if (getIntent().hasExtra("save")){
            mapManager = (MapManager) getIntent().getSerializableExtra("save");
            level = mapManager.getLevel();
            totalUndoTimes = mapManager.getTotalUndoTimes();
        }else {
            level = (int) getIntent().getSerializableExtra("level");
            totalUndoTimes = (int) getIntent().getSerializableExtra("undoStep");
            LevelFactory levelFactory = new LevelFactory(getApplicationContext());
            mapManager = new MapManager(level, totalUndoTimes, levelFactory.getGameElements(level));
        }
        setupGridView(mapManager);
    }

    /**
     * Setup the grid view where tiles are placed.
     */
    private void setupGridView(final MapManager mapManager){
        presenter.setMapManager(mapManager);
        gridView = findViewById(R.id.boxMapGrid);
        gridView.setNumColumns(mapManager.getNumCol());
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int displayWidth = gridView.getMeasuredWidth();
                        columnDim = displayWidth / mapManager.getNumCol();
                        mapAdapter = new MapAdapter(mapManager.getTilesBg(), columnDim,
                                getApplicationContext());
                        updateMap(mapManager);
                    }
                }
        );
        gridView.setPresenter(presenter);
    }

    /**
     * Active the listener for the step picker edit text.
     */
    private void addStepInputListener(){
        undoText.setText("1");
        undoText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.onUndoTextClicked();
            }
        });
    }

    /**
     * Activate the start button.
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.UndoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onUndoButtonClicked(Integer.parseInt(undoText.getText().toString()));
            }
        });
    }

    /**
     * Activate the reset button.
     */
    private void addResetButtonListener() {
        Button resetButton = findViewById(R.id.ResetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame(level, false);
            }
        });
    }

    @Override
    public void makeToastNoUndoTimesLeftText() {
        Toast.makeText(this, "No times or undo out of limit!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeInvalidMovementText() {
        Toast.makeText(this, "Invalid Movement", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void showNumberPicker(){
        NumberPickerDialog newFragment = new NumberPickerDialog();
        newFragment.setValueChangeListener(new NumberPicker.OnValueChangeListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                undoText.setText(Integer.toString(numberPicker.getValue()));
            }
        });
        newFragment.show(getSupportFragmentManager(), "time picker");
    }

    @Override
    public void display() {
        gridView.setAdapter(mapAdapter);
    }

    @Override
    public void updateMap(MapManager mapManager) {
        mapAdapter.setPerson(mapManager.person);
        mapAdapter.setBoxesList(mapManager.getBoxList());
        display();
    }

    @Override
    public void levelComplete() {
        AlertDialog.Builder completeBuilder = new AlertDialog.Builder(this);
        @SuppressLint("InflateParams")
        View completeView = getLayoutInflater().inflate(R.layout.box_complete_popup, null);
        TextView levelText = completeView.findViewById(R.id.indicateLevel);
        String levelDisplay = "Level " + String.valueOf(level);
        levelText.setText(levelDisplay);
        createMenuButton(completeView);
        createReplayButton(completeView);
        createNextButton(completeView);
        completeBuilder.setView(completeView);
        dialog = completeBuilder.create();
        dialog.setCancelable(false);
        dialog.show();
        presenter.saveScores(this);
    }

    /**
     * Activate menu button in dialog.
     * @param view view
     */
    private void createMenuButton(View view){
        Button menu = view.findViewById(R.id.boxMenuButton);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
    }

    /**
     * Activate replay button in dialog
     * @param view view
     */
    private void createReplayButton(View view){
        Button replay = view.findViewById(R.id.boxReplayButton);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame(level, true);
            }
        });
    }

    /**
     * Activate next button in dialog.
     * @param view view
     */
    private void createNextButton(View view) {
        Button next = view.findViewById(R.id.boxNextButton);
        if (level == 9) {
            next.setEnabled(false);
        } else {
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startNewGame(level + 1, true);
                }
            });
        }
    }

    /**
     * Start a new game.
     * @param level the level of new game to be started.
     * @param ifCloseDialog if there exist an alert dialog that should be dismissed.
     */
    private void startNewGame(int level, Boolean ifCloseDialog){
        Intent tmp = new Intent(getApplicationContext(), BoxGameActivity.class);
        tmp.putExtra("level", level);
        tmp.putExtra("undoStep", totalUndoTimes);
        startActivity(tmp);
        if (ifCloseDialog){
            dialog.dismiss();
        }
        finish();
    }
}