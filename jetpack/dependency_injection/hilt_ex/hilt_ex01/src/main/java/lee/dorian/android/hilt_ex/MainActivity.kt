package lee.dorian.android.hilt_ex

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import dagger.hilt.android.AndroidEntryPoint
import lee.dorian.android.hilt_ex.SharedPreferencesKey.SP_KEY_NAME
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sp: SharedPreferences

    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById<EditText>(R.id.etName).apply {
            val name = sp.getString(SP_KEY_NAME, "")
            setText(name)
            addTextChangedListener(textWatcher)
        }
    }

    private val textWatcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            sp.edit().apply {
                putString(SP_KEY_NAME, s.toString())
                commit()
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

}