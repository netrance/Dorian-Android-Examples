package cf.netrance.android.custom_dialog_ex;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define a layout to be the view of the dialog.
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewOfCustomDialog = inflater.inflate(R.layout.layout_custom_dialog, null);

        // Create and show the dialog.
        AlertDialog.Builder customDialogBuilder = new AlertDialog.Builder(this);
        customDialogBuilder.setView(viewOfCustomDialog);
        final AlertDialog customDialog = customDialogBuilder.show();

        // Set listener(s) of view(s) on the dialog.
        Button btnOK = (Button)viewOfCustomDialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }

}
