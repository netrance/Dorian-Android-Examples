package cf.domone.android.swiperefreshlayout_ex01;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * This activity shows an example about how to refresh a view.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TEST_URL = "http://www.youtube.com/";

    /** This web view will be refreshed. */
    private WebView wvExample;

    /** To refresh the web view by a vertical swipe gesture */
    private SwipeRefreshLayout srlExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.wvExample = (WebView)findViewById(R.id.wvExample);
        this.wvExample.getSettings().setJavaScriptEnabled(true);
        this.wvExample.setWebViewClient(new WebViewClient());
        this.wvExample.loadUrl(TEST_URL);

        this.srlExample = (SwipeRefreshLayout)findViewById(R.id.srlExample);
        this.srlExample.setOnRefreshListener(srlExampleRefreshListener);
    }

    private SwipeRefreshLayout.OnRefreshListener srlExampleRefreshListener
            = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // Refreshs the web view.
            wvExample.reload();

            // Hides the refresh icon on the top of the SwipeRefreshLayout view.
            srlExample.setRefreshing(false);
        }
    };
}
