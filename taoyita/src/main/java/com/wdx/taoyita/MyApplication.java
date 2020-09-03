package com.wdx.taoyita;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/2 22:24
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
