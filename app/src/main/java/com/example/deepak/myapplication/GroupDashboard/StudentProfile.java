package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.FormConstarins;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;
import com.example.deepak.myapplication.Utility.UserDataParser;
import com.example.deepak.myapplication.Utility.UserInfo;

import java.util.ArrayList;

/**
 * Created by Deepak on 4/22/2017.
 */

public class StudentProfile extends Fragment {

    LinearLayout form1_ll, form2_ll, form3_ll, form4_ll;
    TextView form2_heading, form2entity1, form2entity2, form2entity3, form2entity4;
    TextView form1_heading, form1entity1, form1entity2, form1entity3, form1entity4;
    TextView form3_heading, form3entity1, form3entity2, form3entity3, form3entity4, form3entity5, form3entity6, form3entity7;
    TextView form4_heading, form4entity1, form4entity2, form4entity3, form4entity4, form4entity5, form4entity6, form4entity7;

    ArrayList<DropDownDataDTO> form2entity1DTO,form2entity2DTO,form2entity3DTO,form2entity4DTO;
    ArrayList<DropDownDataDTO> form3entity1DTO,form3entity2DTO,form3entity3DTO,form3entity4DTO;
    ArrayList<DropDownDataDTO> form4entity1DTO,form4entity2DTO,form4entity3DTO,form4entity4DTO;

    FormConstarins form1constains,form2constains,form3constains,form4constains;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashboard_student_profile, container, false);
        studentData = new StudentDAO(getActivity()).getStudentForNumber("9327668689");
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

        form2entity1 = (TextView) view.findViewById(R.id.form2entity1);
        form2entity2 = (TextView) view.findViewById(R.id.form2entity2);
        form2entity3 = (TextView) view.findViewById(R.id.form2entity3);
        form2entity4 = (TextView) view.findViewById(R.id.form2entity4);

        form3entity1 = (TextView) view.findViewById(R.id.form3entity1);
        form3entity2 = (TextView) view.findViewById(R.id.form3entity2);
        form3entity3 = (TextView) view.findViewById(R.id.form3entity3);
        form3entity4 = (TextView) view.findViewById(R.id.form3entity4);
        form3entity5 = (TextView) view.findViewById(R.id.form3entity5);
        form3entity6 = (TextView) view.findViewById(R.id.form3entity6);
        form3entity7 = (TextView) view.findViewById(R.id.form3entity7);

        form4entity1 = (TextView) view.findViewById(R.id.form4entity1);
        form4entity2 = (TextView) view.findViewById(R.id.form4entity2);
        form4entity3 = (TextView) view.findViewById(R.id.form4entity3);
        form4entity4 = (TextView) view.findViewById(R.id.form4entity4);
        form4entity5 = (TextView) view.findViewById(R.id.form4entity5);
        form4entity6 = (TextView) view.findViewById(R.id.form4entity6);
        form4entity7 = (TextView) view.findViewById(R.id.form4entity7);

        setData();
    }

    StudentDTO studentData;

    private void setData() {

        DropDownDataDAO dao = new DropDownDataDAO(getActivity());
        form2entity1DTO = dao.getFormData(Constant.FORM_TWO_CHILD_ONE_COMMON_CODE);
        form2entity2DTO = dao.getFormData(Constant.FORM_TWO_CHILD_TWO_COMMON_CODE);
        form2entity3DTO = dao.getFormData(Constant.FORM_TWO_CHILD_THREE_COMMON_CODE);
        form2entity4DTO = dao.getFormData(Constant.FORM_TWO_CHILD_FOUR_COMMON_CODE);

        form3entity1DTO = dao.getFormData(Constant.FORM_THREE_CHILD_ONE_COMMON_CODE);
        form3entity2DTO = dao.getFormData(Constant.FORM_THREE_CHILD_TWO_COMMON_CODE);
        form3entity3DTO = dao.getFormData(Constant.FORM_THREE_CHILD_THREE_COMMON_CODE);
        form3entity4DTO = dao.getFormData(Constant.FORM_THREE_CHILD_FOUR_COMMON_CODE);

        form4entity1DTO = dao.getFormData(Constant.FORM_FOUR_CHILD_ONE_COMMON_CODE);
        form4entity2DTO = dao.getFormData(Constant.FORM_FOUR_CHILD_TWO_COMMON_CODE);
        form4entity3DTO = dao.getFormData(Constant.FORM_FOUR_CHILD_THREE_COMMON_CODE);
        form4entity4DTO = dao.getFormData(Constant.FORM_FOUR_CHILD_FOUR_COMMON_CODE);

        form1constains = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_ONE_CONSTRAINS,getActivity());
        form2constains = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_TWO_CONSTRAINS,getActivity());
        form3constains = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_THREE_CONSTRAINS,getActivity());
        form4constains = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_FOUR_CONSTRAINS,getActivity());

        setDataForm1();
        setDataForm2();
        setDataForm3();
        setDataForm4();

    }
    private void setDataForm1() {
        form1entity1.setText(studentData.getForm1Entity1()+" "+studentData.getForm1Entity2());
        form1entity3.setText(studentData.getForm1Entity3());
        form1entity4.setText(studentData.getForm1Entity4());
    }
    private void setDataForm2() {
        if (!(studentData.getForm2Entity1ID() == 0 && studentData.getForm2Entity2ID() == 0 &&
                studentData.getForm2Entity3ID() == 0 && studentData.getForm2Entity4ID() == 0)) {
            form2_ll.setVisibility(View.VISIBLE);
            form2_heading.setText(form1constains.getTITLE());

            if (studentData.getForm2Entity1ID() != 0) {
                form2entity1.setVisibility(View.VISIBLE);
                for (int i=0; i<form2entity1DTO.size(); i++){
                    if (form2entity1DTO.get(i).getId() == studentData.getForm2Entity1ID())
                        form2entity1.setText(form2constains.getCHILD_ONE_TITLE()+"  "+form2entity1DTO.get(i).getTitle());
                }
            }

            if (studentData.getForm2Entity2ID() != 0) {
                form2entity2.setVisibility(View.VISIBLE);
                for (int i=0; i<form2entity2DTO.size(); i++){
                    if (form2entity2DTO.get(i).getId() == studentData.getForm2Entity2ID())
                        form2entity2.setText(form2constains.getCHILD_TWO_TITLE()+"  "+form2entity2DTO.get(i).getTitle());
                }
            }

            if (studentData.getForm2Entity3ID() != 0) {
                form2entity3.setVisibility(View.VISIBLE);
                for (int i=0; i<form3entity3DTO.size(); i++){
                    if (form2entity3DTO.get(i).getId() == studentData.getForm2Entity3ID())
                        form2entity3.setText(form2constains.getCHILD_THREE_TITLE()+"  "+form2entity3DTO.get(i).getTitle());
                }
            }
            if (studentData.getForm2Entity4ID() != 0) {
                form2entity4.setVisibility(View.VISIBLE);
                for (int i=0; i<form2entity4DTO.size(); i++){
                    if (form2entity4DTO.get(i).getId() == studentData.getForm2Entity4ID())
                        form2entity4.setText(form2constains.getCHILD_FOUR_TITLE()+"  "+form2entity4DTO.get(i).getTitle());
                }
            }
        }
    }
    private void setDataForm3() {
        if (!(studentData.getForm3Entity1ID() == 0 && studentData.getForm3Entity2ID() == 0 &&
                studentData.getForm3Entity3ID() == 0 && studentData.getForm3Entity4ID() == 0 &&
                (studentData.getForm3Entity5() == null || studentData.getForm3Entity5().length()>0) &&
                (studentData.getForm3Entity6() == null || studentData.getForm3Entity6().length()>0)&&
                (studentData.getForm3Entity7() == null ||studentData.getForm3Entity7().length()>0))) {
            form3_ll.setVisibility(View.VISIBLE);
            Log.d("rohit", "form3 visible ");
            if (studentData.getForm3Entity1ID() != 0) {
                form3entity1.setVisibility(View.VISIBLE);
                for (int i=0; i<form3entity1DTO.size(); i++){
                    if (form3entity1DTO.get(i).getId() == studentData.getForm3Entity1ID())
                        form3entity1.setText(form3constains.getCHILD_ONE_TITLE()+"  "+form3entity1DTO.get(i).getTitle());
                }
            }

            if (studentData.getForm3Entity2ID() != 0) {
                form3entity2.setVisibility(View.VISIBLE);
                for (int i=0; i<form3entity2DTO.size(); i++){
                    if (form3entity2DTO.get(i).getId() == studentData.getForm3Entity2ID())
                        form3entity2.setText(form3constains.getCHILD_TWO_TITLE()+"  "+form3entity2DTO.get(i).getTitle());
                }
            }

            if (studentData.getForm3Entity3ID() != 0) {
                form3entity3.setVisibility(View.VISIBLE);
                for (int i=0; i<form3entity3DTO.size(); i++){
                    if (form3entity3DTO.get(i).getId() == studentData.getForm3Entity3ID())
                        form3entity3.setText(form3constains.getCHILD_THREE_TITLE()+"  "+form3entity3DTO.get(i).getTitle());
                }
            }
            if (studentData.getForm3Entity4ID() != 0) {
                form3entity4.setVisibility(View.VISIBLE);
                for (int i=0; i<form3entity4DTO.size(); i++){
                    if (form3entity4DTO.get(i).getId() == studentData.getForm3Entity4ID())
                        form3entity4.setText(form3constains.getCHILD_FOUR_TITLE()+"  "+form3entity4DTO.get(i).getTitle());
                }
            }


            if (studentData.getForm3Entity5() != null && studentData.getForm3Entity5().length()>0) {
                form3entity5.setVisibility(View.VISIBLE);
                form3entity5.setText(form3constains.getCHILD_FIVE_TITLE()+" "+studentData.getForm3Entity5() );
            }

            if (studentData.getForm3Entity6() != null && studentData.getForm3Entity6().length()>0) {
                form3entity6.setVisibility(View.VISIBLE);
                form3entity6.setText(form3constains.getCHILD_SIX_TITLE()+" "+studentData.getForm3Entity6() );
            }
            if (studentData.getForm3Entity7() != null && studentData.getForm3Entity7().length()>0) {
                form3entity7.setVisibility(View.VISIBLE);
                form3entity7.setText(form3constains.getCHILD_SEVEN_TITLE()+" "+studentData.getForm3Entity7() );
            }
        }
    }
    private void setDataForm4() {

        if (!(studentData.getForm4Entity1ID() == 0 && studentData.getForm4Entity2ID() == 0 &&
                studentData.getForm4Entity3ID() == 0 && studentData.getForm4Entity4ID() == 0 &&
                (studentData.getForm4Entity5() == null || studentData.getForm4Entity5().length() == 0) &&
                (studentData.getForm4Entity6() == null || studentData.getForm4Entity6().length() == 0)&&
                (studentData.getForm4Entity7() == null ||studentData.getForm4Entity7().length() == 0))) {
            form4_ll.setVisibility(View.VISIBLE);
            Log.d("rohit", "form4 visible ");
            if (studentData.getForm4Entity1ID() != 0) {
                form4entity1.setVisibility(View.VISIBLE);
                for (int i=0; i<form4entity1DTO.size(); i++){
                    if (form4entity1DTO.get(i).getId() == studentData.getForm4Entity1ID())
                        form4entity1.setText(form4constains.getCHILD_ONE_TITLE()+"  "+form4entity1DTO.get(i).getTitle());
                }
            }

            if (studentData.getForm4Entity2ID() != 0) {
                form4entity2.setVisibility(View.VISIBLE);
                for (int i=0; i<form4entity2DTO.size(); i++){
                    if (form4entity2DTO.get(i).getId() == studentData.getForm4Entity2ID())
                        form4entity2.setText(form4constains.getCHILD_TWO_TITLE()+"  "+form4entity2DTO.get(i).getTitle());
                }
            }

            if (studentData.getForm4Entity3ID() != 0) {
                form4entity3.setVisibility(View.VISIBLE);
                for (int i=0; i<form4entity3DTO.size(); i++){
                    if (form4entity3DTO.get(i).getId() == studentData.getForm4Entity3ID())
                        form4entity3.setText(form4constains.getCHILD_THREE_TITLE()+"  "+form4entity3DTO.get(i).getTitle());
                }
            }
            if (studentData.getForm4Entity4ID() != 0) {
                form4entity4.setVisibility(View.VISIBLE);
                for (int i=0; i<form4entity4DTO.size(); i++){
                    if (form4entity4DTO.get(i).getId() == studentData.getForm4Entity4ID())
                        form4entity4.setText(form4constains.getCHILD_FOUR_TITLE()+"  "+form4entity4DTO.get(i).getTitle());
                }
            }


            if (studentData.getForm4Entity5() != null && studentData.getForm4Entity5().length()>0) {
                form4entity5.setVisibility(View.VISIBLE);
                form4entity5.setText(form4constains.getCHILD_FIVE_TITLE()+"  "+studentData.getForm4Entity5());
            }

            if (studentData.getForm4Entity6() != null && studentData.getForm4Entity6().length()>0) {
                form4entity6.setVisibility(View.VISIBLE);
                form4entity6.setText(form4constains.getCHILD_SIX_TITLE()+"  "+studentData.getForm4Entity6());
            }
            if (studentData.getForm4Entity7() != null && studentData.getForm4Entity7().length()>0) {
                form4entity7.setVisibility(View.VISIBLE);
                form4entity7.setText(form4constains.getCHILD_SEVEN_TITLE()+"  "+studentData.getForm4Entity7());
            }
        }
    }


}
