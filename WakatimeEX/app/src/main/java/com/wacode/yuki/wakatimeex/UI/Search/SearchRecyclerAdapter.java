package com.wacode.yuki.wakatimeex.UI.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wacode.yuki.wakatimeex.Entity.SearchListEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/08.
 */
public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private ArrayList<SearchListEntity> mDataList;

    public SearchRecyclerAdapter(Context context, ArrayList<SearchListEntity> dataList){
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    @Override
    public SearchRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_search_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchListEntity item = mDataList.get(position);

        holder.textViewName.setText(item.getName());
        holder.linearLayout.setId(position);
        holder.linearLayout.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SearchListEntity item = mDataList.get(v.getId());
            int userId = item.getId();
            // TODO: 2016/06/06 intent userProfile

        }
    };


    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        LinearLayout linearLayout;

        public ViewHolder(View v){
            super(v);
            textViewName = (TextView)v.findViewById(R.id.textViewName);
            linearLayout = (LinearLayout)v.findViewById(R.id.linearLayoutRecycler);
        }
    }
}
