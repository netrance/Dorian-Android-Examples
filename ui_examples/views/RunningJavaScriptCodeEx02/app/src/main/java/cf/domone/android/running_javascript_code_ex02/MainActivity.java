package cf.domone.android.running_javascript_code_ex02;

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
        wvExample.loadUrl("http://netrance.cafe24.com/HtmlExamples/WithAndroid/reading_name.html");

        // Runs additional JavaScript code after loading the web page.
        wvExample.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setName("world", "id_span_ex", wvExample);
            }
        });
    }

    private void setName(String name, String elementId, WebView webView) {
        webView.loadUrl(String.format("javascript: setNameToElement('%s', '%s');", name, elementId));
    }

}
