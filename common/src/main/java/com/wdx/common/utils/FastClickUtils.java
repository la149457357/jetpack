package com.wdx.common.utils;

/**
 * Created by zcx on 2018/4/9.
 */

public class FastClickUtils {
    private static final int MIN_CLICK_DELAY_TIME = 500;//控制连续点击的最短时间
    private static long lastClickTime;//最后一次点击时间
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
