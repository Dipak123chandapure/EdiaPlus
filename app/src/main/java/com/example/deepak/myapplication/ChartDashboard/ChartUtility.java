package com.example.deepak.myapplication.ChartDashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ChartUtility {
    public static ArrayList<String> getXAxitDataList(Date startingDate, Date endingDate) {
        ArrayList<String> dateList = new ArrayList<>();
        SimpleDateFormat xStringFormatter = new SimpleDateFormat("MMM dd", Locale.getDefault());

        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(startingDate);
        dateList.add(xStringFormatter.format(currentDate.getTime()));
        currentDate.add(Calendar.DAY_OF_YEAR, 1);

        while (currentDate.getTime().after(startingDate) && currentDate.getTime().before(endingDate)) {
            dateList.add(xStringFormatter.format(currentDate.getTime()));
            Log.d("rohit", "date: " + xStringFormatter.format(currentDate.getTime()));
            currentDate.add(Calendar.DAY_OF_YEAR, 1);
        }
        Log.d("rohit", "size " + dateList.size());
        return dateList;
    }


    public static ArrayList<String> getXAxitDataListForLastXDays(int x) {
        ArrayList<String> dateList = new ArrayList<>();
        SimpleDateFormat xStringFormatter = new SimpleDateFormat("MMM dd", Locale.getDefault());

        Calendar now = Calendar.getInstance();

        Calendar startingDate = Calendar.getInstance();
        startingDate.add(Calendar.DAY_OF_YEAR, -x);

        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -x);

        currentDate.add(Calendar.DAY_OF_YEAR, +1);

        while (currentDate.getTime().after(startingDate.getTime()) && currentDate.getTime().before(now.getTime())) {
            dateList.add(xStringFormatter.format(currentDate.getTime()));
            // Log.d("rohit", "date: " + xStringFormatter.format(currentDate.getTime()));
            currentDate.add(Calendar.DAY_OF_YEAR, +1);
        }
        dateList.add(xStringFormatter.format(currentDate.getTime()));
        Log.d("rohit", "size " + dateList.size());
        return dateList;
    }


    public static ArrayList<String> getXAxitDataListForYear() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

        Calendar now = Calendar.getInstance();
        Date nowDate = now.getTime();

        String startin_year = fmt.format(nowDate).substring(0, 4);
        String starting_month = 1 + "";
        String starting_day = 1 + "";

        String starting_date_string = startin_year + "/" + starting_month + "/" + starting_day;

        String ending_year = (Integer.valueOf(fmt.format(nowDate).substring(0, 4)) + 1) + "";
        String ending_month = 1 + "";
        String ending_day = 1 + "";
        String ending_date_string = ending_year + "/" + ending_month + "/" + ending_day;


        Date staring_date = null;
        Date ending_date = null;
        try {
            staring_date = fmt.parse(starting_date_string);
            ending_date = fmt.parse(ending_date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (null != staring_date && null != ending_date)
            return getXAxitDataList(staring_date, ending_date);


        return null;
    }


    public static ArrayList<String> getXAxitDataListForCurrentMonth() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        Calendar now = Calendar.getInstance();
        Date nowDate = now.getTime();
        String nowDateString = fmt.format(nowDate);

        String startin_year = nowDateString.substring(0, 4);
        String starting_month = nowDateString.substring(5, 7);
        String starting_day = 1 + "";
        String starting_date_string = startin_year + "/" + starting_month + "/" + starting_day;
        Log.d("rohit", starting_date_string);

        String ending_year = nowDateString.substring(0, 4);
        String ending_month = (Integer.valueOf(nowDateString.substring(5, 7)) + 1) + "";
        String ending_day = 1 + "";
        String ending_date_string = ending_year + "/" + ending_month + "/" + ending_day;

        Log.d("rohit", ending_date_string);

        Date staring_date = null;
        Date ending_date = null;
        try {
            staring_date = fmt.parse(starting_date_string);
            ending_date = fmt.parse(ending_date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (null != staring_date && null != ending_date)
            return getXAxitDataList(staring_date, ending_date);

        return null;
    }

    private ArrayList<String> getXAxitDataListForCurrentWeek() {
        Calendar now = Calendar.getInstance();
        now.getTime();
        return null;
    }

    public static boolean mgetDataForBarGraph(ArrayList<Integer> list, String column, Boolean isComputed, StringBuilder query) {

        if (list.size() > 0) {
            if (isComputed)
                query.append(" AND ");
            else isComputed = true;
            computeStatement(query, list, column);
        }

        return isComputed;
    }

//    public static ArrayList<CommonCodeValues> getTimeLimitList() {
//        String[] title = {"Current Year", "Current Month", "Last 30 days", "Last 15 days", "Last 7 days"};
//        ArrayList<CommonCodeValues> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            CommonCodeValues value = new CommonCodeValues();
//            value.setTitle(title[i]);
//            value.setId(i);
//            list.add(value);
//        }
//        return list;
//    }

    public static void computeStatement(StringBuilder query, ArrayList<Integer> list, String Column) {

        for (int i = 0; i < list.size(); i++) {
            if (i == 0)
                query.append("(");

            query.append(Column + " == '" + list.get(i) + "'");

            if (i == (list.size() - 1))
                query.append(")");
            else query.append(" OR ");
        }
    }


    public static DateLimitObject getStartingAndEndingDateForCurrentYear() {
        DateLimitObject dateLimitObject = new DateLimitObject();

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

        Calendar now = Calendar.getInstance();
        Date nowDate = now.getTime();

        String startin_year = fmt.format(nowDate).substring(0, 4);
        String starting_month = 1 + "";
        String starting_day = 1 + "";

        String starting_date_string = startin_year + "/" + starting_month + "/" + starting_day;

        String ending_year = (Integer.valueOf(fmt.format(nowDate).substring(0, 4)) + 1) + "";
        String ending_month = 1 + "";
        String ending_day = 1 + "";
        String ending_date_string = ending_year + "/" + ending_month + "/" + ending_day;

        Log.d("rohit", "SD " + starting_date_string);
        Log.d("rohit", "ED " + ending_date_string);

        Date staring_date = null;
        Date ending_date = null;
        try {
            staring_date = fmt.parse(starting_date_string);
            ending_date = fmt.parse(ending_date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateLimitObject.setStartingDate(staring_date);
        dateLimitObject.setEndingDate(ending_date);

        if (null != staring_date && null != ending_date)
            return dateLimitObject;

        return null;
    }


    public static DateLimitObject getStartingAndEndingDateForCurrentMonth() {
        DateLimitObject dateLimitObject = new DateLimitObject();

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        Calendar now = Calendar.getInstance();
        Date nowDate = now.getTime();
        String nowDateString = fmt.format(nowDate);

        String startin_year = nowDateString.substring(0, 4);
        String starting_month = nowDateString.substring(5, 7);
        String starting_day = 1 + "";
        String starting_date_string = startin_year + "/" + starting_month + "/" + starting_day;
        Log.d("rohit", starting_date_string);

        String ending_year = nowDateString.substring(0, 4);
        String ending_month = (Integer.valueOf(nowDateString.substring(5, 7)) + 1) + "";
        String ending_day = 1 + "";
        String ending_date_string = ending_year + "/" + ending_month + "/" + ending_day;

        Log.d("rohit", "SD " + starting_date_string);
        Log.d("rohit", "ED " + ending_date_string);

        Date staring_date = null;
        Date ending_date = null;
        try {
            staring_date = fmt.parse(starting_date_string);
            ending_date = fmt.parse(ending_date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateLimitObject.setStartingDate(staring_date);
        dateLimitObject.setEndingDate(ending_date);

        if (null != staring_date && null != ending_date)
            return dateLimitObject;

        return null;
    }


    public static DateLimitObject getStartingAndEndingDateForLastxDays(int x) {
        DateLimitObject dateLimitObject = new DateLimitObject();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

        Calendar ending_date_c = Calendar.getInstance();
        Date ending_date = ending_date_c.getTime();

        Calendar staring_date_c = Calendar.getInstance();
        staring_date_c.add(Calendar.DAY_OF_YEAR, -x);
        Date staring_date = staring_date_c.getTime();

        Log.d("rohit", "SD " + fmt.format(staring_date.getTime()));
        Log.d("rohit", "ED " + fmt.format(ending_date.getTime()));

        dateLimitObject.setStartingDate(staring_date);
        dateLimitObject.setEndingDate(ending_date);
        return dateLimitObject;
    }


    public static HashMap<String, Integer> commonCodeHashMapForYear = new HashMap<>();
    public static HashMap<String, Integer> bubbleChartMap = new HashMap<>();
    public static HashMap<String, Integer> stackedBarChartMap = new HashMap<>();


//    public static void prepareStudentData(Context mContext) {
//        Log.d("rohit", "Started preparing Data");
//        DatabaseHandler handler = new DatabaseHandler(mContext);
//        DateLimitObject yearObject = getStartingAndEndingDateForCurrentYear();
//
//        if (yearObject != null) {
//            commonCodeHashMapForYear = handler.setInitialDefaultValueForGivenTimeAndCommonCodeHashMAp(yearObject.getStartingDate().getTime() + "", yearObject.getEndingDate().getTime() + "");
//        }
//
//        Log.d("rohit", "end preparing Data");
//    }

    public static Bitmap resultBitmap;


}
