package com.wacode.yuki.wakatimeex.UI.FollowRanking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wacode.yuki.wakatimeex.R;
import com.wacode.yuki.wakatimeex.UI.Team.RankingFragment;

/**
 * Created by Riberd on 2016/06/07.
 */
public class FollowRankingActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_ranking);

        setViews();
    }

    private void setViews(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RankingFragment fragment = new RankingFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.linearLayoutFollowRanking,fragment);
        transaction.commit();
    }

    @Override
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
