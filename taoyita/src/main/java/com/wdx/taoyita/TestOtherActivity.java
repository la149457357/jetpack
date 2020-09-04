package com.wdx.taoyita;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/ttt/testotheractivity")
public class TestOtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_else);
        TextView tv_router=findViewById(R.id.tv_router);
        tv_router.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/kotlin/kotlintestactivity").navigation();
                finish();
            }
        });
       // ARouter.getInstance().inject(this);

        Log.e("wdx","end  ===========");
    }
}
