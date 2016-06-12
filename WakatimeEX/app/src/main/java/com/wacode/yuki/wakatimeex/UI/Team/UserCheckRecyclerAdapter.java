package com.wacode.yuki.wakatimeex.UI.Team;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wacode.yuki.wakatimeex.Entity.UserListEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/10.
 */
public class UserCheckRecyclerAdapter extends RecyclerView.Adapter<UserCheckRecyclerAdapter.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private ArrayList<UserListEntity> mDataList;
    private Context mContext;

    public UserCheckRecyclerAdapter(Context context, ArrayList<UserListEntity> dataList){
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public UserCheckRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_usercheck_recyclerview,null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final UserListEntity item = mDataList.get(position);
        Picasso.with(mContext).load(item.getUrl()).error(R.mipmap.ic_launcher).networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE).fit().centerCrop().into(holder.imageViewIcon);
        holder.textViewName.setText(item.getName());
        holder.checkBox.setId(position);
        holder.checkBox.setChecked(item.isValidCheck());
        holder.checkBox.setOnCheckedChangeListener(check);
        holder.relativeLayout.setId(position);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserListEntity item = mDataList.get(v.getId());
                if (item.isValidCheck()){
                    item.setValidCheck(false);
                    holder.checkBox.setChecked(false);
                }else {
                    item.setValidCheck(true);
                    holder.checkBox.setChecked(true);
                }
            }
        });
    }

    private CheckBox.OnCheckedChangeListener check = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            UserListEntity item = mDataList.get(buttonView.getId());
            item.setValidCheck(isChecked);
        }
    };

    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewIcon;
        TextView textViewName;
        CheckBox checkBox;
        RelativeLayout relativeLayout;
        public ViewHolder(View view){
            super(view);
            imageViewIcon = (ImageView) view.findViewById(R.id.imageViewIcon);
            textViewName = (TextView)view.findViewById(R.id.textViewName);
            checkBox = (CheckBox)view.findViewById(R.id.checkbox);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        }
    }
}
