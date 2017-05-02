package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.deepak.myapplication.R;

public class GroupDashboard extends Fragment {

    ViewPager view_pager;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashboard, container, false);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager);
        setUpViewPager();
        return view;
    }

    private void setUpViewPager() {
        CustomFragmentPageradapter adapter = new CustomFragmentPageradapter(getActivity().getSupportFragmentManager());
        Groups groups = new Groups();
        GroupStudents GroupStudents = new GroupStudents();
        StudentProfile StudentProfile = new StudentProfile();
        groups.setOnGroupItemClicked(GroupStudents, view_pager);
        GroupStudents.setOnStudentClicked(StudentProfile, view_pager);
        adapter.addFragment(groups, "Group Fragment");
        adapter.addFragment(GroupStudents, "Group Details");
        adapter.addFragment(StudentProfile, "Group_Student");
        view_pager.setOffscreenPageLimit(2);
        view_pager.setAdapter(adapter);
    }
}
