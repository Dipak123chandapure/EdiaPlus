package com.example.deepak.myapplication.SMSDashbard;
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
import android.widget.LinearLayout;

import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;

import java.util.ArrayList;

public class SMSDashboardFragment extends Fragment implements View.OnClickListener, SMSDashboardStudentListAdapter.OnSMSToListCallback {
    RecyclerView s_d_f_l_to_recycler_view, s_d_f_l_add_student_list_recycler_view;
    LinearLayout s_d_f_l_temp_text_ll;
    ImageView s_d_f_l_add_student_image;
    String TYPE = "";
    ArrayList<StudentDTO> mList = new ArrayList<>();
    ArrayList<StudentDTO> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sms_dashboard_fragment_layout, container, false);
        if (null != getArguments()) {
            TYPE = getArguments().getString(Constant.SMS_TYPE);
            mList = getArguments().getParcelableArrayList(Constant.SMS_CLIENT_LIST);
        }
        inItView(view);
        setUpRecyclerview();
        return view;
    }

    private void inItView(View view) {
        s_d_f_l_to_recycler_view = (RecyclerView) view.findViewById(R.id.s_d_f_l_to_recycler_view);
        s_d_f_l_add_student_list_recycler_view = (RecyclerView) view.findViewById(R.id.s_d_f_l_add_student_list_recycler_view);
        s_d_f_l_temp_text_ll = (LinearLayout) view.findViewById(R.id.s_d_f_l_temp_text_ll);
        s_d_f_l_add_student_image = (ImageView) view.findViewById(R.id.s_d_f_l_add_student_image);
        switch (TYPE){
            case Constant.SMS_SINGE_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                break;

            case Constant.SMS_MULTIPLE_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                break;

            case Constant.SMS_GROUP_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                break;

            case Constant.SMS_NO_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.VISIBLE);
                s_d_f_l_add_student_image.setOnClickListener(this);

                break;

            default:
                s_d_f_l_add_student_image.setVisibility(View.VISIBLE);
                s_d_f_l_add_student_image.setOnClickListener(this);
                break;
        }



    }
    SMSDashboardToListAdapter adapter;
    SMSDashboardStudentListAdapter adapter1;

    private void setUpRecyclerview() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        s_d_f_l_to_recycler_view.setLayoutManager(manager);
        adapter = new SMSDashboardToListAdapter(getActivity(), mList);
        s_d_f_l_to_recycler_view.setAdapter(adapter);

        s_d_f_l_add_student_list_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        StudentDAO dao = new StudentDAO(getActivity());
        list = dao.getStudentList(null, 0);
        adapter1 = new SMSDashboardStudentListAdapter(getActivity(), list);
        adapter1.setOnSMSClientEntryChanged(this);
        s_d_f_l_add_student_list_recycler_view.setAdapter(adapter1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.s_d_f_l_add_student_image:
                if (s_d_f_l_add_student_list_recycler_view.getVisibility() != View.VISIBLE) {
                    s_d_f_l_temp_text_ll.setVisibility(View.GONE);
                    s_d_f_l_add_student_list_recycler_view.setVisibility(View.VISIBLE);
                } else {
                    s_d_f_l_add_student_list_recycler_view.setVisibility(View.GONE);
                    s_d_f_l_temp_text_ll.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onSMSCliendAdded(StudentDTO dto) {
        Log.d("rohit","onAddedClient clicked");
        mList.add(dto);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSMSCliendRemoved(StudentDTO dto) {
        Log.d("rohit","onRemovedClient clicked");
        if (mList.contains(dto)) {
            mList.remove(dto);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMore(int index) {
        Log.d("rohit", "Load more is called");
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> dtolist = handler.getStudentList(null, index);
        list.addAll(dtolist);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter1.notifyDataSetChanged();
            }
        });
    }
}
