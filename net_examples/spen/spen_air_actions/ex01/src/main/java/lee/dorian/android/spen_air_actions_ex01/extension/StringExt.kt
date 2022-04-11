package lee.dorian.android.spen_air_actions_ex01.extension

fun String.hasScheme(scheme: String): Boolean {
    return startsWith("${scheme}://")
}
