package fall2018.csc207project.Controllers;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.util.Map;
import fall2018.csc207project.R;
import fall2018.csc207project.Views.RecyclerViewOnLongPressListener;

/**
 * The class GameCenterListViewAdapter that extends ArrayAdapter<String>
 */
public class GameCenterListViewAdapter extends RecyclerView.Adapter<GameCenterListViewAdapter.ViewHolder> {

    private RecyclerViewOnLongPressListener listener;
    private View.OnClickListener fabListener;
    /**
     * The Map of String and Integer which are the info
     * for the games you added in the GameCenter.
     */
    private Map<String, Integer> bgMap;
    private List<String> gameList;
    private boolean isVisible = false;

    /**
     * Construct a new GameCenterListViewAdapter
     * by given a Context, a List<String>, a Map<String, Integer>
     *
     * @param gameList the list of String representing the games' name
     * @param bgMap the Map of each games' info
     */
    public GameCenterListViewAdapter(List<String> gameList, Map<String, Integer> bgMap){
        this.gameList = gameList;
        this.bgMap = bgMap;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        public RecyclerViewOnLongPressListener listener;
        public ImageView image;
        public TextView text;
        FloatingActionButton fab;

        ViewHolder(View itemView, RecyclerViewOnLongPressListener listener, View.OnClickListener
                fabListener) {
            super(itemView);
            image = itemView.findViewById(R.id.background);
            text = itemView.findViewById(R.id.gameName);
            this.listener = listener;
            fab = itemView.findViewById(R.id.delete);
            fab.setOnClickListener(fabListener);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onLongPress(view, 0);
            return true;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.local_game_center_row, parent, false);
        return new ViewHolder(v, listener, fabListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        String game = gameList.get(i);
        holder.text.setText(game);
        holder.image.setImageResource(bgMap.get(game));
        holder.itemView.setTag(game);
        holder.fab.setTag(game);
        if (isVisible){
            holder.fab.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.scale_up));
            holder.fab.show();
        } else {
            holder.fab.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.scale_down));
            holder.fab.hide();
        }
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public void updateVisibility(boolean newValue){
        isVisible= newValue;
        this.notifyDataSetChanged();
    }

    public void setOnLongPressListener(RecyclerViewOnLongPressListener listener){
        this.listener = listener;
    }

    public void setFabListener(View.OnClickListener listener){
        this.fabListener = listener;
    }

    public void remove(String game) {
        int position = gameList.indexOf(game);
        gameList.remove(position);
        notifyItemRemoved(position);
    }
}
