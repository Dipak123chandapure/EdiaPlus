package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.StudentDashboard.StudentsListAdapter;

import java.util.ArrayList;

public class GroupStudents extends Fragment implements StudentsListAdapter.OnGroupStudentCallback {
    RecyclerView student_list_recycler;
    ArrayList<StudentDTO> mList;
    StudentsListAdapter adpter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashbard_students, container, false);
        student_list_recycler = (RecyclerView) view.findViewById(R.id.student_list_recycler);
        setUpRecyclerView();
        Log.d("rohit", "oncreate is called");
        return view;
    }

    private void setUpRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mList = new BatchDAO(getActivity()).getStudentsForBatch(1L, 0);
        adpter = new StudentsListAdapter(getActivity(), mList);
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


    @Override
    public void studentClicked(int position) {
        if (null != mOnStudentClicked)
            mOnStudentClicked.studentClicked(mList.get(position));
    }

    @Override
    public void popupMenuClicked(MenuItem menuItem, StudentDTO dto) {

    }

    public void changeBatch(BatchDTO batch) {
        mList.clear();
        ArrayList<StudentDTO> list = new BatchDAO(getActivity()).getStudentsForBatch(batch.getId(), 0);
        mList.addAll(list);
        Log.d("rohit", "mList "+mList.size());
        adpter.notifyDataSetChanged();
    }

    OnStudentClicked mOnStudentClicked;
    public void setOnStudentClicked(OnStudentClicked mOnStudentClicked){
        this.mOnStudentClicked = mOnStudentClicked;
    }
    public interface OnStudentClicked{
         void studentClicked(StudentDTO sto);
    }
}
