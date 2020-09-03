package com.wdx.common.utils;



/**
 * Created by zcx on 2018/3/20.
 */

public class FilePathUtil {

    //获取外部存储路径
    public static String getSDCardFilePath(){
        return "";//.getExternalCacheDir().getAbsolutePath();
    }
    //获取应用内部存储路径
    public static String getAppFilePath(){
        return "";// MyApplication.context.getFilesDir().getAbsolutePath();
    }
}
