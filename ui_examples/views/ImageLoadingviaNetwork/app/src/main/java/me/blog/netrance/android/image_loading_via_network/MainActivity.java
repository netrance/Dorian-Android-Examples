package me.blog.netrance.android.image_loading_via_network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_activity_main);

        ImageView ivExample = (ImageView)findViewById(R.id.ivExample);
        ImageLoaderTask imageLoaderTask = new ImageLoaderTask(
                ivExample,
                "http://www.fnordware.com/superpng/pnggrad8rgb.png"
        );
        imageLoaderTask.execute();
    }

}
