package lee.dorian.android.livedata_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    val liveName = MutableLiveData<String>()

    lateinit var views: Views

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        views = Views().apply {
            btnPostName.setOnClickListener(btnPostNameClickListener)
        }
        liveName.observe(this, liveNameObserver)
    }

    private val btnPostNameClickListener = View.OnClickListener {
        //liveName.postValue(views.etName.text.toString())
        liveName.value = views.etName.text.toString()
    }

    private val liveNameObserver = Observer<String> { name ->
        Toast.makeText(this@MainActivity, "Hello, ${name}.", Toast.LENGTH_SHORT).show()
    }

    inner class Views(
        val etName: EditText = findViewById<EditText>(R.id.etName),
        val btnPostName: Button = findViewById<Button>(R.id.btnPostName)
    )

}