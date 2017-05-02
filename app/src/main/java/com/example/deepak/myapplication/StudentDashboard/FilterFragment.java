package com.example.deepak.myapplication.StudentDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DAO.ParentDropDownDAO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.ParentDropDownDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;

import java.util.ArrayList;

public class FilterFragment extends Fragment
        implements FilterParentAdapter.OnFilterParentItemSelected, FilterChildAdapter.OnFilterChildItemSelected {

    RecyclerView s_d_f_f_l_parent_recycler, s_d_f_f_l_child_recycler;
    FilterChildAdapter adapter;
    ArrayList<ParentDropDownDTO> parentList;
    ArrayList<DropDownDataDTO> childList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_dashboard_filte, container, false);
        s_d_f_f_l_parent_recycler = (RecyclerView) view.findViewById(R.id.s_d_f_f_l_parent_recycler);
        s_d_f_f_l_child_recycler = (RecyclerView) view.findViewById(R.id.s_d_f_f_l_child_recycler);

        setUpParentRecycler();
        setUpChildRecycler();
        return view;
    }
    private void setUpChildRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        s_d_f_f_l_child_recycler.setLayoutManager(manager);
        childList = new DropDownDataDAO(getActivity()).getFormData(parentList.get(0).getDatabaseKey());
        adapter = new FilterChildAdapter(getActivity(), childList);
        adapter.setOnFilterChildItemSelected(this);
        s_d_f_f_l_child_recycler.setAdapter(adapter);
    }

    private void setUpParentRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        s_d_f_f_l_parent_recycler.setLayoutManager(manager);
        parentList = new ParentDropDownDAO(getActivity()).getAllParentsDropDown();
        FilterParentAdapter adapter = new FilterParentAdapter(getActivity(), parentList);
        adapter.setmOnFilterParentItemSelected(this);
        s_d_f_f_l_parent_recycler.setAdapter(adapter);
    }



    OnFilterSeleted mOnFilterSeleted;
    public void setOnFilterSeleted(OnFilterSeleted mOnFilterSeleted){
        this.mOnFilterSeleted = mOnFilterSeleted;
    }
    ParentDropDownDTO currentPaterntDTO;
    public void onFilterParentItemSelected(ParentDropDownDTO parentDropDownDTO) {
        currentPaterntDTO = parentDropDownDTO;
        ArrayList<DropDownDataDTO> list = new DropDownDataDAO(getActivity()).getFormData(parentDropDownDTO.getDatabaseKey());
        childList.clear();
        childList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public void onFilterChildItemSelected(DropDownDataDTO ChildDropDownDTO) {

        String QUERY = getQuery(ChildDropDownDTO);
        if (null != mOnFilterSeleted)
            mOnFilterSeleted.onFilterSeleted(QUERY);

        Log.d("rohit", "QUERY "+QUERY);

        getActivity().getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                .remove(this).commit();

    }

    private String getQuery(DropDownDataDTO childDropDownDTO) {
        switch (currentPaterntDTO.getDatabaseKey()){
            case Constant.FORM_2_CHILD_1:
                return "FORM_2_ENTITY_1_ID = '"+childDropDownDTO.getId()+"'";
            case Constant.FORM_2_CHILD_2:
                return "FORM_2_ENTITY_2_ID = '"+childDropDownDTO.getId()+"'";

            case Constant.FORM_2_CHILD_3:
                return "FORM_2_ENTITY_3_ID = '"+childDropDownDTO.getId()+"'";

            case Constant.FORM_2_CHILD_4:
                return "FORM_2_ENTITY_4_ID = '"+childDropDownDTO.getId()+"'";


            case Constant.FORM_3_CHILD_1:
                return "FORM_3_ENTITY_1_ID = '"+childDropDownDTO.getId()+"'";
            case Constant.FORM_3_CHILD_2:
                return "FORM_3_ENTITY_2_ID = '"+childDropDownDTO.getId()+"'";
            case Constant.FORM_3_CHILD_3:
                return "FORM_3_ENTITY_3_ID = '"+childDropDownDTO.getId()+"'";
            case Constant.FORM_3_CHILD_4:
                return "FORM_3_ENTITY_4_ID = '"+childDropDownDTO.getId()+"'";


            case Constant.FORM_4_CHILD_1:
                return "FORM_4_ENTITY_1_ID = '"+childDropDownDTO.getId()+"'";
            case Constant.FORM_4_CHILD_2:
                return "FORM_4_ENTITY_2_ID = '"+childDropDownDTO.getId()+"'";
            case Constant.FORM_4_CHILD_3:
                return "FORM_4_ENTITY_3_ID = '"+childDropDownDTO.getId()+"'";
            case Constant.FORM_4_CHILD_4:
                return "FORM_4_ENTITY_4_ID = '"+childDropDownDTO.getId()+"'";
            default:return null;
        }
    }

    public interface OnFilterSeleted{
        void onFilterSeleted(String QUERY);
    }
}
