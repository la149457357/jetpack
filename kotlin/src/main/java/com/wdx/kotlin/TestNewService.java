package com.wdx.kotlin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/2 18:18
 */
public class TestNewService extends Service {

        private Looper serviceLooper;
        private ServiceHandler serviceHandler;
        int i=0;

        // Handler that receives messages from the thread
        private final class ServiceHandler extends Handler {
            public ServiceHandler(Looper looper) {
                super(looper);
            }
            @Override
            public void handleMessage(Message msg) {
                // Normally we would do some work here, like download a file.
                // For our sample, we just sleep for 5 seconds.
                try {
                    Thread.sleep(3000);
                    i++;
                    String string = "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + i;
                    Log.e("wdx",string);

                    Editor editor = sp.edit();
                    editor.putString("name"+i, ""+string);

                    editor.commit();
                } catch (InterruptedException e) {
                    // Restore interrupt status.
                    Thread.currentThread().interrupt();
                }
                // Stop the service using the startId, so that we don't stop
                // the service in the middle of handling another job
                stopSelf(msg.arg1);
                serviceHandler.sendMessageDelayed(new Message(),3000);
            }
        }

        @Override
        public void onCreate() {
            // Start up the thread running the service. Note that we create a
            // separate thread because the service normally runs in the process's
            // main thread, which we don't want to block. We also make it
            // background priority so CPU-intensive work doesn't disrupt our UI.
            HandlerThread thread = new HandlerThread("ServiceStartArguments",
                    0);
            thread.start();
            i=0;
            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = thread.getLooper();
            serviceHandler = new ServiceHandler(serviceLooper);
        }

        public static int ID= 133332312;
        SharedPreferences sp;
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
         /*   IMPORTANCE_NONE：      (0)关闭通知。在任何地方都不会显示，被阻塞
            IMPORTANCE_MIN：          (1)开启通知。不弹出，不发提示音，状态栏中无显示
            IMPORTANCE_LOW：        (2)开启通知。不弹出，不发提示音，状态栏中显示
            IMPORTANCE_DEFAULT：(3)开启通知。不弹出，发出提示音，状态栏中显示【默认】
            IMPORTANCE_HIGH：       (4)开启通知。会弹出，发出提示音，状态栏中显示

            IMPORTANCE_MAX：        (5)开启通知。会弹出，发出提示音，可以使用full screen intents(比如来电)。重要程度最高
            IMPORTANCE_UNSPECIFIED：(-1000)表示用户未设重要值。该值是为了持久的首选项，且永不应该与实际通知相关联*/
             sp = getSharedPreferences("file", Context.MODE_PRIVATE);
            i=0;
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

// 【适配Android8.0】给NotificationManager对象设置NotificationChannel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("notification_id", "notification_name", NotificationManager.IMPORTANCE_LOW);
                notificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
// 【适配Android8.0】设置Notification的Channel_ID,否则不能正常显示
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setChannelId("notification_id");
            }
            Notification notification=builder.build();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) { // 注意notification也要适配Android 8 哦
                startForeground(ID, notification);// 通知栏标识符 前台进程对象唯一ID
            }
            // For each start request, send a message to start a job and deliver the
            // start ID so we know which request we're stopping when we finish the job
            Message msg = serviceHandler.obtainMessage();
            msg.arg1 = startId;
            serviceHandler.sendMessageDelayed(msg,3000);

            flags = START_STICKY;
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            // We don't provide binding, so return null
            return null;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
            i=0;
        }
}
