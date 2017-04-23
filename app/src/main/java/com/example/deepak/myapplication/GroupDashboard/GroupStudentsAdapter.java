package com.example.deepak.myapplication.GroupDashboard;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Utility;

import java.util.ArrayList;



public class GroupStudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StudentDTO> mList;

    public GroupStudentsAdapter(Context mContext, ArrayList<StudentDTO> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_dashboard_student_list_item, parent, false);
        return new LeadListViewHolder(view);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder mHolder = (LeadListViewHolder) holder;
        StudentDTO data = mList.get(position);
        mHolder.s_d_r_g_i_campaign_name.setText(data.getForm1Entity1() + " " + data.getForm1Entity2());
        mHolder.s_d_r_g_i_circular_text.setText(data.getForm1Entity1().charAt(0) + "");
        int colour = Utility.getCircularTextBackground(data.getForm1Entity1().charAt(0));

        mHolder.s_d_r_g_i_circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);
        mHolder.s_d_r_g_i_email_id.setText(data.getForm1Entity3());
        mHolder.s_d_r_g_i_mobile_num.setText(data.getForm1Entity4());

        if (position == (mList.size() - 10))
            loadMore(mList.size());
    }

    private void loadMore(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOnGroupStudentCallback.loadMore(index);
            }
        }).start();
    }


    OnGroupStudentCallback mOnGroupStudentCallback;

    public void setOnGroupStudentCallback(OnGroupStudentCallback mOnGroupStudentCallback) {
        this.mOnGroupStudentCallback = mOnGroupStudentCallback;
    }

    public interface OnGroupStudentCallback {
        void loadMore(int index);
    }

    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView s_d_r_g_i_circular_text;
        TextView s_d_r_g_i_campaign_name;
        TextView s_d_r_g_i_email_id;
        TextView s_d_r_g_i_mobile_num;
        public LeadListViewHolder(View v) {
            super(v);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            s_d_r_g_i_circular_text = (TextView) itemView.findViewById(R.id.s_d_r_g_i_circular_text);
            s_d_r_g_i_campaign_name = (TextView) itemView.findViewById(R.id.s_d_r_g_i_campaign_name);
            s_d_r_g_i_email_id = (TextView) itemView.findViewById(R.id.s_d_r_g_i_email_id);
            s_d_r_g_i_mobile_num = (TextView) itemView.findViewById(R.id.s_d_r_g_i_mobile_num);
        }

    }
}
