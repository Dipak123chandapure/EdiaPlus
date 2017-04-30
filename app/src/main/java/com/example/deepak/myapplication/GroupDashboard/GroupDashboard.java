package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.deepak.myapplication.AddActivity.AddActivityDialog;
import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.Utility.Constant;


public class GroupDashboard extends Fragment implements GroupAdapter.GroupFroagmetCallback, GroupStudents.OnStudentClicked, View.OnClickListener, AddGroupDialog.OnBatchaAdded {

    ViewPager view_pager;
    ImageView floating_btn;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashboard, container, false);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager);
        floating_btn = (ImageView) view.findViewById(R.id.floating_btn);
        floating_btn.setOnClickListener(this);
        setUpViewPager();
        return view;
    }

    Groups fragment1;
    GroupStudents fragmet2;
    StudentProfile fragment3;

    private void setUpViewPager() {
        CustomFragmentPageradapter adapter = new CustomFragmentPageradapter(getActivity().getSupportFragmentManager());
        fragment1 = new Groups();
        fragment1.setGroupFroagmetCallback(this);
        fragmet2 = new GroupStudents();
        fragmet2.setOnStudentClicked(this);
        fragment3 = new StudentProfile();


        adapter.addFragment(fragment1, "Group Fragment");
        adapter.addFragment(fragmet2, "Group Details");
        adapter.addFragment(fragment3, "Group_Student");
        view_pager.setOffscreenPageLimit(2);
        view_pager.setAdapter(adapter);
    }

    @Override
    public void onGroupItemClicked(BatchDTO dto) {
        if (null != fragmet2)
           fragmet2.changeBatch(dto);

        view_pager.setCurrentItem(1);
    }

    @Override
    public void onGroupMenuIconClicked(MenuItem item, BatchDTO dto) {
        switch (item.getItemId()) {
            case R.id.menu_sms:
                SMSDashboardFragment smsFragment = new SMSDashboardFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.SMS_TYPE, Constant.SMS_GROUP_CLIENT);
                bundle.putParcelable(Constant.SMS_BATCH_DTO, dto);
                smsFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, smsFragment).commit();
                break;
        }

    }

    @Override
    public void studentClicked(StudentDTO sto) {
        if (null != fragment3)
            fragment3.changeStudent(sto);
        view_pager.setCurrentItem(2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_btn:
                AddGroupDialog dialog = new AddGroupDialog(getActivity());
                dialog.setOnBatchaAdded(this);
                dialog.show();
                break;
        }
    }

    @Override
    public void onBatchAdded(BatchDTO batch) {
        fragment1.addBatch(batch);
    }
}
