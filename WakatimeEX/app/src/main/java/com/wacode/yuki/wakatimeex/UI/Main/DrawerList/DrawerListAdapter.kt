package com.wacode.yuki.wakatimeex.UI.Main.DrawerList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.wacode.yuki.wakatimeex.Entity.TopDrawerEntity

/**
 * Created by yuki on 2016/05/27.
 */
class DrawerListAdapter(context: Context?, resource: Int, objects: MutableList<TopDrawerEntity>?)
    : ArrayAdapter<TopDrawerEntity>(context, resource, objects) {

    private val layoutInflater: LayoutInflater
    private val resource:Int

    init {
        layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.resource = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = if(convertView == null){
            layoutInflater.inflate(resource,null) as DrawerListLayout
        }else{
            convertView as DrawerListLayout
        }

        view.bindViews(getItem(position),position)
        return view
    }
}