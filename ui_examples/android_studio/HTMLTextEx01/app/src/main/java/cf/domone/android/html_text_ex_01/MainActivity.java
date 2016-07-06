package cf.domone.android.html_text_ex_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define a text with HTML tags.
        String htmlText = "This is <font color=#0000FF>blue</font> and <i>italic</i>.";

        TextView tvHtmlTextExample = (TextView)findViewById(R.id.tvHtmlTextExample);
        tvHtmlTextExample.setText(Html.fromHtml(htmlText));
    }
}
