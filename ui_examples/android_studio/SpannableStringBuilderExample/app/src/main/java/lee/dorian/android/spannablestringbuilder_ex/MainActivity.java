package lee.dorian.android.spannablestringbuilder_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvSpannableEx = (TextView)findViewById(R.id.tvSpannableEx);

        String account = "@dorian-lee";
        String post = "@dorian-dev/7s5eag";
        SpannableStringBuilder tvSpannableExContentBuilder = new SpannableStringBuilder();
        SpannableStringBuilderUtil.appendAndSetSpan(
                tvSpannableExContentBuilder,
                account,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
                new StyleSpan(Typeface.BOLD),
                //new ForegroundColorSpan(0xFFFF0000),
                new AccountClickableSpan(account)
        );
        tvSpannableExContentBuilder.append(" voted ");
        SpannableStringBuilderUtil.appendAndSetSpan(
                tvSpannableExContentBuilder,
                post,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
                new StyleSpan(Typeface.BOLD),
                //new ForegroundColorSpan(0xFFFF0000),
                new PostClickableSpan(post)
        );

        tvSpannableEx.setMovementMethod(LinkMovementMethod.getInstance());
        tvSpannableEx.setText(tvSpannableExContentBuilder, TextView.BufferType.SPANNABLE);
    }

    class AccountClickableSpan extends ClickableSpan {

        private String account;

        public AccountClickableSpan(String account) {
            this.account = account;
        }

        @Override
        public void onClick(@NonNull View widget) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(String.format("https://steemit.com/%s", account)));
            startActivity(intent);
        }

    }

    class PostClickableSpan extends ClickableSpan {

        private String post;

        public PostClickableSpan(String post) {
            this.post = post;
        }

        @Override
        public void onClick(@NonNull View widget) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(String.format("https://steemit.com/%s", post)));
            startActivity(intent);
        }

    }

}