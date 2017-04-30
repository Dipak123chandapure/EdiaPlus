package com.example.deepak.myapplication.ChartDashboard;


import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class DayAxisValueFormatter implements IAxisValueFormatter
{
    private BarLineChartBase<?> chart;

    public DayAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }
    ArrayList<String> mList;
    public void setXAxisData(ArrayList<String> mList){
        this.mList = mList;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mList.get((int)(value % mList.size()));
    }
}
