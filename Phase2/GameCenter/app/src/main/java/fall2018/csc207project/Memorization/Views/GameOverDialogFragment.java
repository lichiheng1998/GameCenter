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

import fall2018.csc207project.Memorization.Controllers.MemoStartingActivity;
import fall2018.csc207project.Memorization.Models.MemoManager;
import fall2018.csc207project.PushTheBox.Controllers.BoxStartingActivity;
import fall2018.csc207project.R;

/**
 * Dialog that will appears when game is over.
 */
public class GameOverDialogFragment extends DialogFragment {

    private Button restartButton;
    private Button quitButton;
    private TextView scoreField;
    GameOverDialogListener listener;

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
        setupRestartButtonListener(getArguments().getSerializable("manager"));
        setupScore(getArguments().getInt("score"));
    }

    public static GameOverDialogFragment newInstance(int score, MemoManager manager) {
        GameOverDialogFragment frag = new GameOverDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("manager", manager);
        args.putInt("score", score);
        frag.setArguments(args);

        return frag;
    }

    private void setupRestartButtonListener(final Serializable manager){
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmp = new Intent(getActivity(), MemoGameActivity.class);
                tmp.putExtra("save", manager);
                startActivity(tmp);
                listener.onRefresh();
            }
        });
    }

    private void setupQuitButtonListener(){
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmp = new Intent(getActivity(), MemoStartingActivity.class);
                startActivity(tmp);
                listener.onRefresh();
            }
        });
    }

    private void setupScore(int score){
        scoreField.setText(String.valueOf(score));
    }
}
