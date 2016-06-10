package com.wacode.yuki.wakatimeex.UI.Team;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wacode.yuki.wakatimeex.Entity.RankingEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/02.
 */
public class RankingFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews();
    }

    private void setViews(){
        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<RankingEntity> list = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            RankingEntity item = new RankingEntity();
            item.setName(String.valueOf(i)+"人目");
            item.setRank(i);
            item.setTime("時間");
            item.setUserId(i);
            item.setBackground(setRankerIcon(i));
            list.add(item);
        }
        RankingRecyclerAdapter adapter = new RankingRecyclerAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
    }

    private ShapeDrawable setRankerIcon(int position){
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        switch (position){
            case 0:
                shapeDrawable.getPaint().setColor(getResources().getColor(R.color.colorGold));
                break;
            case 1:
                shapeDrawable.getPaint().setColor(getResources().getColor(R.color.colorSilver));
                break;
            case 2:
                shapeDrawable.getPaint().setColor(getResources().getColor(R.color.colorBronze));
                break;
            default:
                shapeDrawable = null;
        }
        return shapeDrawable;
    }
}
