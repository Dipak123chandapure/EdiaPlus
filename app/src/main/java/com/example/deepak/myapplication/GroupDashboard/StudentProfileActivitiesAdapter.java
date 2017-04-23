package com.example.deepak.myapplication.GroupDashboard;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;
import com.example.deepak.myapplication.Utility.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Deepak on 4/23/2017.
 */

public class StudentProfileActivitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ActivityDTO> mList;
    ArrayList<DropDownDataDTO> list;
    public StudentProfileActivitiesAdapter(Context mContext, ArrayList<ActivityDTO> mList) {
        this.mList = mList;
        list = new DropDownDataDAO(mContext).getFormData(Constant.ACTIVITY_DROPDOWN_VALUES);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_profile_activity_item, parent, false);
        return new StudentProfileActivitiesAdapter.LeadListViewHolder(view);

    }

    private SimpleDateFormat dateFormatForMonthDay = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StudentProfileActivitiesAdapter.LeadListViewHolder mHolder = (StudentProfileActivitiesAdapter.LeadListViewHolder) holder;
        ActivityDTO data = mList.get(position);
        mHolder.activity_title.setText(getActivityTitleForID(data.getActvityTypeID()));
        mHolder.date_time.setText(dateFormatForMonthDay.format(data.getCreatedDate()));
        mHolder.activity_comment.setText(data.getActivityComment());

        if (position == (mList.size() - 10))
            loadMore(mList.size());
    }

    private void loadMore(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();
    }
    private String getActivityTitleForID(int actvityTypeID) {
        for (int i =0; i<list.size(); i++){
            if (list.get(i).getId() == actvityTypeID)
                return list.get(i).getTitle();
        }

        return "Unknown Activity";
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
        TextView activity_title;
        TextView date_time;
        TextView activity_comment;
        public LeadListViewHolder(View v) {
            super(v);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            activity_title = (TextView) itemView.findViewById(R.id.activity_title);
            date_time = (TextView) itemView.findViewById(R.id.date_time);
            activity_comment = (TextView) itemView.findViewById(R.id.activity_comment);
        }

    }
}
