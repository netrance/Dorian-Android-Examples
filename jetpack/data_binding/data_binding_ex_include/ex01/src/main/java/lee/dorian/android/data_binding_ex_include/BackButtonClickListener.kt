package lee.dorian.android.data_binding_ex_include

import android.app.Activity
import android.view.View

class BackButtonClickListener(val activity: Activity) : View.OnClickListener {

    override fun onClick(v: View?) {
        activity.finish()
    }

}