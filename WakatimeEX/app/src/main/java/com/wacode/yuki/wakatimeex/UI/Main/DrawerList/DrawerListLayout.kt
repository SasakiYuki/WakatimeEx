package com.wacode.yuki.wakatimeex.UI.Main.DrawerList

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.bindView
import com.wacode.yuki.wakatimeex.Entity.TopDrawerEntity
import com.wacode.yuki.wakatimeex.R

/**
 * Created by yuki on 2016/05/27.
 */
class DrawerListLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val imageView:ImageView by bindView(R.id.imageView)
    private val textView:TextView by bindView(R.id.textView)


    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun bindViews(topDrawerEntity: TopDrawerEntity,position:Int){
        if(position)
    }
}