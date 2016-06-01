package cf.domone.android.okhttp_get_example_01;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    /** View to input a name */
    private EditText etName;

    /** View to request a message from a server */
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMessage(etName.getText().toString());
            }
        });
    }

    /**
     * This method request a message from a server
     * @param name The name to be sent to the server
     */
    private void requestMessage(String name) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("netrance.cafe24.com")
                .addPathSegment("AspServerExample/GetExample/GetExample01.aspx")
                .addQueryParameter("name", name)
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        client.newCall(request).enqueue(callbackAfterGettingMessage);
    }

    /**
     * Callback object to handle the message received from the server.
     */
    private Callback callbackAfterGettingMessage = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            final AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
            adb.setTitle("Error")
                    .setMessage("Cannot connect to the server.")
                    .setPositiveButton("OK", onOkClickListener);
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
            final JSONObject jsonOutput = JsonUtil.getJSONObjectFrom(strJsonOutput);
            final AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);

            adb.setTitle("Result")
                    .setMessage(JsonUtil.getStringFrom(jsonOutput, "message"))
                    .setPositiveButton("OK", onOkClickListener);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adb.show();
                }
            });

        }
    };

    private DialogInterface.OnClickListener onOkClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };
}
