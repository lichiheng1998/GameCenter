package fall2018.csc207project.SlidingTile.Views;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.widget.NumberPicker;

import java.util.Objects;

/**
 * The class that represents a number picker.
 * Codes are taken from url: "http://www.zoftino.com/android-numberpicker-dialog-example"
 */
public class NumberPickerDialog extends DialogFragment {

    /**
     * The NumberPicker.OnValueChangeListener for creating a new NumberPicker.
     */
    private NumberPicker.OnValueChangeListener valueChangeListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final NumberPicker numberPicker = new NumberPicker(getActivity());
        numberPicker.setValue(1);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(60);
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Choose Value");
        builder.setMessage("Choose a number :");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });
        builder.setView(numberPicker);
        return builder.create();
    }

    /**
     * Set the value of the NumberPicker
     * by given a NumberPicker.OnValueChangeListener
     *
     * @param valueChangeListener a NumberPicker need to be set
     */
    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}