package lee.dorian.android.custom_switch_ex

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val swExample = findViewById(R.id.swExample) as Switch
        swExample.setOnCheckedChangeListener { switchView, isChecked ->
            if (isChecked) {
                // Write your code here if you want to handle checking this switch.
                Toast.makeText(this, "Switch is on.", Toast.LENGTH_SHORT).show()
            }
            else {
                // Write your code here if you want to handle unchecking this switch.
                Toast.makeText(this, "Switch is off.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}