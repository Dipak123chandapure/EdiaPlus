package com.example.deepak.myapplication.ChartDashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deepak.myapplication.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Random;


public class Chart_Dashboard_Bar_Chart_Fragment extends Fragment implements OnChartValueSelectedListener {

    BarChart chart_dashboard_BarChart;
    LinearLayout chart_dashboard_BarChart_ll;
    DayAxisValueFormatter formatter;
    XAxis xAxis;
    ArrayList<String> mList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_dashboard_bar_chart_fragmnet_layout, container, false);
        inItView(view);
        mList = ChartUtility.getXAxitDataListForCurrentMonth();
        setUpBasrChart();
        setData();
        return view;
    }


    public void inItView(View view) {

        chart_dashboard_BarChart_ll = (LinearLayout) view.findViewById(R.id.chart_dashboard_BarChart_ll);
        chart_dashboard_BarChart = (BarChart) view.findViewById(R.id.chart_dashboard_BarChart);
        chart_dashboard_BarChart.setNoDataText("Loading....");
    }

    private void setUpBasrChart() {

        chart_dashboard_BarChart.setOnChartValueSelectedListener(Chart_Dashboard_Bar_Chart_Fragment.this);
        chart_dashboard_BarChart.setDrawBarShadow(false);
        chart_dashboard_BarChart.setDrawValueAboveBar(true);
        chart_dashboard_BarChart.getDescription().setEnabled(false);
        chart_dashboard_BarChart.setMaxVisibleValueCount(364);
        chart_dashboard_BarChart.setPinchZoom(false);
        chart_dashboard_BarChart.setDrawGridBackground(false);

        formatter = new DayAxisValueFormatter(chart_dashboard_BarChart);
        formatter.setXAxisData(mList);
        IAxisValueFormatter xAxisFormatter = formatter;

        xAxis = chart_dashboard_BarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = (IAxisValueFormatter) new MyAxisValueFormatter();
        YAxis leftAxis = chart_dashboard_BarChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMaximum(25);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = chart_dashboard_BarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setAxisMaximum(25);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);

        Legend l = chart_dashboard_BarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        XYMarkerView mv = new XYMarkerView(getActivity(), xAxisFormatter);
        mv.setChartView(chart_dashboard_BarChart);
        chart_dashboard_BarChart.setMarker(mv);

    }

    private void setData() {
        Random r = new Random();
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            yVals1.add(new BarEntry(i,r.nextInt(10)));
        }
        setDataOnUi(yVals1);
    }


    public void setDataOnUi(final ArrayList<BarEntry> yVals1) {
        if (null != getActivity()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    chart_dashboard_BarChart.clear();
                    BarDataSet set1;
                    Log.d("rohit", "data set phase1 complete");
                    set1 = new BarDataSet(yVals1, "The year 2017");

                    set1.setColors(Color.argb(255, 201, 189, 177));
                    ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);
                    BarData data = new BarData(dataSets);
                    data.setValueTextSize(0);
                    data.setBarWidth(0.9f);
                    chart_dashboard_BarChart.setData(data);
                    chart_dashboard_BarChart.animateY(800, Easing.EasingOption.EaseInOutQuad);
                    Log.d("rohit", "data set phase2 complete");
                }
            });
        }
    }

    public void onValueSelected(Entry e, Highlight h) {

    }

    public void onNothingSelected() {

    }


}
