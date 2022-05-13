package lee.dorian.android.image_file_download_ex01

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URLConnection

enum class BitmapSaveResult {
    SUCCESS,
    INVALID_CONTENT_PRIVIDER,
    COMPRESSION_FAILED,
    UNKNOWN_ERROR
}

fun Bitmap.saveAsFile(fileName: String, contentResolver: ContentResolver): BitmapSaveResult {
    lateinit var imageOutStream: OutputStream
    val fileExtension = fileName.getFileExtension()
    val mimeType = URLConnection.guessContentTypeFromName(fileName) ?: "image/png"
    val targetDirectory = Environment.DIRECTORY_PICTURES

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val imagePath = Environment.getExternalStoragePublicDirectory(targetDirectory)
        val imageFile = File(imagePath, fileName)
        imageOutStream = FileOutputStream(imageFile)
    }
    else {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, mimeType)
            put(MediaStore.Images.Media.RELATIVE_PATH, targetDirectory)
        }

        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) ?:
            return BitmapSaveResult.INVALID_CONTENT_PRIVIDER
        imageOutStream = contentResolver.openOutputStream(uri) ?:
            return BitmapSaveResult.INVALID_CONTENT_PRIVIDER
    }

    imageOutStream.use { outputStream ->
        val bitmapCompressFormat = when (fileExtension) {
            "jpg", "JPG" -> Bitmap.CompressFormat.JPEG
            "png", "PNG" -> Bitmap.CompressFormat.PNG
            else -> Bitmap.CompressFormat.PNG
        }

        return if (compress(bitmapCompressFormat, 100, outputStream)) {
            BitmapSaveResult.SUCCESS
        }
        else {
            BitmapSaveResult.COMPRESSION_FAILED
        }
    }

    return BitmapSaveResult.UNKNOWN_ERROR
}