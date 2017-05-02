package com.example.deepak.myapplication.ActivityDashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.deepak.myapplication.ChartDashboard.ChartUtility;
import com.example.deepak.myapplication.ChartDashboard.DateLimitObject;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.Utility.Constant;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ActivityDashboardFragmnet extends Fragment implements View.OnClickListener, CompactCalendarView.CompactCalendarViewListener, ActivityDashboardActivityListAdapter.OnActivityListCallBachk {

    RecyclerViewExpandableItemManager expMgr;
    ActivityDashboardActivityListAdapter adapter;
    RecyclerView a_d_f_l_recycler_view;
    ImageView a_d_f_l_expand_calender_image;
    TextView a_d_f_l_date_indicator_text, a_d_f_l_separator_two;

    MaterialRippleLayout riiple_layout;
    CompactCalendarView a_d_f_l_calender_view;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonthDay = new SimpleDateFormat("dd - MMM - yyyy ", Locale.getDefault());

    ArrayList<ActivityDTO> mList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard_fragment_layout, container, false);
        inItView(view);
        setUpRecyclerView();
        DateLimitObject date = ChartUtility.getStartingAndEndingDateForCurrentMonth();
        addEventsForCurrentMonth(date);
        return view;
    }

    private void inItView(View view) {
        a_d_f_l_recycler_view = (RecyclerView) view.findViewById(R.id.a_d_f_l_recycler_view);
        a_d_f_l_calender_view = (CompactCalendarView) view.findViewById(R.id.a_d_f_l_calender_view);
        a_d_f_l_calender_view.setListener(this);
        a_d_f_l_expand_calender_image = (ImageView) view.findViewById(R.id.a_d_f_l_expand_calender_image);
        a_d_f_l_separator_two = (TextView) view.findViewById(R.id.a_d_f_l_separator_two);
        a_d_f_l_date_indicator_text = (TextView) view.findViewById(R.id.a_d_f_l_date_indicator_text);
        riiple_layout = (MaterialRippleLayout) view.findViewById(R.id.riiple_layout);
        riiple_layout.setOnClickListener(this);

    }

    private void setUpRecyclerView() {
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);
        mList = new ActivitiesDAO(getActivity()).getActivities(getStartingDate(now).getTime(), getStartingDate(tomorrow).getTime());
        expMgr = new RecyclerViewExpandableItemManager(null);
        a_d_f_l_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ActivityDashboardActivityListAdapter(getActivity(), mList);
        adapter.setmOnActivityListCallBachk(this);
        a_d_f_l_recycler_view.setAdapter(expMgr.createWrappedAdapter(adapter));
        ((SimpleItemAnimator) a_d_f_l_recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
        expMgr.attachRecyclerView(a_d_f_l_recycler_view);
        a_d_f_l_date_indicator_text.setText(dateFormatForMonthDay.format(now.getTimeInMillis()));
    }

    public Date getStartingDate(Calendar date) {
        String dateString = dateFormatForMonthDay.format(date.getTime());
        Date Date = null;
        try {
            Date = dateFormatForMonthDay.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Date;
    }

    public void onClick(View v) {
        changeCalenderVisibility();
    }

    public void changeCalenderVisibility() {
        if (a_d_f_l_calender_view.getVisibility() == View.VISIBLE) {
            a_d_f_l_calender_view.setVisibility(View.GONE);
            a_d_f_l_expand_calender_image.setImageResource(R.mipmap.arrow_drop_down);
            a_d_f_l_separator_two.setVisibility(View.GONE);
        } else {
            a_d_f_l_calender_view.setVisibility(View.VISIBLE);
            a_d_f_l_separator_two.setVisibility(View.VISIBLE);
            a_d_f_l_expand_calender_image.setImageResource(R.mipmap.arrow_drop_up_grey);
        }
    }

    public void onDayClick(Date dateClicked) {
        a_d_f_l_date_indicator_text.setText(dateFormatForMonthDay.format(dateClicked));
        ActivitiesDAO handler = new ActivitiesDAO(getActivity());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateClicked);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        Long statingMilli = dateClicked.getTime(), endingMilli = calendar.getTime().getTime();
        ArrayList<ActivityDTO> list = handler.getActivities(statingMilli, endingMilli);
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
        changeCalenderVisibility();
    }


    public void onMonthScroll(Date firstDayOfNewMonth) {
        a_d_f_l_date_indicator_text.setText(dateFormatForMonth.format(firstDayOfNewMonth));
        Calendar startingMonthCalender = Calendar.getInstance();
        startingMonthCalender.setTime(firstDayOfNewMonth);
        Calendar endingMonthCalender = Calendar.getInstance();
        endingMonthCalender.setTime(firstDayOfNewMonth);
        endingMonthCalender.add(Calendar.MONTH, 1);
        DateLimitObject date = new DateLimitObject();
        date.setStartingDate(startingMonthCalender.getTime());
        date.setEndingDate(endingMonthCalender.getTime());
        addEventsForCurrentMonth(date);
    }

    public void onChildItemClicked(int position, StudentDTO dto) {
        switch (position) {
            case 0:
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

            case 1:

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

        }
    }


    public void addEventsForCurrentMonth(final DateLimitObject date) {
        new Thread(new Runnable() {
            public void run() {
                ActivitiesDAO dao = new ActivitiesDAO(getActivity());
                if (null != date) {
                    Long startingmilli = date.getStartingDate().getTime();
                    Long endingmilli = date.getEndingDate().getTime();
                    ArrayList<ActivityDTO> eventList = dao.getActivities(startingmilli, endingmilli);
                    for (int i = 0; i < eventList.size(); i++) {
                        a_d_f_l_calender_view.addEvent(new Event(Color.GRAY, eventList.get(i).getNextActionDate()));
                    }
                }
            }
        }).start();

    }
}
