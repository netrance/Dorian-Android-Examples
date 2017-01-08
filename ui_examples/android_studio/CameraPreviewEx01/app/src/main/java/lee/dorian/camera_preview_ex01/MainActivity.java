package lee.dorian.camera_preview_ex01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera mCamera;

    private CameraPreview vCamera;

    /** Request code to request camera permission */
    private static final int PERMISSIONS_REQUEST_CAMERA = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vCamera = (CameraPreview)findViewById(R.id.vCamera);

        if (!CameraUtils.hasCameraFeature(this)) {
            DialogUtils.openFinishDialog(this, "Warning", "No camera on this device.", "OK");
            return;
        }

        int permissionCheckResult = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        );

        if (permissionCheckResult != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.CAMERA },
                    PERMISSIONS_REQUEST_CAMERA);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        int permissionCheckResult = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        );

        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            runCamera();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            hideNavigationBar();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // To prevent battery consumption after this activity is inactive.
        if (null != mCamera) {
            mCamera.stopPreview();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_DENIED)) {
                    DialogUtils.openFinishDialog(
                            this,
                            "Warning",
                            "Cannot run camera because its permission is denied.",
                            "OK"
                    );
                    return;
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                break;
        }

    }

    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                //| View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        Log.d("dorian", "hide navi bar.");
    }

    private void runCamera() {
        if (null != mCamera) {
            mCamera.startPreview();
            return;
        }

        vCamera.getHolder().addCallback(this);
        vCamera.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        try {
            mCamera = Camera.open(0);
        }
        catch (RuntimeException re) {
            re.printStackTrace();
        }
    }

    private void releaseCamera() {
        if (null == mCamera) {
            return;
        }

        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        CameraUtils.doAutorotation(this, mCamera);

        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        }
        catch (IOException ie) {
            ie.printStackTrace();
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (null == holder.getSurface()) {
            return;
        }

        try {
            mCamera.stopPreview();
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }

}
