package lee.dorian.android.font_ex.ex01

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvHelloWorld = findViewById(R.id.tvHelloWorld) as TextView
        val typeface = Typeface.createFromAsset(assets, "font_nanum_gothic.ttf")
        tvHelloWorld.typeface = typeface
    }
}