package com.wdx.model.repository;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.DataSource.KeyType;
import com.wdx.center.adapter.Concert;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 15:35
 */
public class ConcertFactory extends DataSource.Factory<Integer, Concert> {

    private MutableLiveData<ConcertDataSource> mSourceLiveData =
            new MutableLiveData<>();

    @Override
    public DataSource<Integer, Concert> create() {
        ConcertDataSource concertDataSource = new ConcertDataSource(KeyType.PAGE_KEYED);
        mSourceLiveData.postValue(concertDataSource);
        return concertDataSource;
    }
}
