package me.blog.netrance.android.keynavibetweenbuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Nothing is implemented here because the objective of this project is
 * just moving a focus from one button to another.
 * Buttons are defined in layout_of_activity_main.xml.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_activity_main);
    }

}
