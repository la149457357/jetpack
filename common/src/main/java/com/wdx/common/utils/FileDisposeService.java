package com.wdx.common.utils;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;


import com.wdx.common.http.DownLoadFileInfo;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zcx on 2018/3/27.
 */

public class FileDisposeService extends Service {
    public static String UPLOAD="upload";
    public static String DOWNLOAD="download";
    ExecutorService fixedThreadPool;

    @Override
    public void onCreate() {
        super.onCreate();
        fixedThreadPool = Executors.newFixedThreadPool(3);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initThread(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //初始化下载线程
    private void initThread(Intent intent) {
        String type=intent.getStringExtra("type");
        Messenger messenger= (Messenger) intent.getExtras().get("messenger");
        if(type!=null&&type.equals(UPLOAD)) {
            //获取上传文件集合
            Map<String,BitmapUploadInfo> upLoadFileInfos= (Map<String,BitmapUploadInfo>) intent.getSerializableExtra("infos");
            if(upLoadFileInfos!=null){
                Set<String> keys=upLoadFileInfos.keySet();
                //循环遍历去起线程上传
                for (String key:keys) {
                    MyUpStatueCallBack myUpStatueCallBack=new MyUpStatueCallBack(key,messenger);
                    UpLoadFileUtil.UpLoadRunnable upLoadRunnable= UpLoadFileUtil.getInstance(upLoadFileInfos.get(key),myUpStatueCallBack);
                    addToThreadPool(upLoadRunnable);
                }
            }
            else {
                //通知activity上传失败
                try {
                    Message message=Message.obtain();
                    message.what=FileDisposeUtil.UPLOADFAIL;
                    message.obj="上传列表为空";
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this,"上传文件列表为空",Toast.LENGTH_LONG).show();
            }
        }else if(type!=null&&type.equals(DOWNLOAD)){
            //获取下载文件集合
            List<DownLoadFileInfo> downLoadFileInfos= (List<DownLoadFileInfo>) intent.getSerializableExtra("infos");
            if(downLoadFileInfos!=null&&downLoadFileInfos.size()>0){
                for(int i=0;i<downLoadFileInfos.size();i++){
                    MyDownStatueCallBack myDownStatueCallBack=new MyDownStatueCallBack(i,messenger);
                    DownLoadFileUtil.DownLoadRunnable downLoadRunnable= DownLoadFileUtil.getInstance(downLoadFileInfos.get(i),myDownStatueCallBack);
                    addToThreadPool(downLoadRunnable);
                }
            }else {
                //通知activity下载失败
                try {
                    Message message=Message.obtain();
                    message.what=FileDisposeUtil.DOWNLOADFAIL;
                    message.obj="下载列表为空";
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this,"下载文件列表为空",Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this,"上传或下载未知错误!",Toast.LENGTH_LONG).show();
        }
    }

    //线程池添加线程
    private void addToThreadPool(Runnable runnable){
        fixedThreadPool.execute(runnable);
    }

    //用于上传进度回调
    public class MyUpStatueCallBack implements UpLoadFileUtil.UpStatueCallBack{
        private String key;
        private Messenger messenger;
        public MyUpStatueCallBack(String key,Messenger messenger){
            this.key=key;
            this.messenger=messenger;
        }
        @Override
        public void onChaged(long content, long current) {
            try {
                Message message=Message.obtain();
                message.what= FileDisposeUtil.UPLOADSTATUE;
                message.obj=key;
                message.arg1= (int) ((current/content)*100);
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onSuccess() {

            try {
                Message message=Message.obtain();
                message.what=FileDisposeUtil.UPLOADSUCCESS;
                message.obj=key;
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailed() {
            try {
                Message message=Message.obtain();
                message.what=FileDisposeUtil.UPLOADFAIL;
                message.obj=key;
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    //用于上传进度回调
    public class MyDownStatueCallBack implements DownLoadFileUtil.DownStatueCallBack {
        private int position;
        private Messenger messenger;
        public MyDownStatueCallBack(int position,Messenger messenger){
            this.position=position;
            this.messenger=messenger;
        }
        @Override
        public void onChaged(long content, long current) {
            try {
                Message message=Message.obtain();
                message.what= FileDisposeUtil.DOWNLOADSTATUE;
                message.arg1=position;
                message.arg2= (int) ((current/content)*100);
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onSuccess(File file) {

            try {
                Message message=Message.obtain();
                message.what=FileDisposeUtil.DOWNLOADSUCCESS;
                message.arg1=position;
                message.obj=file;
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailed() {
            try {
                Message message=Message.obtain();
                message.what=FileDisposeUtil.DOWNLOADFAIL;
                message.arg1=position;
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
