package com.wdx.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Messenger;

import com.wdx.common.http.DownLoadFileInfo;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zcx on 2018/3/27.
 */

public class FileDisposeUtil {
    public final static int UPLOADSUCCESS=101;//上传成功
    public final static int UPLOADFAIL=102;//上传失败
    public final static int UPLOADSTATUE=103;//上传进度
    public final static int DOWNLOADSUCCESS=201;//下载成功
    public final static int DOWNLOADFAIL=202;//下载失败
    public final static int DOWNLOADSTATUE=203;//下载进度
    //开启上传功能服务
    public static void startUpLoad(Activity activity, Map<String,BitmapUploadInfo> upLoadFileInfos, Handler myHandler){
        Intent intent=new Intent(activity,FileDisposeService.class);
        intent.putExtra("type",FileDisposeService.UPLOAD);
        intent.putExtra("infos", (Serializable) upLoadFileInfos);
        intent.putExtra("messenger",new Messenger(myHandler));
        activity.startService(intent);
    }
    //开启下载功能服务
    public static void startDownLoad(Activity activity, List<DownLoadFileInfo> downLoadFileInfos,Handler myHandler){
        Intent intent=new Intent(activity,FileDisposeService.class);
        intent.putExtra("type",FileDisposeService.DOWNLOAD);
        intent.putExtra("infos", (Serializable) downLoadFileInfos);
        intent.putExtra("messenger",new Messenger(myHandler));
        activity.startService(intent);
    }

}
