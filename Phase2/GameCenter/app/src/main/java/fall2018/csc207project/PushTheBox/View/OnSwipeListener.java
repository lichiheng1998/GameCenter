package fall2018.csc207project.PushTheBox.View;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Class used to detect the swipe motion of player.
 * Adapted from url: "https://stackoverflow.com/questions/13095494/how-to-detect-swipe-direction-between-left-right-and-up-down"
 */
public abstract class OnSwipeListener extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                           float velocityX, float velocityY) {
        onSwipe(getSlope(e1.getX(), e1.getY(), e2.getX(), e2.getY()));
        return true;
    }

    /**
     * Swipe two tiles.
     *
     * @param dir the direction to process the swipe
     */
    public abstract void onSwipe(int dir);

    /**
     * Get the slope by the given float x1, x2, y1, y2.
     *
     * @param x1 float value
     * @param y1 float value
     * @param x2 float value
     * @param y2 float value
     * @return the slope by the given int x1, x2, y1, y2
     */
    private int getSlope(float x1, float y1, float x2, float y2) {
        Double angle = Math.toDegrees(Math.atan2(y1 - y2, x2 - x1));
        if (angle > 45 && angle <= 135)
            // top
            return 1;
        if (angle >= 135 && angle < 180 || angle < -135 && angle > -180)
            // left
            return 2;
        if (angle < -45 && angle>= -135)
            // down
            return 3;
        if (angle > -45 && angle <= 45)
            // right
            return 4;
        return 0;
    }
}
