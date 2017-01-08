package lee.dorian.camera_preview_ex01;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * The view to show the preview of a camera
 */
public class CameraPreview extends SurfaceView {

    public CameraPreview(Context context) {
        super(context);
        init();
    }

    public CameraPreview(Context context, AttributeSet as) {
        super(context, as);
        init();
    }

    public CameraPreview(Context context, AttributeSet as, int defStyle) {
        super(context, as, defStyle);
        init();
    }

    private void init() {
    }

}
