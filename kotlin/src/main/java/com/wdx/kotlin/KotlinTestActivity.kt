package com.wdx.kotlin

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.wdx.common.base.VBaseActivity
import com.wdx.kotlin.adapter.KotlinAdapter
import com.wdx.kotlin.adapter.MyViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest


/**
 *
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/4 11:32
 */
@Route(path = "/kotlin/kotlintestactivity")
class KotlinTestActivity : VBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)
        var tv_final = findViewById<TextView>(R.id.tv_final)
        var rcv_kotlin_test = findViewById<RecyclerView>(R.id.rcv_kotlin_test)


        var adapter = KotlinAdapter()


        Log.e("wdx", "********************************")

        tv_final.let {
            it.setOnClickListener{
                Toast.makeText(this, "kotlin测试关闭了", Toast.LENGTH_LONG).show()

                finish()
            }
        }
        tv_final.let {
           it. setOnTouchListener OnTouchListener@{ view, motionEvent ->
               when(motionEvent.action){
                   MotionEvent.ACTION_UP -> {
                   }
                   MotionEvent.ACTION_DOWN -> {
                   }
               }
               return@OnTouchListener true
           }
        }
    }


}