package cf.domone.android.okhttp_ssl_ex01;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * This activity shows an example to connect a server via HTTPS protocol.
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvServerResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvServerResponse = (TextView)findViewById(R.id.tvServerResponse);

        OkHttp2Util.request(
                "https",
                "docs.google.com",
                -1,
                "",
                null,
                null,
                serverResponseHandler
        );
    }

    /**
     * Callback object to handle the message received from the server.
     */
    private Callback serverResponseHandler = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            final AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
            adb.setTitle("Error")
                    .setMessage("Cannot connect to the server.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adb.show();
                }
            });

        }

        /**
         * This method shows the received message using an alert dialog.
         * @param response The received messaged is in this parameter
         * @throws IOException
         */
        @Override
        public void onResponse(Response response) throws IOException {
            final String strJsonOutput = response.body().string();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvServerResponse.setText(strJsonOutput);
                }
            });
        }
    };
}
