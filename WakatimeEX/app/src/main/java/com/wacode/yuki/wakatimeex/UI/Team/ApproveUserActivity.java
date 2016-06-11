package com.wacode.yuki.wakatimeex.UI.Team;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.wacode.yuki.wakatimeex.Entity.UserListEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/10.
 */
public class ApproveUserActivity extends AppCompatActivity{
    private ArrayList<UserListEntity> mCheckList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercheck_list);

        setViews();
        setRecyclerView();
    }

    private void setViews(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        Button buttonSubmit = (Button)findViewById(R.id.buttonSubmit);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonSubmit.setOnClickListener(onClick);
    }

    private void setRecyclerView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ApproveUserActivity.this));
        mCheckList = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            UserListEntity item = new UserListEntity();
            item.setId(i);
            item.setName(String.valueOf(i) + "人目");
            item.setUrl(String.valueOf(i));
            item.setValidCheck(false);
            mCheckList.add(item);
        }
        UserCheckRecyclerAdapter adapter = new UserCheckRecyclerAdapter(ApproveUserActivity.this, mCheckList);
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < mCheckList.size(); i++){
                if (mCheckList.get(i).isValidCheck()){
                    // TODO: 2016/06/11 add userID
                }
            }
            setApproveUserDialog();
        }
    };

    private void setApproveUserDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ApproveUserActivity.this);
        dialog.setMessage(getResources().getString(R.string.approve_dialog_message));
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: 2016/06/11 approve user
            }
        });
        dialog.setNegativeButton("Cancel",null);
        dialog.show();
    }

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
