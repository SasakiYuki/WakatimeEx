package com.wacode.yuki.wakatimeex.UI.Team;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wacode.yuki.wakatimeex.R;

/**
 * Created by Riberd on 2016/06/02.
 */
public class TeamMainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private static final int MENU_WITHDRAWAL = 0;
    private static final int MENU_APPROVAL = 1;
    private static final int MENU_KICK = 2;
    private static final int MENU_DISPERSION = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        setViews();
    }

    private void setViews() {
        String[] title = {"Ranking", "Detail"};
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewPagerTeam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TeamViewPagerAdapter adapter = new TeamViewPagerAdapter(getSupportFragmentManager(), title);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(select);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_WITHDRAWAL, 0, getResources().getText(R.string.teamDetail_menu_withdrawal));
        menu.add(0, MENU_APPROVAL, 0, getResources().getText(R.string.teamDetail_menu_approval));
        menu.add(0, MENU_KICK, 0, getResources().getText(R.string.teamDetail_menu_kick));
        menu.add(0, MENU_DISPERSION, 0, getResources().getText(R.string.teamDetail_menu_dispersion));

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        boolean result = true;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case MENU_WITHDRAWAL:
                setExitTeamDialog();
                break;
            case MENU_APPROVAL:
                intent = new Intent(TeamMainActivity.this,ApproveUserActivity.class);
                startActivity(intent);
                break;
            case MENU_KICK:
                intent = new Intent(TeamMainActivity.this,DeselectUserActivity.class);
                startActivity(intent);
                break;
            case MENU_DISPERSION:
                setDisbandTeamDialog();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    private void setExitTeamDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(TeamMainActivity.this);
        dialog.setMessage(R.string.teamDetail_dialog_withdrawal);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //exit team
            }
        });
        dialog.setNegativeButton("Cancel",null);
        dialog.show();
    }

    private void setDisbandTeamDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(TeamMainActivity.this);
        dialog.setMessage(R.string.teamDetail_dialog_dispersion);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //disband team
            }
        });
        dialog.setNegativeButton("Cancel",null);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
