package com.example.deepak.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.deepak.myapplication.ActivityDashboard.ActivityDashboardFragmnet;
import com.example.deepak.myapplication.AddStudent.AddStudentFragment;
import com.example.deepak.myapplication.Application.App;
import com.example.deepak.myapplication.ChartDashboard.Chart_Dashboard_Fragment;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.AttachmentDAO;
import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.AttachmentDTO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Database.OfflineDatabaseHelper;
import com.example.deepak.myapplication.EmailDashboard.EmailDashboardFragment;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.Settings.SettingsFragment;
import com.example.deepak.myapplication.SmartCaller.SmartCallerDashboardFragment;
import com.example.deepak.myapplication.StudentDashboard.StudentDashboard;
import com.example.deepak.myapplication.Utility.Constant;
import com.example.deepak.myapplication.Utility.ModalData;
import com.example.deepak.myapplication.Utility.UserDataParser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FrameLayout main_frame_layout;
    ImageView m_d_f_a_b_image_one, m_d_f_a_b_image_two, m_d_f_a_b_image_three, m_d_f_a_b_image_four,
            m_d_f_a_b_image_five, m_d_f_a_b_image_six, m_d_f_a_b_image_seven;
    OnMActivityResult mOnMActivityResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inItView();
        App.getInstance().setLiveContext(this);

        if (null != getIntent() && null != getIntent().getStringExtra(Constant.LAUNCHING_FRAGMENT)) {
            addFragment(getIntent());
        } else
            getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, new StudentDashboard()).commit();
        //setDefaultActivities();

    }
    public void setDefaultActivities(){
        for (int i =0; i<5;i ++){
            DropDownDataDTO dto = new DropDownDataDTO();
            dto.setTitle(activityTypetitle[i]);
            dto.setSystemValue(true);
            dto.setVirtuallyDeleted(false);
            DropDownDataDAO dao = new DropDownDataDAO(this);
            dao.saveFormData(Constant.ACTIVITY_DROPDOWN_VALUES, dto);
        }
    }

    String[] activityTypetitle = {"Send SMS", "Received SMS", "Dialed Call", "Missed Call", "Received Call" };
    private void addFragment(Intent intent) {
        String LAUNCHING_FRAGMENT = intent.getStringExtra(Constant.LAUNCHING_FRAGMENT);
        String studentString = null;
        StudentDTO student = null;
        String mob_no;

        switch (LAUNCHING_FRAGMENT) {
            case Constant.SMART_CALLER_DASHBOARD_FRAGMENT:
                getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, new SmartCallerDashboardFragment()).commit();
                break;
            case Constant.SMS_DASHBOARD_FRAGMENT:
                studentString =  intent.getStringExtra(Constant.STUDENT_LIST);
                student = new Gson().fromJson(studentString, StudentDTO.class);
                SMSDashboardFragment fragment = new SMSDashboardFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.SMS_TYPE, Constant.SMS_SINGE_CLIENT);
                ArrayList<StudentDTO> list = new ArrayList<>();
                list.add(student);
                bundle.putParcelableArrayList(Constant.SMS_CLIENT_LIST, list);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .add(R.id.main_frame_layout, fragment).commit();
                break;

            case Constant.EMAIL_DASHBOARD_FRAGMENT:
                studentString =  intent.getStringExtra(Constant.STUDENT_LIST);
                student = new Gson().fromJson(studentString, StudentDTO.class);
                EmailDashboardFragment fragment1 = new EmailDashboardFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString(Constant.SMS_TYPE, Constant.SMS_SINGE_CLIENT);
                ArrayList<StudentDTO> list1 = new ArrayList<>();
                list1.add(student);
                bundle1.putParcelableArrayList(Constant.SMS_CLIENT_LIST, list1);
                fragment1.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .add(R.id.main_frame_layout, fragment1).commit();

                break;
            case Constant.ADD_STUDENT_DASHBOARD_FRAGMENT:
                mob_no = intent.getParcelableExtra(Constant.STUDENT_MOB);
                AddStudentFragment addStudent = new AddStudentFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString(Constant.STUDENT_MOB, mob_no);
                addStudent.setArguments(bundle3);
                getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, addStudent).commit();

                break;

            case Constant.EDIT_STUDENT_DASHBOARD_FRAGMENT:
                studentString =  intent.getStringExtra(Constant.STUDENT_LIST);
                student = new Gson().fromJson(studentString, StudentDTO.class);
                AddStudentFragment addStudent1 = new AddStudentFragment();
                Bundle bundle4 = new Bundle();
                bundle4.putParcelable(Constant.STUDENT_LIST, student);
                addStudent1.setArguments(bundle4);
                getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, addStudent1).commit();

                break;
        }

    }


    private void inItView() {
        main_frame_layout = (FrameLayout) findViewById(R.id.main_frame_layout);

        m_d_f_a_b_image_one = (ImageView) findViewById(R.id.m_d_f_a_b_image_one);
        m_d_f_a_b_image_one.setOnClickListener(this);

        m_d_f_a_b_image_two = (ImageView) findViewById(R.id.m_d_f_a_b_image_two);
        m_d_f_a_b_image_two.setOnClickListener(this);

        m_d_f_a_b_image_three = (ImageView) findViewById(R.id.m_d_f_a_b_image_three);
        m_d_f_a_b_image_three.setOnClickListener(this);

        m_d_f_a_b_image_four = (ImageView) findViewById(R.id.m_d_f_a_b_image_four);
        m_d_f_a_b_image_four.setOnClickListener(this);

        m_d_f_a_b_image_five = (ImageView) findViewById(R.id.m_d_f_a_b_image_five);
        m_d_f_a_b_image_five.setOnClickListener(this);

        m_d_f_a_b_image_six = (ImageView) findViewById(R.id.m_d_f_a_b_image_six);
        m_d_f_a_b_image_six.setOnClickListener(this);

        m_d_f_a_b_image_seven = (ImageView) findViewById(R.id.m_d_f_a_b_image_seven);
        m_d_f_a_b_image_seven.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.m_d_f_a_b_image_one:
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new Chart_Dashboard_Fragment()).commit();

                break;
            case R.id.m_d_f_a_b_image_two:

                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new SmartCallerDashboardFragment()).commit();
                break;

            case R.id.m_d_f_a_b_image_three:

                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new ActivityDashboardFragmnet()).commit();
                break;

            case R.id.m_d_f_a_b_image_four:

                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new StudentDashboard()).commit();
                break;

            case R.id.m_d_f_a_b_image_five:

                SMSDashboardFragment smsFragment = new SMSDashboardFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.SMS_TYPE, Constant.SMS_NO_CLIENT);
                smsFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, smsFragment).commit();
                break;
            case R.id.m_d_f_a_b_image_six:
                EmailDashboardFragment emailDashboardFragment = new EmailDashboardFragment();
                Bundle emailBundle = new Bundle();
                emailBundle.putString(Constant.SMS_TYPE, Constant.SMS_NO_CLIENT);
                emailDashboardFragment.setArguments(emailBundle);
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, emailDashboardFragment).commit();
                break;
            case R.id.m_d_f_a_b_image_seven:
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new SettingsFragment()).commit();

                break;
        }

        hideKeyboard();
    }



    public void onBackPressed() {
        super.onBackPressed();
    }

    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = ((Activity) this).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK) {
            if (null != mOnMActivityResult)
                mOnMActivityResult.onMActivityResult(data);
        }
    }


    public void setOnMActivityResult(OnMActivityResult mOnMActivityResult) {
        this.mOnMActivityResult = mOnMActivityResult;

    }

    public interface OnMActivityResult {
        void onMActivityResult(Intent intent);
    }
}


