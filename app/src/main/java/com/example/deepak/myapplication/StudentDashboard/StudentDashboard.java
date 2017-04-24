package com.example.deepak.myapplication.StudentDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.GroupDashboard.FragmentPageradapter;
import com.example.deepak.myapplication.GroupDashboard.StudentProfile;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class StudentDashboard extends Fragment implements View.OnClickListener, TextWatcher, ViewPager.OnPageChangeListener, StudentList.OnStudentSelected {
    ViewPager view_pager;

    ArrayList<StudentDTO> mList;
    String QUERY = null;
    RecyclerView recycler_view;

    ImageView search_icon, group_icon, filter_icon, floating_btn;
    EditText search_et;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_dashboard, container, false);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager_1);
        setUpViewPager();
        inItView(view);
        return view;
    }

    StudentProfile fragment3;
    StudentList fragment1;

    private void setUpViewPager() {
        FragmentPageradapter adapter = new FragmentPageradapter(getActivity().getSupportFragmentManager());
        fragment1 = new StudentList();
        fragment3 = new StudentProfile();
        fragment1.setOnStudentSelected(this);
        adapter.addFragment(fragment1, "Group Fragment");
        adapter.addFragment(fragment3, "Group_Student");
        view_pager.setOffscreenPageLimit(2);
        view_pager.setAdapter(adapter);

    }

    private void inItView(View view) {
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        floating_btn = (ImageView) view.findViewById(R.id.floating_btn);
        filter_icon = (ImageView) view.findViewById(R.id.filter_icon);
        group_icon = (ImageView) view.findViewById(R.id.group_icon);
        search_icon = (ImageView) view.findViewById(R.id.search_icon);
        search_et = (EditText) view.findViewById(R.id.search_et);

        filter_icon.setOnClickListener(fragment1);
        group_icon.setOnClickListener(fragment1);
        search_icon.setOnClickListener(fragment1);
        search_et.setOnClickListener(this);
        floating_btn.setOnClickListener(fragment1);

        view_pager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_et:
                Log.d("rohit", "Clicked");
                search_et.setCursorVisible(true);
                search_et.addTextChangedListener(this);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (search_et.getText().toString().length() < 1)
            QUERY = null;
        else
            QUERY = StudentDAO.FORM_1_ENTITY_3 + " LIKE '%" + search_et.getText().toString() + "%'";
        fragment1.afterTextChanged(QUERY);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 1) {
            filter_icon.setVisibility(View.GONE);
            group_icon.setVisibility(View.GONE);
            search_icon.setVisibility(View.GONE);
            search_et.setEnabled(false);
            search_et.setText("Student Profile");
        } else {
            filter_icon.setVisibility(View.VISIBLE);
            group_icon.setVisibility(View.VISIBLE);
            search_icon.setVisibility(View.VISIBLE);
            search_et.setEnabled(true);
            search_et.setText("");
            search_et.setHint("Search Student");
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStudentClicked(StudentDTO dto) {
        if (null != fragment3)
        fragment3.changeStudent(dto);

        view_pager.setCurrentItem(1);
    }
}
