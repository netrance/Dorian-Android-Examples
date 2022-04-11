package lee.dorian.android.spen_air_actions_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import lee.dorian.android.spen_air_actions_ex01.extension.copyToClipboard
import lee.dorian.android.spen_air_actions_ex01.extension.goBack
import lee.dorian.android.spen_air_actions_ex01.extension.goForward
import lee.dorian.android.spen_air_actions_ex01.extension.hasScheme

class MainActivity : AppCompatActivity() {

    lateinit var views: Views

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        views = Views()
    }

    inner class Views(
        val etURL: EditText = findViewById(R.id.etURL),
        val wvContent: WebView = findViewById(R.id.wvContent)
    ) {
        init {
            etURL.setOnKeyListener(etURLKeyDownListener)
            wvContent. webViewClient = customWebViewClient
        }
    }

    val customWebViewClient = object: WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            views.etURL.apply {
                setText(url)
                clearFocus()
            }
        }
    }

    val etURLKeyDownListener = View.OnKeyListener { view: View, keyCode: Int, keyEvent: KeyEvent ->
        var url = views.etURL.text.toString()
        if (!url.hasScheme("http") and !url.hasScheme("https")) {
            url = "https://$url"
        }

        when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                if (url.isNotEmpty() and (KeyEvent.ACTION_DOWN == keyEvent.action)) {
                    views.wvContent.loadUrl(url)
                    true
                }
            }
        }

        false
    }

    // Handles air actions.
    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        val wvContent = views.wvContent
        val isCtrlPressed = event?.isCtrlPressed ?: false
        val isShiftPressed = event?.isShiftPressed ?: false
        val isKeyDown = (KeyEvent.ACTION_DOWN == event?.action)

        if (!isKeyDown) {
            return false
        }

        when (event?.keyCode) {
            KeyEvent.KEYCODE_C -> {
                if (isCtrlPressed and isShiftPressed) {
                    copyToClipboard(views.etURL.text.toString())
                    Toast.makeText(this, "Copied URL.", Toast.LENGTH_SHORT).show()
                    return true
                }
            }

            KeyEvent.KEYCODE_BACK -> {
                if (isCtrlPressed and isShiftPressed) {
                    wvContent.goForward {}
                }
                else if ((isCtrlPressed) or (!isCtrlPressed and !isShiftPressed)) {
                    wvContent.goBack { finish() }
                }
                else {
                    return false
                }

                return true
            }

            KeyEvent.KEYCODE_PAGE_UP -> {
                if (isCtrlPressed and isShiftPressed) {
                    wvContent.pageUp(false)
                    return true
                }
            }

            KeyEvent.KEYCODE_PAGE_DOWN -> {
                if (isCtrlPressed and isShiftPressed) {
                    wvContent.pageDown(false)
                    return true
                }
            }

            KeyEvent.KEYCODE_F5 -> {
                if (isCtrlPressed and isShiftPressed) {
                    wvContent.reload()
                    return true
                }
            }
        }

        return super.dispatchKeyEvent(event)
    }

}