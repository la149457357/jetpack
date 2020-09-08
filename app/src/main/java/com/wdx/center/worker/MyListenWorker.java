package com.wdx.center.worker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 14:35
 */
public class MyListenWorker extends Worker {

    Context context;
    public MyListenWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
    }

 /*   @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        Log.e("wdx", "执行了 doWork() 操作！");

        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:la149457357@163.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
        data.putExtra(Intent.EXTRA_TEXT, "这是内容");
        data.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(data);

        return (ListenableFuture<Result>) Result.retry();
    }*/

    @NonNull
    @Override
    public Result doWork() {
        Log.e("wdx", "执行了 doWork() 操作！");

     /*   Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:la149457357@163.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
        data.putExtra(Intent.EXTRA_TEXT, "这是内容");
        data.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(data);*/

        return Result.retry();
    }


}
