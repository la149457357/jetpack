package com.wdx.center.datasource;

import androidx.annotation.NonNull;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingSource.LoadParams;
import androidx.paging.PagingSource.LoadResult;
import com.google.common.util.concurrent.ListenableFuture;
import com.wdx.model.info.UserInfo;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import org.jetbrains.annotations.NotNull;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/3 11:12
 */
class MyDataSource extends ListenableFuturePagingSource<Integer, UserInfo> {
        @NonNull
        private String mQuery;
        @NonNull
        private Executor mBgExecutor;

    MyDataSource(
                @NonNull String query, @NonNull Executor bgExecutor) {
            mQuery = query;
            mBgExecutor = bgExecutor;
        }

        @NotNull
        public ListenableFuture<LoadResult<Integer, UserInfo>> loadFuture(
                @NotNull LoadParams<Integer> params) {
            // Start refresh at page 1 if undefined.
            Integer nextPageNumber = params.getKey();
            if (nextPageNumber == null) {
                nextPageNumber = 1;
            }


            ListenableFuture<LoadResult<Integer, UserInfo>> pageFuture = Futures.transform(
                    mBackend.searchUsers(mQuery, nextPageNumber),
                    this::toLoadResult, mBgExecutor);

            ListenableFuture<LoadResult<Integer, UserInfo>> partialLoadResultFuture = Futures.catching(
                    pageFuture, HttpException.class,
                    LoadResult.Error::new, mBgExecutor);

            return Futures.catching(partialLoadResultFuture,
                    IOException.class, LoadResult.Error::new, mBgExecutor);
        }

        private LoadResult<Integer, UserInfo> toLoadResult(
                @NonNull SearchUsersResponse response) {
            return new LoadResult.Page<Integer, UserInfo>(
                    response.getUsers(),
                    null, // Only paging forward.
                    response.getNextPageNumber(),
                    LoadResult.Page.COUNT_UNDEFINED,
                    LoadResult.Page.COUNT_UNDEFINED);
        }
}
