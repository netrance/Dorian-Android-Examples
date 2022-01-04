package lee.dorian.android.radiobuttonex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewHolder vh;
    private ListenerHolder lh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vh = new ViewHolder();
        lh = new ListenerHolder();
    }

    private class ViewHolder {
        public RadioGroup rgYesNo;    // To select one of Yes and No radio buttons
        public RadioButton rbYes;
        public RadioButton rbNo;
        public Button btnNext;

        public ViewHolder() {
            rgYesNo = (RadioGroup)findViewById(R.id.rgYesNo);
            rbYes = (RadioButton)findViewById(R.id.rbYes);
            rbNo = (RadioButton)findViewById(R.id.rbNo);
            btnNext = (Button)findViewById(R.id.btnNext);
        }
    }

    private class ListenerHolder {
        public ListenerHolder() {
            vh.btnNext.setOnClickListener(btnNextClickListener);
        }

        public View.OnClickListener btnNextClickListener = (v) -> {
            // We can check which radio button is selected by calling its isChecked() method.
            if (vh.rbYes.isChecked()) {
                Toast.makeText(MainActivity.this, "Thank you.", Toast.LENGTH_SHORT).show();
            }
            else if (vh.rbNo.isChecked()) {
                Toast.makeText(MainActivity.this, "Can't go to next if No is selected.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Nothing is selected.", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
