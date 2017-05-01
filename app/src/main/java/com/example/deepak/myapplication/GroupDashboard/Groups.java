package com.example.deepak.myapplication.GroupDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.R;
import java.util.ArrayList;


public class Groups extends Fragment implements View.OnClickListener, AddGroupDialog.OnBatchaAdded {

    RecyclerView group_dashbard_fragment_recycler;
    GroupAdapter adapter;
    ArrayList<BatchDTO> mList;
    GroupAdapter.GroupFroagmetCallback mGroupFroagmetCallback;
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
        adapter.setGroupFroagmetCallback(mGroupFroagmetCallback);
        group_dashbard_fragment_recycler.setAdapter(adapter);
    }

    public void setGroupFroagmetCallback(GroupAdapter.GroupFroagmetCallback mGroupFroagmetCallback){
        this.mGroupFroagmetCallback = mGroupFroagmetCallback;
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
}
