package me.blog.netrance.android.loading_local_html;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * This activity shows loading a local HTML file into a web view.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wvExample = (WebView)findViewById(R.id.wvExample);
        wvExample.loadUrl("file:///android_asset/example.html");
    }
}
