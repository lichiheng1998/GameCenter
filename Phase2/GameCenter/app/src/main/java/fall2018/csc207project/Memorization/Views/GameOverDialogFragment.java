package fall2018.csc207project.Memorization.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.io.Serializable;

import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.R;

/**
 * Dialog that will appears when game is over.
 */
public class GameOverDialogFragment extends DialogFragment {

    /**
     * button to restart a new game
     */
    private Button restartButton;

    /**
     * button to quit
     */
    private Button quitButton;

    /**
     * text view for displaying score
     */
    private TextView scoreField;

    /**
     * A GameOverDialogListener show on screen when game is over.
     */
    GameOverDialogListener listener;

    /**
     * A dialog that will show on screen when game is over.
     */
    public interface GameOverDialogListener {
        void onRefresh();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gameover_dialog, container);
    }

    /**
     * Adapted from "https://www.cs.dartmouth.edu/~sergey/cs65/fragments-puzzles.txt"
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (MemoGameActivity)context;
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (GameOverDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getDialog().setCanceledOnTouchOutside(false);
        quitButton = view.findViewById(R.id.quit);
        restartButton = view.findViewById(R.id.restart);
        scoreField = view.findViewById(R.id.score);
        setupQuitButtonListener();
        assert getArguments() != null;
        setupRestartButtonListener(getArguments().getSerializable("manager"));
        setupScore(getArguments().getInt("score"));
    }

    /**
     * Create a new GameOverDialogFragment
     * be given an int and a MemoManager.
     *
     * @param score the score for the current user
     * @param manager the MemoManager to manage MemoGame
     *
     * @return a new GameOverDialogFragment be given an int and a MemoManager
     */
    public static GameOverDialogFragment newInstance(int score, MemoManager manager) {
        GameOverDialogFragment frag = new GameOverDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("manager", manager);
        args.putInt("score", score);
        frag.setArguments(args);

        return frag;
    }

    /**
     * Set up the restart button by given a final Serializable manager.
     *
     * @param manager a Serializable manager
     */
    private void setupRestartButtonListener(final Serializable manager){
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRefresh();
                Intent tmp = new Intent(getActivity(), MemoGameActivity.class);
                tmp.putExtra("save", manager);
                startActivity(tmp);
            }
        });
    }

    /**
     * Set up the Quit button for quiting the game.
     */
    private void setupQuitButtonListener(){
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRefresh();
            }
        });
    }

    /**
     * Set up the current user's score by given an int.
     *
     * @param score the current user's score
     */
    private void setupScore(int score){
        scoreField.setText(String.valueOf(score));
    }
}
