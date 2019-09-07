package fall2018.csc207project.Memorization.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import fall2018.csc207project.R;

/**
 * Instruction Dialog Fragment for Memorization Master
 */
public class GameStartDialogFragment extends DialogFragment {

    /**
     * interface that handles events after dialog appear
     */
    private StartDialogListener listener;

    /**
     * Adapted from "https://www.cs.dartmouth.edu/~sergey/cs65/fragments-puzzles.txt"
     * Ensure interface StartDialogListener is implemented
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (MemoGameActivity)context;
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (StartDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    /**
     * interface for events after buttons in the dialog are clicked
     */
    interface StartDialogListener{
        void onButtonClicked();
    }

    /**
     * create a dialog that gives game instruction
     * @param savedInstanceState saved instances
     * @return a dialog object
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.memo_instructions);
        builder.setPositiveButton(R.string.memo_proceed, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                listener.onButtonClicked();
            }
        });
        return builder.create();
    }
}
