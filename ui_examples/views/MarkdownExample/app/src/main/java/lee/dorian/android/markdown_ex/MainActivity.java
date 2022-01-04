package lee.dorian.android.markdown_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import us.feras.mdv.MarkdownView;

public class MainActivity extends AppCompatActivity {

    private EditText etContent;
    private MarkdownView mvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContent = (EditText)findViewById(R.id.etContent);
        mvContent = (MarkdownView)findViewById(R.id.mvContent);
        etContent.addTextChangedListener(etContentTextWatcher);
    }

    private TextWatcher etContentTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            mvContent.loadMarkdown(etContent.getText().toString());
        }

    };

}