package fall2018.csc207project.Memorization.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import fall2018.csc207project.R;
import fall2018.csc207project.SlidingTile.Models.TileScore;

public class MemoScoreBoardAdapter extends BaseAdapter {

    private List<TileScore> memoTopScores;
    private Context mContext;

    MemoScoreBoardAdapter(List<TileScore> list, Context context) {
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

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
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

    class MyViewHolder{
        TextView userName;
        TextView userScore;
        MyViewHolder(View base) {
            userName = base.findViewById(R.id.scoreBoardUser);
            userScore = base.findViewById(R.id.scoreBoardScore);
        }
    }
}
