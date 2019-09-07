package fall2018.csc207project.SlidingTile.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import java.io.Serializable;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile implements Comparable<Tile>, Serializable {

    /**
     * The unique id.
     */
    private int id;


    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     * @param id the background
     */
    Tile(int id) {
        this.id = id + 1;
    }

    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }

    /**
     * Return the background image for creating Tile buttons.
     *
     * @param context the context.
     * @param wholeBg the bitmap representation of the full background.
     * @param complexity the complexity of the board.
     * @return the background of the current tile.
     */
    public BitmapDrawable getBackground(Context context, Bitmap wholeBg, int complexity){
        // is the complexity is changed, or it is the first board,
        // get the complete image and calculate the width and height
        int width = wholeBg.getWidth()/complexity;
        int height = wholeBg.getHeight()/complexity;

        Bitmap portionBg = this.getPortionBg(wholeBg, complexity, width, height);
        // make bitmap drawable to put on button
        BitmapDrawable backgroundBd = new BitmapDrawable(context.getResources(), portionBg);

        if (id == complexity * complexity){
            Bitmap blank = portionBg.copy(portionBg.getConfig(), true);
            blank.eraseColor(Color.WHITE);
            return new BitmapDrawable(context.getResources(), blank);
        }
        return backgroundBd;
    }

    /**
     * Return the portion image by cropping the given background.
     *
     * @param width the width of the portion.
     * @param height the height of the portion.
     * @param wholeBg the bitmap representation of the full background.
     * @param complexity the complexity of the board.
     * @return the portion of the given background.
     */
    private Bitmap getPortionBg(Bitmap wholeBg, int complexity, int width, int height){
        // find the left top coordinate for the tile on the complete image
        int x = (id-1)%complexity * width;
        int y = (id-1)/complexity * height;
        // crop out the part for tile
        return Bitmap.createBitmap(wholeBg, x, y, width, height);
    }
}
