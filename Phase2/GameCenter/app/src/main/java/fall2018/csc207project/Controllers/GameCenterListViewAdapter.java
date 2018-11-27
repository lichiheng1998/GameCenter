package fall2018.csc207project.Controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import fall2018.csc207project.R;

public class GameCenterListViewAdapter extends ArrayAdapter<String>{

    private Map<String, Integer> bgMap;

    public GameCenterListViewAdapter(Context context, List<String> gameList, Map<String, Integer> bgMap){
        super(context,0, gameList);
        this.bgMap = bgMap;
    }

    /**
     * The view holder class is used for implement the view holder pattern of android.
     * */
    class MyViewHolder{
        TextView textView;
        ImageView imageView;
        private MyViewHolder(View v){
            this.textView = v.findViewById(R.id.gameName);
            this.imageView = v.findViewById(R.id.background);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
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
     * The view holder class is used for implement the view holder pattern of android.
     * @param resId The id of the resource.
     * @return imageRounded The bit with the round corner.
     * */
    private Bitmap createRoundedImage(int resId){
        Bitmap mbitmap = BitmapFactory.decodeResource(getContext().getResources(), resId);
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(),
                mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())),
                100, 100, mpaint);
        return imageRounded;
    }
}
