package com.wdx.common.http;


/**
 * Created by zcx on 2018/3/20.
 */

public class DownLoadFileInfo extends BaseInfo{
    private String url;//文件网络地址
    private String timeStamp;//文件时间戳
    private String name;//文件名
    private String type;//文件类型
    private int progress;//下载进度

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

}
