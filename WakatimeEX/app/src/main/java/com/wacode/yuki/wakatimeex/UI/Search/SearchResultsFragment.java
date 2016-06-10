package com.wacode.yuki.wakatimeex.UI.Search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wacode.yuki.wakatimeex.Entity.SearchListEntity;
import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;

/**
 * Created by Riberd on 2016/06/07.
 */
public class SearchResultsFragment extends Fragment{
    private int mMode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMode = getArguments().getInt(SearchActivity.KEY_FRAGMENT_MODE);
        return inflater.inflate(R.layout.fragment_recyclerview,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViews();
    }

    public static SearchResultsFragment newInstance(int mode) {
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        args.putInt(SearchActivity.KEY_FRAGMENT_MODE, mode);
        fragment.setArguments(args);
        return fragment;
    }

    private void setViews(){
        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<SearchListEntity>  list = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            SearchListEntity item = new SearchListEntity();
            item.setId(i);
            item.setName(String.valueOf(i)+ "人目");
            list.add(item);
        }

        SearchRecyclerAdapter adapter = new SearchRecyclerAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
    }
}
