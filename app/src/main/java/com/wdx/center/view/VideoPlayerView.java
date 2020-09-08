package com.wdx.center.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/1 14:42
 */
public class VideoPlayerView extends LinearLayout {

    public VideoPlayerView(Context context) {
        super(context);
        init(context);
    }

    public VideoPlayerView(Context context,
            @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VideoPlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    Context context;

    private void init(Context context) {
        this.context = context;


    }

    private static final String TAG = "AAA";

    private LifecycleOwner mLifecycleOwner;

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
        mLifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(LifecycleOwner source, final Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_CREATE) {
                    onCreate();
                } else if (event == Lifecycle.Event.ON_START) {
                    onStart();
                } else if (event == Lifecycle.Event.ON_RESUME) {
                    onResume();
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    onPause();
                } else if (event == Lifecycle.Event.ON_STOP) {
                    onStop();
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    onDestroy();
                }
            }
        });
    }

    public void onCreate() {
        Log.e(TAG, "onCreate:");
    }

    public void onStart() {
        Log.e(TAG, "onStart:");
    }

    public void onResume() {
        Log.e(TAG, "onResume:");
    }

    public void onPause() {
        Log.e(TAG, "onPause:");
    }

    public void onStop() {
        Log.e(TAG, "onStop:");
    }

    public void onDestroy() {
        Log.e(TAG, "onDestroy:");
    }

}
