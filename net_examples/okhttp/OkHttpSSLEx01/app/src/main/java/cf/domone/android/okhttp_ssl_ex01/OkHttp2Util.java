package cf.domone.android.okhttp_ssl_ex01;

import android.os.Build;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * This class holds util methods using OkHttp 2.x library.
 */
public class OkHttp2Util {

    /**
     * Requests data from the server.
     * @param scheme "http" or "https"
     * @param serverHost The host of a server (e.g. "netrance.cafe24.com")
     * @param portNum The port number needed to connect the server. Use -1 if you don't assign.
     * @param pathSegments The path of a file or a directory on the server (e.g. "HttpExamples/jQuery/img_opaque_ex.html")
     * @param fields The fields to be submitted into the server. Use null if no field is needed.
     * @param postedContent The JSON object to be posted to the server. Use null if no content is needed.
     * @param serverResponseHandler The callback object to handle the responds from the server
     */
    public static void request(
            String scheme,
            String serverHost,
            int portNum,
            String pathSegments,
            Map<String, String> fields,
            JSONObject postedContent,
            Callback serverResponseHandler
    ) {

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder httpUrlBuilder = new HttpUrl.Builder()
                .scheme(scheme)
                .host(serverHost);

        // Set port number.
        if (-1 != portNum) {
            httpUrlBuilder.port(portNum);
        }

        // Set path segments.
        StringTokenizer st = new StringTokenizer(pathSegments, "/");
        while (st.hasMoreTokens()) {
            httpUrlBuilder.addPathSegment(st.nextToken());
        }

        // Set query parameters.
        if (null != fields) {
            Set<String> keys = fields.keySet();
            Iterator<String> i = keys.iterator();
            while (i.hasNext()) {
                String paramName = i.next();
                String paramValue = fields.get(paramName);
                httpUrlBuilder.addQueryParameter(paramName, paramValue);
            }
        }

        // Make and set trust manager(s) to solve SSLHandshakeException exception.
        // The exception occurs from Kit Kat or lower versions of Android.
        if ("https".equals(scheme) && (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)) {
            try {
                TrustManager[] trustAllCerts = getTrustAllCerts();
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                client.setSslSocketFactory(sc.getSocketFactory());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Prepare Request object.
        Request.Builder reqBuilder = new Request.Builder().url(httpUrlBuilder.build());
        if (null != postedContent) {
            RequestBody reqBody = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    postedContent.toString()
            );
            reqBuilder.post(reqBody);
        }

        // Request data from the server.
        Request request = reqBuilder.build();
        client.newCall(request).enqueue(serverResponseHandler);
    }

    /**
     * Get trust manager to permit all of certifications.
     * @return TrustManager array with one X509TrustManager element
     */
    private static TrustManager[] getTrustAllCerts() {
        return new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[] {};
            }
        }};
    }
}
