package com.example.deepak.myapplication.GroupDashboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.deepak.myapplication.ActivityDashboard.ActivityDashboardActivityListAdapter;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.AttachmentDAO;
import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.AttachmentDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.FormConstarins;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.EmailDashboard.EmailAttchmentAdapter;
import com.example.deepak.myapplication.MainActivity;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;
import com.example.deepak.myapplication.Utility.UserDataParser;
import com.example.deepak.myapplication.Utility.UserInfo;
import com.google.gson.Gson;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;

import static android.R.attr.data;

/**
 * Created by Deepak on 4/22/2017.
 */

public class StudentProfile extends Fragment implements EmailAttchmentAdapter.OnAttchmentRemoved, View.OnTouchListener, MainActivity.OnMActivityResult, View.OnClickListener {

    LinearLayout form1_ll, form2_ll, form3_ll, form4_ll;
    TextView form1entity1, form1entity3, form1entity4, form2_heading, form3_heading, form4_heading;

    FormConstarins form2constains, form3constains, form4constains;
    RecyclerView student_activities_recycler, student_attachment_recycler, form2_recycler, form4_recycler, form3_recycler;

    ImageView add_attachment_icon;
    ScrollView scroll_view;

    StudentDTO studentData;
    ArrayList<ActivityDTO> mList;
    ArrayList<AttachmentDTO> attachmentList;
    EmailAttchmentAdapter adapter1;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashboard_student_profile, container, false);
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnMActivityResult(this);
        inItView(view);
        return view;
    }

    private void inItView(View view) {

        form1_ll = (LinearLayout) view.findViewById(R.id.form1_ll);
        form2_ll = (LinearLayout) view.findViewById(R.id.form2_ll);
        form3_ll = (LinearLayout) view.findViewById(R.id.form3_ll);
        form4_ll = (LinearLayout) view.findViewById(R.id.form4_ll);

        form2_heading = (TextView) view.findViewById(R.id.form2_heading);
        form3_heading = (TextView) view.findViewById(R.id.form3_heading);
        form4_heading = (TextView) view.findViewById(R.id.form4_heading);

        form1entity1 = (TextView) view.findViewById(R.id.form1entity1);
        form1entity3 = (TextView) view.findViewById(R.id.form1entity3);
        form1entity4 = (TextView) view.findViewById(R.id.form1entity4);

        form2constains = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_TWO_CONSTRAINS, getActivity());
        form3constains = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_THREE_CONSTRAINS, getActivity());
        form4constains = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_FOUR_CONSTRAINS, getActivity());

        student_activities_recycler = (RecyclerView) view.findViewById(R.id.student_activities_recycler);
        student_activities_recycler.setOnTouchListener(this);
        student_attachment_recycler = (RecyclerView) view.findViewById(R.id.student_attachment_recycler);
        student_attachment_recycler.setOnTouchListener(this);

        form2_recycler = (RecyclerView) view.findViewById(R.id.form2_recycler);
        form2_recycler.setOnTouchListener(this);
        form3_recycler = (RecyclerView) view.findViewById(R.id.form3_recycler);
        form3_recycler.setOnTouchListener(this);
        form4_recycler = (RecyclerView) view.findViewById(R.id.form4_recycler);
        form4_recycler.setOnTouchListener(this);

        add_attachment_icon = (ImageView) view.findViewById(R.id.add_attachment_icon);
        add_attachment_icon.setOnClickListener(this);

        scroll_view = (ScrollView) view.findViewById(R.id.scroll_view);
        if (null != studentData)
            setRecyclerView();
        else scroll_view.setVisibility(View.GONE);
    }

    private void setRecyclerView() {
        setUpForm1();
        setUpForm2Recycler();
        setUpForm3Recycler();
        setUpForm4Recycler();
        setUpAttachmentRecycler();
        setUpActivitiesRecycler();
    }

    private void setUpForm1() {
        form1entity1.setText(studentData.getForm1Entity1() + " " + studentData.getForm1Entity2());
        form1entity3.setText(studentData.getForm1Entity3());
        form1entity4.setText(studentData.getForm1Entity4());
    }


    private void setUpForm4Recycler() {

        ArrayList<StudentProfileFormDTO> list = new ArrayList<>();
        if (0 != studentData.getForm4Entity1ID())
            list.add(new StudentProfileFormDTO(form4constains.getCHILD_ONE_TITLE(), studentData.getForm2Entity1()));
        if (0 != studentData.getForm4Entity2ID())
            list.add(new StudentProfileFormDTO(form4constains.getCHILD_TWO_TITLE(), studentData.getForm4Entity2()));
        if (0 != studentData.getForm4Entity3ID())
            list.add(new StudentProfileFormDTO(form4constains.getCHILD_THREE_TITLE(), studentData.getForm4Entity3()));
        if (0 != studentData.getForm4Entity4ID())
            list.add(new StudentProfileFormDTO(form4constains.getCHILD_FOUR_TITLE(), studentData.getForm4Entity4()));
        if (null != studentData.getForm4Entity5() && 0 != studentData.getForm4Entity5().length())
            list.add(new StudentProfileFormDTO(form4constains.getCHILD_FIVE_TITLE(), studentData.getForm4Entity5()));
        if (null != studentData.getForm4Entity6() && 0 != studentData.getForm4Entity6().length())
            list.add(new StudentProfileFormDTO(form4constains.getCHILD_SIX_TITLE(), studentData.getForm4Entity6() + ""));
        if (null != studentData.getForm4Entity7() && 0 != studentData.getForm4Entity7().length())
            list.add(new StudentProfileFormDTO(form4constains.getCHILD_SEVEN_TITLE(), studentData.getForm4Entity7() + ""));
        if (list.size() > 0) {
            form4_heading.setText(form4constains.getTITLE());
            StudentProfileFormAdapter adapter = new StudentProfileFormAdapter(getActivity(), list);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            form4_recycler.setLayoutManager(manager);
            form4_recycler.setAdapter(adapter);
        } else form4_ll.setVisibility(View.GONE);
    }

    private void setUpForm3Recycler() {

        ArrayList<StudentProfileFormDTO> list = new ArrayList<>();
        if (0 != studentData.getForm3Entity1ID())
            list.add(new StudentProfileFormDTO(form3constains.getCHILD_ONE_TITLE(), studentData.getForm3Entity1()));
        if (0 != studentData.getForm3Entity2ID())
            list.add(new StudentProfileFormDTO(form3constains.getCHILD_TWO_TITLE(), studentData.getForm3Entity2()));
        if (0 != studentData.getForm3Entity3ID())
            list.add(new StudentProfileFormDTO(form3constains.getCHILD_THREE_TITLE(), studentData.getForm3Entity3()));
        if (0 != studentData.getForm3Entity4ID())
            list.add(new StudentProfileFormDTO(form3constains.getCHILD_FOUR_TITLE(), studentData.getForm3Entity4()));
        if (null != studentData.getForm3Entity5() && 0 != studentData.getForm3Entity5().length())
            list.add(new StudentProfileFormDTO(form3constains.getCHILD_FIVE_TITLE(), studentData.getForm3Entity5()));
        if (null != studentData.getForm3Entity6() && 0 != studentData.getForm3Entity6().length())
            list.add(new StudentProfileFormDTO(form3constains.getCHILD_SIX_TITLE(), studentData.getForm3Entity6()));
        if (null != studentData.getForm3Entity7() && 0 != studentData.getForm3Entity7().length())
            list.add(new StudentProfileFormDTO(form3constains.getCHILD_SEVEN_TITLE(), studentData.getForm3Entity7()));

        if (list.size() > 0) {
            form3_heading.setText(form3constains.getTITLE());
            StudentProfileFormAdapter adapter = new StudentProfileFormAdapter(getActivity(), list);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            form3_recycler.setLayoutManager(manager);
            form3_recycler.setAdapter(adapter);
        } else form3_ll.setVisibility(View.GONE);
    }

    private void setUpForm2Recycler() {
        ArrayList<StudentProfileFormDTO> list = new ArrayList<>();
        list.add(new StudentProfileFormDTO(form2constains.getCHILD_ONE_TITLE(), studentData.getForm2Entity1()));
        list.add(new StudentProfileFormDTO(form2constains.getCHILD_TWO_TITLE(), studentData.getForm2Entity2()));
        list.add(new StudentProfileFormDTO(form2constains.getCHILD_THREE_TITLE(), studentData.getForm2Entity3()));
        list.add(new StudentProfileFormDTO(form2constains.getCHILD_FOUR_TITLE(), studentData.getForm2Entity4()));
        Log.d("rohit", "form21title " + form2constains.getCHILD_ONE_TITLE());
        if (list.size() > 0) {
            form2_heading.setText(form2constains.getTITLE());
            StudentProfileFormAdapter adapter = new StudentProfileFormAdapter(getActivity(), list);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            form2_recycler.setLayoutManager(manager);
            form2_recycler.setAdapter(adapter);
        } else form2_ll.setVisibility(View.GONE);
    }

    private void setUpActivitiesRecycler() {
        attachmentList = new AttachmentDAO(getActivity()).getAttachment(studentData);
        adapter1 = new EmailAttchmentAdapter(getActivity(), attachmentList);
        adapter1.setOnAttchmentRemoved(this);
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        student_attachment_recycler.setLayoutManager(manager2);
        student_attachment_recycler.setAdapter(adapter1);
    }

    private void setUpAttachmentRecycler() {
        mList = new ActivitiesDAO(getActivity()).getActivitiesForStudent(studentData);
        Log.d("rohit", "size " + mList.size());
        StudentProfileActivitiesAdapter adapter = new StudentProfileActivitiesAdapter(getActivity(), mList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        student_activities_recycler.setLayoutManager(manager);
        student_activities_recycler.setAdapter(adapter);
    }


    public void onAttchmentRemoved(int poition) {
        new AttachmentDAO(getActivity()).removeAttachment(attachmentList.get(poition));

        attachmentList.remove(poition);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter1.notifyDataSetChanged();
            }
        });

    }

    public void changeStudent(StudentDTO sto) {
        scroll_view.setVisibility(View.VISIBLE);
        studentData = new StudentDAO(getActivity()).getStudentForNumber(sto.getForm1Entity4());
        setRecyclerView();
    }


    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }


    public void onMActivityResult(Intent data) {
        String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
        AttachmentDAO dao = new AttachmentDAO(getActivity());
        AttachmentDTO attachment = new AttachmentDTO();
        attachment.setSection("test section");
        attachment.setUrl(filePath);
        ArrayList<AttachmentDTO> attamentLsit = new ArrayList<>();
        attamentLsit.add(attachment);
        dao.saveAttachment(studentData, attamentLsit);

        attachmentList.add(attachment);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter1.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            new MaterialFilePicker()
                    .withActivity(getActivity())
                    .withRequestCode(10)
                    .start();
        } else {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }


    }
}
