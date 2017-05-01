package com.example.deepak.myapplication.Settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.deepak.myapplication.Database.DAO.ParentDropDownDAO;
import com.example.deepak.myapplication.Database.DTO.ParentDropDownDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class SettingsFragment extends Fragment {

    RecyclerView drop_down_recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment_layout, container, false);
        drop_down_recycler = (RecyclerView) view.findViewById(R.id.drop_down_recycler);
        setUpRecycler();
        return view;
    }

    private void setUpRecycler() {
        ParentDropDownDAO parentDropDownDAO = new ParentDropDownDAO(getActivity());
        ArrayList<ParentDropDownDTO> list = parentDropDownDAO.getAllParentsDropDown();
        DropDownRecyclerAdapter adpter = new DropDownRecyclerAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        drop_down_recycler.setLayoutManager(manager);
        drop_down_recycler.setAdapter(adpter);
    }
}
