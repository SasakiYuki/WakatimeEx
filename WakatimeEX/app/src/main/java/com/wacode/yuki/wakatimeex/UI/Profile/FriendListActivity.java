package com.wacode.yuki.wakatimeex.UI.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wacode.yuki.wakatimeex.Entity.UserListEntity;
import com.wacode.yuki.wakatimeex.R;
import com.wacode.yuki.wakatimeex.UI.Team.UserRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/10.
 */
public class FriendListActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setViews();
        setRecyclerView();
    }

    private void setViews(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        int mode = getIntent().getIntExtra(ProfileMainActivity.KEY_INTENT_MODE,0) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switch (mode){
            case 0:
                getSupportActionBar().setTitle(R.string.profile_follow);
                break;
            case 1:
                getSupportActionBar().setTitle(R.string.profile_follower);
                break;
        }
    }

    private void setRecyclerView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(FriendListActivity.this));
        ArrayList<UserListEntity> list = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            UserListEntity item = new UserListEntity();
            item.setName(String.valueOf(i) + "人目");
            item.setUrl(String.valueOf(i));
            item.setId(i);
            list.add(item);
        }
        UserRecyclerAdapter adapter = new UserRecyclerAdapter(FriendListActivity.this,list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
