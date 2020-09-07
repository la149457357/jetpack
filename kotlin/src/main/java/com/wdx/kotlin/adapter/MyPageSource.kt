package com.wdx.kotlin.adapter

import androidx.paging.PagingSource

/**
 *
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/7 11:38
 */
class ArticleDataSource:PagingSource<Int,ItemData>() {

    /**
     * 实现这个方法来触发异步加载(例如从数据库或网络)。 这是一个suspend挂起函数，可以很方便的使用协程异步加载
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemData> {

        return try {
            val page = params.key?:0
            //获取网络数据
            val result = ResponseData();
            LoadResult.Page(
                    //需要加载的数据
                    data = result.data!!.datas,
                    //如果可以往上加载更多就设置该参数，否则不设置
                    prevKey = null,
                    //加载下一页的key 如果传null就说明到底了
                    nextKey = if(result.data!!.curPage== result.data!!.pageCount) null else page+1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }
}


