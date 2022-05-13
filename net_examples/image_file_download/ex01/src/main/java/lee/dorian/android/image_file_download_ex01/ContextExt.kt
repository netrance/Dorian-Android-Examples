package lee.dorian.android.image_file_download_ex01

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun Context.isPermissionGranted(permission: String): Boolean {
    return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, permission)
}

fun Context.downloadImage(imageURL: String, resultCallback: (bitmapSaveResult: Boolean) -> Unit) {
    Glide.with(this).load(imageURL).into(object: CustomTarget<Drawable>() {

        override fun onResourceReady(drawable: Drawable, transition: Transition<in Drawable>?) {
            val fileName = imageURL.substring(imageURL.lastIndexOf('/') + 1)
            val bitmap = (drawable as BitmapDrawable).bitmap
            val bitmapSaveResult = bitmap.saveAsFile(fileName, contentResolver)

            if (BitmapSaveResult.SUCCESS == bitmapSaveResult) {
                resultCallback(true)
            }
            else {
                resultCallback(false)
            }
        }

        override fun onLoadCleared(placeholder: Drawable?) {
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            super.onLoadFailed(errorDrawable)
            resultCallback(false)
        }

    })
}
