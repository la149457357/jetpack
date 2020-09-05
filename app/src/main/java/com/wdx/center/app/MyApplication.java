package com.wdx.center.app;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/2 16:48
 */
public class MyApplication extends Application implements Configuration.Provider {

    @Override
    public void onCreate() {
        super.onCreate();
        WorkManager.getInstance(getApplicationContext());
      /*  if (true) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }*/
        ARouter.openLog();     // Print log
        ARouter.openDebug();
        // Initialize the SDK
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application

    }

    @NonNull
    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .build();
    }
}
