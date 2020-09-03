package com.wdx.center;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import com.wdx.center.databinding.ActivityMainBinding;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/8/31 17:01
 */
class BaseActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    public void bindLayout(int layoutId){
        activityMainBinding = DataBindingUtil.setContentView(this,layoutId);
    }
    LifecycleRegistry mLifecycleRegistry;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }
}
