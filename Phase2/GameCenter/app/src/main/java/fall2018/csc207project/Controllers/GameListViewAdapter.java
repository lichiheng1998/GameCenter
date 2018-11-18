package fall2018.csc207project.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.List;
import java.util.Set;
import fall2018.csc207project.R;

public class GameListViewAdapter extends BaseAdapter{

    private String[] globalList;
    private List<String> userGames;
    private CompoundButton.OnCheckedChangeListener listener;
    private Context context;

    GameListViewAdapter(Context context, String[] globalList, List<String> userGames,
                        CompoundButton.OnCheckedChangeListener listener){
        this.context = context;
        this.globalList = globalList;
        this.userGames = userGames;
        this.listener = listener;
    }

    // View Holder pattern for android.
    class MyViewHolder{
        Switch aSwitch;
        public MyViewHolder(View v){
            this.aSwitch = v.findViewById(R.id.GameSwitch);
            this.aSwitch.setOnCheckedChangeListener(listener);
        }
    }
    @Override
    public int getCount() {
        return globalList.length;
    }

    @Override
    public Object getItem(int i) {
        return globalList[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View row = view;
        MyViewHolder holder;
        if (row == null) {
            row = LayoutInflater.from(context).
                    inflate(R.layout.game_list_col, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (MyViewHolder)row.getTag();
        }
        String game = globalList[i];
        holder.aSwitch.setText(game);
        holder.aSwitch.setChecked(userGames.contains(game));
        return row;
    }
}
