package lee.dorian.android.volley_get_ex02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This activity request a greeting message from a server,
 * and shows it on the screen.
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = (TextView)findViewById(R.id.tvMessage);

        // Requests to get a greeting message from a server.
        RequestQueue rq = Volley.newRequestQueue(this);
        String url = "http://netrance.cafe24.com/AspServerExample/GetExample/GetExample01.aspx?name=park";
        JsonObjectRequest sr = new JsonObjectRequest(
                url,
                null,
                responseGetMessageListener,
                responseGetMessageErrorListener
        );
        rq.add(sr);
    }

    /**
     * Listener to handle the response of the server for requesting to get a greeting message
     */
    private Response.Listener<JSONObject> responseGetMessageListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                tvMessage.setText(response.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
                tvMessage.setText("Cannot read message from server.");
            }
        }
    };

    /**
     * Listener to handle the error for requesting to get a greeting message
     */
    private Response.ErrorListener responseGetMessageErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            tvMessage.setText(error.getMessage());
        }
    };
}
