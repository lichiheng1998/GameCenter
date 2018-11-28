package fall2018.csc207project.Memorization.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import fall2018.csc207project.Memorization.Models.MemoScore;
import fall2018.csc207project.R;

/**
 * A class called MemoScoreBoardAdapter extends BaseAdapter.
 */
public class MemoScoreBoardAdapter extends BaseAdapter {

    /**
     * List of of MemoScores.
     */
    private List<MemoScore> memoTopScores;

    /**
     * List of of MemoScores.
     */
    private Context mContext;

    /**
     * Construct a new MemoScoreBoardAdapter
     * by given a list and a context.
     *
     * @param list the list of MemoScores
     * @param context the context of this app
     */
    MemoScoreBoardAdapter(List<MemoScore> list, Context context) {
        memoTopScores = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return memoTopScores.size();
    }

    @Override
    public Object getItem(int pos) {
        return new String[] {memoTopScores.get(pos).user,
                String.valueOf(memoTopScores.get(pos).value)};
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        MemoScoreBoardAdapter.MyViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.memo_score_board_row, parent, false);
            viewHolder = new MemoScoreBoardAdapter.MyViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MemoScoreBoardAdapter.MyViewHolder) view.getTag();
        }
        viewHolder.userName.setText(memoTopScores.get(position).user);
        viewHolder.userScore.setText(String.valueOf(memoTopScores.get(position).value));
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
