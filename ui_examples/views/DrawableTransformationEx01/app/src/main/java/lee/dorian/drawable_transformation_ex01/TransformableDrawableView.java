package lee.dorian.drawable_transformation_ex01;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import com.almeros.android.multitouch.MoveGestureDetector;
import com.almeros.android.multitouch.RotateGestureDetector;

/**
 * The image view that can transform its drawable.
 * It is possible to translate, scale and rotate the drawable in the view.
 */
public class TransformableDrawableView extends ImageView {

    /** The original width of the drawable */
    private int mDrawableWidth;

    /** The original height of the drawable */
    private int mDrawableHeight;

    /** The matrix to transform the drawable */
    private Matrix mMatrix = new Matrix();

    /** The scale factor for the width and height of the drawable */
    private float mScaleFactor = 1.f;

    /** How much degree to rotate the drawable */
    private float mRotationDegrees = 0.f;

    /** How long the drawable translates horizontally */
    private float mFocusX = 0.f;

    /** How long the drawable translates vertically */
    private float mFocusY = 0.f;

    /** To detect the gestures to scale the drawable */
    private ScaleGestureDetector mScaleDetector;

    /** To detect the gesture to rotate the drawable */
    private RotateGestureDetector mRotateDetector;

    /** To detect the gesture to translate the drawable */
    private MoveGestureDetector mMoveDetector;

    /** Indicates the boundary of the drawable before transforming */
    private BoundaryRect mDrawableBoundaryRect;

    /** Indicates the boundary of the transformed drawable */
    private BoundaryRect mTransformedBoundaryRect;

    /** Indicates the area of the drawable possible to detect gestures */
    private RectF mDrawableTouchableArea;

    /** Paint object to draw the rectangles */
    private Paint mBoundaryRectPaint;

    /** Flag that means if the drawble is being transformed. */
    private boolean mIsTransforming = false;

    /** Set this field to true if you want to debug. */
    private boolean mDebugFlag = false;

    public TransformableDrawableView(Context context) {
        super(context);
        init();
    }

    public TransformableDrawableView(Context context, AttributeSet as) {
        super(context, as);
        init();
    }

    public TransformableDrawableView(Context context, AttributeSet as, int defStyle) {
        super(context, as, defStyle);
        init();
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);

        Drawable d = getDrawable();
        mDrawableWidth = d.getIntrinsicWidth();
        mDrawableHeight = d.getIntrinsicHeight();

        // Initialize Gesture Detectors
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener(0.5f, 3.0f));
        mRotateDetector = new RotateGestureDetector(getContext(), new RotateListener());
        mMoveDetector = new MoveGestureDetector(getContext(), new MoveListener());

        // Initialize paint object(s).
        mBoundaryRectPaint = new Paint();
        mBoundaryRectPaint.setColor(Color.GREEN);
        mBoundaryRectPaint.setStyle(Paint.Style.STROKE);
        mBoundaryRectPaint.setStrokeWidth(5.0f);

        // Initialize matrix object.
        mMatrix.reset();
        mMatrix.postScale(mScaleFactor, mScaleFactor);
        setImageMatrix(mMatrix);

        // Initialize rectangles.
        mDrawableBoundaryRect = new BoundaryRect(
                new Point(0, 0),
                new Point(0, mDrawableHeight),
                new Point(mDrawableWidth, mDrawableHeight),
                new Point(mDrawableWidth, 0)
        );
        mTransformedBoundaryRect = new BoundaryRect();
        mDrawableBoundaryRect.transform(mTransformedBoundaryRect, mMatrix);
        mDrawableTouchableArea = new RectF(0.0f, 0.0f, (float)(mDrawableWidth - 1), (float)(mDrawableHeight - 1));
        mMatrix.mapRect(mDrawableTouchableArea);
    }

    public void setDebugFlag(boolean debugFlag) {
        mDebugFlag = debugFlag;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mDebugFlag) {
            mTransformedBoundaryRect.draw(canvas, mBoundaryRectPaint);
            canvas.drawRect(mDrawableTouchableArea, mBoundaryRectPaint);
        }
    }

    /**
     * The event handler to transform the drawable using the matrix by touching
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Prevents transforming the drawable when the outside of it is touched.
                if (mDrawableTouchableArea.contains(event.getX(), event.getY())) {
                    mIsTransforming = true;
                    handleDrawableTransformation(event);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (mIsTransforming) {
                    mIsTransforming = false;
                    handleDrawableTransformation(event);
                }
                return true;

            case MotionEvent.ACTION_MOVE:
            default:
                if (mIsTransforming) {
                    handleDrawableTransformation(event);
                }
                return true;
        }
    }

    private void handleDrawableTransformation(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        mRotateDetector.onTouchEvent(event);
        mMoveDetector.onTouchEvent(event);

        // Set matrix.
        int pivotX = getDrawable().getIntrinsicWidth() >> 1;
        int pivotY = getDrawable().getIntrinsicHeight() >> 1;
        mMatrix.reset();
        mMatrix.postScale(mScaleFactor, mScaleFactor, pivotX, pivotY);
        mMatrix.postRotate(mRotationDegrees, pivotX, pivotY);
        mMatrix.postTranslate(mFocusX - 0, mFocusY - 0);
        setImageMatrix(mMatrix);

        // Shows the boundary rect of the transformed drawable.
        mDrawableBoundaryRect.transform(mTransformedBoundaryRect, mMatrix);

        // Update the touchable area of the drawable.
        mDrawableTouchableArea.set(0.0f, 0.0f, (float)(mDrawableWidth - 1), (float)(mDrawableHeight - 1));
        mMatrix.mapRect(mDrawableTouchableArea);

        invalidate();
    }

    /**
     * Traslates the drawable from the origin of this view to (x, y).
     * @param x
     * @param y
     */
    public void moveDrawable(int x, int y) {
        mFocusX = x;
        mFocusY = y;

        mMatrix.postTranslate(mFocusX, mFocusY);
        setImageMatrix(mMatrix);

        mDrawableBoundaryRect.transform(mTransformedBoundaryRect, mMatrix);

        mDrawableTouchableArea.set(0.0f, 0.0f, (float)(mDrawableWidth - 1), (float)(mDrawableHeight - 1));
        mMatrix.mapRect(mDrawableTouchableArea);
    }

    /** Aligns the drawable to the center of this view. */
    public void centerDrawable() {
        post(new Runnable() {
            @Override
            public void run() {
                View parent = (View)getParent();
                int xToCenter = (int)(parent.getMeasuredWidth() - mDrawableWidth * mScaleFactor) >> 1;
                int yToCenter = (int)(parent.getMeasuredHeight() - mDrawableHeight * mScaleFactor) >> 1;
                mFocusX = xToCenter;
                mFocusY = yToCenter;

                mMatrix.postTranslate(mFocusX, mFocusY);
                setImageMatrix(mMatrix);

                mDrawableBoundaryRect.transform(mTransformedBoundaryRect, mMatrix);

                mDrawableTouchableArea.set(0.0f, 0.0f, (float)(mDrawableWidth - 1), (float)(mDrawableHeight - 1));
                mMatrix.mapRect(mDrawableTouchableArea);
            }
        });
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        private float mMinScaleFactor;

        private float mMaxScaleFactor;

        public ScaleListener() {
            mMinScaleFactor = 0.5f;
            mMaxScaleFactor = 2.0f;
        }

        public ScaleListener(float minScaleFactor, float maxScaleFactor) {
            this.mMinScaleFactor = minScaleFactor;
            this.mMaxScaleFactor = maxScaleFactor;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor(); // scale change since previous event

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(mMinScaleFactor, Math.min(mScaleFactor, mMaxScaleFactor));

            return true;
        }
    }

    private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener {
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            mRotationDegrees -= detector.getRotationDegreesDelta();
            Log.d("dorian", "new rotation degree - " + mRotationDegrees);
            return true;
        }
    }

    private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener {
        @Override
        public boolean onMove(MoveGestureDetector detector) {
            PointF d = detector.getFocusDelta();
            mFocusX += d.x;
            mFocusY += d.y;

            return true;
        }
    }

}
