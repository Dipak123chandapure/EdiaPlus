package com.example.deepak.myapplication.SmartCaller;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.SMSDashbard.SMSDashboardFragment;
import com.example.deepak.myapplication.Utility.Constant;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import java.util.ArrayList;


public class SmartCallerDashboardFragment extends Fragment implements SmartCallerDashboardCallListAdapter.OnSmartCallerListAdapterCallback {
    RecyclerViewExpandableItemManager expMgr;
    SmartCallerDashboardCallListAdapter adapter;
    RecyclerView s_c_d_f_l_recycler_view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.smart_caller_dashboard_fragment_layout, container, false);
        s_c_d_f_l_recycler_view = (RecyclerView) view.findViewById(R.id.s_c_d_f_l_recycler_view);
        setUpRecyclerView();
        return view;
    }

    ArrayList<ActivityDTO> mList;
    private void setUpRecyclerView() {
        expMgr = new RecyclerViewExpandableItemManager(null);
        s_c_d_f_l_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        ActivitiesDAO dto = new ActivitiesDAO(getActivity());
        mList = dto.getActivities(0);
        adapter = new SmartCallerDashboardCallListAdapter(getActivity(), mList);
        adapter.setOnSmartCallerListAdapterCallback(this);
        s_c_d_f_l_recycler_view.setAdapter(expMgr.createWrappedAdapter(adapter));
        ((SimpleItemAnimator) s_c_d_f_l_recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
        expMgr.attachRecyclerView(s_c_d_f_l_recycler_view);

    }


    @Override
    public void onLoadMore(int index) {
        Log.d("rohit", "Load more is called");
        ActivitiesDAO handler = new ActivitiesDAO(getActivity());
        ArrayList<ActivityDTO> list = handler.getActivities(index);
        mList.addAll(list);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onChildItemClicked(int position, StudentDTO sto) {
        switch (position){
            case 0:
                SMSDashboardFragment fragment = new SMSDashboardFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.SMS_TYPE, Constant.SMS_SINGE_CLIENT);
                ArrayList<StudentDTO> list = new ArrayList<>();
                list.add(sto);
                bundle.putParcelableArrayList(Constant.SMS_CLIENT_LIST, list);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                        .replace(R.id.main_frame_layout, fragment).commit();
                break;

            case 1:

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

        }
    }


}
