package lee.dorian.android.image_file_download_ex01

fun String.getFileExtension(): String {
    return substring(lastIndexOf('.') + 1)
}