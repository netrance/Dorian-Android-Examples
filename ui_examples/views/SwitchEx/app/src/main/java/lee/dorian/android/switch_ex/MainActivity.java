package lee.dorian.android.switch_ex;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewHolder viewHolder;
    private ListenerHolder listenerHolder;

    private boolean isInitializing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewHolder = new ViewHolder();
        listenerHolder = new ListenerHolder();
    }

    /**
     * Has views of this activity.
     */
    private class ViewHolder {
        public EditText etId;
        public Switch swSaveId;
        public Button btnOK;

        public ViewHolder() {
            etId = (EditText)findViewById(R.id.etId);
            swSaveId = (Switch)findViewById(R.id.swSaveId);
            btnOK = (Button)findViewById(R.id.btnOK);
            init();
        }

        public void init() {
            etId.post(() -> {
                String id = PrefUtil.loadId(MainActivity.this);
                if (!id.isEmpty()) {
                    etId.setText(id);
                    swSaveId.setChecked(true);
                }

                isInitializing = false;
            });
        }
    }

    /**
     * Has listeners of this activity.
     */
    private class ListenerHolder {
        public ListenerHolder() {
            viewHolder.swSaveId.setOnCheckedChangeListener(swSaveIdCheckChangeListener);
            viewHolder.btnOK.setOnClickListener(btnOKClickListener);
        }

        /**
         * Listener to handle toggling 'Save ID' Switch view.
         */
        private CompoundButton.OnCheckedChangeListener swSaveIdCheckChangeListener = (targetView, isChecked) -> {
            if (isInitializing) {
                return;
            }
            else if (isChecked) {
                new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Warning")
                    .setMessage("Save your ID?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        Toast.makeText(MainActivity.this, "Click OK button to save your ID into this form.", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        targetView.setChecked(false);
                    })
                    .show();
            }
            else {
                Toast.makeText(MainActivity.this, "Click OK button to delete your ID on this form.", Toast.LENGTH_SHORT).show();
            }
        };

        /**
         * Listener to handle clicking OK button.
         */
        private View.OnClickListener btnOKClickListener = (clickedView) -> {
            if (viewHolder.swSaveId.isChecked()) {
                String id =  viewHolder.etId.getText().toString();
                PrefUtil.saveId(MainActivity.this, id);
                Toast.makeText(MainActivity.this, "Your ID " + id + " has been saved into this form.", Toast.LENGTH_SHORT).show();
            }
            else {
                PrefUtil.deleteId(MainActivity.this);
                Toast.makeText(MainActivity.this, "Your ID has been deleted from this form.", Toast.LENGTH_SHORT).show();
            }
        };

    }
}
