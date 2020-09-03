package com.wdx.common.http;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2018/4/8.
 */

public class HttpRunnable<T> implements Runnable{
    T t;
    Request request;
    String url;
    RequestBody body;
    final Response[] response = {null};
    public HttpRunnable(T t, String url, RequestBody body) {
        this.t = t;
        this. body=body;
        this.url=url;
        request = new Request.Builder()
                .url(this.url)
                .post(body)
                .build();

    }

    @Override
    public void run() {

    }
}
