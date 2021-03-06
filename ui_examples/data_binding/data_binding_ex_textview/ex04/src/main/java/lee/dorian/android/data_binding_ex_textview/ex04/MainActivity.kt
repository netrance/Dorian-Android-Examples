package lee.dorian.android.data_binding_ex_textview.ex04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import lee.dorian.android.data_binding_ex_textview.ex04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.member = Member(1, "dorian", "Dorian Lee", 10000)
        binding.idTextColor = resources.getColor(R.color.user_id_color, null)
    }

}