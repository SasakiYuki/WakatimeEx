package com.wacode.yuki.wakatimeex.UI.Search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Riberd on 2016/06/07.
 */
public class SearchViewPagerAdapter extends FragmentPagerAdapter{
    private String[] mTitle;
    public SearchViewPagerAdapter(FragmentManager fm, String title[]){
        super(fm);
        mTitle = title;
    }

    @Override
    public Fragment getItem(int position) {
        return SearchResultsFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
