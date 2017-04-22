package com.example.deepak.myapplication.EmailDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


/**
 * Created by DeepakC on 22/03/2017.
 */

public class EmailDashboardStudentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StudentDTO> mList;

    public EmailDashboardStudentListAdapter(Context mContext, ArrayList<StudentDTO> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_dashboard_student_list_recycler_group_item, parent, false);
        return new LeadListViewHolder(view);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder lHolder = (LeadListViewHolder) holder;
        if (null != mList) {
            lHolder.checkBox.setChecked(mList.get(position).getCkecked());
            lHolder.student_name.setText(mList.get(position).getForm1Entity1()+" "+mList.get(position).getForm1Entity2());
            lHolder.mob_no.setText(mList.get(position).getForm1Entity4());
        }

        if (position == (mList.size() - 5))
            loadMore(mList.size());
    }

    private void loadMore(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOnSMSToListCallback.loadMore(index);
            }
        }).start();
    }


    OnSMSToListCallback mOnSMSToListCallback;

    public void setOnSMSClientEntryChanged(OnSMSToListCallback mOnSMSToListCallback) {
        this.mOnSMSToListCallback = mOnSMSToListCallback;
    }

    public interface OnSMSToListCallback {
        void onSMSCliendAdded(StudentDTO dto);
        void onSMSCliendRemoved(StudentDTO dto);
        void loadMore(int index);
    }

    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox checkBox;
        TextView student_name, mob_no;
        public LeadListViewHolder(View v) {
            super(v);
            checkBox = (CheckBox) v.findViewById(R.id.checkBox);
            student_name = (TextView) v.findViewById(R.id.student_name);
            mob_no = (TextView) v.findViewById(R.id.mob_no);
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CheckBox chekbox = (CheckBox) v;
            if (chekbox.isChecked()){
                mOnSMSToListCallback.onSMSCliendAdded(mList.get(getAdapterPosition()));
                mList.get(getAdapterPosition()).setCkecked(true);
            }else {
                mOnSMSToListCallback.onSMSCliendRemoved(mList.get(getAdapterPosition()));
                mList.get(getAdapterPosition()).setCkecked(false);
            }
        }
    }
}
