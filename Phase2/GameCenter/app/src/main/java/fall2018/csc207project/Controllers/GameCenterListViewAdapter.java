package fall2018.csc207project.Controllers;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Map;

import fall2018.csc207project.Models.GlideApp;
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
    private Map<String, StorageReference> bgMap;
    private List<String> gameList;
    private boolean isVisible = false;

    public GameCenterListViewAdapter(List<String> gameList, Map<String, StorageReference> bgMap){
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
        GlideApp.with(holder.itemView.getContext())
                .load(bgMap.get(game))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.image);
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
