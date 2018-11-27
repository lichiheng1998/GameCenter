package fall2018.csc207project.SlidingTile.Controllers;
import java.sql.SQLOutput;
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
    private LayoutInflater inflater;


    public SlidingTileScoreBoardAdapter(List<TileScore> list, Context context) {
        mContext = context;
        slidingTileTopScores = list;
        inflater = (LayoutInflater) mContext
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
//        View v = convertView;
//        CompleteListViewHolder viewHolder;
//        if (convertView == null) {
//            mLayoutInflater= (LayoutInflater) mContext
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = mLayoutInflater.inflate(R.layout.tile_game_score_board_row,parent, false);
//            viewHolder = new CompleteListViewHolder(v);
//            v.setTag(viewHolder);
//        } else {
//            viewHolder = (CompleteListViewHolder) v.getTag();
//        }
//
//        viewHolder.userName.setText(slidingTileTopScores.get(position).user);
//        viewHolder.userScore.setText(slidingTileTopScores.get(position).value+"");
//        System.out.println(slidingTileTopScores.get(position).user);
//        System.out.println(slidingTileTopScores.get(position).value);
//        return v;
        View row = inflater.inflate(R.layout.tile_game_score_board_row, parent,false);
        TextView userName = (TextView)row.findViewById(R.id.scoreBoardUser);
        TextView score = (TextView)row.findViewById(R.id.scoreBoardScore);

        userName.setText(slidingTileTopScores.get(position).user);
        score.setText(slidingTileTopScores.get(position).value+"");

        return row;
    }
}
//class CompleteListViewHolder {
//    public TextView userName;
//    public TextView userScore;
//    public CompleteListViewHolder(View view) {
//        userName = (TextView) view.findViewById(R.id.scoreBoardUser);
//        userScore = (TextView) view.findViewById(R.id.scoreBoardScore);
//    }
//}

