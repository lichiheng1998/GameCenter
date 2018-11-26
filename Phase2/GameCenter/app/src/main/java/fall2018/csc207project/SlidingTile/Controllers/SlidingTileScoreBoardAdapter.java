package fall2018.csc207project.SlidingTile.Controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;
import fall2018.csc207project.SlidingTile.Models.SlidingTileScoreManager;

public class SlidingTileScoreBoardAdapter extends BaseAdapter {

    private List<SlidingTileScoreManager> slidingTileTopScores;
    private Context context;

    SlidingTileScoreBoardAdapter(List<SlidingTileScoreManager> slidingTileTopScores, Context context) {
        this.slidingTileTopScores = slidingTileTopScores;
        this.context = context;
    }

    @Override
    public int getCount() {
        return slidingTileTopScores.size();
    }

    @Override
    public Object getItem(int position) {
        return new String[] {slidingTileTopScores.get(position).getCurrentUser(), null, null};
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
