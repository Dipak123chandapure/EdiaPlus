package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;

public class Group_Details_Fragmet extends Fragment implements GroupStudentsAdapter.OnGroupStudentCallback {
    RecyclerView student_list_recycler;
    ArrayList<StudentDTO> mList;
    GroupStudentsAdapter adpter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_details_layout, container, false);
        student_list_recycler = (RecyclerView) view.findViewById(R.id.student_list_recycler);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mList = new StudentDAO(getActivity()).getStudentList(null, 0);
        adpter = new GroupStudentsAdapter(getActivity(), mList);
        adpter.setOnGroupStudentCallback(this);
        student_list_recycler.setLayoutManager(manager);
        student_list_recycler.setAdapter(adpter);
    }

    @Override
    public void loadMore(int index) {
        Log.d("rohit", "Load more is called");
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> list = handler.getStudentList(null, index);
        mList.addAll(list);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adpter.notifyDataSetChanged();
            }
        });
    }
}
