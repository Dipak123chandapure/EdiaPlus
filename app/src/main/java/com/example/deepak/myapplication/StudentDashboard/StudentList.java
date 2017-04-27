package com.example.deepak.myapplication.StudentDashboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepak.myapplication.AddActivity.AddActivityDialog;
import com.example.deepak.myapplication.AddStudent.AddStudentFragment;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.GroupDashboard.GroupDashboard;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.Utility.Constant;

import java.util.ArrayList;

public class StudentList extends Fragment implements
        FilterFragment.OnFilterSeleted,
        View.OnClickListener, StudentsListAdapter.OnGroupStudentCallback {

    ArrayList<StudentDTO> mList;
    String QUERY = null;
    StudentsListAdapter adapter;
    RecyclerView recycler_view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_dashboard_list, container, false);
        inItView(view);
        mList = new ArrayList<>();
        setUpRecyclerView();
        return view;
    }

    private void inItView(View view) {
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    private void setUpRecyclerView() {
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        StudentDAO studentDAO = new StudentDAO(getActivity());
        mList = studentDAO.getStudentList(QUERY, 0);
        adapter = new StudentsListAdapter(getActivity(), mList);
        adapter.setOnGroupStudentCallback(this);
        recycler_view.setAdapter(adapter);
    }


    public void onMenuChildItemClicekd(int child_index, StudentDTO dto) {
        switch (child_index) {
            case 0:
                AddActivityDialog dialog = new AddActivityDialog(getActivity(), dto);
                dialog.show();
                break;
            case 1:


            case 3:
                break;
            case 4:
                break;
            case 5:
                if (null != mOnStudentSelected)
                    mOnStudentSelected.onStudentClicked(dto);
                break;

        }
    }

    @Override
    public void loadMore(int index) {
        Log.d("rohit", "Load more is called");
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> list = handler.getStudentList(QUERY, index);
        mList.addAll(list);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void studentClicked(int position) {
        if (null != mOnStudentSelected)
            mOnStudentSelected.onStudentClicked(mList.get(position));
    }

    @Override
    public void popupMenuClicked(MenuItem menuItem, StudentDTO dto) {
        switch (menuItem.getItemId()){
           case R.id.menu_sms:
               SMSDashboardFragment fragment = new SMSDashboardFragment();
               Bundle bundle = new Bundle();
               bundle.putString(Constant.SMS_TYPE, Constant.SMS_SINGE_CLIENT);
               ArrayList<StudentDTO> list = new ArrayList<>();
               list.add(dto);
               bundle.putParcelableArrayList(Constant.SMS_CLIENT_LIST, list);
               fragment.setArguments(bundle);
               getActivity().getSupportFragmentManager().beginTransaction().
                       setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                       .replace(R.id.main_frame_layout, fragment).commit();
               break;



            case R.id.menu_call:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + dto.getForm1Entity4()));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    getActivity().startActivity(intent);
                }

                break;
        }
    }

    public interface OnStudentSelected {
        void onStudentClicked(StudentDTO dto);
    }

    OnStudentSelected mOnStudentSelected;

    public void setOnStudentSelected(OnStudentSelected mOnStudentSelected) {
        this.mOnStudentSelected = mOnStudentSelected;
    }



    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.filter_icon:
                FilterFragment filterFragment = new FilterFragment();
                filterFragment.setOnFilterSeleted(this);
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .add(R.id.main_frame_layout, filterFragment).commit();
                break;

            case R.id.group_icon:
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new GroupDashboard()).commit();
                break;

        }

    }

    public void afterTextChanged(String QUERY) {
        this.QUERY = QUERY;
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> list = handler.getStudentList(QUERY, 0);
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onFilterSeleted(String QUERY) {
        this.QUERY = QUERY;
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> list = handler.getStudentList(QUERY, 0);
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

}
