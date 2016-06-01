package cf.domone.android.okhttp_get_example_01;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Util class to use JSON objects more easily
 */
public class JsonUtil {

    /**
     * Get the JSON object from a JSON-encoded string
     * @param jsonString A JSON-encoded string
     * @return
     */
    public static JSONObject getJSONObjectFrom(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Read a string mapped by key from a JSON object if the key exists
     * @param jsonObject The source JSON object
     * @param key The key of jsonObject
     * @return The string mapped by key
     */
    public static String getStringFrom(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
