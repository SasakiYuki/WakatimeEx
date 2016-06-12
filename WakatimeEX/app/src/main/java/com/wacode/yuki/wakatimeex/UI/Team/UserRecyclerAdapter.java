package com.wacode.yuki.wakatimeex.UI.Team;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wacode.yuki.wakatimeex.Entity.UserListEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/06.
 */
public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private ArrayList<UserListEntity> mDataList;
    private Context mContext;

    public UserRecyclerAdapter(Context context, ArrayList<UserListEntity> dataList){
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_user_recyclerview,null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(UserRecyclerAdapter.ViewHolder holder, int position) {
        UserListEntity item = mDataList.get(position);

        Picasso.with(mContext).load(item.getUrl()).error(R.mipmap.ic_launcher).networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE).fit().centerCrop().into(holder.imageViewIcon);
        holder.textViewName.setText(item.getName());
        holder.linearLayoutUser.setOnClickListener(onClick);
        holder.linearLayoutUser.setId(position);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: 2016/06/10 intent userProfile
        }
    };



    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewIcon;
        TextView textViewName;
        LinearLayout linearLayoutUser;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewIcon = (ImageView)itemView.findViewById(R.id.imageViewIcon);
            textViewName = (TextView)itemView.findViewById(R.id.textViewName);
            linearLayoutUser = (LinearLayout)itemView.findViewById(R.id.linearLayoutUser);
        }
    }
}
