package com.example.deepak.myapplication.GroupDashboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.example.deepak.myapplication.Database.DAO.ParentDropDownDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.AttachmentDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.FormConstarins;
import com.example.deepak.myapplication.Database.DTO.ParentDropDownDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.EmailDashboard.EmailAttchmentAdapter;
import com.example.deepak.myapplication.MainActivity;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.StudentDashboard.StudentList;
import com.example.deepak.myapplication.Utility.Constant;
import com.example.deepak.myapplication.Utility.UserDataParser;
import com.example.deepak.myapplication.Utility.UserInfo;
import com.google.gson.Gson;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;

import static android.R.attr.data;


public class StudentProfile extends Fragment implements EmailAttchmentAdapter.OnAttchmentRemoved, View.OnTouchListener, MainActivity.OnMActivityResult, View.OnClickListener, GroupStudents.OnStudentClicked, StudentList.OnStudentSelected {
    LinearLayout form1_ll;
    TextView form1entity1, form1entity3, form1entity4;

    RecyclerView student_activities_recycler, student_attachment_recycler, form2_recycler;
    ImageView add_attachment_icon;
    ScrollView scroll_view;

    StudentDTO studentData;
    ArrayList<ActivityDTO> mList;
    ArrayList<AttachmentDTO> attachmentList;
    EmailAttchmentAdapter adapter1;
    ArrayList<ParentDropDownDTO> parentList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashboard_student_profile, container, false);
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnMActivityResult(this);
        inItView(view);
        return view;
    }

    private void inItView(View view) {
        form1_ll = (LinearLayout) view.findViewById(R.id.form1_ll);

        form1entity1 = (TextView) view.findViewById(R.id.form1entity1);
        form1entity3 = (TextView) view.findViewById(R.id.form1entity3);
        form1entity4 = (TextView) view.findViewById(R.id.form1entity4);

        parentList = new ParentDropDownDAO(getActivity()).getParentsDropDown();
        student_activities_recycler = (RecyclerView) view.findViewById(R.id.student_activities_recycler);
        student_activities_recycler.setOnTouchListener(this);
        student_attachment_recycler = (RecyclerView) view.findViewById(R.id.student_attachment_recycler);
        student_attachment_recycler.setOnTouchListener(this);

        form2_recycler = (RecyclerView) view.findViewById(R.id.form2_recycler);
        form2_recycler.setOnTouchListener(this);

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
        setUpAttachmentRecycler();
        setUpActivitiesRecycler();
    }

    private void setUpForm1() {
        form1entity1.setText(studentData.getForm1Entity1() + " " + studentData.getForm1Entity2());
        form1entity3.setText(studentData.getForm1Entity3());
        form1entity4.setText(studentData.getForm1Entity4());
    }


    private void setUpForm2Recycler() {
        ArrayList<StudentProfileFormDTO> list = new ArrayList<>();
        list.add(new StudentProfileFormDTO(parentList.get(4).getTitle(), studentData.getForm2Entity1()));
        list.add(new StudentProfileFormDTO(parentList.get(5).getTitle(), studentData.getForm2Entity2()));
        list.add(new StudentProfileFormDTO(parentList.get(6).getTitle(), studentData.getForm2Entity3()));
        list.add(new StudentProfileFormDTO(parentList.get(7).getTitle(), studentData.getForm2Entity4()));

        if (null != studentData.getForm3Entity1ID())
            list.add(new StudentProfileFormDTO(parentList.get(8).getTitle(), studentData.getForm3Entity1()));
        if (null != studentData.getForm3Entity2ID())
            list.add(new StudentProfileFormDTO(parentList.get(9).getTitle(), studentData.getForm3Entity2()));
        if (null != studentData.getForm3Entity3ID())
            list.add(new StudentProfileFormDTO(parentList.get(10).getTitle(), studentData.getForm3Entity3()));
        if (null != studentData.getForm3Entity4ID())
            list.add(new StudentProfileFormDTO(parentList.get(11).getTitle(), studentData.getForm3Entity4()));
        if (null != studentData.getForm3Entity5() && 0 != studentData.getForm3Entity5().length())
            list.add(new StudentProfileFormDTO(parentList.get(12).getTitle(), studentData.getForm3Entity5()));
        if (null != studentData.getForm3Entity6() && 0 != studentData.getForm3Entity6().length())
            list.add(new StudentProfileFormDTO(parentList.get(13).getTitle(), studentData.getForm3Entity6()));
        if (null != studentData.getForm3Entity7() && 0 != studentData.getForm3Entity7().length())
            list.add(new StudentProfileFormDTO(parentList.get(14).getTitle(), studentData.getForm3Entity7()));


        if (null != studentData.getForm4Entity1ID())
            list.add(new StudentProfileFormDTO(parentList.get(15).getTitle(), studentData.getForm2Entity1()));
        if (null != studentData.getForm4Entity2ID())
            list.add(new StudentProfileFormDTO(parentList.get(16).getTitle(), studentData.getForm4Entity2()));
        if (null != studentData.getForm4Entity3ID())
            list.add(new StudentProfileFormDTO(parentList.get(17).getTitle(), studentData.getForm4Entity3()));
        if (null != studentData.getForm4Entity4ID())
            list.add(new StudentProfileFormDTO(parentList.get(18).getTitle(), studentData.getForm4Entity4()));
        if (null != studentData.getForm4Entity5() && 0 != studentData.getForm4Entity5().length())
            list.add(new StudentProfileFormDTO(parentList.get(19).getTitle(), studentData.getForm4Entity5()));
        if (null != studentData.getForm4Entity6() && 0 != studentData.getForm4Entity6().length())
            list.add(new StudentProfileFormDTO(parentList.get(20).getTitle(), studentData.getForm4Entity6() + ""));
        if (null != studentData.getForm4Entity7() && 0 != studentData.getForm4Entity7().length())
            list.add(new StudentProfileFormDTO(parentList.get(21).getTitle(), studentData.getForm4Entity7() + ""));


        StudentProfileFormAdapter adapter = new StudentProfileFormAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        form2_recycler.setLayoutManager(manager);
        form2_recycler.setAdapter(adapter);

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

    public void studentClicked(StudentDTO sto, ViewPager viewPager) {
        scroll_view.setVisibility(View.VISIBLE);
        studentData = new StudentDAO(getActivity()).getStudentForNumber(sto.getForm1Entity4());
        setRecyclerView();
        viewPager.setCurrentItem(2);
    }


    public void onStudentClicked(StudentDTO dto, ViewPager viewPager) {
        viewPager.setCurrentItem(1);
        scroll_view.setVisibility(View.VISIBLE);
        studentData = new StudentDAO(getActivity()).getStudentForNumber(dto.getForm1Entity4());
        setRecyclerView();
    }
}
