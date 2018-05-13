package lee.dorian.android.switch_ex;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Util class to save data into preferences and load them from it
 */

public class PrefUtil {
    private static final String PREF_NAME = "switch_ex_pref";

    public static String loadId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString("id", "");
    }

    public static void saveId(Context context, String id) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("id", id);
        editor.commit();
    }

    public static void deleteId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.remove("id");
        editor.commit();
    }
}
