package com.wacode.yuki.wakatimeex.UI.Main

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableLayout
import butterknife.bindView
import com.wacode.yuki.wakatimeex.R
import com.wacode.yuki.wakatimeex.UI.BasicFeatures.BasicFeaturesLayout
import com.wacode.yuki.wakatimeex.UI.Main.ViewPager.MainPagerAdapter
import java.util.*

/**
 * Created by yuki on 2016/05/26.
 */
class TopViewPagerLayout(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private val viewPager: ViewPager by bindView(R.id.viewPager)
    private val tablayout: TabLayout by bindView(R.id.tablayout)


    override fun onFinishInflate() {
        super.onFinishInflate()
        setViewPager()
    }

    private fun setViewPager(){
        val view:BasicFeaturesLayout = BasicFeaturesLayout(context,null)
        var list:ArrayList<BasicFeaturesLayout> = ArrayList()
        for(i in 0..3){
            list.add(view.bindViews(i))
        }

        viewPager.adapter = MainPagerAdapter(context,list)
        tablayout.setupWithViewPager(viewPager)
    }
}