package cf.domone.android.gesture_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * This activity shows an example of gesturing on a web view.
 */
public class MainActivity extends AppCompatActivity {

    /** The target view to apply gestures */
    private WebView wvExample;

    /** The object to catch gestures on the web view */
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the web view on this activity.
        this.wvExample = (WebView)findViewById(R.id.wvExample);
        this.wvExample.getSettings().setJavaScriptEnabled(true);
        this.wvExample.setWebViewClient(new WebViewClient());
        this.wvExample.loadUrl("http://www.google.com");

        // Applies the gesture listener to the web view.
        this.gestureDetector = new GestureDetector(this, new WebViewOnGestureListener(this.wvExample));
        this.wvExample.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // makes the gesture listener response when a user touches the web view.
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

}