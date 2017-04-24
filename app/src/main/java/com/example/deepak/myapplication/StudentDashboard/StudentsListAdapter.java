package com.example.deepak.myapplication.StudentDashboard;

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



public class StudentsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StudentDTO> mList;

    public StudentsListAdapter(Context mContext, ArrayList<StudentDTO> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        return new LeadListViewHolder(view);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder mHolder = (LeadListViewHolder) holder;
        StudentDTO data = mList.get(position);
        mHolder.campaign_name.setText(data.getForm1Entity1() + " " + data.getForm1Entity2());
        mHolder.circular_text.setText(data.getForm1Entity1().charAt(0) + "");
        int colour = Utility.getCircularTextBackground(data.getForm1Entity1().charAt(0));

        mHolder.circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);
        mHolder.email_id.setText(data.getForm1Entity3());
        mHolder.mobile_num.setText(data.getForm1Entity4());

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
        void studentClicked(int position);
    }

    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout linearLayout;
        TextView circular_text;
        TextView campaign_name;
        TextView email_id;
        TextView mobile_num;
        public LeadListViewHolder(View v) {
            super(v);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            linearLayout.setOnClickListener(this);
            circular_text = (TextView) itemView.findViewById(R.id.circular_text);
            campaign_name = (TextView) itemView.findViewById(R.id.campaign_name);
            email_id = (TextView) itemView.findViewById(R.id.email_id);
            mobile_num = (TextView) itemView.findViewById(R.id.mobile_num);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.linear_layout:
                    if (null != mOnGroupStudentCallback)
                        mOnGroupStudentCallback.studentClicked(getAdapterPosition());
                    break;
            }
        }
    }
}