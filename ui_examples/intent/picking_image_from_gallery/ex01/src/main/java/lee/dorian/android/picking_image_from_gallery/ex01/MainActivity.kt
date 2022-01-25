package lee.dorian.android.picking_image_from_gallery.ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var views: Views

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        views = Views().apply {
            tvSelectImage.setOnClickListener(onClickListenerToSelectImage)
            ivLoaded.setOnClickListener(onClickListenerToSelectImage)
        }
    }

    inner class Views(
        val tvSelectImage: TextView = findViewById(R.id.tvSelectImage) as TextView,
        val ivLoaded: ImageView = findViewById(R.id.ivLoaded) as ImageView
    )

    private val onClickListenerToSelectImage = View.OnClickListener {
        Intent(Intent.ACTION_PICK).apply {
            setType("image/*")
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
            galleryActivityResultLauncher.launch(this)
        }
    }

    private val galleryActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode != RESULT_OK) {
            return@registerForActivityResult
        }

        activityResult.data?.data.also { imageURI ->
            views.tvSelectImage.visibility = View.GONE
            views.ivLoaded.setImageURI(imageURI)
            views.ivLoaded.visibility = View.VISIBLE
        }
    }

}