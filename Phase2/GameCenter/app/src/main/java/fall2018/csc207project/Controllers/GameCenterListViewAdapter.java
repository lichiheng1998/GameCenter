package fall2018.csc207project.Controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.util.Map;
import fall2018.csc207project.R;

/**
 * The class GameCenterListViewAdapter that extends ArrayAdapter<String>
 */
public class GameCenterListViewAdapter extends ArrayAdapter<String> {

    /**
     * The Map of String and Integer which are the info
     * for the games you added in the GameCenter.
     */
    private Map<String, Integer> bgMap;

    /**
     * Construct a new GameCenterListViewAdapter
     * by given a Context, a List<String>, a Map<String, Integer>
     *
     * @param context the context of the app
     * @param gameList the list of String representing the games' name
     * @param bgMap the Map of each games' info
     */
    public GameCenterListViewAdapter(Context context, List<String> gameList, Map<String, Integer> bgMap){
        super(context,0, gameList);
        this.bgMap = bgMap;
    }

    /**
     * The view holder class is used for implement the view holder pattern of android.
     */
    class MyViewHolder{

        /**
         * The TextView of each game.
         */
        TextView textView;

        /**
         * The ImageView of each game.
         */
        ImageView imageView;

        /**
         * Construct a new MyViewHolder by given a View.
         *
         * @param v the current view
         */
        private MyViewHolder(View v){
            this.textView = v.findViewById(R.id.gameName);
            this.imageView = v.findViewById(R.id.background);
        }
    }

    @NonNull
    @Override
    public View getView(int i, View view, @NonNull ViewGroup parent) {
        View row = view;
        MyViewHolder holder;
        if (row == null) {
            row = LayoutInflater.from(getContext()).
                    inflate(R.layout.local_game_center_row, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (MyViewHolder)row.getTag();
        }

        holder.textView.setText(getItem(i));
        holder.imageView.setImageBitmap(createRoundedImage(bgMap.get(getItem(i))));
        return row;
    }

    /**
     * Cut the resource with the resId to the image with round corner.
     * @param resId The id of the resource.
     * @return imageRounded The bit with the round corner.
     * */
    private Bitmap createRoundedImage(int resId){
        Bitmap mBitMap = BitmapFactory.decodeResource(getContext().getResources(), resId);
        Bitmap imageRounded = Bitmap.createBitmap(mBitMap.getWidth(), mBitMap.getHeight(),
                mBitMap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(new BitmapShader(mBitMap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mBitMap.getWidth(), mBitMap.getHeight())),
                100, 100, mPaint);
        return imageRounded;
    }
}
