package com.example.deepak.myapplication.ChartDashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.deepak.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import static com.example.deepak.myapplication.ChartDashboard.ChartUtility.getXAxitDataListForCurrentMonth;
import static com.example.deepak.myapplication.ChartDashboard.ChartUtility.getXAxitDataListForLastXDays;

public class Chart_Dashboard_Line_Chart_Fagment extends Fragment {

    LineChart chart_dashbaord_line_chart;


    ArrayList<String> mList;
    DayAxisValueFormatter formatter;
    XAxis xAxis;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.chart_dashboard_line_chart_fragmnet_layout, container, false);
        mList = getXAxitDataListForCurrentMonth();
        inItView(view);
        setUpLineChart();
        setData();
        return view;
    }

    public void inItView(View view) {

        chart_dashbaord_line_chart = (LineChart) view.findViewById(R.id.chart_dashbaord_line_chart);
        chart_dashbaord_line_chart.setNoDataText("Loading....");
    }


    private void setUpLineChart() {

        chart_dashbaord_line_chart.setDrawGridBackground(false);
        chart_dashbaord_line_chart.getDescription().setEnabled(false);

        formatter = new DayAxisValueFormatter(chart_dashbaord_line_chart);

        formatter.setXAxisData(mList);
        IAxisValueFormatter xAxisFormatter = formatter;

        xAxis = chart_dashbaord_line_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);


        YAxis leftAxis = chart_dashbaord_line_chart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(30f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        YAxis rightAxis = chart_dashbaord_line_chart.getAxisRight();
        rightAxis.removeAllLimitLines();
        rightAxis.setAxisMaximum(30f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.enableGridDashedLine(10f, 10f, 0f);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setDrawLimitLinesBehindData(true);

        chart_dashbaord_line_chart.getAxisRight().setEnabled(true);
        XYMarkerView mv = new XYMarkerView(getActivity(), xAxisFormatter);
        mv.setChartView(chart_dashbaord_line_chart);
        chart_dashbaord_line_chart.setMarker(mv);

        Legend l = chart_dashbaord_line_chart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
    }

    private void setData() {
        Random r = new Random();
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            values.add(new BarEntry(i, r.nextInt(10)));
        }
        setDataOnUi(values);
    }


    public void setDataOnUi(final ArrayList<Entry> values) {
        if (null != getActivity()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    chart_dashbaord_line_chart.clear();
                    LineDataSet set1;
                    set1 = new LineDataSet(values, "DataSet 1");
                    set1.setColor(Color.GRAY);
                    set1.setCircleColor(Color.BLACK);
                    set1.setValueTextColor(Color.TRANSPARENT);
                    set1.setLineWidth(0.4f);
                    set1.setCircleRadius(1f);
                    set1.setDrawCircleHole(false);
                    set1.setValueTextSize(9f);
                    set1.setDrawFilled(true);
                    ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                    dataSets.add(set1);
                    LineData data = new LineData(dataSets);
                    chart_dashbaord_line_chart.animateX(2500);
                    chart_dashbaord_line_chart.setData(data);

                }
            });
        }

    }


}
