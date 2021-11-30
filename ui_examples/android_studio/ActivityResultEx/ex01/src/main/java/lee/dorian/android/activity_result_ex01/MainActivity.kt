package lee.dorian.android.activity_result_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnToJoinFormActivity: AppCompatButton
    private lateinit var tvWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToJoinFormActivity = findViewById(R.id.btnToJoinFormActivity)
        tvWelcome = findViewById(R.id.tvWelcome)

        btnToJoinFormActivity.visibility = View.VISIBLE
        tvWelcome.visibility = View.GONE

        btnToJoinFormActivity.setOnClickListener(btnToJoinFormActivityClickListener)
    }

    private val btnToJoinFormActivityClickListener = View.OnClickListener {
        val intent = Intent(this, JoinFormActivity::class.java)
        joinFormActivityResultLauncher.launch(intent)
    }

    private val joinFormActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode != RESULT_OK) {
            return@registerForActivityResult
        }

        btnToJoinFormActivity.visibility = View.GONE
        tvWelcome.apply {
            val name = it.data?.getStringExtra(JoinFormActivity.IntentExtraKey.NICKNAME) ?: "anonymous"
            text = "Welcome, ${name}."
            visibility = View.VISIBLE
        }
    }

}