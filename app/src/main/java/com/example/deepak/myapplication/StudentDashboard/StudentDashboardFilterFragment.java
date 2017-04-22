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
import android.widget.ImageView;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.UserDataParser;
import com.example.deepak.myapplication.Utility.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentDashboardFilterFragment extends Fragment
        implements StudentDashboardFilterParentAdapter.OnFilterParentItemSelected, View.OnClickListener, AddDropDownValueDialog.OnDropDownValueAdded {

    RecyclerView s_d_f_f_l_parent_recycler, s_d_f_f_l_child_recycler;
    StudentDashboardFilterChildListAdapter adapter;
    JSONArray jsonArray;
    ArrayList<ArrayList<DropDownDataDTO>> list;
    String TYPE;
    int position;
    ImageView s_d_f_F_l_check_mark,  add_drop_down_child;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_dashboard_filte_fragment_layout, container, false);
        s_d_f_f_l_parent_recycler = (RecyclerView) view.findViewById(R.id.s_d_f_f_l_parent_recycler);
        s_d_f_f_l_child_recycler = (RecyclerView) view.findViewById(R.id.s_d_f_f_l_child_recycler);
        s_d_f_F_l_check_mark = (ImageView) view.findViewById(R.id.s_d_f_F_l_check_mark);
        add_drop_down_child = (ImageView) view.findViewById(R.id.add_drop_down_child);

        s_d_f_F_l_check_mark.setOnClickListener(this);
        add_drop_down_child.setOnClickListener(this);

        getDataForRecyclerView();
        setUpParentRecycler();
        setUpChildRecycler(list.get(0));
        position = 0;
        return view;
    }
    private void setUpChildRecycler(ArrayList<DropDownDataDTO> list) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        s_d_f_f_l_child_recycler.setLayoutManager(manager);
        adapter = new StudentDashboardFilterChildListAdapter(getActivity(), list);
        s_d_f_f_l_child_recycler.setAdapter(adapter);
    }
    private void setUpParentRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        s_d_f_f_l_parent_recycler.setLayoutManager(manager);
        StudentDashboardFilterParentAdapter adapter = new StudentDashboardFilterParentAdapter(getActivity(), jsonArray, list);
        adapter.setmOnFilterParentItemSelected(this);
        s_d_f_f_l_parent_recycler.setAdapter(adapter);
    }
    private void getDataForRecyclerView() {
        jsonArray = null;
        list = null;
        try {
            DropDownDataDAO handler = new DropDownDataDAO(getActivity());
            jsonArray = new JSONArray(UserInfo.getInstance().readFilterJSONArray(UserDataParser.FILTER_DATA, getActivity()));
            list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                ArrayList<DropDownDataDTO> dataList = handler.getFormData(jsonObject.optString("KEY"));

                if (i == 0){
                   TYPE =  jsonObject.optString("KEY");
                }
                list.add(dataList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onFilterParentItemSelected(ArrayList<DropDownDataDTO> list, int position) {
        try {
            JSONObject jsonObject = (JSONObject) jsonArray.get(position);
            TYPE = jsonObject.optString("KEY");
            Log.d("rohit", "TYPE "+TYPE);
            this.position = position;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setUpChildRecycler(list);
    }
    public String computeQueryFromFilter() throws JSONException {
        Boolean hasOneEntryAtLeast = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (hasOneEntryAtLeast)
                builder.append(" AND (");
            else builder.append("(");

            ArrayList<DropDownDataDTO> formDataList = list.get(i);
            Boolean hasOneChildEntryAtleast =false;
            for (int j = 0; j < formDataList.size(); j++) {
                if (formDataList.get(j).getChecked()) {
                    if (hasOneChildEntryAtleast)
                        builder.append(" OR ");

                    builder.append(jsonObject.optString("DATABASE_KEY") + " = '" + formDataList.get(j).getId() + "'");
                    hasOneEntryAtLeast = true;
                    hasOneChildEntryAtleast = true;
                }
            }

            if (hasOneChildEntryAtleast)
            builder.append(")");
            else {
                if (hasOneEntryAtLeast)
                    builder.delete(builder.length()-6, builder.length());
                else builder.delete(builder.length()-1, builder.length());
            }
        }
        return builder.toString();
    }



    public void onClick(View v) {
        switch (v.getId()){
            case R.id.s_d_f_F_l_check_mark:
                try {
                    String query = computeQueryFromFilter();
                    Log.d("rohit", "query "+query);
                    if (null != mOnFilterSeleted)
                        mOnFilterSeleted.onFilterSeleted(query);

                    getActivity().getSupportFragmentManager().beginTransaction().
                            setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                            .remove(this).commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;


            case R.id.add_drop_down_child:
                AddDropDownValueDialog dialog = new AddDropDownValueDialog(getActivity(),TYPE,position );
                dialog.setOnDropDownValueAdded(this);
                dialog.show();
                break;



        }
    }

    OnFilterSeleted mOnFilterSeleted;
    public void setOnFilterSeleted(OnFilterSeleted mOnFilterSeleted){
        this.mOnFilterSeleted = mOnFilterSeleted;
    }

    @Override
    public void onDropDownValueAdded(int position) {
        getDataForRecyclerView();
        setUpParentRecycler();
        setUpChildRecycler(list.get(position));
    }

    public interface OnFilterSeleted{
        void onFilterSeleted(String QUERY);
    }
}
