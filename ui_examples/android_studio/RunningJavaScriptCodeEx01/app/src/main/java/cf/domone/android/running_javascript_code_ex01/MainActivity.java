package cf.domone.android.running_javascript_code_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView wvExample = (WebView)findViewById(R.id.wvExample);
        wvExample.getSettings().setJavaScriptEnabled(true);
        wvExample.loadUrl("javascript: document.write('Hello, dogs and pigs.<br/>');");
        wvExample.loadUrl("javascript: document.write('He was also such a dog or a pig.');");
    }

}