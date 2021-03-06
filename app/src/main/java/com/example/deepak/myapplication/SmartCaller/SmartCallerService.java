package com.example.deepak.myapplication.SmartCaller;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.MainActivity;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;

public class SmartCallerService extends Service implements View.OnTouchListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private WindowManager windowManager;
    WindowManager.LayoutParams params, params1;
    View smart_caller_dial_header;
    ViewPager smart_caller_dial_view_pager;
    String number;
    StudentDTO studentData;
    LinearLayout smart_caller_non_exisiting_student_input_ll;
    RecyclerView smart_caller_exisiting_student_recycler;
    LinearLayout smart_caller_exisiting_student_recycler_ll;

    Long callStartingTime, callEndingTime;
    String callType;

    EditText edit_text1, edit_text2, edit_text3;
    ImageView image_one, image_two, image_three, image_four;

    public void onCreate() {
        super.onCreate();
        registerReceiver(smartCallerDialBroadCastReceiver, new IntentFilter(Constant.SMART_CALLER_DIAL_BROAD_CAST_RECEIVER));
        Log.d("rohit", "oncreate service is called");
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        smart_caller_dial_header = inflater.inflate(R.layout.smart_caller_dial_main_layout, null);
        smart_caller_dial_view_pager = (ViewPager) smart_caller_dial_header.findViewById(R.id.smart_caller_dial_view_pager);
        smart_caller_dial_view_pager.setAdapter(new SmartCallerViewPagerAdapter(this));

        smart_caller_dial_view_pager.post(new Runnable() {
            public void run() {
                smart_caller_dial_view_pager.setCurrentItem(1);
                smart_caller_non_exisiting_student_input_ll = (LinearLayout) smart_caller_dial_header.findViewById(R.id.smart_caller_non_exisiting_student_input_ll);
                smart_caller_exisiting_student_recycler = (RecyclerView) smart_caller_dial_view_pager.findViewById(R.id.smart_caller_exisiting_student_recycler);
                smart_caller_exisiting_student_recycler_ll = (LinearLayout) smart_caller_dial_view_pager.findViewById(R.id.smart_caller_exisiting_student_recycler_ll);
                Log.d("rohit", "setting visibility");

                image_one = (ImageView) smart_caller_dial_view_pager.findViewById(R.id.image_one);
                image_two = (ImageView) smart_caller_dial_view_pager.findViewById(R.id.image_two);
                image_three = (ImageView) smart_caller_dial_view_pager.findViewById(R.id.image_three);
                image_four = (ImageView) smart_caller_dial_view_pager.findViewById(R.id.image_four);

                image_one.setOnClickListener(SmartCallerService.this);
                image_two.setOnClickListener(SmartCallerService.this);
                image_three.setOnClickListener(SmartCallerService.this);
                image_four.setOnClickListener(SmartCallerService.this);

                if (studentData != null) {
                    smart_caller_non_exisiting_student_input_ll.setVisibility(View.GONE);
                    smart_caller_exisiting_student_recycler.setVisibility(View.VISIBLE);
                    smart_caller_exisiting_student_recycler_ll.setVisibility(View.VISIBLE);

                    image_one.setImageResource(R.mipmap.smart_caller_grey);
                    image_two.setImageResource(R.mipmap.chat_grey);
                    image_three.setImageResource(R.mipmap.email_grey);
                    image_four.setImageResource(R.mipmap.edit_grey);

                    setUpRecyclerViewWithData();
                } else {
                    edit_text1 = (EditText) smart_caller_dial_view_pager.findViewById(R.id.edit_text1);
                    edit_text2 = (EditText) smart_caller_dial_view_pager.findViewById(R.id.edit_text2);
                    edit_text3 = (EditText) smart_caller_dial_view_pager.findViewById(R.id.edit_text3);
                }
            }
        });

        smart_caller_dial_view_pager.addOnPageChangeListener(this);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 600;

        smart_caller_dial_view_pager.setOnTouchListener(this);
        windowManager.addView(smart_caller_dial_header, params);
        Log.d("rohit", "end of on create");
    }


    private void setUpRecyclerViewWithData() {
        ArrayList<String> list = new ArrayList<>();
        list.add(studentData.getForm1Entity1() + " " + studentData.getForm1Entity2());
        list.add(studentData.getForm2Entity1());
        list.add(studentData.getForm2Entity2());
        list.add(studentData.getForm2Entity3());
        list.add(studentData.getForm2Entity4());
        list.add(studentData.getForm2Entity1());
        list.add(studentData.getCreatedOnMilli() + "");
        Log.d("rohit", "size" + list.size());
        SmartCallerRecyclerAdapter adapter = new SmartCallerRecyclerAdapter(this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        smart_caller_exisiting_student_recycler.setLayoutManager(manager);
        smart_caller_exisiting_student_recycler.setAdapter(adapter);
    }

    private int initialX;
    private int initialY;
    private float initialTouchX;
    private float initialTouchY;

    public void onDestroy() {
        super.onDestroy();
        if (null == callEndingTime) {
            updateLeadAndAddActvity(studentData);
        }

        windowManager.removeView(smart_caller_dial_header);
        unregisterReceiver(smartCallerDialBroadCastReceiver);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int position) {
        if (position == 0 || position == 2)
            stopSelf();

    }

    public void onPageScrollStateChanged(int state) {

    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("rohit", "onStartCommand is called");
        number = intent.getStringExtra(Constant.SMART_CALLER_DIAL_NUMBER);
        callType = intent.getStringExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE);
        callStartingTime = Calendar.getInstance().getTimeInMillis();

        StudentDAO handler = new StudentDAO(this);
        studentData = handler.getStudentForNumber(number);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateLeadAndAddActvity(StudentDTO studentData) {
        if (null != studentData) {
            Log.d("rohit", "studentData " + new Gson().toJson(studentData));
            StudentDAO handler = new StudentDAO(this);
            studentData.setUpdatedONMilli(callStartingTime);
            studentData.setUpdatedON(callStartingTime + "");
            studentData.setStudentDataJSON(new Gson().toJson(studentData));

            ActivityDTO activityData = new ActivityDTO();
            activityData.setCreatedDate(callStartingTime);
            activityData.setModificationDate(callStartingTime);
            activityData.setForm1Entity1(studentData.getForm1Entity1() + " " + studentData.getForm1Entity2());
            activityData.setForm1Entity3(studentData.getForm1Entity3());
            activityData.setForm1Entity4(studentData.getForm1Entity4());
            activityData.setActvityTypeID(SmartCallUtil.getIDForCallType(callType));
            activityData.setSmartCallDuration(SmartCallUtil.getCallDuration(callEndingTime, callStartingTime));
            activityData.setIsDone(1);
            activityData.setStudentID(studentData.getId());
            activityData.setNextActionDate(callStartingTime);
            activityData.setActivityDataJSON(new Gson().toJson(activityData));

            ActivitiesDAO dao = new ActivitiesDAO(this);
            dao.addActivity(activityData);
            handler.updateStudent(studentData);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = params.x;
                initialY = params.y;
                initialTouchX = event.getRawX();
                initialTouchY = event.getRawY();
                return false;
            case MotionEvent.ACTION_UP:
                return false;
            case MotionEvent.ACTION_MOVE:
                params.x = initialX
                        + (int) (event.getRawX() - initialTouchX);
                params.y = initialY
                        - (int) (event.getRawY() - initialTouchY);

                windowManager.updateViewLayout(smart_caller_dial_header, params);
                return false;
        }
        return false;
    }


    public BroadcastReceiver smartCallerDialBroadCastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d("rohit", "broadcast received" + intent.getStringExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE));
            callType = intent.getStringExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE);

            switch (intent.getStringExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE)) {
                case Constant.SMART_CALLER_DIAL_TYPE_OUTGOING_CALL_ENDED:
                    callEndingTime = Calendar.getInstance().getTimeInMillis();
                    updateLeadAndAddActvity(studentData);
                    break;

                case Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_RECEIVED:
                    callStartingTime = Calendar.getInstance().getTimeInMillis();
                    break;

                case Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_ENDED:
                    callEndingTime = Calendar.getInstance().getTimeInMillis();
                    updateLeadAndAddActvity(studentData);
                    break;

                case Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_MISSED:
                    callEndingTime = Calendar.getInstance().getTimeInMillis();
                    updateLeadAndAddActvity(studentData);
                    break;
            }
        }
    };

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_one:
                if (smart_caller_exisiting_student_recycler_ll.getVisibility() == View.VISIBLE)
                    startApplication(Constant.SMART_CALLER_DASHBOARD_FRAGMENT);
                else quickAddStudent();
                break;

            case R.id.image_two:
                if (smart_caller_exisiting_student_recycler_ll.getVisibility() == View.VISIBLE)
                    startApplication(Constant.SMS_DASHBOARD_FRAGMENT);
                else startApplication(Constant.ADD_STUDENT_DASHBOARD_FRAGMENT);
                break;

            case R.id.image_three:
                if (smart_caller_exisiting_student_recycler_ll.getVisibility() == View.VISIBLE)
                    startApplication(Constant.EMAIL_DASHBOARD_FRAGMENT);
                else startApplication(Constant.SMART_CALLER_DASHBOARD_FRAGMENT);
                break;

            case R.id.image_four:
                if (smart_caller_exisiting_student_recycler_ll.getVisibility() == View.VISIBLE)
                    startApplication(Constant.EDIT_STUDENT_DASHBOARD_FRAGMENT);
                else stopSelf();
                break;

        }
    }


    private void startApplication(final String TYPE) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(SmartCallerService.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra(Constant.LAUNCHING_FRAGMENT, TYPE);
                intent1.putExtra(Constant.STUDENT_LIST, new Gson().toJson(studentData));
                intent1.putExtra(Constant.STUDENT_MOB, number);
                SmartCallerService.this.startActivity(intent1);
            }
        }).start();
        stopSelf();
    }


    private void quickAddStudent() {
    }
}