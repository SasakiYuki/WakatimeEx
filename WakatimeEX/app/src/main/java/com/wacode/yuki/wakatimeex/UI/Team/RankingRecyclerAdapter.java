package com.wacode.yuki.wakatimeex.UI.Team;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wacode.yuki.wakatimeex.Entity.RankingEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/03.
 */
public class RankingRecyclerAdapter extends RecyclerView.Adapter<RankingRecyclerAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private ArrayList<RankingEntity> mDataList;
    private Context mContext;

    public RankingRecyclerAdapter(Context context, ArrayList<RankingEntity> dataList) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
        mContext = context;
    }

    @Override
    public RankingRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_ranking_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RankingEntity item =mDataList.get(position);

        holder.textViewRank.setText(String.valueOf(item.getRank() +1));
        if (position < 3){
            holder.textViewRank.setBackground(setRankerIcon(position));
        }
        holder.textViewName.setText(item.getName());
        holder.textViewTime.setText(item.getTime());
        holder.linearLayout.setOnClickListener(click);
        holder.linearLayout.setId(position);
    }

    private ShapeDrawable setRankerIcon(int position){
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        switch (position){
            case 0:
                shapeDrawable.getPaint().setColor(mContext.getResources().getColor(R.color.colorGold));
                break;
            case 1:
                shapeDrawable.getPaint().setColor(mContext.getResources().getColor(R.color.colorSilver));
                break;
            case 2:
                shapeDrawable.getPaint().setColor(mContext.getResources().getColor(R.color.colorBronze));
                break;
        }
        return shapeDrawable;
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RankingEntity item = mDataList.get(v.getId());
            int userId = item.getUserId();
            // TODO: 2016/06/06 intent userProfile
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRank;
        TextView textViewName;
        TextView textViewTime;
        LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            textViewRank = (TextView)v.findViewById(R.id.textViewRank);
            textViewName = (TextView)v.findViewById(R.id.textViewName);
            textViewTime = (TextView)v.findViewById(R.id.textViewTime);
            linearLayout = (LinearLayout)v.findViewById(R.id.linearLayout);
        }
    }
}
