package com.wacode.yuki.wakatimeex.UI.CreateTeam;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.wacode.yuki.wakatimeex.R;

import java.util.List;

/**
 * Created by Riberd on 2016/06/01.
 */
public class TeamIconGridAdapter extends ArrayAdapter<String>{
    private LayoutInflater mLayoutInflater;

    public TeamIconGridAdapter(Context context, int resource, List<String> lists) {
        super(context, resource,lists);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position);
        convertView = mLayoutInflater.inflate(R.layout.item_colorpick_dialog,null);
        LinearLayout view = (LinearLayout) convertView.findViewById(R.id.view_color);

        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(Color.parseColor(item));
        view.setBackground(shapeDrawable);

        return convertView;
    }
}
