package com.wdx.common.http;

import android.os.Handler;
import android.util.Log;


import com.wdx.common.utils.UpLoadFileUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;


/**
 * Created by admin on 2018/3/15.
 */

public class MyUploadFile {
    public static String TAG = "AAA";
    OkHttpClient okHttpClient=new OkHttpClient();

    public <T> void upLoadFile(String actionUrl, HashMap<String, Object> paramsMap, final UpLoadFileUtil.UpStatueCallBack callBack) {
        try {
            //补全请求地址
            String requestUrl = String.format("", "", actionUrl);
            MultipartBody.Builder builder = new MultipartBody.Builder();
            //设置类型
            builder.setType(MultipartBody.FORM);
            //追加参数
            for (String key : paramsMap.keySet()) {
                Object object = paramsMap.get(key);
                if (!(object instanceof File)) {
                    builder.addFormDataPart(key, object.toString());
                } else {
                    File file = (File) object;
                    builder.addFormDataPart(key, file.getName(), createProgressRequestBody(MediaType.parse("image/jpg; charset=utf-8"), file,callBack));
                }
            }
            //创建RequestBody
            RequestBody body = builder.build();
            //创建Request
            final Request request = new Request.Builder().url(requestUrl).post(body).build();
            final Call call = okHttpClient.newBuilder().writeTimeout(50, TimeUnit.SECONDS).build().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        Log.e(TAG, "response ----->" + string);
                        //添加上传回调广播
                        callBack.onSuccess();
                        Log.e(TAG, "上传成功");
                    } else {
                        //添加上传回调广播
                        callBack.onFailed();
                        Log.e(TAG, "上传失败");
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
    //文件上传
    public <T> RequestBody createProgressRequestBody(final MediaType contentType, final File file, final UpLoadFileUtil.UpStatueCallBack callBack) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source;
                try {
                    source = Okio.source(file);
                    Buffer buf = new Buffer();
                    long remaining = contentLength();
                    long current = 0;
                    for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
                        sink.write(buf, readCount);
                        current += readCount;
                        Log.e(TAG, "current------>" + current);
                        //添加上传回调广播 remaining为总大小 current为当前上传大小
                        callBack.onChaged(remaining,current);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
