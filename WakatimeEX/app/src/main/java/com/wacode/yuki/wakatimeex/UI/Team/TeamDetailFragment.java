package com.wacode.yuki.wakatimeex.UI.Team;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wacode.yuki.wakatimeex.Entity.RankingEntity;
import com.wacode.yuki.wakatimeex.Entity.UserListEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/02.
 */
public class TeamDetailFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_detail,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews();
        setRecyclerView();
    }

    private void setViews(){
        TextView textViewTeamName = (TextView)getView().findViewById(R.id.textViewTeamName);
        TextView textViewTeamIcon = (TextView)getView().findViewById(R.id.textViewTeamIcon);
        TextView textViewOwner = (TextView)getView().findViewById(R.id.textViewOwnerName);
        LinearLayout linearLayoutOwner = (LinearLayout)getView().findViewById(R.id.linerLayoutOwnerName);
        TextView textViewDescription = (TextView)getView().findViewById(R.id.textViewDescription);

        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(getResources().getColor(R.color.colorGreen));

        textViewTeamName.setText("SmartAppProject");

        textViewTeamIcon.setBackground(shapeDrawable);
        textViewTeamIcon.setText("S");

        textViewOwner.setText("榛葉");
        linearLayoutOwner.setOnClickListener(onClick);

        textViewDescription.setText("aaaaaaaaaaaaaaaaaaaaaaaaaa");

    }

    private void setRecyclerView(){
        TextView textViewCount = (TextView)getView().findViewById(R.id.textViewMemberCount);
        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerViewMembers);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<UserListEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            UserListEntity item = new UserListEntity();
            item.setName("Name"+ String.valueOf(i));
            item.setId(i);
            item.setUrl(String.valueOf(i));
            list.add(item);
        }

        UserRecyclerAdapter adapter = new UserRecyclerAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

        textViewCount.setText("20人");
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: 2016/06/06 intent ownerProfile
        }
    };
}
