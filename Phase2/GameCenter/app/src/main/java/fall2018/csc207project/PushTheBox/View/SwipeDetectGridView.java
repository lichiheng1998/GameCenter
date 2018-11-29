package fall2018.csc207project.PushTheBox.View;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import fall2018.csc207project.PushTheBox.Controllers.GamePresenter;

/**
 * The class SwipeDetectGridView that extends GridView.
 */
public class SwipeDetectGridView extends GridView {

    /**
     * The GestureDetector for this game.
     */
    GestureDetector gestureDetector;

    /**
     * The GamePresenter for this game.
     */
    GamePresenter presenter;

    /**
     * To move up, down, right or left.
     */
    private final static int UP = 1;
    private final static int LEFT = 2;
    private final static int DOWN = 3;
    private final static int RIGHT = 4;

    /**
     * Process a swipe for two Tiles.
     *
     * @param context the context of this app
     */
    public SwipeDetectGridView(Context context) {
        super(context);
        init(context);
    }

    /**
     * Process a swipe for two Tiles.
     *
     * @param context the context of this app
     * @param attrs the AttributeSet to process a swipe
     */
    public SwipeDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Process a swipe for two Tiles.
     *
     * @param context the context of this app
     * @param attrs the AttributeSet to process a swipe
     * @param defStyleAttr the style of the AttributeSet
     */
    public SwipeDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SwipeDetectGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * The init for the constructor SwipeDetectGridView.
     *
     * @param context the context of this app
     */
    @SuppressLint("ClickableViewAccessibility")
    private void init(final Context context) {
        gestureDetector=new GestureDetector(context, new OnSwipeListener(){
            @Override
            public void onSwipe(int dir) {
                switch (dir){
                    case UP:
                        presenter.arrowButtonClicked(context, "up");
                        break;
                    case LEFT:
                        presenter.arrowButtonClicked(context, "left");
                        break;
                    case DOWN:
                        presenter.arrowButtonClicked(context, "down");
                        break;
                    case RIGHT:
                        presenter.arrowButtonClicked(context, "right");
                        break;
                }
            }
        });
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    /**
     * Set the GamePresenter.
     *
     * @param presenter the given GamePresenter
     */
    public void setPresenter(GamePresenter presenter){
        this.presenter = presenter;
    }
}
