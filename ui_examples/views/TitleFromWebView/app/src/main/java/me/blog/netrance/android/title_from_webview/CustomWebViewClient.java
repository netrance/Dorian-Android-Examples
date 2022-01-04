package me.blog.netrance.android.title_from_webview;

import android.support.v7.app.ActionBar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Custom web view client.
 * It was defined to read the title from a web view.
 * @author Domone
 */

public class CustomWebViewClient extends WebViewClient {

    private ActionBar actionBar;

    public CustomWebViewClient(ActionBar actionBar) {
        this.actionBar = actionBar;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        this.actionBar.setTitle(view.getTitle());
    }
}
