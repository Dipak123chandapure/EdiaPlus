package com.example.deepak.myapplication.StudentDashboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.deepak.myapplication.AddActivity.AddActivityDialog;
import com.example.deepak.myapplication.AddStudent.AddStudentFragment;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.GroupDashboard.GroupDashboardFragment;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.Utility.Constant;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import java.util.ArrayList;


public class StudentDashboardFragment extends Fragment implements
        StudentDashboardFilterFragment.OnFilterSeleted,
        StudentDashboardStudentListAdapter.OnStudentListAdapterCallback, View.OnClickListener, TextWatcher {

    RecyclerViewExpandableItemManager expMgr;
    StudentDashboardStudentListAdapter adapter;
    RecyclerView main_dashboard_recycler_view;
    ArrayList<StudentDTO> mList;
    ImageView s_d_m_l_floating_button;
    String QUERY = null;

    ImageView s_d_h_b_l_filter_icon, s_d_h_b_l_group_icon, s_d_h_b_l_search_icon;
    EditText s_d_h_b_l_search_edit_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_dashboard_main_layout, container, false);
        inItView(view);
        mList = new ArrayList<>();
        setUpRecyclerView();
        return view;
    }

    private void inItView(View view) {
        main_dashboard_recycler_view = (RecyclerView) view.findViewById(R.id.main_dashboard_recycler_view);
        s_d_m_l_floating_button = (ImageView) view.findViewById(R.id.s_d_m_l_floating_button);
        s_d_h_b_l_filter_icon = (ImageView) view.findViewById(R.id.s_d_h_b_l_filter_icon);
        s_d_h_b_l_group_icon = (ImageView) view.findViewById(R.id.s_d_h_b_l_group_icon);
        s_d_h_b_l_search_icon = (ImageView) view.findViewById(R.id.s_d_h_b_l_search_icon);
        s_d_h_b_l_search_edit_text = (EditText) view.findViewById(R.id.s_d_h_b_l_search_edit_text);


        s_d_h_b_l_filter_icon.setOnClickListener(this);
        s_d_h_b_l_group_icon.setOnClickListener(this);
        s_d_h_b_l_search_icon.setOnClickListener(this);
        s_d_h_b_l_search_edit_text.setOnClickListener(this);
        s_d_m_l_floating_button.setOnClickListener(this);
    }

    private void setUpRecyclerView() {
        expMgr = new RecyclerViewExpandableItemManager(null);
        main_dashboard_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        StudentDAO studentDAO = new StudentDAO(getActivity());
        mList = studentDAO.getStudentList(QUERY, 0);
        adapter = new StudentDashboardStudentListAdapter(getActivity(), mList);
        adapter.setOnStudentListAdapterCallback(this);
        main_dashboard_recycler_view.setAdapter(expMgr.createWrappedAdapter(adapter));
        ((SimpleItemAnimator) main_dashboard_recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
        expMgr.attachRecyclerView(main_dashboard_recycler_view);
    }


    @Override
    public void onLoadMore(int index) {
        Log.d("rohit", "Load more is called");
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> list = handler.getStudentList(QUERY, index);
        mList.addAll(list);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onChildItemClicekd(int child_index, StudentDTO dto) {
        switch (child_index) {
            case 0:
                AddActivityDialog dialog = new AddActivityDialog(getActivity(), dto);
                dialog.show();
                break;
            case 1:
                SMSDashboardFragment fragment = new SMSDashboardFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.SMS_TYPE, Constant.SMS_SINGE_CLIENT);
                ArrayList<StudentDTO> list = new ArrayList<>();
                list.add(dto);
                bundle.putParcelableArrayList(Constant.SMS_CLIENT_LIST, list);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, fragment).commit();
                break;
            case 2:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + dto.getForm1Entity4()));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    getActivity().startActivity(intent);
                }

                break;
            case 3:
                break;
            case 4:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.s_d_h_b_l_search_edit_text:
                Log.d("rohit", "Clicked");
                s_d_h_b_l_search_edit_text.setCursorVisible(true);
                s_d_h_b_l_search_edit_text.addTextChangedListener(this);
                break;

            case R.id.s_d_h_b_l_filter_icon:
                StudentDashboardFilterFragment studentDashboardFilterFragment = new StudentDashboardFilterFragment();
                studentDashboardFilterFragment.setOnFilterSeleted(this);
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .add(R.id.main_frame_layout, studentDashboardFilterFragment).commit();
                break;

            case R.id.s_d_h_b_l_group_icon:
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, new GroupDashboardFragment()).commit();
                break;

            case R.id.s_d_h_b_l_search_icon:
                break;


            case R.id.s_d_m_l_floating_button:

            getActivity().getSupportFragmentManager().beginTransaction().
                    setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                    .replace(R.id.main_frame_layout, new AddStudentFragment()).commit();
                break;

        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        if (s_d_h_b_l_search_edit_text.getText().toString().length() < 1)
            QUERY = null;
        else
            QUERY = StudentDAO.FORM_1_ENTITY_3 + " LIKE '%" + s_d_h_b_l_search_edit_text.getText().toString() + "%'";

        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> list = handler.getStudentList(QUERY, 0);
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onFilterSeleted(String QUERY) {
        this.QUERY = QUERY;
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> list = handler.getStudentList(QUERY, 0);
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }


}
