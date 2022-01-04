package lee.dorian.android.activity_result_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class JoinFormActivity : AppCompatActivity() {

    private lateinit var etNickname: EditText
    private lateinit var btnJoin: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_form)

        etNickname = findViewById(R.id.etNickname)
        btnJoin = findViewById(R.id.btnJoin)

        btnJoin.setOnClickListener(btnJoinClickListener)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
        finish()
    }

    private val btnJoinClickListener = View.OnClickListener {
        if (etNickname.text.isEmpty()) {
            Toast.makeText(this@JoinFormActivity, "Nickname is empty.", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }

        val intent = Intent().apply {
            putExtra(IntentExtraKey.NICKNAME, etNickname.text.toString())
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    object IntentExtraKey {
        const val NICKNAME = "nickname"
    }

}