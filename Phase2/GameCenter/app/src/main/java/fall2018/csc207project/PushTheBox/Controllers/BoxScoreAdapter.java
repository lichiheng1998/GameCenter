package fall2018.csc207project.PushTheBox.Controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc207project.PushTheBox.Models.BoxScore;
import fall2018.csc207project.R;

public class BoxScoreAdapter extends BaseAdapter {

    private Context context;
    private List<BoxScore> scores;

    public BoxScoreAdapter(Context context, List<BoxScore> scores){
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
            view = LayoutInflater.from(context).inflate(R.layout.box_game_score_board_row, parent, false);
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
    class MyViewHolder{
        TextView userName;
        TextView userScore;
        MyViewHolder(View base) {
            userName = base.findViewById(R.id.boxScoreBoardUser);
            userScore = base.findViewById(R.id.boxScoreBoardScore);
        }
    }

