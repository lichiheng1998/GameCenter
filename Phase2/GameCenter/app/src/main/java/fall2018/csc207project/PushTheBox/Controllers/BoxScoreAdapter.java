package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import fall2018.csc207project.PushTheBox.Models.BoxScore;
import fall2018.csc207project.R;

/**
 * The class BoxScoreAdapter that extends BaseAdapter.
 */
public class BoxScoreAdapter extends BaseAdapter {

    /**
     * The context for the app.
     */
    private Context context;

    /**
     * List of BoxScore want to be show on the ListView.
     */
    private List<BoxScore> scores;

    /**
     * Construct a new BoxScoreAdapter
     * by taking a Context and a List<BoxScore>.
     *
     * @param context the context for the app
     * @param scores List of BoxScore want to be show on the ListView
     */
    BoxScoreAdapter(Context context, List<BoxScore> scores){
        this.context = context;
        this.scores = scores;
    }
    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("No enter here", "GetView Entered or Not");
        View view = convertView;
        MyViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.box_game_score_board_row, parent,
                    false);
            viewHolder = new MyViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        viewHolder.userName.setText(scores.get(position).user);
        viewHolder.userScore.setText(String.valueOf(scores.get(position).value));

        return view;
    }
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
        userName = base.findViewById(R.id.boxScoreBoardUser);
        userScore = base.findViewById(R.id.boxScoreBoardScore);
    }
}


