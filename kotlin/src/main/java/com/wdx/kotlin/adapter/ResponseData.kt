package com.wdx.kotlin.adapter

/**
 *
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/7 11:42
 */
internal class ResponseData {
    var data : DataInfo?= DataInfo()

    class DataInfo {
        var curPage =1
        var pageCount =5
        var datas:ArrayList<ItemData> = listOf<ItemData>(ItemData(), ItemData()) as ArrayList<ItemData>
    }
}