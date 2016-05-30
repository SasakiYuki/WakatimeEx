package com.wacode.yuki.wakatimeex.UI.BasicFeatures

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import butterknife.bindView
import com.wacode.yuki.wakatimeex.R

/**
 * Created by yuki on 2016/05/26.
 */
class BasicFeaturesLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun bindViews(position:Int):BasicFeaturesLayout{
        val v = layoutInflater.inflate(R.layout.layout_basic_main,null) as BasicFeaturesLayout
        setView(v,position)
        return v
    }

    private fun setView(v: View,position: Int){
        val wakaactivity = v.findViewById(R.id.wakaactivity) as WakaActivityView
        val daily = v.findViewById(R.id.daily) as DailyAverageView
        val editors = v.findViewById(R.id.editors) as EditorsView
        val languages = v.findViewById(R.id.languages) as LanguagesView

        when(position){
            0 -> wakaactivity.visibility = VISIBLE
            1 -> daily.visibility = VISIBLE
            2 -> editors.visibility =VISIBLE
            3 -> languages.visibility = VISIBLE
        }
    }

}