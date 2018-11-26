package fall2018.csc207project.Memorization.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc207project.R;

public class GameStartDialogFragment extends DialogFragment {
    private Button proceed;
    private TextView instructions;
    GameStartDialogListener listener;

    public interface GameStartDialogListener{
        void proceedToGame();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.memo_start_dialog, container);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getDialog().setCanceledOnTouchOutside(false);
        proceed = view.findViewById(R.id.proceed);

    }
}
