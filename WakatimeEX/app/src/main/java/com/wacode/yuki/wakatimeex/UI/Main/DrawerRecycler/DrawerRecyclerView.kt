package com.wacode.yuki.wakatimeex.UI.Main.DrawerRecycler

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.wacode.yuki.wakatimeex.Entity.TopDrawerEntity
import com.wacode.yuki.wakatimeex.R
import java.util.*

/**
 * Created by yuki on 2016/06/02.
 */
class DrawerRecyclerView(context: Context?, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setAdapters(array:ArrayList<TopDrawerEntity>){
        layoutManager = LinearLayoutManager(context)
        val adapter = DrawerRecyclerAdapter(context, R.layout.layout_main_recycler,array)
        setAdapter(adapter)
    }
}