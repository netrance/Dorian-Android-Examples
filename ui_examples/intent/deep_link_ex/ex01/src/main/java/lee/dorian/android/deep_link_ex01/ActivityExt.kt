package lee.dorian.android.deep_link_ex01

import android.app.Activity
import android.content.Intent

fun Activity.isStartedByDeepLink(): Boolean {
    return (null != intent.data) and Intent.ACTION_VIEW.equals(intent.action)
}