package com.wdx.center.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/2 10:57
 */
class MyListAdapter extends PagedListAdapter {



    MyItemCallBack myItemCallBack;
    public MyItemCallBack getCallBack(){
        if(myItemCallBack == null){
            myItemCallBack=new MyItemCallBack();
        }
        return new MyItemCallBack();
    }
    protected MyListAdapter(@NonNull ItemCallback diffCallback) {
        super(diffCallback);
    }

    protected MyListAdapter(@NonNull AsyncDifferConfig config) {
        super(config);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    public class MyItemCallBack extends DiffUtil.ItemCallback{

        @Override
        public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
            return false;
        }
    }
}
