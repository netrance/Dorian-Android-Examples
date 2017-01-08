package lee.dorian.camera_preview_ex01;

import android.app.Activity;
import android.content.DialogInterface;
import android.app.AlertDialog;

/**
 * Util class to open dialogs more easily.
 */
public class DialogUtils {

    /**
     * Opens a dialog. The activity finishes when the button of the dialog is clicked.
     * @param activity The activity that shows the dialog
     * @param title The title of the dialog
     * @param message The message of the dialog
     * @param buttonName The name of the button on the dialog
     */
    public static void openFinishDialog(
            final Activity activity,
            final String title,
            final String message,
            final String buttonName
    ) {
        AlertDialog ad = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .show();
    }

}
