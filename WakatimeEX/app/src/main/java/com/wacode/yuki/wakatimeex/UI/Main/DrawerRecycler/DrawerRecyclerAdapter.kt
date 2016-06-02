package com.wacode.yuki.wakatimeex.UI.Main.DrawerRecycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wacode.yuki.wakatimeex.Entity.TopDrawerEntity
import java.util.*

/**
 * Created by yuki on 2016/06/02.
 */
class DrawerRecyclerAdapter(val context: Context, private val resource:Int, private val objects: ArrayList<TopDrawerEntity>) : RecyclerView.Adapter<DrawerRecyclerAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int, payloads: MutableList<Any>?) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount() = objects.size

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int)  = ViewHolder(layoutInflater.inflate(resource,p0,false))


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {
        }
    }
}
