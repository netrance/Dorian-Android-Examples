package cf.domone.android.okhttp_post_example_01;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText etDollar;
    private EditText etWon;
    private Button   btnChangeDollarIntoWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etDollar = (EditText)findViewById(R.id.etDollar);
        this.etWon    = (EditText)findViewById(R.id.etWon);
        this.btnChangeDollarIntoWon = (Button)findViewById(R.id.btnChangeDollarIntoWon);

        this.btnChangeDollarIntoWon.setOnClickListener(btnChangeDollarIntoWonClickListener);
    }

    private View.OnClickListener btnChangeDollarIntoWonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String strDollar = etDollar.getText().toString();

            if (strDollar.isEmpty()) {
                openSimpleAlertDialog("Error", "Please input a dollar value.");
                return;
            }

            long dollar = Long.parseLong(strDollar);
            requestWonFromDollar(dollar);
        }
    };

    private void requestWonFromDollar(long dollar) {
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonInput = new JSONObject();

        try {
            jsonInput.put("dollar", dollar);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("netrance.cafe24.com")
                .addPathSegment("AspServerExample/Ex06_Exchange/Ex06_ExchangeServer.aspx")
                .build();
        RequestBody reqBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                jsonInput.toString()
        );
        Request request = new Request.Builder()
                .url(httpUrl)
                .post(reqBody)
                .build();

        client.newCall(request).enqueue(callbackAfterGettingWon);
    }

    private Callback callbackAfterGettingWon = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            openSimpleAlertDialogOnUiThread(
                    "Error",
                    "Cannot connect to the server or there exists a server error."
            );
        }

        @Override
        public void onResponse(Response response) throws IOException {
            final String strJsonOutput = response.body().string();

            try {
                JSONObject jsonOutput = new JSONObject(strJsonOutput);
                final int won = jsonOutput.getInt("won");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etWon.setText("" + won);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
                openSimpleAlertDialogOnUiThread("Error", "Cannot get the won exchanged.");
            }
        }
    };

    private void openSimpleAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void openSimpleAlertDialogOnUiThread(final String title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                openSimpleAlertDialog(title, message);
            }
        });
    }
}
