package com.wdx.common.http;

/**
 * Created by admin on 2018/3/15.
 */

public interface ReqProgressCallBack<T> {
    /**
     * 响应进度更新
     */
    void onProgress(long total, long current);
}