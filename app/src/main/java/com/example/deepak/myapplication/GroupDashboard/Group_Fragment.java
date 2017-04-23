package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class Group_Fragment extends Fragment {

    RecyclerView group_dashbard_fragment_recycler;
    GroupDashboardRecyclerAdapter adapter;
    ArrayList<BatchDTO> mList;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_fragment_layout, container, false);
        group_dashbard_fragment_recycler = (RecyclerView) view.findViewById(R.id.group_dashbard_fragment_recycler);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        group_dashbard_fragment_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        BatchDAO batchDAO = new BatchDAO(getActivity());
        mList = batchDAO.getBatchs();
        adapter = new GroupDashboardRecyclerAdapter(getActivity(), mList);
        group_dashbard_fragment_recycler.setAdapter(adapter);
    }
}
