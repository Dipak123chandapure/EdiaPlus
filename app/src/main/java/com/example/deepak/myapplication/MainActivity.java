package com.example.deepak.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.deepak.myapplication.ActivityDashboard.ActivityDashboardFragmnet;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Database.OfflineDatabase;
import com.example.deepak.myapplication.EmailDashboard.EmailDashboardFragment;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.SmartCaller.SmartCallerDashboardFragment;
import com.example.deepak.myapplication.StudentDashboard.StudentDashboardFragment;
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



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inItView();

        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, new StudentDashboardFragment()).commit();


//        try {
//            JSONObject jsonObject = new JSONObject(new ModalData().getAllMasters());
//            UserDataParser.parseAllMasters(new ModalData().getAllMasters(), this);
//        } catch (JSONException e) {
//            Log.d("rohit ", "parsing exception " + e.toString());
//            e.printStackTrace();
//        }
//
//        setUpDefaultCommonCode();

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
                        .replace(R.id.main_frame_layout, new StudentDashboardFragment()).commit();
                break;

            case R.id.m_d_f_a_b_image_five:
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new SMSDashboardFragment()).commit();
                break;
            case R.id.m_d_f_a_b_image_six:
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new EmailDashboardFragment()).commit();
                break;
            case R.id.m_d_f_a_b_image_seven:
                break;
        }

        hideKeyboard();
    }

    String firstNames[] = {"Rohan", "Sohan", "Mohan", "Rohit", "Deepak", "Kunal", "Girish", "Shirish",
            "Sonali", "Kunal", "Manohar", "Haridas", "Shankar", "Santosh", "Rana", "Samir",
            "Ronit", "John", "Jacky", "Alexander", "Omkar", "Swatee", "Sonali", "Aditi"};

    String lastNames[] = {"Kumar", "Khanna", "Raut", "Bond", "Pandy", "Bandewar", "Ranawat", "Deshmukh",
            "Kapur", "Khan", "Ranjan", "Chandapure", "Kalkatwad", "Kadpewad", "Gundawar", "Nayar",
            "Bhamidipati", "Sinhan", "Chaan", "Great", "Mirzapure", "Raut", "shirsagr", "Narkar"};

    String TYPE[] = {
            Constant.FORM_TWO_CHILD_ONE_COMMON_CODE, Constant.FORM_TWO_CHILD_TWO_COMMON_CODE,
            Constant.FORM_TWO_CHILD_THREE_COMMON_CODE, Constant.FORM_TWO_CHILD_FOUR_COMMON_CODE,

            Constant.FORM_THREE_CHILD_ONE_COMMON_CODE, Constant.FORM_THREE_CHILD_TWO_COMMON_CODE,
            Constant.FORM_THREE_CHILD_THREE_COMMON_CODE, Constant.FORM_THREE_CHILD_FOUR_COMMON_CODE,

            Constant.FORM_FOUR_CHILD_ONE_COMMON_CODE, Constant.FORM_FOUR_CHILD_TWO_COMMON_CODE,
            Constant.FORM_FOUR_CHILD_THREE_COMMON_CODE, Constant.FORM_FOUR_CHILD_FOUR_COMMON_CODE,
    };

    public void setUpDefaultCommonCode(){
//        DropDownDataDAO dao = new DropDownDataDAO(this);
//        for (int i =0; i<12; i++){
//            for (int j = 0; j<5; j++){
//                DropDownDataDTO dropDownDataDTO = new DropDownDataDTO();
//                dropDownDataDTO.setTitle("parent "+ i +"test "+j);
//                dropDownDataDTO.setDetails("parent "+ i +"test "+j);
//                dropDownDataDTO.setSystemValue(false);
//                dropDownDataDTO.setVirtuallyDeleted(false);
//                dao.saveFormData(TYPE[i], dropDownDataDTO);
//            }
//        }


        setUpDefaultData();
    }









    private void setUpDefaultData() {

        StudentDAO handler = new StudentDAO(this);
        Random r = new Random();
        ArrayList<String> list = new ArrayList<>();
        list.add("https://www.recordingfile.location/file1.mp3");
        list.add("https://www.recordingfile.location/file5.mp3");
        String listString = new Gson().toJson(list);
        for (int i = 0; i < 1000; i++) {
            StudentDTO data = new StudentDTO();
            String firstName = firstNames[r.nextInt(firstNames.length)];
            String lastName = lastNames[r.nextInt(lastNames.length)];
            String mob_no = r.nextInt(1000000000) + "";

            data.setForm1Entity1(firstName);
            data.setForm1Entity2(lastName);
            data.setForm1Entity3(firstName + lastName.charAt(0) + "@gmail.com");
            data.setForm1Entity4(mob_no);

            data.setForm2Entity1ID(r.nextInt(4)+1);
            data.setForm2Entity2ID(r.nextInt(4)+1);
            data.setForm2Entity3ID(r.nextInt(4)+1);
            data.setForm2Entity4ID(r.nextInt(4)+1);

            data.setForm3Entity1ID(r.nextInt(4)+1);
            data.setForm3Entity2ID(r.nextInt(4)+1);
            data.setForm3Entity3ID(r.nextInt(4)+1);


            Long date = getRandomDate();
            data.setCreatedOnMilli(date);
            data.setUpdatedONMilli(date);
            data.setCreatedOn(date+"");
            data.setUpdatedON(date+"");
            data.setSyncStatus("NEW");

            data.setStudentDataJSON(new Gson().toJson(data));
            handler.addStudent(data);

            ActivityDTO data1 = new ActivityDTO();
            data1.setNextActionDate(date);
            data1.setModificationDate(date);
            data1.setCreatedDate(date);

            data1.setForm1Entity1(firstName +" " +lastName);
            data1.setForm1Entity3(firstName + lastName.charAt(0) + "@gmail.com");
            data1.setForm1Entity4(mob_no);

            data1.setActivityComment("In the algorithm above, k (a parameter of the algorithm) is the number In the algorithm above,");
            data1.setActvityTypeID(r.nextInt(5)+1);
            data1.setActivityBody("In the algorithm above, k (a parameter of the algorithm) is the number\n" +
                    "of clusters we want to find; and the cluster centroids μj represent our current\n" +
                    "guesses for the positions of the centers of the clusters. To initialize the cluster\n" +
                    "centroids (in step 1 of the algorithm above), we could choose k training\n" +
                    "examples randomly, and set the cluster centroids to be equal to the values of");

            data1.setActivityAttachmentList(listString);
            data1.setSmartCallDuration(r.nextInt(3)+"m "+r.nextInt(60)+"s");
            data1.setActivityDataJSON(new Gson().toJson(data1));

            new ActivitiesDAO(this).addActivity(data1);
        }
    }

    private Long getRandomDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh mm", Locale.getDefault());
        Calendar calender = Calendar.getInstance();
        int month = new Random().nextInt(12);
        int date = new Random().nextInt(28);
        int hours = new Random().nextInt(12);
        int min = new Random().nextInt(60);

        calender.set(2017, month, date,hours,min);
        Log.d("rohit", "Date: " + fmt.format(calender.getTime()));
        Log.d("rohit", "Time: " + calender.getTime().getTime());
        return calender.getTime().getTime();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK) {
            //String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            if (null != mOnMActivityResult)
                mOnMActivityResult.onMActivityResult(data);
            //Log.d("rohit", "file path" + filePath);
        }
    }
    OnMActivityResult mOnMActivityResult;

    public void setOnMActivityResult(OnMActivityResult mOnMActivityResult) {
        this.mOnMActivityResult = mOnMActivityResult;

    }

    public interface OnMActivityResult {
        void onMActivityResult(Intent intent);
    }

    @Override
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
}


