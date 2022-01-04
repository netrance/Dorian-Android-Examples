package me.blog.netrance.android.title_from_webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * This example demonstrates how to read the title from a web view
 * and update it to the title of this action bar.
 * CustomWebViewClient class is derived from WebViewClient,
 * and it was implemented for this activity to read the title from the web view.
 *
 * @author Domone
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_activity_main);

        WebView wvExample = (WebView)findViewById(R.id.wvExample);
        WebSettings wvExampleSettings = wvExample.getSettings();
        wvExampleSettings.setJavaScriptEnabled(true);
        wvExample.setWebViewClient(new CustomWebViewClient(getSupportActionBar()));
        wvExample.loadUrl("http://www.google.com");
    }

}
