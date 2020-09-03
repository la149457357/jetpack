package com.wdx.model.repository;

import androidx.lifecycle.ViewModel;
import com.wdx.common.http.HttpCallBack;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/3 20:17
 */
public class BaseViewModel extends ViewModel {
 public HttpCallBack mReposityCallBack;
 public void initCallBack(){
     if(mReposityCallBack == null){
         mReposityCallBack = new HttpCallBack() {
             @Override
             public void onResponse(Object o) {
                 onHttpResponse(o);
             }

             @Override
             public void onError(Exception t) {
                onHttpError();
             }
         };

     } }



    public void onHttpResponse(Object o){

     }
    private void onHttpError() {
    }
}
