package com.wdx.center.worker;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 14:35
 */
public class MyListenWorker extends Worker {


    public MyListenWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e("wdx", "执行了 doWork() 操作！");
        return Result.success();
    }


}
