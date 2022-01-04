package lee.dorian.drawable_transformation_ex01;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;

/**
 * Rectangle with four points to show the boundary of the drawable.
 */
public class BoundaryRect {
    private Point p2;
    private Point p1;
    private Point p3;
    private Point p4;

    public BoundaryRect() {
        this.p1 = new Point(0, 0);
        this.p2 = new Point(0, 0);
        this.p3 = new Point(0, 0);
        this.p4 = new Point(0, 0);
    }

    public BoundaryRect(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public void setP1(int x, int y) {
        p1.x = x;
        p1.y = y;
    }

    public void setP2(int x, int y) {
        p2.x = x;
        p2.y = y;
    }

    public void setP3(int x, int y) {
        p3.x = x;
        p3.y = y;
    }

    public void setP4(int x, int y) {
        p4.x = x;
        p4.y = y;
    }

    /**
     * Creates the transformed rectangle object with a matrix.
     * @param transformedBoundaryRect The target rectangle to be transformed
     * @param matrix The matrix to transform
     */
    public void transform(@NonNull BoundaryRect transformedBoundaryRect, @NonNull Matrix matrix) {
        float mappedP1[] = new float[2];
        float mappedP2[] = new float[2];
        float mappedP3[] = new float[2];
        float mappedP4[] = new float[2];

        mappedP1[0] = p1.x;
        mappedP1[1] = p1.y;
        matrix.mapPoints(mappedP1);
        mappedP2[0] = p2.x;
        mappedP2[1] = p2.y;
        matrix.mapPoints(mappedP2);
        mappedP3[0] = p3.x;
        mappedP3[1] = p3.y;
        matrix.mapPoints(mappedP3);
        mappedP4[0] = p4.x;
        mappedP4[1] = p4.y;
        matrix.mapPoints(mappedP4);

        transformedBoundaryRect.setP1((int) mappedP1[0], (int) mappedP1[1]);
        transformedBoundaryRect.setP2((int) mappedP2[0], (int) mappedP2[1]);
        transformedBoundaryRect.setP3((int) mappedP3[0], (int) mappedP3[1]);
        transformedBoundaryRect.setP4((int) mappedP4[0], (int) mappedP4[1]);
    }

    /**
     * Draws the rectangle.
     * @param canvas
     * @param paint
     */
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
        canvas.drawLine(p2.x, p2.y, p3.x, p3.y, paint);
        canvas.drawLine(p3.x, p3.y, p4.x, p4.y, paint);
        canvas.drawLine(p4.x, p4.y, p1.x, p1.y, paint);
    }
}
