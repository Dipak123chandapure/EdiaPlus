package com.example.deepak.myapplication.ChartDashboard;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

public class Chart_Dashboard_Pie_Chart_Fragment extends Fragment implements OnChartValueSelectedListener {

    View view;
    PieChart chart_dashbaord_pie_chart;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chart_dashboard_pie_chart_fragment_layout, container, false);
        inItView(view);
        setUpPieChart();
        setData();
        return view;
    }

    public void inItView(View view) {
        chart_dashbaord_pie_chart = (PieChart) view.findViewById(R.id.chart_dashbaord_pie_chart);
        chart_dashbaord_pie_chart.setNoDataText("Loading...");
    }

    private void setUpPieChart() {
        chart_dashbaord_pie_chart.setUsePercentValues(true);
        chart_dashbaord_pie_chart.getDescription().setEnabled(false);
        chart_dashbaord_pie_chart.setDragDecelerationFrictionCoef(0.95f);
        chart_dashbaord_pie_chart.setExtraOffsets(35.f, 10.f, 0.f, 0.f);
        chart_dashbaord_pie_chart.setDrawHoleEnabled(true);
        chart_dashbaord_pie_chart.setHoleColor(Color.WHITE);
        chart_dashbaord_pie_chart.setTransparentCircleColor(Color.WHITE);
        chart_dashbaord_pie_chart.setTransparentCircleAlpha(110);
        chart_dashbaord_pie_chart.setHoleRadius(58f);
        chart_dashbaord_pie_chart.setTransparentCircleRadius(61f);
        chart_dashbaord_pie_chart.setDrawCenterText(true);
        chart_dashbaord_pie_chart.setRotationAngle(0);
        chart_dashbaord_pie_chart.setRotationEnabled(true);
        chart_dashbaord_pie_chart.setHighlightPerTapEnabled(true);
        chart_dashbaord_pie_chart.setOnChartValueSelectedListener(this);

        Legend l = chart_dashbaord_pie_chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        chart_dashbaord_pie_chart.setEntryLabelColor(Color.TRANSPARENT);
        chart_dashbaord_pie_chart.setEntryLabelTextSize(12f);

    }

    ArrayList<String> list = new ArrayList<>();

    private void setData() {
        list.add("Test1");
        list.add("Test2");
        list.add("Test3");
        list.add("Test4");
        list.add("Test5");

        Random r = new Random();
        ArrayList<PieEntry> entries = new ArrayList<>();
        int TOTAL = 0;
        for (int i = 0; i < 5; i++) {
            StringBuilder builder = new StringBuilder();
            entries.add(new PieEntry(r.nextInt(60), list.get(i)));
        }
        setDataOnui(entries, TOTAL);
    }


    public void setDataOnui(final ArrayList<PieEntry> entries, final int TOTAL) {
        if (null != getActivity()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    chart_dashbaord_pie_chart.clear();
                    chart_dashbaord_pie_chart.setCenterText("Total\n" + TOTAL);
                    PieDataSet dataSet = new PieDataSet(entries, "Sources");
                    dataSet.setSliceSpace(3f);
                    dataSet.setSelectionShift(5f);
                    chart_dashbaord_pie_chart.notifyDataSetChanged();
                    ArrayList<Integer> colors = new ArrayList<>();
                    for (int c : ColorTemplate.VORDIPLOM_COLORS)
                        colors.add(c);

                    for (int c : ColorTemplate.JOYFUL_COLORS)
                        colors.add(c);
                    dataSet.setColors(colors);


                    dataSet.setValueLinePart1OffsetPercentage(80.f);
                    dataSet.setValueLinePart1Length(0.2f);
                    dataSet.setValueLinePart2Length(0.4f);
                    dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

                    PieData data = new PieData(dataSet);
                    data.setValueFormatter(new PercentFormatter());
                    data.setValueTextSize(11f);
                    data.setValueTextColor(Color.BLACK);

                    chart_dashbaord_pie_chart.setData(data);
                    chart_dashbaord_pie_chart.highlightValues(null);
                    chart_dashbaord_pie_chart.invalidate();
                    chart_dashbaord_pie_chart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
                }
            });
        }
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        PieEntry pieEntry = (PieEntry) e;
        chart_dashbaord_pie_chart.setCenterText(pieEntry.getLabel() + "\n" + pieEntry.getValue());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}
