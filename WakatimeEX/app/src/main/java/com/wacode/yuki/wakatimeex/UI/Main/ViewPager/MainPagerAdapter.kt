package com.wacode.yuki.wakatimeex.UI.Main.ViewPager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.wacode.yuki.wakatimeex.Enum.BasicFeatures
import com.wacode.yuki.wakatimeex.UI.BasicFeatures.BasicFeaturesLayout
import java.util.*

/**
 * Created by yuki on 2016/05/26.
 */
class MainPagerAdapter(val context:Context,val list:ArrayList<BasicFeaturesLayout>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any? {
        container.addView(list[position])
        return list[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
        container.removeView(`object` as BasicFeaturesLayout)
    }

    override fun isViewFromObject(p0: View?, p1: Any?): Boolean {
        return p0 == p1 as BasicFeaturesLayout
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(BasicFeatures.values()[position].titleResource)
    }
}

