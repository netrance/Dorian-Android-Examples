package lee.dorian.android.light_mode_only_ex01

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}