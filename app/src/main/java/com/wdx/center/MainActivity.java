package com.wdx.center;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.wdx.center.adapter.ConcertViewAdapter;
import com.wdx.center.adapter.SimplePaddingDecoration;
import com.wdx.center.view.MySwipeRefreshLayout;
import com.wdx.center.view.MySwipeRefreshLayout.OnLoadMoreListener;
import com.wdx.center.view.VideoPlayerView;
import com.wdx.center.worker.MyListenWorker;
import com.wdx.common.midia.MyTestImpl;
import com.wdx.kotlin.KotlinTest;
import com.wdx.kotlin.TestNewService;
import com.wdx.model.ConcertViewModel;
import com.wdx.model.UserViewModel;
import com.wdx.tv.Movie;
import java.util.Map;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


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

    private void getsharedInfo() {
        SharedPreferences sp = getSharedPreferences("file",
                this.MODE_PRIVATE);
        Toast.makeText(this, "click item", Toast.LENGTH_SHORT).show();
        Map<String, ?> map = sp.getAll();
        if (map == null) {

            Toast.makeText(this, "shared is null", Toast.LENGTH_SHORT).show();
        }
        for (String key : map.keySet()) {
            // map.get(key);
            Log.e("wdx", "shared value =  " + key + map.get(key));
        }

    }


    private void startWorkManager() {

       /* OneTimeWorkRequest secondWork = new OneTimeWorkRequest.Builder(MyListenWorker.class)
                .setInitialDelay(5000, TimeUnit.MILLISECONDS) // 在满足约束条件的前提下，初始延迟时间为5S
                .setBackoffCriteria(BackoffPolicy.LINEAR,10,TimeUnit.SECONDS) // 重试间隔时间为：curTime + 10 * 重试次数
                .build();*/
        // PeriodicWorkRequest secondWork = new PeriodicWorkRequest.Builder(MyListenWorker.class,15,TimeUnit.MINUTES).build();
//1.约束条件
        //Constraints myconstraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)//不在电量不足时执行
                .setRequiresCharging(true)//在充电时执行
                .setRequiresStorageNotLow(true)//不在存储容量不足时执行
                .setRequiresDeviceIdle(true)//在待机状态执行
                .build();
//2.传入参数
        Data data = new Data.Builder().putString("demo", "helloworld").build();
//3.构造work
        OneTimeWorkRequest httpwork = new OneTimeWorkRequest.Builder(MyListenWorker.class)
                .setConstraints(constraints).setInputData(data).build();
//4.放入执行队列
        WorkManager.getInstance(this).enqueue(httpwork);

      /*  OneTimeWorkRequest workA = OneTimeWorkRequest.from(MyListenWorker.class);
        OneTimeWorkRequest workB = OneTimeWorkRequest.from(MyListenWorker.class);
        OneTimeWorkRequest workC = OneTimeWorkRequest.from(MyListenWorker.class);
        WorkManager.getInstance(this)
    .beginWith(Arrays.asList(workA, workB))
                .then(workC)
                .enqueue();*/
     /*   WorkManager
                .getInstance(this)
                .enqueue(secondWork);
*/
    }

    private void startService() {
        Intent intent = new Intent(this, TestNewService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //适配8.0机制
            this.startForegroundService(intent);
        } else {
            this.startService(intent);
        }
    }

    private void setData() {
        //测试代码
        KotlinTest kotlinTest = new KotlinTest();
        Movie movie = new Movie();
        TestNewService myser = new TestNewService();
        MyTestImpl myTest = new MyTestImpl();

        //-------------------
    }


    EditText et_input;
    RecyclerView rcv_list;
    ConcertViewModel viewModel;
    MySwipeRefreshLayout swipeRefreshLayout;

    private void initView() {
        VideoPlayerView videoPlayerView = new VideoPlayerView(this);
        videoPlayerView.setLifecycleOwner(this);

        et_input = findViewById(R.id.et_input);
        rcv_list = findViewById(R.id.rcv_list);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRecycleView(rcv_list);

        ConcertViewAdapter adapter = new ConcertViewAdapter();
        viewModel = new ConcertViewModel();
        viewModel.getConvertList().observe(this, concerts -> adapter.submitList(concerts));
        rcv_list.setAdapter(adapter);
        rcv_list.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv_list.addItemDecoration(new SimplePaddingDecoration(this));

    }

    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        rcv_list.addOnItemTouchListener(new OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        getsharedInfo();
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        swipeRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                viewModel.setOnload();
                Toast.makeText(MainActivity.this, "loadmore", Toast.LENGTH_SHORT).show();
            }
        });
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
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

    @Override
    public void onRefresh() {
        viewModel.setdatalist();
        swipeRefreshLayout.setRefreshing(false);
        SharedPreferences sp = this.getSharedPreferences("file", this.MODE_PRIVATE);

        sp.edit().clear().commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopForeground(true);
        Intent intent = new Intent("com.dbjtech.waiqin.destroy");
        sendBroadcast(intent);
    }
}