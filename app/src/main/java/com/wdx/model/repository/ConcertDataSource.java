package com.wdx.model.repository;

import androidx.paging.DataSource;
import com.wdx.center.adapter.Concert;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 15:36
 */
class ConcertDataSource extends DataSource<Integer, Concert> {

    public ConcertDataSource(@NotNull KeyType type) {
        super(type);
    }

    @NotNull
    @Override
    public Integer getKeyInternal$paging_common(@NotNull Concert concert) {
        return null;
    }


    @Nullable
    @Override
    public Object load$paging_common(@NotNull Params<Integer> params,
            @NotNull Continuation<? super BaseResult<Concert>> continuation) {
        return null;
    }
}
