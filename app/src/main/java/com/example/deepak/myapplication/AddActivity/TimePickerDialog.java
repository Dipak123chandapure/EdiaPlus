package com.example.deepak.myapplication.AddActivity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.deepak.myapplication.R;

public class TimePickerDialog extends Dialog implements View.OnClickListener {

    TimePicker time_picker_dialog_time;
    Button time_picker_dialog_cancle_btn, time_picker_dialog_add_btn;
    OnTimePickertimeSelected mOnTimePickertimeSelected;

    public TimePickerDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.time_picker_dialog_layout);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = (Gravity.CENTER | Gravity.RIGHT);
        getWindow().setAttributes(params);

        time_picker_dialog_time = (TimePicker) findViewById(R.id.time_picker_dialog_time);
        time_picker_dialog_cancle_btn = (Button) findViewById(R.id.time_picker_dialog_cancle_btn);
        time_picker_dialog_cancle_btn.setOnClickListener(this);
        time_picker_dialog_add_btn = (Button) findViewById(R.id.time_picker_dialog_add_btn);
        time_picker_dialog_add_btn.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_picker_dialog_add_btn:
                if (null != mOnTimePickertimeSelected)
                mOnTimePickertimeSelected.onTimePickertimeSelected(time_picker_dialog_time.getCurrentHour(),
                        time_picker_dialog_time.getCurrentMinute());
                dismiss();
                break;

            case R.id.time_picker_dialog_cancle_btn:
                break;

        }
    }



    public void setOnTimePickertimeSelected(OnTimePickertimeSelected mOnTimePickertimeSelected) {
        this.mOnTimePickertimeSelected = mOnTimePickertimeSelected;
    }

    public interface OnTimePickertimeSelected {
        void onTimePickertimeSelected(int hrs, int min);
    }
}
