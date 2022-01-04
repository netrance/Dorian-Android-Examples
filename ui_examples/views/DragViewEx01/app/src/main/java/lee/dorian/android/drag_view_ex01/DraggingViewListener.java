package lee.dorian.android.drag_view_ex01;

import android.view.MotionEvent;
import android.view.View;

/**
 * Touch listener to implement dragging a view
 */
public class DraggingViewListener implements View.OnTouchListener {
    /** The distance between the left position of the touched view and the clicked point. */
    private int deltaX;

    /** The distance between the top position of the touched view and the clicked point. */
    private int deltaY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                deltaX = x - (int)v.getX();
                deltaY = y - (int)v.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                v.setX(x - deltaX);
                v.setY(y - deltaY);
                return true;
        }

        // No handles the other touch events.
        return false;
    }
}
