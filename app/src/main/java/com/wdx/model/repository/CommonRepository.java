package com.wdx.model.repository;

import android.content.Context;
import com.wdx.common.http.HttpCallBack;
import com.wdx.common.http.MyOkHttpUtils;
import com.wdx.common.http.MyRequestParams;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/3 16:14
 */
public class CommonRepository<T> {

    private static MyOkHttpUtils myOkHttpUtils;
    private static CommonRepository mCommonRepository;
    public static CommonRepository getResInstance(){
        if(myOkHttpUtils==null){
            myOkHttpUtils=new MyOkHttpUtils();
        }
        if(mCommonRepository==null){
            mCommonRepository = new CommonRepository();
        }
        return mCommonRepository;
    }

    public void requestDataPost(String requestType, final T t, MyRequestParams myRequestParams, final HttpCallBack<T> mHttpCallBack) {
        myOkHttpUtils.request_okPost(requestType,t,myRequestParams,mHttpCallBack);
    }

}
