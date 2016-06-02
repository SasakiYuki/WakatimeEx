package com.wacode.yuki.wakatimeex.UI.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wacode.yuki.wakatimeex.Entity.TeamDataEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.List;

/**
 * Created by Riberd on 2016/05/30.
 */
public class ProfileGridAdapter extends ArrayAdapter<TeamDataEntity>{
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public ProfileGridAdapter(Context context, int resource, List<TeamDataEntity> objects) {
        super(context, resource,objects);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeamDataEntity item = getItem(position);
        convertView = mLayoutInflater.inflate(R.layout.item_profile_team,null);
        ImageView imageView_icon = (ImageView)convertView.findViewById(R.id.imageView_teamIcon);
        TextView textView_name = (TextView)convertView.findViewById(R.id.textView_teamName);
        TextView textView_status = (TextView)convertView.findViewById(R.id.textView_teamStatus);

        imageView_icon.setImageBitmap(item.getIcon());
        textView_name.setText(item.getName());
        textView_status.setText(getTeamStatus(item.getStatus()));

        return convertView;
    }

    private String getTeamStatus(int position){
        switch (position){
            case 0:
                return "創始者";
            case 1:
                return "リーダー";
            case 2:
                return "メンバー";
            default:
                return "メンバー";
        }
    }

}
