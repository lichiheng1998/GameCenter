package fall2018.csc207project.SlidingTile.Controllers;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.TileScore;

/**
 * A class called SlidingTileScoreBoardAdapter extends BaseAdapter.
 */
public class SlidingTileScoreBoardAdapter extends BaseAdapter {

    /**
     * List of of TileScores.
     */
    private List<TileScore> slidingTileTopScores;

    /**
     * The context of this app.
     */
    private Context mContext;

    /**
     * Construct a new SlidingTileScoreBoardAdapter
     * by given a list and a context.
     *
     * @param list the list of TileScores
     * @param context the context of this app
     */
    SlidingTileScoreBoardAdapter(List<TileScore> list, Context context) {
        slidingTileTopScores = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return slidingTileTopScores.size();
    }

    @Override
    public Object getItem(int pos) {
        return new String[] {slidingTileTopScores.get(pos).user,
                String.valueOf(slidingTileTopScores.get(pos).value)};
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        MyViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.tile_game_score_board_row, parent, false);
            viewHolder = new MyViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        viewHolder.userName.setText(slidingTileTopScores.get(position).user);
        viewHolder.userScore.setText(String.valueOf(slidingTileTopScores.get(position).value));
        return view;
    }

    /**
     *  A class called MyViewHolder
     */
    class MyViewHolder{

        /**
         * Current user's name
         */
        TextView userName;

        /**
         * Current user's score
         */
        TextView userScore;

        /**
         * Construct a new MyViewHolder by given a View base.
         *
         * @param base the current view show on screen
         */
        MyViewHolder(View base) {
            userName = base.findViewById(R.id.memoScoreBoardUser);
            userScore = base.findViewById(R.id.memoScoreBoardScore);
        }
    }
}



