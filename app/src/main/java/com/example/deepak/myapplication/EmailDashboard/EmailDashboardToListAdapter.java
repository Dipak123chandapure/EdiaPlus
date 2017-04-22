package com.example.deepak.myapplication.EmailDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


/**
 * Created by DeepakC on 22/03/2017.
 */

public class EmailDashboardToListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StudentDTO> mList;

    public EmailDashboardToListAdapter(Context mContext, ArrayList<StudentDTO> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_dashboard_to_recycler_group_item, parent, false);
        return new LeadListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != mList) {
            LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
            headingViewHolder.student_name.setText(mList.get(position).getForm1Entity1());
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder {

        TextView student_name;


        public LeadListViewHolder(View v) {
            super(v);
            student_name = (TextView) v.findViewById(R.id.student_name);
        }
    }
}
