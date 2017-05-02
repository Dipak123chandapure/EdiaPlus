package com.example.deepak.myapplication.StudentDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.deepak.myapplication.AddStudent.AddStudentFragment;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.GroupDashboard.CustomFragmentPageradapter;
import com.example.deepak.myapplication.GroupDashboard.StudentProfile;
import com.example.deepak.myapplication.R;


public class StudentDashboard extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    ViewPager view_pager;
    ImageView search_icon,  filter_icon, floating_btn;
    EditText search_et;
    StudentProfile StudentProfile;
    StudentList studnetList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.student_dashboard, container, false);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager_1);
        setUpViewPager();
        inItView(view);
        return view;
    }


    private void setUpViewPager() {
        CustomFragmentPageradapter adapter = new CustomFragmentPageradapter(getActivity().getSupportFragmentManager());
        studnetList = new StudentList();
        StudentProfile = new StudentProfile();
        studnetList.setOnStudentSelected(StudentProfile, view_pager);
        adapter.addFragment(studnetList, "Group Fragment");
        adapter.addFragment(StudentProfile, "Group_Student");
        view_pager.setOffscreenPageLimit(1);
        view_pager.setAdapter(adapter);
    }

    private void inItView(View view) {
        floating_btn = (ImageView) view.findViewById(R.id.floating_btn);
        filter_icon = (ImageView) view.findViewById(R.id.filter_icon);
        search_icon = (ImageView) view.findViewById(R.id.search_icon);
        search_et = (EditText) view.findViewById(R.id.search_et);

        filter_icon.setOnClickListener(studnetList);
        search_icon.setOnClickListener(this);
        search_et.setOnClickListener(this);
        floating_btn.setOnClickListener(this);
        view_pager.setOnPageChangeListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_et:
                Log.d("rohit", "Clicked");
                search_et.setCursorVisible(true);
                break;

            case R.id.search_icon:
                String QUERY = null;
                if (search_et.getText().toString().length() < 1)
                    QUERY = null;
                else
                    QUERY = StudentDAO.FORM_1_ENTITY_3 + " LIKE '%" + search_et.getText().toString() + "%'";
                studnetList.afterTextChanged(QUERY);
                break;
            case R.id.floating_btn:

                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new AddStudentFragment()).commit();
                break;


        }
    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 1) {
            filter_icon.setVisibility(View.GONE);
            search_icon.setVisibility(View.GONE);
            search_et.setEnabled(false);
            search_et.setText("Student Profile");
        } else {
            filter_icon.setVisibility(View.VISIBLE);
            search_icon.setVisibility(View.VISIBLE);
            search_et.setEnabled(true);
            search_et.setText("");
            search_et.setHint("Search Student");
        }
    }


    public void onPageSelected(int position) {

    }


    public void onPageScrollStateChanged(int state) {

    }
}
