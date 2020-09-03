package com.wdx.common.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.wdx.common.R;
import com.wdx.common.app.MyApplication;
import com.wdx.common.http.BaseResult;
import com.wdx.common.http.HttpCallBack;
import com.wdx.common.utils.FastClickUtils;
import com.wdx.common.utils.SharePreferenceUtils;


/**
 * Created by admin on 2017/12/22.
 */

public class BaseActivity extends Activity implements View.OnClickListener{
    public MyApplication kApp;
    public Bundle baseBundle;

    public ImageView iv_back;
    TextView tv_title;
    public ImageView iv_title_right_one;
    public ImageView  iv_title_right_two;
    TextView tv_title_right_one;
    public  Handler baseHandler;
    public HttpCallBack<Object> mUserCallBack;
    public Gson gson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kApp =(MyApplication)getApplication();
        gson=new Gson();
        getBundle();
        //setWindowStatusBarColor(this,0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initHttpCallBack();
        //Exception();
    }
    public void Exception(){
        //避免出现android.os.NetworkOnMainThreadException异常
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
    }
    public void initHttpCallBack(){
        if(mUserCallBack==null){
            mUserCallBack=new HttpCallBack<Object>() {
                @Override
                public void onResponse(final Object t) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("msg",""+t);
                            onMsgResponse( t);
                        }
                    });
                }

                @Override
                public void onError(Exception o) {
                    onHttpError(o);
                }
            };
        }
       // MyOkHttpUtils.getInstances().request_okPost(BaseConfig.SENDMSG,new TestInfoResult(),new MyRequestParams(),mUserCallBack);
    }

    public void onHttpError(Exception o) {

    }

    public int width;
    public int height;
    public void getScreenWithHeight(){
        WindowManager wm = (WindowManager) this
                .getSystemService(this.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        final float scale = this.getResources().getDisplayMetrics().density;
       // Toast.makeText(this,"width == "+width +"height ==" + height+" scale == "+scale,Toast.LENGTH_LONG).show();
    }
    private void setTitleListener() {
        if(iv_back!=null){
            iv_back.setOnClickListener(this);
        }
        if(iv_title_right_one!=null){
            iv_title_right_one.setOnClickListener(this);
        }
        if(tv_title_right_one!=null){
            tv_title_right_one.setOnClickListener(this);
        }
        if(iv_title_right_two!=null){
            iv_title_right_two.setOnClickListener(this);
        }
    }

    private void setTitleName(String titleName) {
        if(tv_title!=null){
            tv_title.setText(titleName);
        }
    }

    public void setRightTitleVisible(int visible,int bill_state){
        iv_title_right_one.setVisibility(visible);
        if(bill_state==1){
            iv_title_right_two.setVisibility(View.VISIBLE);
        }else {
            iv_title_right_two.setVisibility(View.GONE);
        }

    }
    public void showRightOneTitle(int drawable){
        iv_title_right_one.setVisibility(View.VISIBLE);
        iv_title_right_one.setImageResource(drawable);
    }
    public void showRightTwoTitle(int drawable){
        iv_title_right_two.setVisibility(View.VISIBLE);
        iv_title_right_two.setImageResource(drawable);
    }
    public Handler getHandler(){
        if(baseHandler==null){
            baseHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                        onHandlerMessage(msg);
                }
            };
        }
        return baseHandler;
    }

    public void onHandlerMessage(Message message){
        Log.e("wdx","----------------");
    }

    public void setFullScreen(){
           /* //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
    }
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bundle getBundle() {
        Intent intent=getIntent();
        if(intent!=null){
            baseBundle=intent.getExtras();
        }
        return baseBundle;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void startActivityNoAnim(Intent intent) {
        super.startActivity(intent);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in1, R.anim.activity_out1);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(!FastClickUtils.isFastClick()){
            return;
        }

    }

    public void onRightTopClick(int type) {


    }
    public void onBackClick() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

      public void onMsgResponse(Object obj){
          Log.e("msg",""+obj);
          BaseResult m= (BaseResult) obj;
          Log.e("msg",""+m);
          switch (m.code){
              case "100001":
                  SharePreferenceUtils.putString(this, BaseKey.PREFERENCES_TOKEN,"");
                  SharePreferenceUtils.putString(this, BaseKey.PREFERENCES_USERINFO,"");
                  break;
              case "500":
                  Toast.makeText(BaseActivity.this,"服务端数据异常",Toast.LENGTH_SHORT).show();
                  break;
              case "20003":
                  Toast.makeText(BaseActivity.this,""+m.msg,Toast.LENGTH_SHORT).show();
                  break;
          }
      }

    @Override
    public void onBackPressed() {
        onBackClick();
    }
}
