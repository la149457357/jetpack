package com.wdx.kotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wdx.kotlin.R

/**
 *
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/7 11:26
 */
open class KotlinAdapter : PagingDataAdapter<ItemData, ArticleViewHolder>(POST_COMPARATOR){

    companion object{
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<ItemData>() {
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean =
                    oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.tvName.text = getItem(position)?.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

}
open class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tvName: TextView = itemView.findViewById(R.id.tvname)
}