package cf.domone.android.okhttp_file_download_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    // Views of this activity
    private EditText etUrl;
    private EditText etDownloadDirectory;
    private Button   btnDownload;

    private File downloadDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get views.
        this.etUrl = (EditText)findViewById(R.id.etUrl);
        this.etDownloadDirectory = (EditText)findViewById(R.id.etDownloadDirectory);
        this.btnDownload = (Button)findViewById(R.id.btnDownload);

        // Get data.
        this.downloadDir = new File(getFilesDir().getAbsolutePath() + "/downloaded");

        // Set views.
        this.etUrl.setText("http://netrance.cafe24.com/test/common/images/trees_without_leaf.jpg");
        this.etDownloadDirectory.setText(getDefaultDownloadDirectory());

        // Set listeners.
        this.btnDownload.setOnClickListener(btnDownloadClickListener);
    }

    private String getDefaultDownloadDirectory() {
        return this.getExternalFilesDir(null) + "/download/";
    }

    private View.OnClickListener btnDownloadClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestFileDownload(etUrl.getText().toString());
        }
    };

    /**
     * Requests a server to download a file.
     * @param fileUrl File adderess of a server. It is supposed that HTTP protocol is used.
     */
    private void requestFileDownload(String fileUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(fileUrl)
                .build();
        CallbackToDownloadFile cbToDownloadFile = new CallbackToDownloadFile(
                this.etDownloadDirectory.getText().toString(),
                getFileNameFrom(fileUrl)
        );
        client.newCall(request).enqueue(cbToDownloadFile);
    }

    private String getFileNameFrom(String url) {
        int lastIndexOfSlash = url.lastIndexOf('/') + 1;
        return url.substring(lastIndexOfSlash, url.length());
    }

    /**
     * Callback class to handle downloading a file after the download request.
     * If there is already the same file, it is overwritten.
     */
    private class CallbackToDownloadFile implements Callback {

        private File directory;
        private File fileToBeDownloaded;

        public CallbackToDownloadFile(String directory, String fileName) {
            this.directory = new File(directory);
            this.fileToBeDownloaded = new File(this.directory.getAbsolutePath() + "/" + fileName);
        }

        @Override
        public void onFailure(Request request, IOException e) {
            openToastOnUiThread(
                    "Cannot download the file. Check if your device is connected to the internet."
            );
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (!this.directory.exists()) {
                this.directory.mkdirs();
            }

            if (this.fileToBeDownloaded.exists()) {
                this.fileToBeDownloaded.delete();
            }

            try {
                this.fileToBeDownloaded.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                openToastOnUiThread("Cannot create the download file. Check the write permission.");
                return;
            }

            InputStream is = response.body().byteStream();
            OutputStream os = new FileOutputStream(this.fileToBeDownloaded);

            final int BUFFER_SIZE = 2048;
            byte[] data = new byte[BUFFER_SIZE];

            int count;
            long total = 0;

            while ((count = is.read(data)) != -1) {
                total += count;
                os.write(data, 0, count);
            }

            os.flush();
            os.close();
            is.close();

            openToastOnUiThread("Download completed.");
        }
    }

    private void openToastOnUiThread(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(
                        MainActivity.this,
                        message,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
