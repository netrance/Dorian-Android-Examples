package lee.dorian.android.linear_gradient_textview_ex01

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val views by lazy {
        Views()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        views.tvExample.setTextColorAsLinearGradient(arrayOf(
            Color.parseColor("#FF0000"),
            Color.parseColor("#0000FF")
        ))
    }

    inner class Views(
        val tvExample: TextView = findViewById(R.id.tvExample)
    )
}