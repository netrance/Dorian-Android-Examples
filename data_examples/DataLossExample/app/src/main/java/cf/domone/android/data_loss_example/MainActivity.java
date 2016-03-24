package cf.domone.android.data_loss_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This example shows data loss after this app is terminated by Android.
 */
public class MainActivity extends AppCompatActivity {

    /** test data to check if it is lost when this activity is recreated. */
    private int currentValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activate the below code to solve data loss.
        //if (null != savedInstanceState) {
        //    currentValue = savedInstanceState.getInt("current_value");
        //}

        refresh();
        findViewById(R.id.btnDecrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentValue--;
                refresh();
            }
        });

        findViewById(R.id.btnIncrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentValue++;
                refresh();
            }
        });
    }

    private void refresh() {
        String content = String.format("Current Value: %d", currentValue);
        TextView tvCurrentValue = (TextView)findViewById(R.id.tvCurrentValue);
        tvCurrentValue.setText(content);
    }

    // Activate this method to solve data loss.
    //@Override
    //public void onSaveInstanceState(Bundle outState) {
    //    outState.putInt("current_value", currentValue);
    //    super.onSaveInstanceState(outState);
    //}
}