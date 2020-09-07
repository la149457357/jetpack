package com.wdx.kotlin.adapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/7 14:12
 */
public class MyViewModel extends ViewModel {

     MutableLiveData<PagingData<ItemData>> itemList ;


    public LiveData<PagingData<ItemData>> getItemData() {
        LiveData<PagingData<ItemData>> pagingDataLiveData = new MutableLiveData<>();

        return pagingDataLiveData;
    }


}
