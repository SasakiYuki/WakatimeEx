package com.wacode.yuki.wakatimeex.UI.Search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wacode.yuki.wakatimeex.R;

/**
 * Created by Riberd on 2016/06/07.
 */
public class SearchActivity extends AppCompatActivity{
    private SearchView mSearchView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private boolean mIsValidSearch;
    public static final String KEY_FRAGMENT_MODE = "key_fragment_mode";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mIsValidSearch = false;
        setViews();
    }

    private void setViews(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_serchview,menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        mSearchView.setIconified(false); //show Always
        mSearchView.setQueryHint(getResources().getText(R.string.search_view_hint));
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setIconifiedByDefault(false); //don't show xbtn
        mSearchView.setOnQueryTextListener(textQuery);
        return true;
    }

    private SearchView.OnQueryTextListener textQuery = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            setViewsToFinish();
            mIsValidSearch = true;
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (mIsValidSearch){
                mViewPager.setVisibility(View.GONE);
                mTabLayout.setVisibility(View.GONE);
                mIsValidSearch = false;
            }
             return false;
        }
    };

    private void setViewsToFinish(){
        String[] title = {"ユーザー("+String.valueOf(10) + ")","グループ(" + String.valueOf(3) + ")"};
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mViewPager = (ViewPager)findViewById(R.id.viewPagerSearch);

        SearchViewPagerAdapter adapter = new SearchViewPagerAdapter(getSupportFragmentManager(),title);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setOnTabSelectedListener(select);

        mViewPager.setVisibility(View.VISIBLE);
        mTabLayout.setVisibility(View.VISIBLE);
    }

    private TabLayout.OnTabSelectedListener select = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    public boolean onOptionsItemSelected(MenuItem item) {
        boolean results = true;
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                results = super.onOptionsItemSelected(item);
        }
        return results;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
