package lee.dorian.camera_preview_ex01;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.support.annotation.NonNull;

/**
 * Util class to control cameras
 */

public class CameraUtils {

    /**
     * Checks if there is camera hardwares on the device.
     * @param context A context to control cameras.
     * @return true if the device has one or more cameras.
     */
    public static boolean hasCameraFeature(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void doAutorotation(@NonNull Context context, @NonNull Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            parameters.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            parameters.setRotation(0);
        }
        else {
            parameters.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            parameters.setRotation(90);
        }
    }
}
