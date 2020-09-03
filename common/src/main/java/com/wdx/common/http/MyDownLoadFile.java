package com.wdx.common.http;

import android.os.Handler;
import android.util.Log;


import com.wdx.common.utils.DownLoadFileUtil;
import com.wdx.common.utils.MD5Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by admin on 2018/3/15.
 */

public class MyDownLoadFile {

    OkHttpClient okHttpClient=new OkHttpClient();
    public void downLoadFile(String fileUrl, final String destFileDir, final DownLoadFileUtil.DownStatueCallBack myDownStatueCallBack) {
        final String fileName = MD5Util.encodeByMd5(fileUrl);
        final File file = new File(destFileDir, fileName);
        if (file.exists()) {
            successCallBack(file, myDownStatueCallBack);
            return;
        }
        final Request request = new Request.Builder().url(fileUrl).build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.toString());
               // failedCallBack("下载失败", callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    Log.e(TAG, "total------>" + total);
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        Log.e(TAG, "current------>" + current);
                        progressCallBack(total, current, myDownStatueCallBack);
                    }
                    fos.flush();
                    successCallBack(file, myDownStatueCallBack);
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                    //failedCallBack("下载失败", callBack);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, e.toString());
                    }
                }
            }
        });
    }

    private void successCallBack(File file,DownLoadFileUtil.DownStatueCallBack myDownStatueCallBack) {
        myDownStatueCallBack.onSuccess(file);
    }

    Handler okHttpHandler;
    private  void progressCallBack(final long total, final long current, final DownLoadFileUtil.DownStatueCallBack myDownStatueCallBack) {
            myDownStatueCallBack.onChaged(total, current);
    }
}
