package lee.dorian.android.image_file_download_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var views: Views

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        views = Views()
    }

    inner class Views(
        val etImageURL: EditText = findViewById(R.id.etImageURL),
        val btnDownloadImage: Button = findViewById(R.id.btnDownloadimage)
    ) {
        init {
            btnDownloadImage.setOnClickListener(btnDownloadImageClickListener)
        }
    }

    val btnDownloadImageClickListener = View.OnClickListener {
        views.etImageURL.clearFocus()
        val permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

        if (isPermissionGranted(permission)) {
            downloadImage(
                views.etImageURL.text.toString(),
                ::handlingImageDownloadResult
            )
        }
        else {
            requestPermissionLauncher.launch(permission)    // 필요 권한 요청
        }
    }

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            downloadImage(
                views.etImageURL.text.toString(),
                ::handlingImageDownloadResult
            )
        }
        else {
            // 권한 부재로 이미지 다운로드 불가 알림
            Toast.makeText(this, "Can't download image due to permission denied.", Toast.LENGTH_SHORT).show()
        }
    }

    fun handlingImageDownloadResult(isSuccessful: Boolean) {
        if (isSuccessful) {
            Toast.makeText(this, "Downloaded image.", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "Can't download image.", Toast.LENGTH_SHORT).show()
        }
    }
}