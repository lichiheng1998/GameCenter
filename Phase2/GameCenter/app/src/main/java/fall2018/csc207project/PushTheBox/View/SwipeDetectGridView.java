package fall2018.csc207project.PushTheBox.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import fall2018.csc207project.PushTheBox.Controllers.BoxGamePresenter;

public class SwipeDetectGridView extends GridView {
    GestureDetector gestureDetector;
    BoxGamePresenter presenter;

    private final static int UP = 1;
    private final static int LEFT = 2;
    private final static int DOWN = 3;
    private final static int RIGHT = 4;

    public SwipeDetectGridView(Context context) {
        super(context);
        init(context);
    }

    public SwipeDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) // API 21
    public SwipeDetectGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

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

    public void setPresenter(BoxGamePresenter presenter){
        this.presenter = presenter;
    }
}
