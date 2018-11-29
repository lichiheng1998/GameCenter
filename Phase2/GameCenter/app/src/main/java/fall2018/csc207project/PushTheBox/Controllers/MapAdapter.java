package fall2018.csc207project.PushTheBox.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fall2018.csc207project.PushTheBox.Models.Box;
import fall2018.csc207project.PushTheBox.Models.Person;

/**
 * Taken from:
 * https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/CustomAdapter.java
 *
 * This Class is an overwrite of the Base Adapter class
 *It is designed to aid setting the button sizes and positions in the GridView
 */
public class MapAdapter extends BaseAdapter {

    /**
     * An Integer[] which contains all id of the Tiles for the given level.
     */
    private Integer[] tileBgs;

    /**
     * The dimension of the column in int.
     */
    private int columnDim;

    /**
     * The context of this app.
     */
    private Context context;

    /**
     * The Person in the game that can move and push the boxes.
     */
    private Person person;

    /**
     * HashMap of boxes information.
     */
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Integer> boxes = new HashMap<>();

    /**
     * Construct a new MapAdapter
     * by given an Integer[] an int, and a Context.
     *
     * @param tileBgs an Integer[] which contains all id of the Tiles for the given level
     * @param columnDim the dimension of the column in int
     * @param context the context of this app
     */
    public MapAdapter(Integer[] tileBgs, int columnDim, Context context) {
        this.tileBgs = tileBgs;
        this.columnDim = columnDim;
        this.context = context;
    }

    /**
     * Set up a Person to move the boxes in this game.
     *
     * @param person the Person to move the boxes
     */
    public void setPerson(Person person){
        this.person = person;
    }

    /**
     * Set up the Boxes in game so the Person can move them.
     *
     * @param boxes the boxes that a Person can move
     */
    public void setBoxesList(ArrayList<Box> boxes){
        this.boxes.clear();
        for (Box box : boxes){
            this.boxes.put(box.getPosition(), box.getImage());
        }
    }

    @Override
    public int getCount() {
        return tileBgs.length;
    }

    @Override
    public Object getItem(int position) {
        if (person != null && position == person.getPosition()){
            return person.getImage();
        }else if (boxes.size() != 0 && boxes.get(position) != null){
            return boxes.get(position);
        }
        return tileBgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource((int)getItem(position));

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(columnDim, columnDim);
        imageView.setLayoutParams(params);
        return imageView;
    }
}
