package com.wdx.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.wdx.center.adapter.Concert;
import com.wdx.model.repository.BaseViewModel;
import java.util.ArrayList;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 15:14
 */
public class ConcertViewModel extends BaseViewModel {


    public  MutableLiveData<ArrayList<Concert>> convertList;
    private DataSource<Integer, Concert> concertDataSource;

    public ConcertViewModel() {
        convertList = new MutableLiveData() ;
        ArrayList pagedlist=new ArrayList();
        pagedlist.add(new Concert());
        pagedlist.add(new Concert());
        pagedlist.add(new Concert());
        convertList.setValue(pagedlist);

    }


    public LiveData<ArrayList<Concert>> getConvertList() {
        return convertList;
    }
}
