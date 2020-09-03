package com.wdx.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wdx.common.base.BaseKey;

/**
 * Created by zcx on 2018/4/12.
 */

public class SharePreferenceUtils {
    public static String token;

    public static SharedPreferences sp;

    public static void getSharedPreference(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(BaseKey.PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
    }
    public static String getString(Context context, String key,
                                    String defValue) {
        getSharedPreference(context);
        String result = sp.getString(key, defValue);
        return result;
    }
    public static void putString(Context context, String key, String value) {
        getSharedPreference(context);
        sp.edit().putString(key, value).commit();
    }

}
