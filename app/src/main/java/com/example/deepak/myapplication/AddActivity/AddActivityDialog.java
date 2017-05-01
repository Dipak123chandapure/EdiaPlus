package com.example.deepak.myapplication.AddActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deepak.myapplication.AddStudent.DropDownDialog;
import com.example.deepak.myapplication.AddStudent.DropDownDialogRecyclerAdapter;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;

public class AddActivityDialog extends Dialog implements View.OnClickListener,
        DropDownDialogRecyclerAdapter.OnAddLeadCommonCodeDialogItemSelected,
        CustomDatePickerDialog.OnActivityDateSelected,
        TimePickerDialog.OnTimePickertimeSelected {
    Context mContext;
    DropDownDataDTO mActivityCoomonCodeValue;
    StudentDTO studentData;

    public AddActivityDialog(Context context, StudentDTO studentData) {
        super(context);
        mContext = context;
        this.studentData = studentData;
    }

    ImageView add_activity_dialog_date_picker, add_activity_dialog_time_picker, add_activity_dialog_template_picker;
    TextView add_activity_dialog_add_button, add_activity_dialog_activity_type_text;
    EditText add_activity_dialog_activity_comment_etxt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_activity_dialog_layout);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = (Gravity.CENTER);
        getWindow().setAttributes(params);
        Log.d("rohit", "student data"+studentData.getForm1Entity1());
        inItView();
    }

    private void inItView() {
        add_activity_dialog_date_picker = (ImageView) findViewById(R.id.add_activity_dialog_date_picker);
        add_activity_dialog_date_picker.setOnClickListener(this);

        add_activity_dialog_time_picker = (ImageView) findViewById(R.id.add_activity_dialog_time_picker);
        add_activity_dialog_time_picker.setOnClickListener(this);

        add_activity_dialog_template_picker = (ImageView) findViewById(R.id.add_activity_dialog_template_picker);
        add_activity_dialog_template_picker.setOnClickListener(this);

        add_activity_dialog_add_button = (TextView) findViewById(R.id.add_activity_dialog_add_button);
        add_activity_dialog_add_button.setOnClickListener(this);

        add_activity_dialog_activity_type_text = (TextView) findViewById(R.id.add_activity_dialog_activity_type_text);
        add_activity_dialog_activity_type_text.setOnClickListener(this);

        add_activity_dialog_activity_comment_etxt = (EditText) findViewById(R.id.add_activity_dialog_activity_comment_etxt);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_activity_dialog_activity_type_text:
                DropDownDataDAO handler = new DropDownDataDAO(mContext);
                ArrayList<DropDownDataDTO> list = handler.getFormData(Constant.ACTIVITY_DROPDOWN_VALUES);
                Log.d("rohit", "value of list "+list.size());
                DropDownDialog dialog = new DropDownDialog(mContext, list, Constant.ACTIVITY_DROPDOWN_VALUES, this);
                dialog.show();
                break;

            case R.id.add_activity_dialog_date_picker:
                CustomDatePickerDialog mDialog = new CustomDatePickerDialog(mContext);
                mDialog.setOnActivityDateSelected(this);
                mDialog.show();
                break;

            case R.id.add_activity_dialog_time_picker:
                TimePickerDialog tDialog = new TimePickerDialog(mContext);
                tDialog.setOnTimePickertimeSelected(this);
                tDialog.show();
                break;

            case R.id.add_activity_dialog_template_picker:
                break;

            case R.id.add_activity_dialog_add_button:
                if (isAllEntriesValid())
                    saveActivity();
                break;

        }

    }

    private void saveActivity() {
        Calendar mCalendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        mCalendar.set(year, month, dayOfMonth, hrs, min);
        ActivityDTO activityData = new ActivityDTO();
        activityData.setCreatedDate(now.getTimeInMillis());
        activityData.setModificationDate(now.getTimeInMillis());
        activityData.setNextActionDate(mCalendar.getTimeInMillis());

        activityData.setActvityTypeID(mActivityCoomonCodeValue.getId());
        activityData.setActivityBody(add_activity_dialog_activity_comment_etxt.getText().toString());
        activityData.setActivityComment(add_activity_dialog_activity_comment_etxt.getText().toString());

        activityData.setStudentID(studentData.getId());

        activityData.setActivityDataJSON(new Gson().toJson(activityData));
        activityData.setIsDone(0);

        studentData.setStudentDataJSON(null);
        String studentDataJSON=  new Gson().toJson(studentData);
        studentData.setStudentDataJSON(studentDataJSON);
        Log.d("rohit", "student data JSON "+studentDataJSON);

        StudentDAO handler = new StudentDAO(mContext);
        ActivitiesDAO handler1 = new ActivitiesDAO(mContext);

        handler1.addActivity(activityData);
       // handler.updateStudent(studentData);
        dismiss();
    }

    private boolean isAllEntriesValid() {
        Boolean isActivityTypeSelected = false;
        Boolean isDateSelected = false;
        Boolean isTimeSelected = false;

        if (add_activity_dialog_activity_type_text.getText().toString().length() > 0)
            isActivityTypeSelected = true;
        else Toast.makeText(mContext, "please select activity", Toast.LENGTH_SHORT).show();


        if (year != 0)
            isDateSelected = true;
        else Toast.makeText(mContext, "please select Date", Toast.LENGTH_SHORT).show();

        if (!(hrs == 0 && min == 0))
            isTimeSelected = true;
        else Toast.makeText(mContext, "please select Time", Toast.LENGTH_SHORT).show();

        if (isActivityTypeSelected && isDateSelected && isTimeSelected)
            return true;
        else return false;
    }


    public void onAddLeadCommonCodeDialogItemSelected(DropDownDataDTO value, String TYPE) {
        add_activity_dialog_activity_type_text.setText(value.getTitle());
        mActivityCoomonCodeValue = value;
    }

    int dayOfMonth, month, year, hrs, min;

    @Override
    public void onActivityDateSelected(int year, int month, int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        Log.d("rohit", "date :" + dayOfMonth);
    }

    @Override
    public void onTimePickertimeSelected(int hrs, int min) {
        this.hrs = hrs;
        this.min = min;
        Log.d("rohit", "hrs" + hrs);
    }
}
