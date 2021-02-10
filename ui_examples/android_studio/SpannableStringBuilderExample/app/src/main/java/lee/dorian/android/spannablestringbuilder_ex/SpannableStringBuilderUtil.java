package lee.dorian.android.spannablestringbuilder_ex;

import android.text.SpannableStringBuilder;

public class SpannableStringBuilderUtil {

    public static SpannableStringBuilder appendAndSetSpan(
        SpannableStringBuilder ssb,
        CharSequence stringToAppend,
        int flags,
        Object... spans
    ) {
        int lengthBeforeAppending = ssb.length();
        int lengthOfStringToAppend = stringToAppend.length();
        ssb.append(stringToAppend);
        for (Object span : spans) {
            ssb.setSpan(span, lengthBeforeAppending, lengthBeforeAppending + lengthOfStringToAppend, flags);
        }
        return ssb;
    }

}
