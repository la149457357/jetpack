package com.wdx.common.utils;


import com.wdx.common.http.DownLoadFileInfo;
import com.wdx.common.http.MyDownLoadFile;
import java.io.File;

/**
 * Created by zcx on 2018/3/27.
 */

public class DownLoadFileUtil {
    public static MyDownLoadFile myDownLoadFile;
    public static DownLoadRunnable getInstance(DownLoadFileInfo downLoadFileInfo,DownStatueCallBack myDownStatueCallBack){
        return new DownLoadRunnable(downLoadFileInfo,myDownStatueCallBack);
    }
    public static class DownLoadRunnable implements Runnable{
        private DownLoadFileInfo downLoadFileInfo;
        private DownStatueCallBack myDownStatueCallBack;
        public DownLoadRunnable(DownLoadFileInfo downLoadFileInfo,DownStatueCallBack myDownStatueCallBack){
            this.downLoadFileInfo= downLoadFileInfo;
            this.myDownStatueCallBack=myDownStatueCallBack;
        }

        @Override
        public void run() {
            myDownLoadFile=new MyDownLoadFile();
            myDownLoadFile.downLoadFile(downLoadFileInfo.getUrl(),FilePathUtil.getAppFilePath(),myDownStatueCallBack);
        }
    }

    //下载状态回调
    public interface DownStatueCallBack{
        void onChaged(long content, long current);
        void onSuccess(File file);
        void onFailed();
    }
}
