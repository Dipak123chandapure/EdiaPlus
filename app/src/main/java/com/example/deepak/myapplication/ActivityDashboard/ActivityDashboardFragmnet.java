package com.example.deepak.myapplication.ActivityDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ActivityDashboardFragmnet extends Fragment implements View.OnClickListener, CompactCalendarView.CompactCalendarViewListener {

    RecyclerViewExpandableItemManager expMgr;
    ActivityDashboardActivityListAdapter adapter;
    RecyclerView a_d_f_l_recycler_view;
    ImageView a_d_f_l_expand_calender_image;
    TextView a_d_f_l_date_indicator_text, a_d_f_l_separator_two;
    CompactCalendarView a_d_f_l_calender_view;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonthDay = new SimpleDateFormat("dd - MMM - yyyy ", Locale.getDefault());

    ArrayList<ActivityDTO> mList;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard_fragment_layout, container, false);
        inItView(view);
        setUpRecyclerView();
        return view;
    }

    private void inItView(View view) {
        a_d_f_l_recycler_view = (RecyclerView) view.findViewById(R.id.a_d_f_l_recycler_view);
        a_d_f_l_calender_view = (CompactCalendarView) view.findViewById(R.id.a_d_f_l_calender_view);
        a_d_f_l_calender_view.setListener(this);
        a_d_f_l_expand_calender_image = (ImageView) view.findViewById(R.id.a_d_f_l_expand_calender_image);
        a_d_f_l_separator_two = (TextView) view.findViewById(R.id.a_d_f_l_separator_two);
        a_d_f_l_expand_calender_image.setOnClickListener(this);
        a_d_f_l_date_indicator_text = (TextView) view.findViewById(R.id.a_d_f_l_date_indicator_text);
        a_d_f_l_date_indicator_text.setOnClickListener(this);
    }

    private void setUpRecyclerView() {
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);
        mList = new ActivitiesDAO(getActivity()).getActivities(now.getTime().getTime(), tomorrow.getTime().getTime());
        expMgr = new RecyclerViewExpandableItemManager(null);
        a_d_f_l_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ActivityDashboardActivityListAdapter(getActivity(), mList);
        a_d_f_l_recycler_view.setAdapter(expMgr.createWrappedAdapter(adapter));
        ((SimpleItemAnimator) a_d_f_l_recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
        expMgr.attachRecyclerView(a_d_f_l_recycler_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.a_d_f_l_expand_calender_image:
                changeCalenderVisibility();
                break;
            case R.id.a_d_f_l_date_indicator_text:
                changeCalenderVisibility();

        }
    }

    public void changeCalenderVisibility(){
        if (a_d_f_l_calender_view.getVisibility() == View.VISIBLE) {
            a_d_f_l_calender_view.setVisibility(View.GONE);
            a_d_f_l_separator_two.setVisibility(View.GONE);
        } else {
            a_d_f_l_calender_view.setVisibility(View.VISIBLE);
            a_d_f_l_separator_two.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDayClick(Date dateClicked) {

        a_d_f_l_date_indicator_text.setText(dateFormatForMonthDay.format(dateClicked));
        ActivitiesDAO handler = new ActivitiesDAO(getActivity());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateClicked);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        Long statingMilli = dateClicked.getTime(), endingMilli = calendar.getTime().getTime();
        ArrayList<ActivityDTO> list = handler.getActivities( statingMilli, endingMilli);
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();

        changeCalenderVisibility();
    }

    @Override
    public void onMonthScroll(Date firstDayOfNewMonth) {
        a_d_f_l_date_indicator_text.setText(dateFormatForMonth.format(firstDayOfNewMonth));
    }
}
