package fall2018.csc207project.Memorization.Controllers;

import fall2018.csc207project.Memorization.Models.MemoManager;

public interface GamePresenter {
    public void onTileClicked(int position);
    public void setMemoManager(MemoManager memoManager);
}
