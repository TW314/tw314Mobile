package tw314.tw314mobile.interfaces;

import android.content.Intent;
import android.support.v4.app.DialogFragment;

/**
 * Created by pedro on 16/11/16.
 */

public interface AlertDialogInterface {
    // Metodos de clique do dialog implementados na Activity
    public void onDialogPositiveClick(DialogFragment dialog);
    public void onDialogNegativeClick(DialogFragment dialog);
}
