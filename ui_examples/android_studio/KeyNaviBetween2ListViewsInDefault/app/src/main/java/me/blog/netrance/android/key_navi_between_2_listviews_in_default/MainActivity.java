package me.blog.netrance.android.key_navi_between_2_listviews_in_default;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * The objective of this activity is
 * to check key navigation between two list views.
 * In general, the focus of selected nth item of the left list view
 * moves to nth item of the right one when pressing right key
 * and vice versa.
 */

public class MainActivity extends AppCompatActivity {

    /** This list view shows parts. */
    private ListView lvPart;

    /** This list view shows the chapters of a selected part.
     * In this project, it shows them of part 1 only.
     */
    private ListView lvChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_activity_main);

        lvPart = (ListView)findViewById(R.id.lvPart);
        lvChapter = (ListView)findViewById(R.id.lvChapter);

        lvPart.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataReader.getParts()));
        lvChapter.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataReader.getChaptersOfPart1()));
    }

}