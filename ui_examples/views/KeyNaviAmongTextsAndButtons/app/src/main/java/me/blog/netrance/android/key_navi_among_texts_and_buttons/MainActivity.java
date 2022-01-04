package me.blog.netrance.android.key_navi_among_texts_and_buttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Nothing is implemented here because the objective of this project is
 * just moving a focus from one button to another.
 * There are also text views, but the focus can't move to them.
 * Buttons are defined in layout_of_activity_main.xml.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_activity_main);
    }

}
