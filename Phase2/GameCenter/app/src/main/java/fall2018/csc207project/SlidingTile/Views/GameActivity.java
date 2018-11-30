package fall2018.csc207project.SlidingTile.Views;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.NumberPicker;
import android.widget.Toast;
import java.util.List;
import fall2018.csc207project.SlidingTile.Controllers.BoardGamePresenter;
import fall2018.csc207project.SlidingTile.Controllers.CustomAdapter;
import fall2018.csc207project.SlidingTile.Controllers.GamePresenter;
import fall2018.csc207project.SlidingTile.Models.BoardManager;
import fall2018.csc207project.R;

/**
 * The game activity that extends AppCompatActivity implements BoardGameView.
 */
public class GameActivity extends AppCompatActivity implements BoardGameView{

    /**
     * The column's width and height.
     */
    private static int columnWidth, columnHeight;

    /**
     * The EditText for the undo.
     */
    private EditText undoText;

    /**
     * The buttons to display.
     */
    private List<Button> tileButtons;

    /**
     * Grid View and calculated column height and width based on device size.
     */
    private GridView gridView;

    /**
     * The GamePresenter that helps the GameActivity
     */
    private GamePresenter presenter;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    @Override
    public void display() {
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new BoardGamePresenter(this, getApplicationContext());
        BoardManager boardManager = (BoardManager) getIntent().getSerializableExtra("save");
        presenter.setBoardManager(boardManager);
        this.tileButtons = presenter.getButtonList(this);
        setContentView(R.layout.activity_main);
        undoText = findViewById(R.id.StepsToUndo);
        setupGridView(boardManager);
        addUndoButtonListener();
        addStepInputListener();
    }

    /**
     * Setup the customized grid view and get the height and weight of the tiles.
     */
    private void setupGridView(final BoardManager boardManager){
        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(boardManager.getComplexity());
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.getComplexity();
                        columnHeight = displayHeight / boardManager.getComplexity();
                        display();
                    }
                });
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
        undoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.onUndoButtonClicked(Integer.parseInt(undoText.getText().toString()));
            }
        });
    }

    /**
     * Display that a game has No Undo Times Left.
     */
    public void makeToastNoUndoTimesLeftText() {
        Toast.makeText(this, "No times or undo out of limit!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Swap the buttons of position pos1 and pos2.
     */
    public void swapButtons(int pos1, int pos2){
        Button button1= tileButtons.get(pos1);
        Button button2 = tileButtons.get(pos2);
        String text = button1.getText().toString();
        button1.setText(button2.getText());
        button2.setText(text);
        Drawable drawable = button1.getBackground();
        button1.setBackground(button2.getBackground());
        button2.setBackground(drawable);
    }

    /**
     * Show the number picker dialog.
     */
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
}
