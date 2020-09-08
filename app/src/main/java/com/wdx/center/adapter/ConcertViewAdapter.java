package com.wdx.center.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.wdx.center.R;
import java.util.ArrayList;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 15:15
 */
public class ConcertViewAdapter extends RecyclerView.Adapter<ConcertViewAdapter.VH> {


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Concert concert = getItem(position);
        if (concert != null) {
            holder.mTitleTextView.setText(concert.getTitle());
            holder.mAuthorTextView.setText(concert.getAuthor());
            holder.mContentTextView.setText(concert.getContent());
        }
    }

    private Concert getItem(int position) {
       return concerts.get(position);

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        if(concerts == null){
            return 0;
        }
        return concerts.size();
    }

    /*  */
   public ArrayList<Concert> concerts;
    public void submitList(ArrayList<Concert> concerts) {
        this.concerts=concerts;
        notifyDataSetChanged();
    }

    public static class VH extends RecyclerView.ViewHolder {

       public TextView mTitleTextView;
        public TextView mAuthorTextView;
        public TextView mContentTextView;


        public VH(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.title);
            mAuthorTextView = itemView.findViewById(R.id.author);
            mContentTextView = itemView.findViewById(R.id.content);
        }
    }

    private static DiffUtil.ItemCallback<Concert> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Concert>() {
                @Override
                public boolean areItemsTheSame(Concert oldConcert, Concert newConcert) {
                    return oldConcert.getTitle().equals(newConcert.getTitle());
                }

                @Override
                public boolean areContentsTheSame(Concert oldConcert,
                        Concert newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
}
