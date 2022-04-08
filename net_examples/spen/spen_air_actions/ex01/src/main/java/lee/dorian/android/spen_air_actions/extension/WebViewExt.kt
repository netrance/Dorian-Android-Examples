package lee.dorian.android.spen_air_actions.extension

import android.webkit.WebView

fun WebView.goBack(runWhenCantBack: () -> Unit) {
    if (canGoBack()) {
        goBack()
    }
    else {
        runWhenCantBack()
    }
}

fun WebView.goForward(runWhenCantForward: () -> Unit) {
    if (canGoForward()) {
        goForward()
    }
    else {
        runWhenCantForward()
    }
}