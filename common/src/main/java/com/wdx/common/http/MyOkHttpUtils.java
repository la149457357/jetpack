package com.wdx.common.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import com.wdx.common.base.BaseConfig;
import com.wdx.common.utils.LogUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2018/3/15.
 */

public class MyOkHttpUtils<T> {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static MyOkHttpUtils myOkHttpUtils;
    final Response[] response = {null};
    public T t;
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    Request request;

    public static MyOkHttpUtils getInstances() {
        if (myOkHttpUtils == null) {
            myOkHttpUtils = new MyOkHttpUtils();
        }
        return myOkHttpUtils;
    }

/*
    loginParams=new LoginParams();
    loginParams.loginname= String.valueOf(edt_name.getText());
    loginParams.password= String.valueOf(edt_password.getText());
                loginParams.addParams("loginname",loginParams.loginname);
                loginParams.addParams("password",loginParams.password);
                MyOkHttpUtils.getInstances().request_okPost(this,BaseConfig.LOGIN,new LoginInfoResult(),loginParams,mUserCallBack,myProgressDialog);*/

    public void request_okPost(final Context context, String requestType, final T t, MyRequestParams myRequestParams, final HttpCallBack<T> mHttpCallBack) {
        this.t = t;
        String json = gson.toJson(myRequestParams, MyRequestParams.class);

        String url = BaseConfig.RequestUrl + requestType;
        RequestBody body = RequestBody.create(JSON, json);

        LogUtils.e("wdx", "*******MyOkHttpUtils 请求参数*******" + url+"\n"+json);

        //Log.e("wdx", "*******MyOkHttpUtils 请求参数*******" + url+"\n"+json);
        if (client == null) {
            client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        }
        Request request = new Request.Builder().url(url)
                .post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("wdx", "*******MyOkHttpUtils 请求失败*******"+e.toString());
                mHttpCallBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("wdx", "*******MyOkHttpUtils 请求成功*******");
                String result = response.body().string();
                LogUtils.e("wdx", result);
                try{
                    T t1 = (T) gson.fromJson(result, t.getClass());
                    mHttpCallBack.onResponse(t1);

                }catch (Exception e){
                    Looper.prepare();
                    e.printStackTrace();
                    Toast.makeText(context,""+t.getClass()+" , json解析异常",Toast.LENGTH_SHORT).show();
                    mHttpCallBack.onError(e);
                    Looper.loop();
                }


            }
        });
    }


}
