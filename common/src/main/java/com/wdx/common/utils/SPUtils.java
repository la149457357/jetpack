package com.wdx.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.wdx.common.base.BaseKey;


/**
 * Created by admin on 2018/1/18.
 */

public class SPUtils {
    public static void setHttpParams(Context context, String ip, String port, String platfrom) {
        SharedPreferences preferences = context.getSharedPreferences(BaseKey.PREFERENCES_NAME, 0);
        preferences.edit().putBoolean(BaseKey.PREFERENCES_HTTP_ISSELECT, true).commit();
        preferences.edit().putString(BaseKey.PREFERENCES_HTTP_IP, ip).commit();
        preferences.edit().putString(BaseKey.PREFERENCES_HTTP_PORT, port).commit();
        preferences.edit().putString(BaseKey.PREFERENCES_HTTP_platfrom, platfrom).commit();
    }
    //获取文件是否已下载过
    public static String getDownFileUri(Context context){
        SharedPreferences preferences = context.getSharedPreferences(BaseKey.PREFERENCES_NAME, 0);
        return preferences.getString(BaseKey.PREFERENCES_FILE_MESSAGE,"");
    }
    //储存文件下载信息
    public static void putDownFileUri(Context context,String message){
        SharedPreferences preferences = context.getSharedPreferences(BaseKey.PREFERENCES_NAME, 0);
        preferences.edit().putString(BaseKey.PREFERENCES_FILE_MESSAGE,message);
    }
}
