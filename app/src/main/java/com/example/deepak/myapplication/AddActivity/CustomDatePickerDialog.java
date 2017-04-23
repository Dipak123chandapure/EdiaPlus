package com.example.deepak.myapplication.AddActivity;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;

import com.example.deepak.myapplication.R;

public class CustomDatePickerDialog extends Dialog implements CalendarView.OnDateChangeListener {

    private CalendarView date_picker_calender_view;
    private OnActivityDateSelected mOnActivityDateSelected;

    public CustomDatePickerDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.date_picker_dialog);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = (Gravity.CENTER);

        date_picker_calender_view = (CalendarView) findViewById(R.id.date_picker_calender_view);
        date_picker_calender_view.setOnDateChangeListener(this);

        getWindow().setAttributes(params);
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        Log.d("rohit", "date :" +dayOfMonth);
        if (null != mOnActivityDateSelected)
        mOnActivityDateSelected.onActivityDateSelected(year, month, dayOfMonth);
        dismiss();
    }

    public void setOnActivityDateSelected(OnActivityDateSelected mOnActivityDateSelected){
        this.mOnActivityDateSelected = mOnActivityDateSelected;
    }

    public interface OnActivityDateSelected{
        void onActivityDateSelected(int year, int month, int dayOfMonth);
    }
}
