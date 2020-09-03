package com.wdx.common.http;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by zcx on 2018/3/20.
 */

public class FileDownLoadService extends Service{
    private String url;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
