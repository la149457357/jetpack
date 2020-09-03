package com.wdx.common.app;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/2 16:48
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
      /*  if (true) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }*/
        ARouter.openLog();     // Print log
        ARouter.openDebug();
        // Initialize the SDK
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application

    }
}
