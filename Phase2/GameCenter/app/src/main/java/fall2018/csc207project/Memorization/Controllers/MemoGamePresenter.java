package fall2018.csc207project.Memorization.Controllers;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Models.MemoTile;
import fall2018.csc207project.Memorization.Views.MemoGameView;

public class MemoGamePresenter implements GamePresenter{
    MemoManager memoManager;
    MemoGameView view;
    boolean isDisplaying;
    Iterator<MemoTile> iterator;

    public MemoGamePresenter(final MemoGameView view) {
        this.view = view;
        isDisplaying = false;
    }

    public void start(){
        iterator = memoManager.iterator();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(iterator.hasNext()){
                    MemoTile tile = iterator.next();
                    if(tile.status == MemoTile.TYPEACTIVE){
                        view.updateButtonToGreen(tile.getId());
                    }
                } else {
                    view.restoreButtonColor();
                    this.cancel();
                }
            }
        }, 0, 2000);
    }
    @Override
    public void onTapOnTile(int position) {
        view.updateButtonToGreen(position);
    }

    @Override
    public void setMemoManager(MemoManager memoManager) {
        this.memoManager = memoManager;
    }
}
