package com.example.deepak.myapplication.GroupDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class StudentProfileFormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StudentProfileFormDTO> mList;
    public StudentProfileFormAdapter(Context mContext, ArrayList<StudentProfileFormDTO> mList) {
        this.mList = mList;
        Log.d("rohit", "size "+mList.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_profile_form_item, parent, false);
        return new StudentProfileFormAdapter.LeadListViewHolder(view);

    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StudentProfileFormAdapter.LeadListViewHolder mHolder = (StudentProfileFormAdapter.LeadListViewHolder) holder;
        mHolder.heading.setText(mList.get(position).getHeading());
        mHolder.sub_heading.setText(mList.get(position).getSubheading());

    }


    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder {

        TextView sub_heading;
        TextView heading;

        public LeadListViewHolder(View v) {
            super(v);
            sub_heading = (TextView) itemView.findViewById(R.id.sub_heading);
            heading = (TextView) itemView.findViewById(R.id.heading);
        }

    }
}
