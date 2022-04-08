package lee.dorian.android.spen_air_actions.extension

fun String.hasScheme(scheme: String): Boolean {
    return startsWith("${scheme}://")
}
