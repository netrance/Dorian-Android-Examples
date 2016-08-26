package cf.domone.android.gesture_ex01;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * The gesture listener to handle the gestures on a web view.
 */
public class WebViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

    /** Target web view to apply gestures */
    protected WebView webView;

    /** Threshold to determine if a gesture can be accepted. */
    protected float swipeThreshold;

    public WebViewOnGestureListener(WebView webView) {
        this.webView = webView;
        this.swipeThreshold = 100.0f;
    }

    public WebViewOnGestureListener(WebView webView, float swipeThreshold) {
        this.webView = webView;
        this.swipeThreshold = swipeThreshold;
    }

    /**
     * Handles user swipe gestures: (1) left to right (2) right to left.
     * In case of (1), this method makes the web view go back in its history.
     * In case of (2), it makes the view go forward in its history.
     * @param e1 The same as the e1 of SimpleOnGestureListener
     * @param e2 The same as the e2 of SimpleOnGestureListener
     * @param velocityX The same as the velocityX of SimpleOnGestureListener
     * @param velocityY The same as the velocityY of SimpleOnGestureListener
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (isGestureFromRightToLeft(e1, e2) && (this.webView.canGoBack())) {
            this.webView.goBack();
            return true;
        }
        else if (isGestureFromLeftToRight(e1, e2) && (this.webView.canGoForward())) {
            this.webView.goForward();
            return true;
        }

        return false;
    }

    private boolean isGestureFromLeftToRight(MotionEvent e1, MotionEvent e2) {
        float startX = e1.getX();
        float endX = e2.getX();

        if ((endX > startX) && ((endX - startX) > this.swipeThreshold)) {
            return true;
        }

        return false;
    }

    private boolean isGestureFromRightToLeft(MotionEvent e1, MotionEvent e2) {
        float startX = e1.getX();
        float endX = e2.getX();

        if ((endX < startX) && ((startX - endX) > this.swipeThreshold)) {
            return true;
        }

        return false;
    }

}