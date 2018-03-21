package lee.dorian.datepicker_ex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewHolder viewHolder;
    private ListenerHolder listenerHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewHolder = new ViewHolder();
        listenerHolder = new ListenerHolder();
    }

    private class ViewHolder {
        public DatePicker dpExample;
        public Button btnOK;

        public ViewHolder() {
            dpExample = (DatePicker)findViewById(R.id.dpExample);
            btnOK = (Button)findViewById(R.id.btnOK);
            init();
        }

        public void init() {
            dpExample.updateDate(2018, 3 - 1, 14);
        }
    }

    private class ListenerHolder {
        public ListenerHolder() {
            viewHolder.btnOK.setOnClickListener(btnOKClickListener);
        }

        public View.OnClickListener btnOKClickListener = (clickedView) -> {
            int year = viewHolder.dpExample.getYear();
            int month = viewHolder.dpExample.getMonth();
            int day = viewHolder.dpExample.getDayOfMonth();

            Toast.makeText(
                MainActivity.this,
                String.format("%04d-%02d-%02d", year, (month + 1), day),
                Toast.LENGTH_SHORT
            ).show();
        };
    }

}
