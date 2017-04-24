package com.example.deepak.myapplication.AddStudent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.FormConstarins;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.StudentDashboard.StudentDashboard;
import com.example.deepak.myapplication.Utility.Constant;
import com.example.deepak.myapplication.Utility.UserDataParser;
import com.example.deepak.myapplication.Utility.UserInfo;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Calendar;

public class AddStudentFragment extends Fragment implements View.OnClickListener, DropDownDialogRecyclerAdapter.OnAddLeadCommonCodeDialogItemSelected {

    LinearLayout a_l_f_l_grand_parent_ll;
    LinearLayout form_one_ll, form_two_ll, form_three_ll, form_four_ll;
    MaterialEditText form1entity1, form1entity2, form1entity3, form1entity4;
    MaterialEditText form2entity1, form2entity2, form2entity3, form2entity4;
    MaterialEditText form3entity1, form3entity2, form3entity3, form3entity4, form3entity5, form3entity6, form3entity7;
    MaterialEditText form4entity1, form4entity2, form4entity3, form4entity4, form4entity5, form4entity6, form4entity7;

    FormConstarins form1constarins, form2constarins, form3constarins, form4constarins;

    DropDownDataDTO form2entity1DTO,form2entity2DTO,form2entity3DTO,form2entity4DTO,
            form3entity1DTO,form3entity2DTO,form3entity3DTO,form3entity4DTO,
            form4entity1DTO,form4entity2DTO,form4entity3DTO,form4entity4DTO;


    Button add_edit_btn, cancel_btn;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_student, container, false);

        inItView(view);
        inItForm();

        return view;
    }

    private void inItView(View view) {
        form_one_ll = (LinearLayout) view.findViewById(R.id.form_one_ll);
        form_two_ll = (LinearLayout) view.findViewById(R.id.form_two_ll);
        form_three_ll = (LinearLayout) view.findViewById(R.id.form_three_ll);
        form_four_ll = (LinearLayout) view.findViewById(R.id.form_four_ll);

        form1entity1 = (MaterialEditText) view.findViewById(R.id.form1entity1);
        form1entity2 = (MaterialEditText) view.findViewById(R.id.form1entity2);
        form1entity3 = (MaterialEditText) view.findViewById(R.id.form1entity3);
        form1entity4 = (MaterialEditText) view.findViewById(R.id.form1entity4);

        form2entity1 = (MaterialEditText) view.findViewById(R.id.form2entity1);
        form2entity2 = (MaterialEditText) view.findViewById(R.id.form2entity2);
        form2entity3 = (MaterialEditText) view.findViewById(R.id.form2entity3);
        form2entity4 = (MaterialEditText) view.findViewById(R.id.form2entity4);
        form2entity1.setOnClickListener(this);
        form2entity2.setOnClickListener(this);
        form2entity3.setOnClickListener(this);
        form2entity4.setOnClickListener(this);

        form3entity1 = (MaterialEditText) view.findViewById(R.id.form3entity1);
        form3entity2 = (MaterialEditText) view.findViewById(R.id.form3entity2);
        form3entity3 = (MaterialEditText) view.findViewById(R.id.form3entity3);
        form3entity4 = (MaterialEditText) view.findViewById(R.id.form3entity4);
        form3entity5 = (MaterialEditText) view.findViewById(R.id.form3entity5);
        form3entity6 = (MaterialEditText) view.findViewById(R.id.form3entity6);
        form3entity7 = (MaterialEditText) view.findViewById(R.id.form3entity7);
        form3entity1.setOnClickListener(this);
        form3entity2.setOnClickListener(this);
        form3entity3.setOnClickListener(this);
        form3entity4.setOnClickListener(this);

        form4entity1 = (MaterialEditText) view.findViewById(R.id.form4entity1);
        form4entity2 = (MaterialEditText) view.findViewById(R.id.form4entity2);
        form4entity3 = (MaterialEditText) view.findViewById(R.id.form4entity3);
        form4entity4 = (MaterialEditText) view.findViewById(R.id.form4entity4);
        form4entity5 = (MaterialEditText) view.findViewById(R.id.form4entity5);
        form4entity6 = (MaterialEditText) view.findViewById(R.id.form4entity6);
        form4entity7 = (MaterialEditText) view.findViewById(R.id.form4entity7);
        form4entity1.setOnClickListener(this);
        form4entity2.setOnClickListener(this);
        form4entity3.setOnClickListener(this);
        form4entity4.setOnClickListener(this);

        add_edit_btn = (Button) view.findViewById(R.id.add_edit_btn);
        add_edit_btn.setOnClickListener(this);
        cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);
    }

    private void inItForm() {
        inItForm1();
        inItForm2();
        inItForm3();
        inItform4();
    }

    private void inItForm1() {
        form1constarins = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_ONE_CONSTRAINS, getActivity());
        if (null != form1constarins) {
            Log.d("rohit", "formone" + new Gson().toJson(form1constarins));
            if (form1constarins.getIS_VISIBLE()) {
                form_one_ll.setVisibility(View.VISIBLE);
                Log.d("rohit", "isvisible" + form1constarins.getIS_CHILD_ONE_VISIBLE());
                if (form1constarins.getIS_CHILD_ONE_VISIBLE()) {
                    form1entity1.setVisibility(View.VISIBLE);
                    form1entity1.setHint(form1constarins.getCHILD_ONE_TITLE());
                    form1entity1.setFloatingLabelText(form1constarins.getCHILD_ONE_TITLE());
                }

                if (form1constarins.getIS_CHILD_TWO_VISIBLE()) {
                    form1entity2.setVisibility(View.VISIBLE);
                    form1entity2.setHint(form1constarins.getCHILD_TWO_TITLE());
                    form1entity2.setFloatingLabelText(form1constarins.getCHILD_TWO_TITLE());
                }

                if (form1constarins.getIS_CHILD_THREE_VISIBLE()) {
                    form1entity3.setVisibility(View.VISIBLE);
                    form1entity3.setHint(form1constarins.getCHILD_THREE_TITLE());
                    form1entity3.setFloatingLabelText(form1constarins.getCHILD_THREE_TITLE());
                }

                if (form1constarins.getIS_CHILD_FOUR_VISIBLE()) {
                    form1entity4.setVisibility(View.VISIBLE);
                    form1entity4.setHint(form1constarins.getCHILD_FOUR_TITLE());
                    form1entity4.setFloatingLabelText(form1constarins.getCHILD_FOUR_TITLE());
                }
            }
        }
    }

    private void inItForm2() {
        form2constarins = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_TWO_CONSTRAINS, getActivity());
        if (null != form2constarins) {
            Log.d("rohit", "formTWO" + new Gson().toJson(form2constarins));
            if (form2constarins.getIS_VISIBLE()) {
                form_two_ll.setVisibility(View.VISIBLE);
                Log.d("rohit", "isvisible" + form2constarins.getIS_CHILD_TWO_VISIBLE());
                if (form2constarins.getIS_CHILD_ONE_VISIBLE()) {
                    form2entity1.setVisibility(View.VISIBLE);
                    form2entity1.setHint(form2constarins.getCHILD_ONE_TITLE());
                    form2entity1.setFloatingLabelText(form2constarins.getCHILD_ONE_TITLE());
                }

                if (form2constarins.getIS_CHILD_TWO_VISIBLE()) {
                    form2entity2.setVisibility(View.VISIBLE);
                    form2entity2.setHint(form2constarins.getCHILD_TWO_TITLE());
                    form2entity2.setFloatingLabelText(form2constarins.getCHILD_TWO_TITLE());
                }

                if (form2constarins.getIS_CHILD_THREE_VISIBLE()) {
                    form2entity3.setVisibility(View.VISIBLE);
                    form2entity3.setHint(form2constarins.getCHILD_THREE_TITLE());
                    form2entity3.setFloatingLabelText(form2constarins.getCHILD_THREE_TITLE());
                }

                if (form2constarins.getIS_CHILD_FOUR_VISIBLE()) {
                    form2entity4.setVisibility(View.VISIBLE);
                    form2entity4.setHint(form2constarins.getCHILD_FOUR_TITLE());
                    form2entity4.setFloatingLabelText(form2constarins.getCHILD_FOUR_TITLE());
                }
            }
        }
    }

    private void inItForm3() {

        form3constarins = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_THREE_CONSTRAINS, getActivity());
        if (null != form3constarins) {
            Log.d("rohit", "formone" + new Gson().toJson(form3constarins));
            if (form3constarins.getIS_VISIBLE()) {
                form_three_ll.setVisibility(View.VISIBLE);
                Log.d("rohit", "isvisible" + form3constarins.getIS_CHILD_ONE_VISIBLE());
                if (form3constarins.getIS_CHILD_ONE_VISIBLE()) {
                    form3entity1.setVisibility(View.VISIBLE);
                    form3entity1.setHint(form3constarins.getCHILD_ONE_TITLE());
                    form3entity1.setFloatingLabelText(form3constarins.getCHILD_ONE_TITLE());
                }

                if (form3constarins.getIS_CHILD_TWO_VISIBLE()) {
                    form3entity2.setVisibility(View.VISIBLE);
                    form3entity2.setHint(form3constarins.getCHILD_TWO_TITLE());
                    form3entity2.setFloatingLabelText(form3constarins.getCHILD_TWO_TITLE());
                }

                if (form3constarins.getIS_CHILD_THREE_VISIBLE()) {
                    form3entity3.setVisibility(View.VISIBLE);
                    form3entity3.setHint(form3constarins.getCHILD_THREE_TITLE());
                    form3entity3.setFloatingLabelText(form3constarins.getCHILD_THREE_TITLE());
                }

                if (form3constarins.getIS_CHILD_FOUR_VISIBLE()) {
                    form3entity4.setVisibility(View.VISIBLE);
                    form3entity4.setHint(form3constarins.getCHILD_FOUR_TITLE());
                    form3entity4.setFloatingLabelText(form3constarins.getCHILD_FOUR_TITLE());
                }

                if (form3constarins.getIS_CHILD_FIVE_VISIBLE()) {
                    form3entity5.setVisibility(View.VISIBLE);
                    form3entity5.setHint(form3constarins.getCHILD_FIVE_TITLE());
                    form3entity5.setFloatingLabelText(form3constarins.getCHILD_FIVE_TITLE());
                }


                if (form3constarins.getIS_CHILD_SIX_VISIBLE()) {
                    form3entity6.setVisibility(View.VISIBLE);
                    form3entity6.setHint(form3constarins.getCHILD_SIX_TITLE());
                    form3entity6.setFloatingLabelText(form3constarins.getCHILD_SIX_TITLE());
                }


                if (form3constarins.getIS_CHILD_SEVEN_VISIBLE()) {
                    form3entity7.setVisibility(View.VISIBLE);
                    form3entity7.setHint(form3constarins.getCHILD_SEVEN_TITLE());
                    form3entity7.setFloatingLabelText(form3constarins.getCHILD_SEVEN_TITLE());
                }
            }
        }
    }

    private void inItform4() {

        form4constarins = UserInfo.getInstance().getFormConstarins(UserDataParser.FORM_FOUR_CONSTRAINS, getActivity());
        if (null != form4constarins) {
            Log.d("rohit", "formone" + new Gson().toJson(form4constarins));
            if (form4constarins.getIS_VISIBLE()) {
                form_four_ll.setVisibility(View.VISIBLE);
                Log.d("rohit", "isvisible" + form4constarins.getIS_CHILD_ONE_VISIBLE());
                if (form4constarins.getIS_CHILD_ONE_VISIBLE()) {
                    form4entity1.setVisibility(View.VISIBLE);
                    form4entity1.setHint(form4constarins.getCHILD_ONE_TITLE());
                    form4entity1.setFloatingLabelText(form4constarins.getCHILD_ONE_TITLE());
                }

                if (form4constarins.getIS_CHILD_TWO_VISIBLE()) {
                    form4entity2.setVisibility(View.VISIBLE);
                    form4entity2.setHint(form4constarins.getCHILD_TWO_TITLE());
                    form4entity2.setFloatingLabelText(form4constarins.getCHILD_TWO_TITLE());
                }

                if (form4constarins.getIS_CHILD_THREE_VISIBLE()) {
                    form4entity3.setVisibility(View.VISIBLE);
                    form4entity3.setHint(form4constarins.getCHILD_THREE_TITLE());
                    form4entity3.setFloatingLabelText(form4constarins.getCHILD_THREE_TITLE());
                }

                if (form4constarins.getIS_CHILD_FOUR_VISIBLE()) {
                    form4entity4.setVisibility(View.VISIBLE);
                    form4entity4.setHint(form4constarins.getCHILD_FOUR_TITLE());
                    form4entity4.setFloatingLabelText(form4constarins.getCHILD_FOUR_TITLE());
                }

                if (form4constarins.getIS_CHILD_FIVE_VISIBLE()) {
                    form4entity5.setVisibility(View.VISIBLE);
                    form4entity5.setHint(form4constarins.getCHILD_FIVE_TITLE());
                    form4entity5.setFloatingLabelText(form4constarins.getCHILD_FIVE_TITLE());
                }


                if (form4constarins.getIS_CHILD_SIX_VISIBLE()) {
                    form4entity6.setVisibility(View.VISIBLE);
                    form4entity6.setHint(form4constarins.getCHILD_SIX_TITLE());
                    form4entity6.setFloatingLabelText(form4constarins.getCHILD_SIX_TITLE());
                }


                if (form4constarins.getIS_CHILD_SEVEN_VISIBLE()) {
                    form4entity7.setVisibility(View.VISIBLE);
                    form4entity7.setHint(form4constarins.getCHILD_SEVEN_TITLE());
                    form4entity7.setFloatingLabelText(form4constarins.getCHILD_SEVEN_TITLE());
                }
            }
        }
    }


    public void onClick(View v) {
        ArrayList<DropDownDataDTO> list = new ArrayList<>();
        DropDownDataDAO dao = new DropDownDataDAO(getActivity());
        DropDownDialog dialog;
        switch (v.getId()) {
            case R.id.form2entity1:
                list = dao.getFormData(Constant.FORM_TWO_CHILD_ONE_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_TWO_CHILD_ONE_COMMON_CODE, this);
                dialog.show();
                break;

            case R.id.form2entity2:
                list = dao.getFormData(Constant.FORM_TWO_CHILD_TWO_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_TWO_CHILD_TWO_COMMON_CODE, this);
                dialog.show();
                break;

            case R.id.form2entity3:
                list = dao.getFormData(Constant.FORM_TWO_CHILD_THREE_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_TWO_CHILD_THREE_COMMON_CODE, this);
                dialog.show();
                break;

            case R.id.form2entity4:
                list = dao.getFormData(Constant.FORM_TWO_CHILD_FOUR_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_TWO_CHILD_FOUR_COMMON_CODE, this);
                dialog.show();
                break;




            case R.id.form3entity1:
                list = dao.getFormData(Constant.FORM_THREE_CHILD_ONE_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list,Constant.FORM_THREE_CHILD_ONE_COMMON_CODE, this);
                dialog.show();
                break;

            case R.id.form3entity2:
                list = dao.getFormData(Constant.FORM_THREE_CHILD_TWO_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list,Constant.FORM_THREE_CHILD_TWO_COMMON_CODE, this);
                dialog.show();
                break;

            case R.id.form3entity3:
                list = dao.getFormData(Constant.FORM_THREE_CHILD_THREE_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list,Constant.FORM_THREE_CHILD_THREE_COMMON_CODE, this);
                dialog.show();
                break;

            case R.id.form3entity4:
                list = dao.getFormData(Constant.FORM_THREE_CHILD_FOUR_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list,Constant.FORM_THREE_CHILD_FOUR_COMMON_CODE, this);
                dialog.show();
                break;




            case R.id.form4entity1:
                list = dao.getFormData(Constant.FORM_FOUR_CHILD_ONE_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list,Constant.FORM_FOUR_CHILD_ONE_COMMON_CODE, this);
                dialog.show();
                break;

            case R.id.form4entity2:
                list = dao.getFormData(Constant.FORM_FOUR_CHILD_TWO_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list,Constant.FORM_FOUR_CHILD_TWO_COMMON_CODE, this);
                dialog.show();
                break;

            case R.id.form4entity3:
                list = dao.getFormData(Constant.FORM_FOUR_CHILD_THREE_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list,Constant.FORM_FOUR_CHILD_THREE_COMMON_CODE ,this);
                dialog.show();
                break;

            case R.id.form4entity4:
                list = dao.getFormData(Constant.FORM_FOUR_CHILD_FOUR_COMMON_CODE);
                dialog = new DropDownDialog(getActivity(), list,Constant.FORM_FOUR_CHILD_FOUR_COMMON_CODE, this);
                dialog.show();
                break;



            case R.id.add_edit_btn:
                if (isAllValidEntries()){
                    addStudent();
                }

                break;

            case R.id.cancel_btn:
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new StudentDashboard()).commit();
                break;
        }

    }

    private void addStudent() {
        StudentDTO studentdata = new StudentDTO();
        studentdata.setForm1Entity1(form1entity1.getText().toString());
        studentdata.setForm1Entity2(form1entity2.getText().toString());
        studentdata.setForm1Entity3(form1entity3.getText().toString());
        studentdata.setForm1Entity4(form1entity4.getText().toString());

        if (null != form2entity1DTO) studentdata.setForm2Entity1ID(form2entity1DTO.getId());
        if (null != form2entity2DTO) studentdata.setForm2Entity2ID(form2entity2DTO.getId());
        if (null != form2entity3DTO) studentdata.setForm2Entity3ID(form2entity3DTO.getId());
        if (null != form2entity4DTO) studentdata.setForm2Entity4ID(form2entity4DTO.getId());

        if (null != form3entity1DTO) studentdata.setForm3Entity1ID(form3entity1DTO.getId());
        if (null != form3entity2DTO) studentdata.setForm3Entity2ID(form3entity2DTO.getId());
        if (null != form3entity3DTO) studentdata.setForm3Entity3ID(form3entity3DTO.getId());
        if (null != form3entity4DTO) studentdata.setForm3Entity4ID(form3entity4DTO.getId());
        if (null != form3entity5.getText()) studentdata.setForm3Entity5(form3entity5.getText().toString());
        if (null != form3entity6.getText()) studentdata.setForm3Entity6(form3entity6.getText().toString());
        if (null != form3entity7.getText()) studentdata.setForm3Entity7(form3entity7.getText().toString());

        if (null != form4entity1DTO) studentdata.setForm4Entity1ID(form4entity1DTO.getId());
        if (null != form4entity2DTO) studentdata.setForm4Entity2ID(form4entity2DTO.getId());
        if (null != form4entity3DTO) studentdata.setForm4Entity3ID(form4entity3DTO.getId());
        if (null != form4entity4DTO) studentdata.setForm4Entity4ID(form4entity4DTO.getId());
        if (null != form4entity5.getText()) studentdata.setForm4Entity5(form4entity5.getText().toString());
        if (null != form4entity6.getText()) studentdata.setForm4Entity6(form4entity6.getText().toString());
        if (null != form4entity7.getText()) studentdata.setForm4Entity7(form4entity7.getText().toString());

        Calendar now  = Calendar.getInstance();
        studentdata.setCreatedOnMilli(now.getTimeInMillis());
        studentdata.setCreatedOn(now.getTimeInMillis()+"");
        studentdata.setUpdatedON(now.getTimeInMillis()+"");
        studentdata.setUpdatedONMilli(now.getTimeInMillis());

        Log.d("rohit", "saving data "+new Gson().toJson(studentdata));
        studentdata.setStudentDataJSON(new Gson().toJson(studentdata));
        StudentDAO dao = new StudentDAO(getActivity());
        dao.addStudent(studentdata);

        getActivity().getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                .replace(R.id.main_frame_layout, new StudentDashboard()).commit();
    }

    private boolean isAllValidEntries() {
        Boolean form1entity1Valid = false;
        Boolean form1entity2Valid = true;
        Boolean form1entity3Valid = false;
        Boolean form1entity4Valid = false;

        Boolean form2entity1Valid = false;
        Boolean form2entity2Valid = false;
        Boolean form2entity3Valid = false;
        Boolean form2entity4Valid = false;

        Boolean form3entity1Valid = false;
        Boolean form3entity2Valid = false;
        Boolean form3entity3Valid = false;
        Boolean form3entity4Valid = false;
        Boolean form3entity5Valid = false;
        Boolean form3entity6Valid = false;
        Boolean form3entity7Valid = false;

        Boolean form4entity1Valid = false;
        Boolean form4entity2Valid = false;
        Boolean form4entity3Valid = false;
        Boolean form4entity4Valid = false;
        Boolean form4entity5Valid = false;
        Boolean form4entity6Valid = false;
        Boolean form4entity7Valid = false;


        if (form1entity1.getText().toString().length()>0){
            form1entity1Valid = true;
        }else form1entity1.setError("enter right entry");


        if (form1entity3.getText().toString().length()>0){
            form1entity3Valid = true;
        }else form1entity3.setError("enter right entry");


        if (form1entity4.getText().toString().length()>0){
            form1entity4Valid = true;
        }else form1entity4.setError("enter right entry");



        if ((form2entity1.getText().toString().length()>0 || !form2constarins.getIS_CHILD_ONE_COMPULSORY()) || form2entity1.getVisibility() == View.GONE){
            form2entity1Valid = true;
        }else form2entity1.setError("enter right entry");

        if ((form2entity2.getText().toString().length()>0 || !form2constarins.getIS_CHILD_TWO_COMPULSORY()) || form2entity2.getVisibility() == View.GONE){
            form2entity2Valid = true;
        }else form2entity2.setError("enter right entry");

        if ((form2entity3.getText().toString().length()>0 || !form2constarins.getIS_CHILD_THREE_COMPULSORY()) || form2entity3.getVisibility() == View.GONE){
            form2entity3Valid = true;
        }else form2entity3.setError("enter right entry");

        if ((form2entity4.getText().toString().length()>0 || !form2constarins.getIS_CHILD_FOUR_COMPULSORY()) || form2entity4.getVisibility() == View.GONE){
            form2entity4Valid = true;
        }else form2entity4.setError("enter right entry");

        
        

        if ((form3entity1.getText().toString().length()>0 || !form3constarins.getIS_CHILD_ONE_COMPULSORY()) || form3entity1.getVisibility() == View.GONE){
            form3entity1Valid = true;
        }else form3entity1.setError("enter right entry");

        if ((form3entity2.getText().toString().length()>0 || !form3constarins.getIS_CHILD_TWO_COMPULSORY()) || form3entity2.getVisibility() == View.GONE){
            form3entity2Valid = true;
        }else form3entity2.setError("enter right entry");

        if ((form3entity3.getText().toString().length()>0 || !form3constarins.getIS_CHILD_THREE_COMPULSORY()) || form3entity3.getVisibility() == View.GONE){
            form3entity3Valid = true;
        }else form3entity3.setError("enter right entry");

        if ((form3entity4.getText().toString().length()>0 || !form3constarins.getIS_CHILD_FOUR_COMPULSORY()) || form3entity4.getVisibility() == View.GONE){
            form3entity4Valid = true;
        }else form3entity4.setError("enter right entry");

        if ((form3entity5.getText().toString().length()>0 || !form3constarins.getIS_CHILD_FIVE_COMPULSORY()) || form3entity5.getVisibility() == View.GONE){
            form3entity5Valid = true;
        }else form3entity5.setError("enter right entry");

        if ((form3entity6.getText().toString().length()>0 || !form3constarins.getIS_CHILD_SIX_COMPULSORY()) || form3entity6.getVisibility() == View.GONE){
            form3entity6Valid = true;
        }else form3entity6.setError("enter right entry");

        if ((form3entity7.getText().toString().length()>0 || !form3constarins.getIS_CHILD_SEVEN_COMPULSORY()) || form3entity7.getVisibility() == View.GONE){
            form3entity7Valid = true;
        }else form3entity7.setError("enter right entry");




        if ((form4entity1.getText().toString().length()>0 || !form4constarins.getIS_CHILD_ONE_COMPULSORY()) || form4entity1.getVisibility() == View.GONE){
            form4entity1Valid = true;
        }else form4entity1.setError("enter right entry");

        if ((form4entity2.getText().toString().length()>0 || !form4constarins.getIS_CHILD_TWO_COMPULSORY()) || form4entity2.getVisibility() == View.GONE){
            form4entity2Valid = true;
        }else form4entity2.setError("enter right entry");

        if ((form4entity3.getText().toString().length()>0 || !form4constarins.getIS_CHILD_THREE_COMPULSORY()) || form4entity3.getVisibility() == View.GONE){
            form4entity3Valid = true;
        }else form4entity3.setError("enter right entry");

        if ((form4entity4.getText().toString().length()>0 || !form4constarins.getIS_CHILD_FOUR_COMPULSORY()) || form4entity4.getVisibility() == View.GONE){
            form4entity4Valid = true;
        }else form4entity4.setError("enter right entry");

        if ((form4entity5.getText().toString().length()>0 || !form4constarins.getIS_CHILD_FIVE_COMPULSORY()) || form4entity5.getVisibility() == View.GONE){
            form4entity5Valid = true;
        }else form4entity5.setError("enter right entry");

        if ((form4entity6.getText().toString().length()>0 || !form4constarins.getIS_CHILD_SIX_COMPULSORY()) || form4entity6.getVisibility() == View.GONE){
            form4entity6Valid = true;
        }else form4entity6.setError("enter right entry");

        if ((form4entity7.getText().toString().length()>0 || !form4constarins.getIS_CHILD_SEVEN_COMPULSORY()) || form4entity7.getVisibility() == View.GONE){
            form4entity7Valid = true;
        }else form4entity7.setError("enter right entry");




        if (form1entity1Valid && form1entity2Valid && form1entity3Valid && form1entity4Valid &&
                form2entity1Valid && form2entity2Valid && form2entity3Valid && form2entity4Valid &&
                form3entity1Valid &&  form3entity2Valid &&  form3entity3Valid &&  form3entity4Valid &&  form3entity5Valid &&  form3entity6Valid &&  form3entity7Valid &&
                form4entity1Valid &&  form4entity2Valid &&  form4entity3Valid &&  form4entity4Valid &&  form4entity5Valid &&  form4entity6Valid &&  form4entity7Valid) {
            return true;
        }else return false;
    }


    public void onAddLeadCommonCodeDialogItemSelected(DropDownDataDTO value, String TYPE) {
        switch (TYPE){
            case Constant.FORM_TWO_CHILD_ONE_COMMON_CODE:
                form2entity1.setText(value.getTitle());
                form2entity1DTO = value;
                break;

            case Constant.FORM_TWO_CHILD_TWO_COMMON_CODE:
                form2entity2.setText(value.getTitle());
                form2entity2DTO = value;
                break;

            case Constant.FORM_TWO_CHILD_THREE_COMMON_CODE:
                form2entity3.setText(value.getTitle());
                form2entity3DTO = value;
                break;

            case Constant.FORM_TWO_CHILD_FOUR_COMMON_CODE:
                form2entity4.setText(value.getTitle());
                form2entity4DTO = value;
                break;


            case Constant.FORM_THREE_CHILD_ONE_COMMON_CODE:
                form3entity1.setText(value.getTitle());
                form3entity1DTO = value;
                break;

            case Constant.FORM_THREE_CHILD_TWO_COMMON_CODE:
                form3entity2.setText(value.getTitle());
                form3entity2DTO = value;
                break;

            case Constant.FORM_THREE_CHILD_THREE_COMMON_CODE:
                form3entity3.setText(value.getTitle());
                form3entity3DTO = value;
                break;

            case Constant.FORM_THREE_CHILD_FOUR_COMMON_CODE:
                form3entity4.setText(value.getTitle());
                form3entity4DTO = value;
                break;


            case Constant.FORM_FOUR_CHILD_ONE_COMMON_CODE:
                form4entity1.setText(value.getTitle());
                form4entity1DTO = value;
                break;

            case Constant.FORM_FOUR_CHILD_TWO_COMMON_CODE:
                form4entity2.setText(value.getTitle());
                form4entity2DTO = value;
                break;

            case Constant.FORM_FOUR_CHILD_THREE_COMMON_CODE:
                form4entity3.setText(value.getTitle());
                form4entity3DTO = value;
                break;

            case Constant.FORM_FOUR_CHILD_FOUR_COMMON_CODE:
                form4entity4.setText(value.getTitle());
                form4entity4DTO = value;
                break;

        }



    }
}
