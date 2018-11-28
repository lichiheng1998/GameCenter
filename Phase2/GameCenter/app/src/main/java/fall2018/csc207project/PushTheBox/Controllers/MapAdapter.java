package fall2018.csc207project.PushTheBox.Controllers;

/*
Taken from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/CustomAdapter.java

This Class is an overwrite of the Base Adapter class
It is designed to aid setting the button sizes and positions in the GridView
 */


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
 * The CustomAdapter that maps the buttons to the customized grid view.
 */
public class MapAdapter extends BaseAdapter {
    private Integer[] tileBgs;
    private int columnDim;
    private Context context;
    private Person person;
    private HashMap<Integer, Integer> boxes = new HashMap<>();

    public MapAdapter(Integer[] tileBgs, int columnDim, Context context) {
        this.tileBgs = tileBgs;
        this.columnDim = columnDim;
        this.context = context;
    }

    public void setPerson(Person person){
        this.person = person;
    }

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
