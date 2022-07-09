package lee.dorian.android.restart_app_ex01

import android.app.Activity

fun Activity.restartApp() {
    finishAffinity()
    startActivity(packageManager.getLaunchIntentForPackage(packageName))
}