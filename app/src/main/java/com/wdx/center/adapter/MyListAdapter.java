package com.wdx.center.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/2 10:57
 */
public class MyListAdapter extends PagingDataAdapter {


    public MyListAdapter(
            @NotNull ItemCallback diffCallback,
            @NotNull CoroutineDispatcher mainDispatcher) {
        super(diffCallback, mainDispatcher);
    }

    public MyListAdapter(@NotNull ItemCallback diffCallback) {
        super(diffCallback);
    }

    public MyListAdapter(@NotNull ItemCallback diffCallback,
            @NotNull CoroutineDispatcher mainDispatcher,
            @NotNull CoroutineDispatcher workerDispatcher) {
        super(diffCallback, mainDispatcher, workerDispatcher);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }
}
