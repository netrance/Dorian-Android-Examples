package lee.dorian.camera_preview_ex01;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Build;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * To test CameraUtils class
 */
@RunWith(AndroidJUnit4.class)
public class CameraUtilsTest {
    @Rule
    public ActivityTestRule<TestActivity> testActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private TestActivity testActivity;

    public CameraUtilsTest() {
    }

    @Before
    public void setUp() {
        testActivity = testActivityTestRule.getActivity();
    }

    @After
    public void tearDown() {
        testActivity.finish();
    }

    @Test
    public void hasCameraFeature() {
        assertEquals("Check if the device has a camera.", true, CameraUtils.hasCameraFeature(testActivity));
    }

    @Test
    public void doAutorotation() {
        Camera camera = Camera.open(0);
        Camera.Parameters parameters = camera.getParameters();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();

        camera.getCameraInfo(0, cameraInfo);

        CameraUtils.doAutorotation(testActivity, camera);
        if (testActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            assertEquals(0, cameraInfo.orientation);
        }
        else {
            assertEquals(90, cameraInfo.orientation);
        }

        camera.release();
    }
}
