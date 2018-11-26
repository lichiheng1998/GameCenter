package fall2018.csc207project.Memorization.Controllers;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Models.MemoTile;
import fall2018.csc207project.Memorization.Views.MemoGameView;

/**
 * Class represents the implementation of the  game logic.
 */
public class MemoGamePresenter implements GamePresenter{
    private int successTap;
    private MemoManager memoManager;
    private MemoGameView view;
    private Iterator<MemoTile> verifyIterator;
    private MemoTile nextToVerify;
    private boolean isDisplaying;
    private int period = 2000;
    private int flashDelay = 1000;
    private boolean gameOver;

    public MemoGamePresenter(final MemoGameView view) {
        this.view = view;
        isDisplaying = false;
        gameOver = false;
        successTap = 0;
        view.updateScore(successTap);
    }

    /**
     * Get the next MemoTile to be verified.
     */
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
        final Iterator<MemoTile> iterator = memoManager.iterator();
        verifyIterator = memoManager.iterator();
        nextToVerify = getVerifyItems();
        isDisplaying = true;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(iterator.hasNext()){
                    flashMemoTile(iterator.next());
                } else {
                    isDisplaying = false;
                    this.cancel();
                }
            }
        }, 2000, period);
    }

    /**
     * If the buttons tap are in wrong order, the game ends. Otherwise, flash the button to green.
     * @param pos the position that user taps.
     */
    public void verify(int pos){
        if (!gameOver && nextToVerify.getId() == pos) {
            view.flashButtonToBlue(pos, flashDelay);
            successTap += 1;
            view.updateScore(successTap);
            memoManager.setScoreTotal(successTap);
            nextToVerify = getVerifyItems();
            if (nextToVerify == null) {
                memoManager.updateActiveTiles();
                startCycle();
            }
        } else {
            view.flashButtonToRed(pos, flashDelay);
            view.showGameOverDialog(successTap, memoManager.getNewInstance());
            gameOver = true;
        }
    }

    /**
     * Flash the tile corresponding to the status of the tile. Green for active tile, red for fake
     * tile.
     */
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
