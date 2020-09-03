package com.wdx.common.http;


/**
 * Created by admin on 2018/4/4.
 */

public interface HttpCallBack<T> {
    void onResponse(T t);
    void onError(Exception t);
}
