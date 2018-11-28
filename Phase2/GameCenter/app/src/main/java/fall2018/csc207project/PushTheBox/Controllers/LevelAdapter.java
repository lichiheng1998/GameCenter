package fall2018.csc207project.PushTheBox.Controllers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import java.util.List;

/**
 * The class LevelAdapter that extends BaseAdapter.
 */
public class LevelAdapter extends BaseAdapter{

    /**
     * The List<Button> for this game's level.
     */
    private List<Button> levelButtons;

    /**
     * Construct a new LevelAdapter by given a List<Button>.
     *
     * @param levelButtons the List<Button> for this game's level
     */
    public LevelAdapter(List<Button> levelButtons){
        this.levelButtons = levelButtons;
    }

    @Override
    public int getCount() {
        return levelButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return levelButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            button = levelButtons.get(position);
        } else {
            button = (Button) convertView;
        }
        return button;
    }
}
