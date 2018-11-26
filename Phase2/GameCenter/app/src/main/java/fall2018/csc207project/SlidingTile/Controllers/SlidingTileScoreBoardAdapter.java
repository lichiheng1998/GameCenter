package fall2018.csc207project.SlidingTile.Controllers;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.TileScore;

public class SlidingTileScoreBoardAdapter extends BaseAdapter {

    private List<TileScore> slidingTileTopScores;
    private Context mContext;
    private LayoutInflater mLayoutInflater = null;


    public SlidingTileScoreBoardAdapter(Context context, List<TileScore> list) {
        mContext = context;
        slidingTileTopScores = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return slidingTileTopScores.size();
    }
    @Override
    public Object getItem(int pos) {
        return slidingTileTopScores.get(pos);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CompleteListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.tile_game_score_board_row, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        viewHolder.userName.setText(slidingTileTopScores.get(position).user);
        viewHolder.userScore.setText(slidingTileTopScores.get(position).value);
        return v;
    }
}
class CompleteListViewHolder {
    public TextView userName;
    public TextView userScore;
    public CompleteListViewHolder(View base) {
//        userName = (TextView) base.findViewById(R.id.user);
    }
}

