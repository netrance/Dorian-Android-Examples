package cf.domone.android.objectanimator_ex02;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

/**
 * This activity shows the example of interpolations applied to view animations.
 */
public class MainActivity extends AppCompatActivity {

    private Button btnRed;
    private Button btnOrange;
    private Button btnGreen;
    private Button btnBlue;
    private Button btnPurple;
    private Button btnBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnRed = (Button)findViewById(R.id.btnRed);
        this.btnOrange = (Button)findViewById(R.id.btnOrange);
        this.btnGreen = (Button)findViewById(R.id.btnGreen);
        this.btnBlue = (Button)findViewById(R.id.btnBlue);
        this.btnPurple = (Button)findViewById(R.id.btnPurple);
        this.btnBlack = (Button)findViewById(R.id.btnBlack);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!hasFocus) {
            return;
        }

        float rootLayoutWidth = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getWidth();

        // Move views from left side to right side.
        moveViewHorizontally(
                this.btnRed,
                rootLayoutWidth - this.btnRed.getWidth(),
                new LinearInterpolator(),
                1500
        );
        moveViewHorizontally(
                this.btnOrange,
                rootLayoutWidth - this.btnOrange.getWidth(),
                new AccelerateInterpolator(),
                1500
        );
        moveViewHorizontally(
                this.btnGreen,
                rootLayoutWidth - this.btnGreen.getWidth(),
                new AccelerateDecelerateInterpolator(),
                1500
        );
        moveViewHorizontally(
                this.btnBlue,
                rootLayoutWidth - this.btnBlue.getWidth(),
                new DecelerateInterpolator(),
                1500
        );
        moveViewHorizontally(
                this.btnPurple,
                rootLayoutWidth - this.btnPurple.getWidth(),
                new BounceInterpolator(),
                1500
        );
        moveViewHorizontally(
                this.btnBlack,
                rootLayoutWidth - this.btnBlack.getWidth(),
                new OvershootInterpolator(),
                1500
        );
    }

    /**
     * Animates a view from its original location to the assigned destination.
     * @param view The target view to be animated
     * @param translationX To animate the horizontal position of the view from (x) to (x + translationX), in pixels
     * @param interpolatorX The interpolator needed to animate the view to the horizontal destination
     * @param duration The time length of the animation. The time unit is millisecond.
     */
    private void moveViewHorizontally(
            View view,
            float translationX,
            Interpolator interpolatorX,
            int duration
    ) {
        ObjectAnimator viewTranslateXAnimator = ObjectAnimator.ofFloat(view, "translationX", translationX);
        viewTranslateXAnimator.setInterpolator(interpolatorX);

        AnimatorSet viewAnimatorSet = new AnimatorSet();
        viewAnimatorSet.playTogether(viewTranslateXAnimator);
        viewAnimatorSet.setDuration(duration);
        viewAnimatorSet.start();
    }
}
