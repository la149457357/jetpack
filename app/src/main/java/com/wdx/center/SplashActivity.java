package com.wdx.center;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/1 10:14
 */
public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout(R.layout.activity_splash);
         myHandler.sendEmptyMessageDelayed(1,1000);
    }

    MyHandler myHandler=new MyHandler(this);
    static class MyHandler extends Handler {
        // WeakReference to the outer class's instance.
        private WeakReference<SplashActivity> mOuter;

        public MyHandler(SplashActivity activity) {
            mOuter = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity outer = mOuter.get();
            if (outer != null) {
                Intent intent =new Intent();
                intent.setClass(outer,MainActivity.class);
                outer.startActivity(intent);
                outer.finish();
               // Log.e("wdx","@@@@@@@@@@@@@@");
            }
        }
    }
}
