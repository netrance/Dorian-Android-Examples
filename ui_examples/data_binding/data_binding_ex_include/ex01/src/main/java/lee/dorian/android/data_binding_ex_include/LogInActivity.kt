package lee.dorian.android.data_binding_ex_include

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import lee.dorian.android.data_binding_ex_include.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)

        binding.title = getString(R.string.common_login)
        binding.isBackButtonVisible = true
        binding.includeToolbar.ivBackButton.setOnClickListener(BackButtonClickListener(this))
        binding.btnLogIn.setOnClickListener(btnLogInClickListener)
    }

    private val btnLogInClickListener = { _: View ->
        Toast.makeText(this, "Under construction", Toast.LENGTH_SHORT).show()
    }
}