package com.wdx.kotlin;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 *
 */
@Route(path = "/kotlin/testactivity")
public class TestActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin);


       initView();
       setListener();

    }

    TextView tv_hhh;
    TextView tv_start;
    private void initView() {
        tv_hhh = findViewById(R.id.tv_hhh);
        tv_start = findViewById(R.id.tv_start);
    }



    private void setListener() {

        tv_hhh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TestActivity.this,"我关闭了",Toast.LENGTH_LONG).show();
                ARouter.getInstance().build("/ttt/testotheractivity").navigation();
                Log.e("wdx","start ===========");
                finish();

            }
        });
        tv_start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(TestActivity.this,KotlinTestActivity.class);
                TestActivity.this.startActivity(intent);
            }
        });
    }
}
