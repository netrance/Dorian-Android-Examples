package lee.dorian.drawable_transformation_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This activity shows the example of transforming a drawable on a view using gestures.
 */
public class MainActivity extends AppCompatActivity {

    private TransformableDrawableView ivExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivExample = (TransformableDrawableView)findViewById(R.id.ivExample);
        ivExample.setDebugFlag(true);
        ivExample.moveDrawable(100, 200);
    }

}
