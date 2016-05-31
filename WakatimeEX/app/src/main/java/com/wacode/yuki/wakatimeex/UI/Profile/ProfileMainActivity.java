package com.wacode.yuki.wakatimeex.UI.Profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wacode.yuki.wakatimeex.R;
import com.wacode.yuki.wakatimeex.UI.Profile.TransFormImage.RoundedTransformation;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/05/30.
 */
public class ProfileMainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);

        setViews();
        setViewsEndFromSyncGet();
    }

    private void setViews(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        LinearLayout linearLayout_follow = (LinearLayout)findViewById(R.id.linearLayout_follow);
        LinearLayout linearLayout_follower = (LinearLayout)findViewById(R.id.linearLayout_follower);

        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        linearLayout_follow.setOnClickListener(OnClick);
        linearLayout_follower.setOnClickListener(OnClick);
    }

    private View.OnClickListener OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.linearLayout_follow:
                    // TODO: 2016/05/30 intent followList
                    break;
                case R.id.linearLayout_follower:
                    // TODO: 2016/05/30  intent followerList
                    break;
                case R.id.linerLayout_moreTeam:
                    // TODO: 2016/05/30 intent AllTeamList
                    break;
            }
        }
    };

    private void setViewsEndFromSyncGet(){
        ImageView imageView_icon = (ImageView)findViewById(R.id.imageView_icon);
        TextView textView_userName = (TextView)findViewById(R.id.textView_name);
        TextView textView_location = (TextView)findViewById(R.id.textView_location);
        TextView textView_webSite = (TextView)findViewById(R.id.textView_webSite);
        TextView textView_follow = (TextView)findViewById(R.id.textView_follow);
        TextView textView_follower = (TextView)findViewById(R.id.textView_follower);
        LinearLayout linearLayout_moreTeams = (LinearLayout)findViewById(R.id.linerLayout_moreTeam);

//        setIconFromUrl(imageView_icon,url);
        textView_userName.setText("名前");
        textView_location.setText("場所");
        textView_webSite.setText("URL");
        textView_follow.setText("人");
        textView_follower.setText("人");

//        if (count > 6){
//            linearLayout_moreTeams.setVisibility(View.VISIBLE);
//            linearLayout_moreTeams.setOnClickListener(OnClick);
//        }
    }

    private void setIconFromUrl(ImageView imageView, String url){
        RoundedTransformation transformation = new RoundedTransformation(15,0);
        Picasso.with(ProfileMainActivity.this).load(url).error(R.mipmap.ic_launcher).networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE).fit().centerCrop().transform(transformation).rotate(90).into(imageView);
    }

    private void setTeamGridView(){
        GridView gridViewTeam = (GridView)findViewById(R.id.gridView_team);
        ProfileGridAdapter adapter = new ProfileGridAdapter(ProfileMainActivity.this,0,createTeamListFromApi());
        gridViewTeam.setAdapter(adapter);
        gridViewTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //// TODO: 2016/05/30 intent TeamDetail
            }
        });

    }

    private ArrayList<TeamData> createTeamListFromApi(){
        ArrayList<TeamData> list = new ArrayList<>();
        return list;
    }

    private Bitmap getIconFromByte(byte[] data){
        Bitmap icon = null;
        if (data != null){
            icon = BitmapFactory.decodeByteArray(data,0,data.length);
        }
        return icon;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = true;
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }
}