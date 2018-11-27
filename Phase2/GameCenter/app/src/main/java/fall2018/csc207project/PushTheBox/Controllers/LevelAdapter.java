package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import java.util.List;

public class LevelAdapter extends BaseAdapter{
    private List<Button> levelButtons;

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
