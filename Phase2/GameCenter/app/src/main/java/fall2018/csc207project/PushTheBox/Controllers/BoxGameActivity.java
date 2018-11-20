package fall2018.csc207project.PushTheBox.Controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import fall2018.csc207project.PushTheBox.Models.Map;
import fall2018.csc207project.PushTheBox.Models.MapManager;
import fall2018.csc207project.PushTheBox.View.MapView;
import fall2018.csc207project.R;

/**
 * The game activity for Push The Box.
 */
public class BoxGameActivity extends AppCompatActivity implements MapView {

    private static int columnDim;

    /**
     * The presenter of the map.
     */
    private BoxGamePresenter presenter;

    /**
     * The grid view.
     */
    private GridView gridView;


    /**
     * The array of tiles' background id.
     */
    Integer[] tileBgs;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.presenter = new BoxGamePresenter(this,getApplication());
        //MapManager mapManager = (MapManager) getIntent().getSerializableExtra("save");
        MapManager mapManager = new MapManager(1);
        tileBgs = mapManager.getTilesBg();

        setContentView(R.layout.box_gaming);
        setupGridView(mapManager);
        addUpButtonListener();
        addLeftButtonListener();
        addDownButtonListener();
        addRightButtonListener();
    }

    /**
     * Setup the grid view where tiles are placed.
     */
    private void setupGridView(final MapManager mapManager){
        presenter.setMapManager(mapManager);
        gridView = findViewById(R.id.mapGrid);
        gridView.setNumColumns(mapManager.getNumCol());
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this
                        );
                        int displayWidth = gridView.getMeasuredWidth();
                        columnDim = displayWidth / mapManager.getNumCol();
                        display();
                    }
                }
        );
    }


    private void addUpButtonListener() {
        ImageButton upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.arrowButtonClicked(getApplicationContext(), "up");
            }
        });
    }

    private void addLeftButtonListener() {
        ImageButton leftButton = findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.arrowButtonClicked(getApplicationContext(), "left");
            }
        });
    }

    private void addDownButtonListener() {
        ImageButton downButton = findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.arrowButtonClicked(getApplicationContext(), "down");
            }
        });
    }

    private void addRightButtonListener() {
        ImageButton rightButton = findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.arrowButtonClicked(getApplicationContext(), "right");
            }
        });
    }


    @Override
    public void display() {
        MapAdapter mapAdapter = new MapAdapter(tileBgs,columnDim, getApplicationContext());
        gridView.setAdapter(mapAdapter);

    }

    @Override
    public void updateMap(MapManager mapManager) {
        tileBgs = mapManager.getTilesBg();
        display();
    }
}