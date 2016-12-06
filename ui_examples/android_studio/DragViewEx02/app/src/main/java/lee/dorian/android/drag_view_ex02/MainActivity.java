package lee.dorian.android.drag_view_ex02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This example shows the example of dragging a view,
 * which does not move out of its parent view.
 */
public class MainActivity extends AppCompatActivity {

    private View viewExample;

    private View viewExample2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DraggingViewListener draggingListener = new DraggingViewListener();

        viewExample = findViewById(R.id.view_example);
        viewExample.setOnTouchListener(draggingListener);
        viewExample2 = findViewById(R.id.view_example2);
        viewExample2.setOnTouchListener(draggingListener);
    }
}
