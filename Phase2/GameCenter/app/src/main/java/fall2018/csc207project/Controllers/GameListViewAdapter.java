package fall2018.csc207project.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import java.util.List;
import fall2018.csc207project.R;

public class GameListViewAdapter extends BaseAdapter{

    /**
     * A List of all game in the global center.
     */
    private String[] globalList;

    /**
     * A List of user's added games
     */
    private List<String> userGames;

    /**
     * A new button picture represent the added game
     */
    private CompoundButton.OnCheckedChangeListener listener;

    /**
     * the context of the app
     */
    private Context context;

    /**
     * Construct a new GameListViewAdapter
     * by given a Context, a String[], a List<String>
     * and a CompoundButton.OnCheckedChangeListener.
     *
     * @param context the context of the app
     * @param globalList a List of all game in the global center
     * @param userGames a List of user's added games
     * @param listener a new button picture represent the added game
     */
    GameListViewAdapter(Context context, String[] globalList, List<String> userGames,
                        CompoundButton.OnCheckedChangeListener listener){
        this.context = context;
        this.globalList = globalList;
        this.userGames = userGames;
        this.listener = listener;
    }

    /**
     * The view holder class is used for implement the view holder pattern of android.
     */
    class MyViewHolder{

        /**
         * The Switch for each game to add or delete from Game Center.
         */
        Switch aSwitch;

        /**
         * Construct a new MyViewHolder by given a View.
         *
         * @param v the current view
         */
        MyViewHolder(View v){
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
