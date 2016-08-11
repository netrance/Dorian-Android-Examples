package cf.domone.android.screen_size_ex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This activity shows the sizes of a screen and its UI elements.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Implemented reading sizes in this method
    // because the sizes of the action bar and the activity content view are determined
    // after onCreate method completes.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!hasFocus) {
            return;
        }

        StringBuilder sizesBuilder = new StringBuilder();
        sizesBuilder
                .append(String.format("Device Screen Width: %dpx\n", SizeUtil.getRealDisplayWidth(this)))
                .append(String.format("Device Screen Height: %dpx\n\n", SizeUtil.getRealDisplayHeight(this)))
                .append(String.format("App Screen Width: %dpx\n", SizeUtil.getAppDisplayWidth(this)))
                .append(String.format("App Screen Height: %dpx\n\n", SizeUtil.getAppDisplayHeight(this)))
                .append(String.format("Status Bar Height: %dpx\n", SizeUtil.getStatusBarHeight(this)))
                .append(String.format("Action Bar Height: %dpx\n", SizeUtil.getActionBarHeight(this)))
                .append(String.format("Navigation Bar Height: %dpx\n\n", SizeUtil.getNavigationBarHeight(this)))
                .append(String.format("Activity Content Width: %dpx\n", SizeUtil.getActivityContentWidth(this)))
                .append(String.format("Activity Content Height: %dpx\n\n", SizeUtil.getActivityContentHeight(this)));

        TextView tvScreenSize = (TextView) findViewById(R.id.tvSizes);
        tvScreenSize.setText(sizesBuilder.toString());
    }
}
