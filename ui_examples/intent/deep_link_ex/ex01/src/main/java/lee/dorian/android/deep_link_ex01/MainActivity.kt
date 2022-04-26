package lee.dorian.android.deep_link_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var views: Views

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        views = Views()

        if (!isStartedByDeepLink()) {
            views.tvMessage.text = createHelloMessage()
        }
        else {
            intent.data?.let {
                val name = it.getQueryParameter("name") ?: "everyone"
                views.tvMessage.text = createHelloMessage(name)
            }
        }
    }

    fun createHelloMessage(name: String): String {
        return "Hello, ${name}."
    }

    fun createHelloMessage(): String {
        return createHelloMessage("world")
    }

    inner class Views (
        val tvMessage: TextView = findViewById(R.id.tvMessage) as TextView
    )
}