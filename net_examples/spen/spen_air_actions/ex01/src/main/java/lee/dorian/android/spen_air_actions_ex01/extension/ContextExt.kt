package lee.dorian.android.spen_air_actions_ex01.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

fun Context.copyToClipboard(text: String) {
    val clipboardManager = getSystemService(AppCompatActivity.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("label", text)
    clipboardManager.setPrimaryClip(clipData)
}