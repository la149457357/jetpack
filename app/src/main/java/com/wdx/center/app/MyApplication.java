package com.wdx.center.app;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wdx.center.room.database.AppDatabase;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/2 16:48
 */
public class MyApplication extends Application implements Configuration.Provider {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        WorkManager.getInstance(getApplicationContext());
        //初始化arouter

        ARouter.openLog();     // Print log
        ARouter.openDebug();
        // Initialize the SDK
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application
       //初始化Room
    }

    public static Context getContext(){
        return context;

    }
    private static final Object sLock = new Object();
    private static AppDatabase INSTANCE;
        public static AppDatabase getInstance(Context context) {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user.db")
                                    .build();
                }
                return INSTANCE;
            }
        }

    //WorkManager使用
    @NonNull
    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .build();
    }
}
