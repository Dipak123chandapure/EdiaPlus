package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.deepak.myapplication.AddActivity.AddActivityDialog;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.EmailDashboard.EmailDashboardFragment;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.Utility.Constant;

import java.util.ArrayList;


public class Groups extends Fragment implements View.OnClickListener, AddGroupDialog.OnBatchaAdded, GroupAdapter.GroupFroagmetCallback {

    RecyclerView group_dashbard_fragment_recycler;
    GroupAdapter adapter;
    ArrayList<BatchDTO> mList;
    ImageView floating_btn;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_dashboard_groups, container, false);
        group_dashbard_fragment_recycler = (RecyclerView) view.findViewById(R.id.group_dashbard_fragment_recycler);
        floating_btn = (ImageView) view.findViewById(R.id.floating_btn);
        floating_btn.setOnClickListener(this);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        group_dashbard_fragment_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        BatchDAO batchDAO = new BatchDAO(getActivity());
        mList = batchDAO.getBatchs();
        adapter = new GroupAdapter(getActivity(), mList);
        adapter.setGroupFroagmetCallback(this);
        group_dashbard_fragment_recycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_btn:
                AddGroupDialog dialog = new AddGroupDialog(getActivity(),mList );
                Log.d("rohit", "size "+mList.size());
                dialog.setOnBatchaAdded(this);
                dialog.show();
                break;
        }
    }

    @Override
    public void onBatchAdded(BatchDTO batch) {
        Log.d("rohit", "batch added");
        mList.add(batch);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBatchEdited(BatchDTO batch) {
        Log.d("rohit", "batch edited");
        adapter.notifyDataSetChanged();
    }


    public void onGroupItemClicked(BatchDTO dto) {
        if (null != mOnGroupItemClicked)
            mOnGroupItemClicked.onGroupItemClicked(dto, viewPager);
    }


    public void onGroupMenuIconClicked(MenuItem item, final BatchDTO dto) {
        switch (item.getItemId()) {
            case R.id.menu_sms:
                SMSDashboardFragment smsFragment = new SMSDashboardFragment();
                BatchDAO batchDAO = new BatchDAO(getActivity());
                int count = batchDAO.getStudentsCountForBatch(dto);
                if (count > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.SMS_TYPE, Constant.SMS_GROUP_CLIENT);
                    bundle.putParcelable(Constant.SMS_BATCH_DTO, dto);
                    smsFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().
                            setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                            .replace(R.id.main_frame_layout, smsFragment).commit();
                } else
                    Toast.makeText(getActivity(), "No Students in the Batch", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_email:
                EmailDashboardFragment emailFragment = new EmailDashboardFragment();
                BatchDAO batch = new BatchDAO(getActivity());
                int count1 = batch.getStudentsCountForBatch(dto);
                if (count1 > 0) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString(Constant.SMS_TYPE, Constant.SMS_GROUP_CLIENT);
                    bundle1.putParcelable(Constant.SMS_BATCH_DTO, dto);
                    emailFragment.setArguments(bundle1);
                    getActivity().getSupportFragmentManager().beginTransaction().
                            setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                            .replace(R.id.main_frame_layout, emailFragment).commit();
                } else
                    Toast.makeText(getActivity(), "No Students in the Batch", Toast.LENGTH_SHORT).show();
                break;


            case R.id.menu_activity:
                AddActivityDialog dialog = new AddActivityDialog(getActivity());
                dialog.setOnActivityAdded(new AddActivityDialog.OnActivityAdded() {
                    public void onActivityAdded(ActivityDTO activity) {
                        activity.setStudentID(dto.getId());
                        new ActivitiesDAO(getActivity()).addBatchActivity(activity);
                    }
                });
                dialog.show();
                break;
        }
    }
    ViewPager viewPager;
    OnGroupItemClicked mOnGroupItemClicked;
    public void setOnGroupItemClicked(OnGroupItemClicked mOnGroupItemClicked, ViewPager viewPager){
        this.mOnGroupItemClicked = mOnGroupItemClicked;
        this.viewPager = viewPager;
    }

    public interface OnGroupItemClicked{
        void onGroupItemClicked(BatchDTO batchDTO, ViewPager viewPager);
    }
}
