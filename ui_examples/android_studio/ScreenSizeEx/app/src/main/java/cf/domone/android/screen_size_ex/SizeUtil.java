package cf.domone.android.screen_size_ex;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Util class to get various kinds of sizes
 */
public class SizeUtil {

    public static int getRealDisplayWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point realDisplaySize = new Point();

        display.getRealSize(realDisplaySize);
        return realDisplaySize.x;
    }

    public static int getRealDisplayHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point realDisplaySize = new Point();

        display.getRealSize(realDisplaySize);
        return realDisplaySize.y;
    }

    public static int getAppDisplayWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size =  new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getAppDisplayHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size =  new Point();
        display.getSize(size);
        return size.y;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
        );

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight(Activity activity) {
        if (activity instanceof AppCompatActivity) {
            ActionBar ab = ((AppCompatActivity) activity).getSupportActionBar();
            return ab.getHeight();
        }
        else {
            android.app.ActionBar ab = activity.getActionBar();
            return ab.getHeight();
        }
    }

    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "navigation_bar_height",
                "dimen",
                "android"
        );

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActivityContentWidth(Activity activity) {
        View content = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        return content.getWidth();
    }

    public static int getActivityContentHeight(Activity activity) {
        View content = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        return content.getHeight();
    }

}
