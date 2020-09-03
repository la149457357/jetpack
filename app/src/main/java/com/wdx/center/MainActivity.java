package com.wdx.center;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;

import com.wdx.center.view.VideoPlayerView;
import com.wdx.common.midia.MyTestImpl;
import com.wdx.kotlin.KotlinTest;
import com.wdx.kotlin.TestNewService;
import com.wdx.model.UserViewModel;
import com.wdx.tv.Movie;


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