package com.example.deepak.myapplication.ChartDashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.GroupDashboard.CustomFragmentPageradapter;
import com.example.deepak.myapplication.R;


public class Chart_Dashboard_Fragment extends Fragment implements View.OnClickListener{
    NonSwipableViewPager chart_fragmnet_view_pager;
    TextView bar_chart, line_chart, pie_chart;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_dashboard_fragment_layout,container,false);
        inItView(view);
        setUpViewPager(view);
        return view;
    }

    private void inItView(View view) {
        bar_chart = (TextView) view.findViewById(R.id.bar_chart);
        bar_chart.setOnClickListener(this);
        line_chart = (TextView) view.findViewById(R.id.line_chart);
        line_chart.setOnClickListener(this);
        pie_chart = (TextView) view.findViewById(R.id.pie_chart);
        pie_chart.setOnClickListener(this);

    }

    private void setUpViewPager(View view) {
        chart_fragmnet_view_pager = (NonSwipableViewPager) view.findViewById(R.id.chart_fragmnet_view_pager);
        chart_fragmnet_view_pager.setPagingEnabled(true);
        CustomFragmentPageradapter adapter = new CustomFragmentPageradapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Chart_Dashboard_Bar_Chart_Fragment(), "BarChart");
        adapter.addFragment(new Chart_Dashboard_Line_Chart_Fagment(), "LineChart");
        adapter.addFragment(new Chart_Dashboard_Pie_Chart_Fragment(), "PieChart");
        chart_fragmnet_view_pager.setOffscreenPageLimit(4);
        chart_fragmnet_view_pager.setAdapter(adapter);
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bar_chart:
                setDefaultBackgroundofTas();
                bar_chart.setBackgroundColor(Color.rgb(156,147,134));
                chart_fragmnet_view_pager.setCurrentItem(0);
                break;
            case R.id.line_chart:
                setDefaultBackgroundofTas();
                line_chart.setBackgroundColor(Color.rgb(156,147,134));
                chart_fragmnet_view_pager.setCurrentItem(1);
                break;
            case R.id.pie_chart:
                setDefaultBackgroundofTas();
                pie_chart.setBackgroundColor(Color.rgb(156,147,134));
                chart_fragmnet_view_pager.setCurrentItem(2);
                break;
        }
    }

    private void setDefaultBackgroundofTas() {
        bar_chart.setBackgroundColor(Color.TRANSPARENT);
        line_chart.setBackgroundColor(Color.TRANSPARENT);
        pie_chart.setBackgroundColor(Color.TRANSPARENT);
    }
}
