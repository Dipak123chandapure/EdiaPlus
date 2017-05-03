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
import com.example.deepak.myapplication.Database.DAO.ParentDropDownDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.FormConstarins;
import com.example.deepak.myapplication.Database.DTO.ParentDropDownDTO;
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


    DropDownDataDTO form2entity1DTO, form2entity2DTO, form2entity3DTO, form2entity4DTO,
            form3entity1DTO, form3entity2DTO, form3entity3DTO, form3entity4DTO,
            form4entity1DTO, form4entity2DTO, form4entity3DTO, form4entity4DTO;


    Button add_edit_btn, cancel_btn;
    Boolean isEdit = false;
    StudentDTO editStudentData = null;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_student, container, false);
        chekForEdit();
        inItView(view);
        inItForm();
        return view;
    }

    private void chekForEdit() {
        if (null != getArguments()) {
            if (null != getArguments().getParcelable(Constant.STUDENT_LIST)) {
                isEdit = true;
                DropDownDataDAO dao = new DropDownDataDAO(getActivity());
                editStudentData = getArguments().getParcelable(Constant.STUDENT_LIST);
                form2entity1DTO = dao.getFormDTO(Constant.FORM_2_CHILD_1, editStudentData.getForm2Entity1ID());
                form2entity2DTO = dao.getFormDTO(Constant.FORM_2_CHILD_2, editStudentData.getForm2Entity2ID());
                form2entity3DTO = dao.getFormDTO(Constant.FORM_2_CHILD_3, editStudentData.getForm2Entity3ID());
                form2entity4DTO = dao.getFormDTO(Constant.FORM_2_CHILD_4, editStudentData.getForm2Entity4ID());

                form3entity1DTO = dao.getFormDTO(Constant.FORM_3_CHILD_1, editStudentData.getForm3Entity1ID());
                form3entity2DTO = dao.getFormDTO(Constant.FORM_3_CHILD_2, editStudentData.getForm3Entity2ID());
                form3entity3DTO = dao.getFormDTO(Constant.FORM_3_CHILD_3, editStudentData.getForm3Entity3ID());
                form3entity4DTO = dao.getFormDTO(Constant.FORM_3_CHILD_4, editStudentData.getForm3Entity4ID());

                form4entity1DTO = dao.getFormDTO(Constant.FORM_4_CHILD_1, editStudentData.getForm4Entity1ID());
                form4entity2DTO = dao.getFormDTO(Constant.FORM_4_CHILD_2, editStudentData.getForm4Entity2ID());
                form4entity3DTO = dao.getFormDTO(Constant.FORM_4_CHILD_3, editStudentData.getForm4Entity3ID());
                form4entity4DTO = dao.getFormDTO(Constant.FORM_4_CHILD_4, editStudentData.getForm4Entity4ID());
            }
        }
    }

    ArrayList<ParentDropDownDTO> parnetLsit;

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

        if (isEdit)
            add_edit_btn.setText("Edit");
        cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);


    }

    private void inItForm() {
        parnetLsit = new ParentDropDownDAO(getActivity()).getParentsDropDown();
        Log.d("rohit", "size" + parnetLsit.size());
        inItForm1();
        inItForm2();
        inItForm3();
        inItform4();
    }

    private void inItForm1() {
        ParentDropDownDTO e1 = parnetLsit.get(0);
        ParentDropDownDTO e2 = parnetLsit.get(1);
        ParentDropDownDTO e3 = parnetLsit.get(2);
        ParentDropDownDTO e4 = parnetLsit.get(3);

        if (e1.getShown() || e2.getShown() || e3.getShown() || e4.getShown()) {
            form_one_ll.setVisibility(View.VISIBLE);
            if (e1.getShown()) {
                form1entity1.setVisibility(View.VISIBLE);
                form1entity1.setHint(e1.getTitle());
                form1entity1.setFloatingLabelText(e1.getTitle());
                if (isEdit && null != editStudentData)
                    form1entity1.setText(editStudentData.getForm1Entity1());
            }

            if (e2.getShown()) {
                form1entity2.setVisibility(View.VISIBLE);
                form1entity2.setHint(e2.getTitle());
                form1entity2.setFloatingLabelText(e2.getTitle());
                if (isEdit && null != editStudentData)
                    form1entity2.setText(editStudentData.getForm1Entity2());
            }

            if (e3.getShown()) {
                form1entity3.setVisibility(View.VISIBLE);
                form1entity3.setHint(e3.getTitle());
                form1entity3.setFloatingLabelText(e3.getTitle());
                if (isEdit && null != editStudentData)
                    form1entity3.setText(editStudentData.getForm1Entity3());
            }

            if (e4.getShown()) {
                form1entity4.setVisibility(View.VISIBLE);
                form1entity4.setHint(e4.getTitle());
                form1entity4.setFloatingLabelText(e4.getTitle());
                if (isEdit && null != editStudentData)
                    form1entity4.setText(editStudentData.getForm1Entity4());
            }
        }

    }

    private void inItForm2() {
        ParentDropDownDTO e1 = parnetLsit.get(4);
        ParentDropDownDTO e2 = parnetLsit.get(5);
        ParentDropDownDTO e3 = parnetLsit.get(6);
        ParentDropDownDTO e4 = parnetLsit.get(7);

        if (e1.getShown() || e2.getShown() || e3.getShown() || e4.getShown()) {
            form_two_ll.setVisibility(View.VISIBLE);
            if (e1.getShown()) {
                form2entity1.setVisibility(View.VISIBLE);
                form2entity1.setHint(e1.getTitle());
                form2entity1.setFloatingLabelText(e1.getTitle());
                if (isEdit && null != editStudentData && null != form2entity1DTO)
                    form2entity1.setText(form2entity1DTO.getTitle());
            }

            if (e2.getShown()) {
                form2entity2.setVisibility(View.VISIBLE);
                form2entity2.setHint(e2.getTitle());
                form2entity2.setFloatingLabelText(e2.getTitle());
                if (isEdit && null != editStudentData && null != form2entity2DTO)
                    form2entity2.setText(form2entity2DTO.getTitle());
            }

            if (e3.getShown()) {
                form2entity3.setVisibility(View.VISIBLE);
                form2entity3.setHint(e3.getTitle());
                form2entity3.setFloatingLabelText(e3.getTitle());
                if (isEdit && null != editStudentData && null != form2entity3DTO)
                    form2entity3.setText(form2entity3DTO.getTitle());
            }

            if (e4.getShown()) {
                form2entity4.setVisibility(View.VISIBLE);
                form2entity4.setHint(e4.getTitle());
                form2entity4.setFloatingLabelText(e4.getTitle());
                if (isEdit && null != editStudentData && null != form2entity4DTO)
                    form2entity4.setText(form2entity4DTO.getTitle());
            }
        }

    }

    private void inItForm3() {

        ParentDropDownDTO e1 = parnetLsit.get(8);
        ParentDropDownDTO e2 = parnetLsit.get(9);
        ParentDropDownDTO e3 = parnetLsit.get(10);
        ParentDropDownDTO e4 = parnetLsit.get(11);
        ParentDropDownDTO e5 = parnetLsit.get(12);
        ParentDropDownDTO e6 = parnetLsit.get(13);
        ParentDropDownDTO e7 = parnetLsit.get(14);

        if (e1.getShown() || e2.getShown() || e3.getShown() || e4.getShown() || e5.getShown() || e6.getShown() || e7.getShown()) {
            form_three_ll.setVisibility(View.VISIBLE);
            if (e1.getShown()) {
                form3entity1.setVisibility(View.VISIBLE);
                form3entity1.setHint(e1.getTitle());
                form3entity1.setFloatingLabelText(e1.getTitle());
                if (isEdit && null != editStudentData && null != form3entity1DTO)
                    form3entity1.setText(form3entity1DTO.getTitle());
            }

            if (e2.getShown()) {
                form3entity2.setVisibility(View.VISIBLE);
                form3entity2.setHint(e2.getTitle());
                form3entity2.setFloatingLabelText(e2.getTitle());
                if (isEdit && null != editStudentData && null != form3entity2DTO)
                    form3entity2.setText(form3entity2DTO.getTitle());
            }

            if (e3.getShown()) {
                form3entity3.setVisibility(View.VISIBLE);
                form3entity3.setHint(e3.getTitle());
                form3entity3.setFloatingLabelText(e3.getTitle());
                if (isEdit && null != editStudentData && null != form3entity3DTO)
                    form3entity3.setText(form3entity3DTO.getTitle());
            }

            if (e4.getShown()) {
                form3entity4.setVisibility(View.VISIBLE);
                form3entity4.setHint(e4.getTitle());
                form3entity4.setFloatingLabelText(e4.getTitle());
                if (isEdit && null != editStudentData && null != form3entity4DTO)
                    form3entity4.setText(form3entity4DTO.getTitle());
            }

            if (e5.getShown()) {
                form3entity5.setVisibility(View.VISIBLE);
                form3entity5.setHint(e5.getTitle());
                form3entity5.setFloatingLabelText(e5.getTitle());
                if (isEdit && null != editStudentData)
                    form3entity5.setText(editStudentData.getForm3Entity5());
            }

            if (e6.getShown()) {
                form3entity6.setVisibility(View.VISIBLE);
                form3entity6.setHint(e6.getTitle());
                form3entity6.setFloatingLabelText(e6.getTitle());
                if (isEdit && null != editStudentData)
                    form3entity6.setText(editStudentData.getForm3Entity6());
            }

            if (e7.getShown()) {
                form3entity7.setVisibility(View.VISIBLE);
                form3entity7.setHint(e7.getTitle());
                form3entity7.setFloatingLabelText(e7.getTitle());
                if (isEdit && null != editStudentData)
                    form3entity7.setText(editStudentData.getForm3Entity7());
            }
        }

    }

    private void inItform4() {

        ParentDropDownDTO e1 = parnetLsit.get(15);
        ParentDropDownDTO e2 = parnetLsit.get(16);
        ParentDropDownDTO e3 = parnetLsit.get(17);
        ParentDropDownDTO e4 = parnetLsit.get(18);
        ParentDropDownDTO e5 = parnetLsit.get(19);
        ParentDropDownDTO e6 = parnetLsit.get(20);
        ParentDropDownDTO e7 = parnetLsit.get(21);

        if (e1.getShown() || e2.getShown() || e3.getShown() || e4.getShown() || e5.getShown() || e6.getShown() || e7.getShown()) {
            form_four_ll.setVisibility(View.VISIBLE);
            if (e1.getShown()) {
                form4entity1.setVisibility(View.VISIBLE);
                form4entity1.setHint(e1.getTitle());
                form4entity1.setFloatingLabelText(e1.getTitle());
                if (isEdit && null != editStudentData && null != form4entity1DTO)
                    form4entity1.setText(form4entity1DTO.getTitle());
            }

            if (e2.getShown()) {
                form4entity2.setVisibility(View.VISIBLE);
                form4entity2.setHint(e2.getTitle());
                form4entity2.setFloatingLabelText(e2.getTitle());
                if (isEdit && null != editStudentData && null != form4entity2DTO)
                    form4entity2.setText(form4entity2DTO.getTitle());
            }

            if (e3.getShown()) {
                form4entity3.setVisibility(View.VISIBLE);
                form4entity3.setHint(e3.getTitle());
                form4entity3.setFloatingLabelText(e3.getTitle());
                if (isEdit && null != editStudentData && null != form4entity3DTO)
                    form4entity3.setText(form4entity3DTO.getTitle());
            }

            if (e4.getShown()) {
                form4entity4.setVisibility(View.VISIBLE);
                form4entity4.setHint(e4.getTitle());
                form4entity4.setFloatingLabelText(e4.getTitle());
                if (isEdit && null != editStudentData && null != form4entity4DTO)
                    form4entity4.setText(form4entity4DTO.getTitle());
            }

            if (e5.getShown()) {
                form4entity5.setVisibility(View.VISIBLE);
                form4entity5.setHint(e5.getTitle());
                form4entity5.setFloatingLabelText(e5.getTitle());
                if (isEdit && null != editStudentData)
                    form4entity5.setText(editStudentData.getForm4Entity5());
            }

            if (e6.getShown()) {
                form4entity6.setVisibility(View.VISIBLE);
                form4entity6.setHint(e6.getTitle());
                form4entity6.setFloatingLabelText(e6.getTitle());
                if (isEdit && null != editStudentData)
                    form4entity6.setText(editStudentData.getForm4Entity6());

            }

            if (e7.getShown()) {
                form4entity7.setVisibility(View.VISIBLE);
                form4entity7.setHint(e7.getTitle());
                form4entity7.setFloatingLabelText(e7.getTitle());
                if (isEdit && null != editStudentData)
                    form4entity7.setText(editStudentData.getForm4Entity7());
            }
        }

    }


    public void onClick(View v) {
        ArrayList<DropDownDataDTO> list = new ArrayList<>();
        DropDownDataDAO dao = new DropDownDataDAO(getActivity());
        DropDownDialog dialog;
        switch (v.getId()) {
            case R.id.form2entity1:
                list = dao.getFormData(Constant.FORM_2_CHILD_1);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_2_CHILD_1, this);
                dialog.show();
                break;

            case R.id.form2entity2:
                list = dao.getFormData(Constant.FORM_2_CHILD_2);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_2_CHILD_2, this);
                dialog.show();
                break;

            case R.id.form2entity3:
                list = dao.getFormData(Constant.FORM_2_CHILD_3);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_2_CHILD_3, this);
                dialog.show();
                break;

            case R.id.form2entity4:
                list = dao.getFormData(Constant.FORM_2_CHILD_4);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_2_CHILD_4, this);
                dialog.show();
                break;


            case R.id.form3entity1:
                list = dao.getFormData(Constant.FORM_3_CHILD_1);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_3_CHILD_1, this);
                dialog.show();
                break;

            case R.id.form3entity2:
                list = dao.getFormData(Constant.FORM_3_CHILD_2);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_3_CHILD_2, this);
                dialog.show();
                break;

            case R.id.form3entity3:
                list = dao.getFormData(Constant.FORM_2_CHILD_3);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_3_CHILD_3, this);
                dialog.show();
                break;

            case R.id.form3entity4:
                list = dao.getFormData(Constant.FORM_3_CHILD_4);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_3_CHILD_4, this);
                dialog.show();
                break;


            case R.id.form4entity1:
                list = dao.getFormData(Constant.FORM_4_CHILD_1);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_4_CHILD_1, this);
                dialog.show();
                break;

            case R.id.form4entity2:
                list = dao.getFormData(Constant.FORM_4_CHILD_2);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_4_CHILD_2, this);
                dialog.show();
                break;

            case R.id.form4entity3:
                list = dao.getFormData(Constant.FORM_4_CHILD_3);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_4_CHILD_3, this);
                dialog.show();
                break;

            case R.id.form4entity4:
                list = dao.getFormData(Constant.FORM_4_CHILD_4);
                dialog = new DropDownDialog(getActivity(), list, Constant.FORM_4_CHILD_4, this);
                dialog.show();
                break;


            case R.id.add_edit_btn:
                if (isAllValidEntries()) {
                    if (!isEdit) {
                        StudentDTO studentdata = getStudentForAdd();
                        StudentDAO studentDao = new StudentDAO(getActivity());
                        studentDao.addStudent(studentdata);
                    } else {
                        StudentDTO studentdata = getStudentForAdd();
                        StudentDAO studentDao = new StudentDAO(getActivity());
                        studentDao.updateStudent(studentdata);
                    }

                    getActivity().getSupportFragmentManager().beginTransaction().
                            setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                            .replace(R.id.main_frame_layout, new StudentDashboard()).commit();
                }

                break;

            case R.id.cancel_btn:
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new StudentDashboard()).commit();
                break;
        }

    }

    private StudentDTO getStudentForAdd() {
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

        if (null != form3entity5.getText())
            studentdata.setForm3Entity5(form3entity5.getText().toString());
        if (null != form3entity6.getText())
            studentdata.setForm3Entity6(form3entity6.getText().toString());
        if (null != form3entity7.getText())
            studentdata.setForm3Entity7(form3entity7.getText().toString());

        if (null != form4entity1DTO) studentdata.setForm4Entity1ID(form4entity1DTO.getId());
        if (null != form4entity2DTO) studentdata.setForm4Entity2ID(form4entity2DTO.getId());
        if (null != form4entity3DTO) studentdata.setForm4Entity3ID(form4entity3DTO.getId());
        if (null != form4entity4DTO) studentdata.setForm4Entity4ID(form4entity4DTO.getId());
        if (null != form4entity5.getText())
            studentdata.setForm4Entity5(form4entity5.getText().toString());
        if (null != form4entity6.getText())
            studentdata.setForm4Entity6(form4entity6.getText().toString());
        if (null != form4entity7.getText())
            studentdata.setForm4Entity7(form4entity7.getText().toString());

        Calendar now = Calendar.getInstance();
        studentdata.setCreatedOnMilli(now.getTimeInMillis());
        studentdata.setCreatedOn(now.getTimeInMillis() + "");
        studentdata.setUpdatedON(now.getTimeInMillis() + "");
        studentdata.setUpdatedONMilli(now.getTimeInMillis());

        Log.d("rohit", "saving data " + new Gson().toJson(studentdata));
        studentdata.setStudentDataJSON(new Gson().toJson(studentdata));

        if (isEdit && null != editStudentData)
            studentdata.setId(editStudentData.getId());

        return studentdata;
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


        if (form1entity1.getText().toString().length() > 0) {
            form1entity1Valid = true;
        } else form1entity1.setError("enter right entry");


        if (form1entity3.getText().toString().length() > 0) {
            form1entity3Valid = true;
        } else form1entity3.setError("enter right entry");


        if (form1entity4.getText().toString().length() > 0) {
            form1entity4Valid = true;
        } else form1entity4.setError("enter right entry");


        if ((form2entity1.getText().toString().length() > 0 || !parnetLsit.get(4).getCompulsory()) || form2entity1.getVisibility() == View.GONE) {
            form2entity1Valid = true;
        } else form2entity1.setError("enter right entry");

        if ((form2entity2.getText().toString().length() > 0 || !parnetLsit.get(5).getCompulsory()) || form2entity2.getVisibility() == View.GONE) {
            form2entity2Valid = true;
        } else form2entity2.setError("enter right entry");

        if ((form2entity3.getText().toString().length() > 0 || !parnetLsit.get(6).getCompulsory()) || form2entity3.getVisibility() == View.GONE) {
            form2entity3Valid = true;
        } else form2entity3.setError("enter right entry");

        if ((form2entity4.getText().toString().length() > 0 || !parnetLsit.get(7).getCompulsory()) || form2entity4.getVisibility() == View.GONE) {
            form2entity4Valid = true;
        } else form2entity4.setError("enter right entry");


        if ((form3entity1.getText().toString().length() > 0 || !parnetLsit.get(8).getCompulsory()) || form3entity1.getVisibility() == View.GONE) {
            form3entity1Valid = true;
        } else form3entity1.setError("enter right entry");

        if ((form3entity2.getText().toString().length() > 0 || !parnetLsit.get(9).getCompulsory()) || form3entity2.getVisibility() == View.GONE) {
            form3entity2Valid = true;
        } else form3entity2.setError("enter right entry");

        if ((form3entity3.getText().toString().length() > 0 || !parnetLsit.get(10).getCompulsory()) || form3entity3.getVisibility() == View.GONE) {
            form3entity3Valid = true;
        } else form3entity3.setError("enter right entry");

        if ((form3entity4.getText().toString().length() > 0 || !parnetLsit.get(11).getCompulsory()) || form3entity4.getVisibility() == View.GONE) {
            form3entity4Valid = true;
        } else form3entity4.setError("enter right entry");

        if ((form3entity5.getText().toString().length() > 0 || !parnetLsit.get(12).getCompulsory()) || form3entity5.getVisibility() == View.GONE) {
            form3entity5Valid = true;
        } else form3entity5.setError("enter right entry");

        if ((form3entity6.getText().toString().length() > 0 || !parnetLsit.get(13).getCompulsory()) || form3entity6.getVisibility() == View.GONE) {
            form3entity6Valid = true;
        } else form3entity6.setError("enter right entry");

        if ((form3entity7.getText().toString().length() > 0 || !parnetLsit.get(14).getCompulsory()) || form3entity7.getVisibility() == View.GONE) {
            form3entity7Valid = true;
        } else form3entity7.setError("enter right entry");


        if ((form4entity1.getText().toString().length() > 0 || !parnetLsit.get(15).getCompulsory()) || form4entity1.getVisibility() == View.GONE) {
            form4entity1Valid = true;
        } else form4entity1.setError("enter right entry");

        if ((form4entity2.getText().toString().length() > 0 || !parnetLsit.get(16).getCompulsory()) || form4entity2.getVisibility() == View.GONE) {
            form4entity2Valid = true;
        } else form4entity2.setError("enter right entry");

        if ((form4entity3.getText().toString().length() > 0 || !parnetLsit.get(17).getCompulsory()) || form4entity3.getVisibility() == View.GONE) {
            form4entity3Valid = true;
        } else form4entity3.setError("enter right entry");

        if ((form4entity4.getText().toString().length() > 0 || !parnetLsit.get(18).getCompulsory()) || form4entity4.getVisibility() == View.GONE) {
            form4entity4Valid = true;
        } else form4entity4.setError("enter right entry");

        if ((form4entity5.getText().toString().length() > 0 || !parnetLsit.get(19).getCompulsory()) || form4entity5.getVisibility() == View.GONE) {
            form4entity5Valid = true;
        } else form4entity5.setError("enter right entry");

        if ((form4entity6.getText().toString().length() > 0 || !parnetLsit.get(20).getCompulsory()) || form4entity6.getVisibility() == View.GONE) {
            form4entity6Valid = true;
        } else form4entity6.setError("enter right entry");

        if ((form4entity7.getText().toString().length() > 0 || !parnetLsit.get(21).getCompulsory()) || form4entity7.getVisibility() == View.GONE) {
            form4entity7Valid = true;
        } else form4entity7.setError("enter right entry");


        if (form1entity1Valid && form1entity2Valid && form1entity3Valid && form1entity4Valid &&
                form2entity1Valid && form2entity2Valid && form2entity3Valid && form2entity4Valid &&
                form3entity1Valid && form3entity2Valid && form3entity3Valid && form3entity4Valid && form3entity5Valid && form3entity6Valid && form3entity7Valid &&
                form4entity1Valid && form4entity2Valid && form4entity3Valid && form4entity4Valid && form4entity5Valid && form4entity6Valid && form4entity7Valid) {
            return true;
        } else return false;
    }


    public void onAddLeadCommonCodeDialogItemSelected(DropDownDataDTO value, String TYPE) {
        Log.d("rohit", "id " + value.getTitle() + value.getId());
        switch (TYPE) {
            case Constant.FORM_2_CHILD_1:
                form2entity1.setText(value.getTitle());
                form2entity1DTO = value;
                break;

            case Constant.FORM_2_CHILD_2:
                form2entity2.setText(value.getTitle());
                form2entity2DTO = value;
                break;

            case Constant.FORM_2_CHILD_3:
                form2entity3.setText(value.getTitle());
                form2entity3DTO = value;
                break;

            case Constant.FORM_2_CHILD_4:
                form2entity4.setText(value.getTitle());
                form2entity4DTO = value;
                break;


            case Constant.FORM_3_CHILD_1:
                form3entity1.setText(value.getTitle());
                form3entity1DTO = value;
                break;

            case Constant.FORM_3_CHILD_2:
                form3entity2.setText(value.getTitle());
                form3entity2DTO = value;
                break;

            case Constant.FORM_3_CHILD_3:
                form3entity3.setText(value.getTitle());
                form3entity3DTO = value;
                break;

            case Constant.FORM_3_CHILD_4:
                form3entity4.setText(value.getTitle());
                form3entity4DTO = value;
                break;


            case Constant.FORM_4_CHILD_1:
                form4entity1.setText(value.getTitle());
                form4entity1DTO = value;
                break;

            case Constant.FORM_4_CHILD_2:
                form4entity2.setText(value.getTitle());
                form4entity2DTO = value;
                break;

            case Constant.FORM_4_CHILD_3:
                form4entity3.setText(value.getTitle());
                form4entity3DTO = value;
                break;

            case Constant.FORM_4_CHILD_4:
                form4entity4.setText(value.getTitle());
                form4entity4DTO = value;
                break;

        }
    }
}
