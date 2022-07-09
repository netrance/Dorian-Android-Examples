package lee.dorian.android.restart_app

import android.app.Activity
import androidx.core.app.ActivityCompat.finishAffinity

fun Activity.restartApp() {
    finishAffinity()
    startActivity(packageManager.getLaunchIntentForPackage(packageName))
}