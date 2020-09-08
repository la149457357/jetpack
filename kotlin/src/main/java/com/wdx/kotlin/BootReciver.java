package com.wdx.kotlin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/8 14:45
 */
class BootReciver extends BroadcastReceiver {
    @RequiresApi(api = VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.dbjtech.waiqin.destroy")) {
            //在这里写重新启动service的相关操作
            startUploadService(context);
        }
    }

    @RequiresApi(api = VERSION_CODES.O)
    private void startUploadService(Context context) {

        Intent intent=new Intent(context, TestNewService.class);
        context.startForegroundService(intent);
    }
}
