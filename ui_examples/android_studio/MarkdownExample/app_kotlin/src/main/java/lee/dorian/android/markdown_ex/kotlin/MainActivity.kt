package lee.dorian.android.markdown_ex.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import us.feras.mdv.MarkdownView

class MainActivity : AppCompatActivity() {

    private lateinit var etContent: EditText
    private lateinit var mvContent: MarkdownView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etContent = findViewById(R.id.etContent) as EditText
        mvContent = findViewById(R.id.mvContent) as MarkdownView
        etContent.addTextChangedListener(etContentTextWatcher)
    }

    private val etContentTextWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            mvContent.loadMarkdown(etContent.text.toString())
        }

    }

}