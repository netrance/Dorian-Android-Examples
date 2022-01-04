package me.blog.netrance.android.opening_drawerlayout;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * This Activity has two buttons to open left and right drawers.
 * Left drawer opens when OPEN LEFT DRAWER button is clicked,
 * and right one does when OPEN RIGHT DRAWER button is clicked.
 */
public class MainActivity extends AppCompatActivity {

    /**
     *  The main layout of this activity.
     *  We can open drawers using it.
     */
    private DrawerLayout dlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_activity_main);

        // Get views.
        this.dlMain = (DrawerLayout)findViewById(R.id.dlMain);
        Button btnOpenLeftDrawer = (Button)findViewById(R.id.btnOpenLeftDrawer);
        Button btnOpenRightDrawer = (Button)findViewById(R.id.btnOpenRightDrawer);

        // Set listeners of views.
        btnOpenLeftDrawer.setOnClickListener(btnOpenLeftDrawerClickListener);
        btnOpenRightDrawer.setOnClickListener(btnOpenRightDrawerClickListener);
    }

    private View.OnClickListener btnOpenLeftDrawerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dlMain.openDrawer(Gravity.LEFT);
        }
    };

    private View.OnClickListener btnOpenRightDrawerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dlMain.openDrawer(Gravity.RIGHT);
        }
    };

}
