package com.example.deepak.myapplication.GroupDashboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepak.myapplication.AddActivity.AddActivityDialog;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.StudentDashboard.StudentsListAdapter;
import com.example.deepak.myapplication.Utility.Constant;

import java.util.ArrayList;

public class GroupStudents extends Fragment implements StudentsListAdapter.OnGroupStudentCallback, Groups.OnGroupItemClicked {
    RecyclerView student_list_recycler;
    ArrayList<StudentDTO> mList;
    StudentsListAdapter adpter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashbard_students, container, false);
        student_list_recycler = (RecyclerView) view.findViewById(R.id.student_list_recycler);
        setUpRecyclerView();
        Log.d("rohit", "oncreate is called");
        return view;
    }

    private void setUpRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mList = new BatchDAO(getActivity()).getStudentsForBatch(1L, 0);
        adpter = new StudentsListAdapter(getActivity(), mList);
        adpter.setOnGroupStudentCallback(this);
        student_list_recycler.setLayoutManager(manager);
        student_list_recycler.setAdapter(adpter);
    }


    public void loadMore(int index) {
        Log.d("rohit", "Load more is called");
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> list = handler.getStudentList(null, index);
        mList.addAll(list);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adpter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void studentClicked(int position) {
        if (null != mOnStudentClicked)
            mOnStudentClicked.studentClicked(mList.get(position), viewPager);
    }

    @Override
    public void popupMenuClicked(MenuItem menuItem, final StudentDTO dto) {
        switch (menuItem.getItemId()) {
            case R.id.menu_sms:
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


            case R.id.menu_call:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + dto.getForm1Entity4()));
                Log.d("rohit", "action call");
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    getActivity().startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                }

                break;

            case R.id.menu_activity:
                AddActivityDialog dialog = new AddActivityDialog(getActivity());
                dialog.setOnActivityAdded(new AddActivityDialog.OnActivityAdded() {
                    public void onActivityAdded(ActivityDTO activity) {
                        activity.setStudentID(dto.getId());
                        new ActivitiesDAO(getActivity()).addActivity(activity);
                    }
                });
                dialog.show();
                break;
        }
    }


    OnStudentClicked mOnStudentClicked;
    ViewPager viewPager;

    public void setOnStudentClicked(OnStudentClicked mOnStudentClicked, ViewPager viewPager) {
        this.mOnStudentClicked = mOnStudentClicked;
        this.viewPager = viewPager;
    }

    @Override
    public void onGroupItemClicked(BatchDTO batchDTO, ViewPager viewPager) {
        mList.clear();
        ArrayList<StudentDTO> list = new BatchDAO(getActivity()).getStudentsForBatch(batchDTO.getId(), 0);
        mList.addAll(list);
        Log.d("rohit", "mList " + mList.size());
        adpter.notifyDataSetChanged();
        viewPager.setCurrentItem(1);
    }

    public interface OnStudentClicked {
        void studentClicked(StudentDTO sto, ViewPager viewPager);
    }
}
