package lee.dorian.android.room_common

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface.OnClickListener

fun Activity.openOKAlertDialog(title: String, message: String, handleOKButtonClick: () -> Unit) {
    AlertDialog.Builder(this).run {
        setTitle(title)
        setMessage(message)
        setPositiveButton("OK", OnClickListener { dialogInterface, i ->
            handleOKButtonClick()
        })
        show()
    }
}
