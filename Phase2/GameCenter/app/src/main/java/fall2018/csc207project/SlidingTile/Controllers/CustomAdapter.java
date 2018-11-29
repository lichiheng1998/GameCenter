package fall2018.csc207project.SlidingTile.Controllers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import java.util.List;

/**
 * Taken from:
 * https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/CustomAdapter.java
 *
 * This Class is an overwrite of the Base Adapter class
 * It is designed to aid setting the button sizes and positions in the GridView
 */
public class CustomAdapter extends BaseAdapter {

    /**
     * The List of Buttons that will
     * be use in the game for user to swipe.
     */
    private List<Button> mButtons;

    /**
     * The Column's width and height.
     */
    private int mColumnWidth, mColumnHeight;

    /**
     * Construct a new CustomAdapter by given a List<Button>, and two ints.
     *
     * @param buttons The List of Buttons that will
     *                be use in the game for user to swipe
     * @param columnWidth the Column's width
     * @param columnHeight the Column's height
     */
    public CustomAdapter(List<Button> buttons, int columnWidth, int columnHeight) {
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }

    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return mButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            button = mButtons.get(position);
            android.widget.AbsListView.LayoutParams params =
                    new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
            button.setLayoutParams(params);
        } else {
            button = (Button) convertView;
        }
        return button;
    }
}
