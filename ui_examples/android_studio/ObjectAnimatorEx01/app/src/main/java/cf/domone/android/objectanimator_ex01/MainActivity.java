package cf.domone.android.objectanimator_ex01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * This activity shows the example of translating views.
 */
public class MainActivity extends AppCompatActivity {

    private Button btnRed;
    private Button btnOrange;
    private Button btnGreen;
    private Button btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRed = (Button)findViewById(R.id.btnRed);
        btnOrange = (Button)findViewById(R.id.btnOrange);
        btnGreen = (Button)findViewById(R.id.btnGreen);
        btnBlue = (Button)findViewById(R.id.btnBlue);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!hasFocus) {
            return;
        }

        translateView(
                btnRed,
                -btnRed.getWidth() >> 1,
                -btnRed.getHeight() >> 1,
                new LinearInterpolator(),
                new LinearInterpolator(),
                1500);
        translateView(
                btnOrange,
                btnOrange.getWidth() >> 1,
                -btnOrange.getHeight() >> 1,
                new LinearInterpolator(),
                new LinearInterpolator(),
                1500);
        translateView(
                btnGreen,
                -btnGreen.getWidth() >> 1,
                btnGreen.getHeight() >> 1,
                new LinearInterpolator(),
                new LinearInterpolator(),
                1500);
        translateView(
                btnBlue,
                btnBlue.getWidth() >> 1,
                btnBlue.getHeight() >> 1,
                new LinearInterpolator(),
                new LinearInterpolator(),
                1500);
    }

    /**
     * Animates a view from its original location to the assigned destination.
     * @param view The target view to be animated
     * @param translationX To animate the horizontal position of the view from (x) to (x + translationX), in pixels
     * @param translationY To animate the vertical position of the view from (y) to (y + translationY), in pixels
     * @param interpolatorX The interpolator needed to animate the view to the horizontal destination
     * @param interpolatorY The interpolator needed to animate the view to the vertical destination
     * @param duration The time length of the animation. The time unit is millisecond.
     */
    private void translateView(
            View view,
            float translationX,
            float translationY,
            Interpolator interpolatorX,
            Interpolator interpolatorY,
            int duration
    ) {
        ObjectAnimator viewTranslateXAnimator = ObjectAnimator.ofFloat(view, "translationX", translationX);
        viewTranslateXAnimator.setInterpolator(interpolatorX);
        ObjectAnimator viewTranslateYAnimator = ObjectAnimator.ofFloat(view, "translationY", translationY);
        viewTranslateYAnimator.setInterpolator(interpolatorY);

        AnimatorSet btnBlueAnimatorSet = new AnimatorSet();
        btnBlueAnimatorSet.playTogether(viewTranslateXAnimator, viewTranslateYAnimator);
        btnBlueAnimatorSet.setDuration(duration);
        btnBlueAnimatorSet.start();
    }
}
