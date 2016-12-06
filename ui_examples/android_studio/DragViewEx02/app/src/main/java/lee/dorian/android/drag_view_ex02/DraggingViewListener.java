package lee.dorian.android.drag_view_ex02;

import android.view.MotionEvent;
import android.view.View;

/**
 * Touch listener to implement dragging a view,
 * which does not move out of its parent view.
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

        View parentView = (View)v.getParent();
        int parentWidth = parentView.getWidth();
        int parentHeight = parentView.getHeight();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                deltaX = x - (int)v.getX();
                deltaY = y - (int)v.getY();
                return true;

            // View v is dragged while during a press gesture.
            // It does not go out of its parent view.
            case MotionEvent.ACTION_MOVE:
                // Prevents the view dragged out of the left side of its parent.
                if ((x - deltaX) < 0) {
                    v.setX(0);
                }
                // Prevents the view dragged out of the right side of its parent.
                else if ((x - deltaX + v.getWidth()) > parentWidth) {
                    v.setX(parentWidth - v.getWidth());
                }
                // General dragging
                else {
                    v.setX(x - deltaX);
                }

                // Prevents the view dragged out of the top side of its parent.
                if ((y - deltaY) < 0) {
                    v.setY(0);
                }
                // Prevents the view dragged out of the bottom side of its parent.
                else if ((y - deltaY + v.getHeight()) > parentHeight) {
                    v.setY(parentHeight - v.getHeight());
                }
                // General dragging
                else {
                    v.setY(y - deltaY);
                }

                return true;
        }

        // No handles the other touch events.
        return false;
    }
}
