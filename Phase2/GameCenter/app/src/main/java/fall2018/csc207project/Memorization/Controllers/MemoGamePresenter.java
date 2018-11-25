package fall2018.csc207project.Memorization.Controllers;

import android.util.Log;
import android.widget.Toast;


import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Models.MemoTile;
import fall2018.csc207project.Memorization.Views.MemoGameView;

/**
 * Class represents the implementation of the  game logic.
 */
public class MemoGamePresenter implements GamePresenter{
    private MemoManager memoManager;
    private MemoGameView view;
    private Iterator<MemoTile> verifyIterator;
    private MemoTile nextToVerify;
    private boolean isDisplaying;
    private int period = 2000;
    private int flashDelay = 1000;
    private boolean gameOver = false;

    public MemoGamePresenter(final MemoGameView view) {
        this.view = view;
        isDisplaying = false;
    }

    private MemoTile getVerifyItems(){
        MemoTile verifyItem = null;
        while (verifyIterator.hasNext()){
            MemoTile item = verifyIterator.next();
            if(item.status == MemoTile.TYPEACTIVE){
                verifyItem = item;
                break;
            }
        }
        return verifyItem;
    }
    public void startCycle(){
        Log.e("debug", "startCycle: hhhhhhhhhhhhhhhhhhhhhhhhhhhh" );
        final Iterator<MemoTile> iterator = memoManager.iterator();
        verifyIterator = memoManager.iterator();
        nextToVerify = getVerifyItems();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(iterator.hasNext()){
                    isDisplaying = true;
                    flashMemoTile(iterator.next());
                } else {
                    isDisplaying = false;
                    this.cancel();
                }
            }
        }, 0, period);
    }

    public void verify(int pos){
       if (!gameOver && nextToVerify.getId() == pos) {
            view.flashButtonToGreen(pos, flashDelay);
            nextToVerify = getVerifyItems();
           if (nextToVerify == null && !gameOver){
               memoManager.updateActiveTiles();
               try {
                   TimeUnit.SECONDS.sleep(2);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               startCycle();
           }
       } else {
            view.flashButtonToRed(pos, flashDelay);
            gameOver = true;
        }
    }

    public void flashMemoTile(MemoTile tile){
        if(tile.status == MemoTile.TYPEACTIVE){
            view.flashButtonToGreen(tile.getId(), flashDelay);
        } else if (tile.status == MemoTile.TYPEFAKE) {
            view.flashButtonToRed(tile.getId(), flashDelay);
        }
    }

    @Override
    public void onTapOnTile(int position) {
        if(!isDisplaying){
            verify(position);
        }
    }

    @Override
    public void setMemoManager(MemoManager memoManager) {
        this.memoManager = memoManager;
    }
}
