package com.example.deepak.myapplication.StudentDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class FilterChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<DropDownDataDTO> mList;
    public FilterChildAdapter(Context mContext, ArrayList<DropDownDataDTO> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_filter_child_item, parent, false);
        return new LeadListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != mList) {
            LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
            if (mList.get(position).getChecked())
                headingViewHolder.s_d_f_r_c_l_i_checkBox.setChecked(true);
            else headingViewHolder.s_d_f_r_c_l_i_checkBox.setChecked(false);
            headingViewHolder.s_d_f_r_c_l_i_text.setText(mList.get(position).getTitle());
        }
    }


    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView s_d_f_r_c_l_i_text;
        CheckBox s_d_f_r_c_l_i_checkBox;


        public LeadListViewHolder(View v) {
            super(v);
            s_d_f_r_c_l_i_checkBox = (CheckBox) v.findViewById(R.id.s_d_f_r_c_l_i_checkBox);
            s_d_f_r_c_l_i_text = (TextView) v.findViewById(R.id.s_d_f_r_c_l_i_text);
            s_d_f_r_c_l_i_checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mList.get(getAdapterPosition()).getChecked())
                mList.get(getAdapterPosition()).setChecked(false);
            else mList.get(getAdapterPosition()).setChecked(true);
        }
    }
}
