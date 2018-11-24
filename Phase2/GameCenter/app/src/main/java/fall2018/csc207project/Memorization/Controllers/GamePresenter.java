package fall2018.csc207project.Memorization.Controllers;

import fall2018.csc207project.Memorization.Models.MemoManager;

public interface GamePresenter {
    void onTapOnTile(int position);
    void setMemoManager(MemoManager memoManager);
    void startCycle();
}
