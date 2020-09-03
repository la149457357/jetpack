package com.wdx.common.utils;

import android.util.Log;
import com.wdx.common.base.BaseConfig;


/**
 * Created by admin on 2018/4/19.
 */

public class LogUtils{
    public static int  LENGTH = 500;
    public static void e(String tag,String msg){
        if (BaseConfig.DEBUG) {
            if (msg.length() > LENGTH) {
                for (int i = 0; i < msg.length(); i += LENGTH) {
                    if (i + LENGTH < msg.length()) {
                        Log.e(tag, msg.substring(i, i + LENGTH));
                    } else {
                        Log.e(tag, msg.substring(i, msg.length()));
                    }
                }
            } else {
                Log.e(tag, msg);
            }
        }
    }

}
