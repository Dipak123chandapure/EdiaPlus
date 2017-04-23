package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class GroupDashboardFragment extends Fragment {

    ViewPager view_pager;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashboard_fragment_layout, container, false);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager);
        setUpViewPager();
        return view;
    }

    private void setUpViewPager() {
        Group_Fragment_Pager_adapter adapter = new Group_Fragment_Pager_adapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Group_Fragment(), "Group Fragment");
        adapter.addFragment(new Group_Details_Fragmet(), "Group Details");
        adapter.addFragment(new Group_Student_Details_Fragment(), "Group_Student");
        view_pager.setOffscreenPageLimit(2);
        view_pager.setAdapter(adapter);
    }
}
