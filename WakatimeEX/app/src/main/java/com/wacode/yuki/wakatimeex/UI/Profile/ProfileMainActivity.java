package com.wacode.yuki.wakatimeex.UI.Profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.wacode.yuki.wakatimeex.Entity.TeamDataEntity;
import com.wacode.yuki.wakatimeex.R;
import com.wacode.yuki.wakatimeex.UI.Profile.TransFormImage.RoundedTransformation;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/05/30.
 */
public class ProfileMainActivity extends AppCompatActivity{
    private Boolean mIsValidButton;
    public static final String KEY_INTENT_MODE = "key_intent_mode";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        mIsValidButton = false;
        setViews();
    }

    private void setViews(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        LinearLayout linearLayout_follow = (LinearLayout)findViewById(R.id.linearLayout_follow);
        LinearLayout linearLayout_follower = (LinearLayout)findViewById(R.id.linearLayout_follower);
        ImageView imageViewIcon = (ImageView)findViewById(R.id.imageView_icon);
        TextView textViewUserName = (TextView)findViewById(R.id.textView_name);
        TextView textViewLocation = (TextView)findViewById(R.id.textView_location);
        TextView textViewWebSite = (TextView)findViewById(R.id.textView_webSite);
        TextView textViewFollow = (TextView)findViewById(R.id.textView_follow);
        TextView textViewFollower = (TextView)findViewById(R.id.textView_follower);
        LinearLayout linearLayoutMoreTeams = (LinearLayout)findViewById(R.id.linerLayout_moreTeam);
        LinearLayout linearLayoutFollowButton = (LinearLayout)findViewById(R.id.linearLayoutFollow);


        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        linearLayout_follow.setOnClickListener(OnClick);
        linearLayout_follower.setOnClickListener(OnClick);
        linearLayoutFollowButton.setOnClickListener(OnClick);

        //        setIconFromUrl(imageViewIcon,url);
        textViewUserName.setText("名前");
        textViewLocation.setText("場所");
        textViewWebSite.setText("URL");
        textViewFollow.setText("人");
        textViewFollower.setText("人");

//        if (count > 6){
//            linearLayout_moreTeams.setVisibility(View.VISIBLE);
//            linearLayout_moreTeams.setOnClickListener(OnClick);
//        }
    }

    private View.OnClickListener OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent ;
            switch (v.getId()){
                case R.id.linearLayout_follow:
                    intent = new Intent(ProfileMainActivity.this,FriendListActivity.class) ;
                    intent.putExtra(KEY_INTENT_MODE,0);
                    startActivity(intent);
                    break;
                case R.id.linearLayout_follower:
                    intent = new Intent(ProfileMainActivity.this,FriendListActivity.class) ;
                    intent.putExtra(KEY_INTENT_MODE,1);
                    startActivity(intent);
                    break;
                case R.id.linerLayout_moreTeam:
                    // TODO: 2016/05/30 intent AllTeamList
                    break;
                case R.id.linearLayoutFollow:
                    changeFollowButton();
                    break;
            }
        }
    };

    private void setIconFromUrl(ImageView imageView, String url){
        RoundedTransformation transformation = new RoundedTransformation(15,0);
        Picasso.with(ProfileMainActivity.this).load(url).error(R.mipmap.ic_launcher).networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE).fit().centerCrop().transform(transformation).into(imageView);
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

    private void changeFollowButton(){
        final LinearLayout linearLayoutFollow = (LinearLayout)findViewById(R.id.linearLayoutFollow);
        final TextView textViewFollow = (TextView)findViewById(R.id.textViewFollow);
        final ImageView imageViewFollow = (ImageView)findViewById(R.id.imageViewFollow);

        if (!mIsValidButton){
            linearLayoutFollow.setBackground(getResources().getDrawable(R.drawable.item_followed_icon));
            textViewFollow.setText(R.string.profile_followed);
            imageViewFollow.setImageResource(R.mipmap.ic_done_white_48dp);
            mIsValidButton = true;
        }else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileMainActivity.this);
            dialog.setMessage(getResources().getText(R.string.profile_follow_message));
            dialog.setNegativeButton("Cancel",null);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    linearLayoutFollow.setBackground(getResources().getDrawable(R.drawable.item_following_icon));
                    textViewFollow.setText(R.string.profile_following);
                    imageViewFollow.setImageResource(R.mipmap.ic_person_add_white_48dp);
                    mIsValidButton = false;
                }
            });
            dialog.show();
        }
    }

    private ArrayList<TeamDataEntity> createTeamListFromApi(){
        ArrayList<TeamDataEntity> list = new ArrayList<>();
        return list;
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

    @Override
    public void onBackPressed() {
        finish();
    }
}
