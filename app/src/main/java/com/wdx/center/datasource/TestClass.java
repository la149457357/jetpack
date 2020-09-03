package com.wdx.center.datasource;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/3 14:29
 */
class TestClass implements Runnable, Future {

    @Override
    public void run() {

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public Object get(long timeout, TimeUnit unit)
            throws ExecutionException, InterruptedException, TimeoutException {
        return null;
    }
}
