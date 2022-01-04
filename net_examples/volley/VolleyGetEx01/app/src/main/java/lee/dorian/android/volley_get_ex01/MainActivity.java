package lee.dorian.android.volley_get_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
        StringRequest sr = new StringRequest(
                Request.Method.GET,
                url,
                responseGetMessageListener,
                responseGetMessageErrorListener
        );
        rq.add(sr);
    }

    /**
     * Listener to handle the response of the server for requesting to get a greeting message
     */
    private Response.Listener<String> responseGetMessageListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            tvMessage.setText(response);
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
