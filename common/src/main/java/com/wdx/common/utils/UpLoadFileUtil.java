package com.wdx.common.utils;


import com.wdx.common.http.MyUploadFile;

/**
 * Created by zcx on 2018/3/27.
 */

public class UpLoadFileUtil {
    public static MyUploadFile myUploadFile;
    
    public static UpLoadRunnable getInstance(BitmapUploadInfo upLoadFileInfo, UpStatueCallBack upStatueCallBack){
        return new UpLoadRunnable(upLoadFileInfo,upStatueCallBack);
    }

    public static class UpLoadRunnable implements Runnable{
        private BitmapUploadInfo upLoadFileInfo;
        private UpStatueCallBack upStatueCallBack;

        public UpLoadRunnable(BitmapUploadInfo upLoadFileInfo,UpStatueCallBack upStatueCallBack){
            this.upLoadFileInfo= upLoadFileInfo;
            this.upStatueCallBack=upStatueCallBack;
        }

        @Override
        public void run() {
            myUploadFile=new MyUploadFile();
            myUploadFile.upLoadFile( upLoadFileInfo.getImg_url(), null,upStatueCallBack);
        }
    }
    //上传状态回调
    public interface UpStatueCallBack{
        void onChaged(long content, long current);
        void onSuccess();
        void onFailed();
    }

}
