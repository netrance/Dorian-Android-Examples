package cf.domone.android.android_bridge_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wvExample = (WebView)findViewById(R.id.wvExample);
        wvExample.getSettings().setJavaScriptEnabled(true);
        wvExample.setWebViewClient(new WebViewClient());
        wvExample.loadUrl("http://netrance.cafe24.com/HtmlExamples/AndroidBridge/android_bridge_example_01.html");
        wvExample.addJavascriptInterface(new AndroidBridge(), "BridgeFromMainActivity");
    }

    /**
     * Bridge class to call Java methods from a web view.
     */
    private class AndroidBridge {

        /**
         * The JavaScript interface to make a web view request to finish this activity
         */
        @JavascriptInterface
        public void finish() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
        }
    }
}
