package com.wdx.center;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.work.BackoffPolicy;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.common.util.concurrent.ListenableFuture;
import com.wdx.center.adapter.ConcertViewAdapter;
import com.wdx.center.view.VideoPlayerView;
import com.wdx.center.worker.MyListenWorker;
import com.wdx.common.midia.MyTestImpl;
import com.wdx.kotlin.KotlinTest;
import com.wdx.kotlin.TestNewService;
import com.wdx.model.ConcertViewModel;
import com.wdx.model.UserViewModel;
import com.wdx.tv.Movie;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindLayout(R.layout.activity_main);

        bindViewModel();
        initView();
        setData();
        setListener();
        startService();
        startWorkManager();
    }


    private void startWorkManager() {

        OneTimeWorkRequest secondWork = new OneTimeWorkRequest.Builder(MyListenWorker.class)
                .setInitialDelay(5000, TimeUnit.MILLISECONDS) // 在满足约束条件的前提下，初始延迟时间为5S
                .setBackoffCriteria(BackoffPolicy.LINEAR,10,TimeUnit.SECONDS) // 重试间隔时间为：curTime + 10 * 重试次数
                .build();

      /*  OneTimeWorkRequest workA = OneTimeWorkRequest.from(MyListenWorker.class);
        OneTimeWorkRequest workB = OneTimeWorkRequest.from(MyListenWorker.class);
        OneTimeWorkRequest workC = OneTimeWorkRequest.from(MyListenWorker.class);
        WorkManager.getInstance(this)
    .beginWith(Arrays.asList(workA, workB))
                .then(workC)
                .enqueue();*/
        WorkManager
                .getInstance(this)
                .enqueue(secondWork);

    }

    private void startService() {
        Intent intent=new Intent(this, TestNewService.class);
        this.startService(intent);
    }

    private void setData() {
        //测试代码
        KotlinTest kotlinTest=new KotlinTest();
        Movie movie=new Movie();
        TestNewService myser=new TestNewService();
        MyTestImpl myTest=new MyTestImpl();

        //-------------------
    }


    EditText et_input;
    RecyclerView rcv_list;
    private void initView() {
        VideoPlayerView videoPlayerView=new VideoPlayerView(this);
        videoPlayerView.setLifecycleOwner(this);

        et_input = findViewById(R.id.et_input);
        rcv_list = findViewById(R.id.rcv_list);

        rcv_list = findViewById(R.id.rcv_list);
        ConcertViewAdapter adapter = new ConcertViewAdapter();
        ConcertViewModel viewModel = new ConcertViewModel();
        viewModel.getConvertList().observe(this, concerts -> adapter.submitList(concerts));
        rcv_list.setAdapter(adapter);
        rcv_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv_list.addItemDecoration(new SimplePaddingDecoration(this));

    }
    private void setListener() {
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s!=null){
                    userViewModel.userInfo.setName(s.toString());
                }
            }
        });
    }

    UserViewModel userViewModel;

    private void bindViewModel() {
        userViewModel = new UserViewModel();
        activityMainBinding.setUserInfo(userViewModel.userInfo);
        userViewModel.requestServerInfo("输入信息");
        activityMainBinding.setLifecycleOwner(this);
    }
}