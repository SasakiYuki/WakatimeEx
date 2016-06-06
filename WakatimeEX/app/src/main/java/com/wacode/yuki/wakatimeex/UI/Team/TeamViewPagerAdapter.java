package com.wacode.yuki.wakatimeex.UI.Team;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Riberd on 2016/06/02.
 */
public class TeamViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitle;
    public TeamViewPagerAdapter(FragmentManager fm, String title[] ){
        super(fm);mTitle = title;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RankingFragment();
            case 1:
                return new TeamDetailFragment();
            default:
                return null;
        }
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
