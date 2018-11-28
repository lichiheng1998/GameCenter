package fall2018.csc207project.Memorization.Controllers;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc207project.Memorization.Models.MemoGameCalculator;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.Memorization.Models.MemoScore;
import fall2018.csc207project.Memorization.Models.MemoTile;
import fall2018.csc207project.Memorization.Views.MemoGameView;
import fall2018.csc207project.Models.DatabaseUtil;
import fall2018.csc207project.Models.ScoreManager;

/**
 * Class represents the implementation of the  game logic.
 */
public class MemoGamePresenter implements GamePresenter {
    private int successTap;
    private MemoManager memoManager;
    private MemoGameView view;
    private Iterator<MemoTile> verifyIterator;
    private MemoTile nextToVerify;
    private boolean isDisplaying;
    private int life;
    private int period = 2000;
    private int flashDelay = 1000;
    private boolean gameOver;

    public MemoGamePresenter(final MemoGameView view) {
        this.view = view;
        isDisplaying = false;
        gameOver = false;
        successTap = 0;
        life = 3;
        view.updateScore(successTap);
        view.updateLife(life);
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
        view.updateStatus(true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(iterator.hasNext()){
                    flashMemoTile(iterator.next());
                } else {
                    isDisplaying = false;
                    view.updateStatus(false);
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
        if (!gameOver && nextToVerify.getId() == pos){
            success(pos);
        } else {
            fail(pos);
        }
    }

    private void success(int pos){
        view.flashButtonToColor(pos, flashDelay, MemoTile.PRESSCOLOR);
        successTap += 1;
        view.updateScore(successTap);
        nextToVerify = getVerifyItems();
        if (nextToVerify == null) {
            memoManager.updateActiveTiles();
            startCycle();
        }
    }
    private void fail(int pos){
        view.flashButtonToColor(pos, flashDelay, MemoTile.WRONGCOLOR);
        life = life == 0 ? 0 : life-1;
        view.updateLife(life);
        gameOver = life == 0;
        if(gameOver){
            memoManager.setScoreTotal(successTap);
            view.showGameOverDialog(successTap, memoManager.getNewInstance());

            MemoScore score = new MemoScore(memoManager.getHeightDifficulty()
                    , memoManager.isLevel(), memoManager.getScoreTotal());
            MemoGameCalculator calculator = new MemoGameCalculator();
//            ScoreManager<MemoScore> scoreManager
//                    = DatabaseUtil.getScoreManager("MemoGame", currentUser, calculator);
//            scoreManager.saveScore(score, context);
        }
    }
    /**
     * Flash the tile corresponding to the status of the tile. Green for active tile, red for fake
     * tile.
     */
    public void flashMemoTile(MemoTile tile){
        if(tile.status == MemoTile.TYPEACTIVE){
            view.flashButtonToColor(tile.getId(), flashDelay, MemoTile.ACTIVECOLOR);
        } else if (tile.status == MemoTile.TYPEFAKE) {
            view.flashButtonToColor(tile.getId(), flashDelay, MemoTile.FAKECOLOR);
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
