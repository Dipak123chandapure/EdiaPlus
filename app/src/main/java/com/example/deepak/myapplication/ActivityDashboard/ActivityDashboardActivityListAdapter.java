package com.example.deepak.myapplication.ActivityDashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class ActivityDashboardActivityListAdapter extends AbstractExpandableItemAdapter<MyGroupBatchesViewHolder, MyChildBatchViewHolder> implements View.OnClickListener {
    ArrayList<ActivityDTO> mList;
    Context mContext;
    ArrayList<DropDownDataDTO> list;

    public ActivityDashboardActivityListAdapter(Context mContext, ArrayList<ActivityDTO> mList) {
        Log.d("rohit", "size "+mList.size());
        this.mContext = mContext;
        this.mList = mList;
        list = new DropDownDataDAO(mContext).getFormData(Constant.ACTIVITY_DROPDOWN_VALUES);
        setHasStableIds(true);
    }


    public int getGroupCount() {
        return mList.size();
    }


    public int getChildCount(int groupPosition) {
        return 1;
    }


    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public MyGroupBatchesViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard_recycler_group_item, parent, false);
        return new MyGroupBatchesViewHolder(v);
    }


    public MyChildBatchViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard_recycler_child_item, parent, false);
        return new MyChildBatchViewHolder(v);
    }
    private SimpleDateFormat dateFormatForMonthDay = new SimpleDateFormat("hh : mm aa", Locale.getDefault());

    public void onBindGroupViewHolder(MyGroupBatchesViewHolder holder, final int groupPosition, int viewType) {

        ActivityDTO data = mList.get(groupPosition);
        if (null != data) {
            holder.a_d_r_g_i_campain_name.setText(data.getForm1Entity1());
            holder.a_d_r_g_i_time.setText(dateFormatForMonthDay.format(data.getNextActionDate()));
            holder.a_d_r_g_i_activity_title.setText(getActivityTitleForID(data.getActvityTypeID()));
            holder.a_d_r_g_i_activity_comment.setText(data.getActivityComment());
        }

    }

    private String getActivityTitleForID(int actvityTypeID) {
        for (int i =0; i<list.size(); i++){
            if (list.get(i).getId() == actvityTypeID)
                return list.get(i).getTitle();
        }

        return "Unknown Activity";
    }


    @Override
    public void onBindChildViewHolder(MyChildBatchViewHolder holder, final int groupPosition, final int childPosition, int viewType) {
//        holder.lead_dashboard_recycler_child_item_alert.setOnClickListener(this);
//        holder.lead_dashboard_recycler_child_item_email.setOnClickListener(this);
//        holder.lead_dashboard_recycler_child_item_sms.setOnClickListener(this);
//        holder.lead_dashboard_recycler_child_item_call.setOnClickListener(this);
//        holder.lead_dashboard_recycler_child_item_details.setOnClickListener(this);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupBatchesViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }

    @Override
    public void onClick(View v) {

    }


    //@Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.lead_dashboard_recycler_child_item_alert:
//                AddActivityDialog dialog = new AddActivityDialog(mContext, mList.get(mGroup_position));
//                dialog.show();
//                break;
//            case R.id.lead_dashboard_recycler_child_item_email:
//                break;
//            case R.id.lead_dashboard_recycler_child_item_sms:
//                break;
//            case R.id.lead_dashboard_recycler_child_item_call:
//                break;
//            case R.id.lead_dashboard_recycler_child_item_details:
//                break;
//        }
//    }
}

class MyGroupBatchesViewHolder extends AbstractExpandableItemViewHolder {
    LinearLayout linearLayout;
    TextView a_d_r_g_i_campain_name;
    TextView a_d_r_g_i_time;
    TextView a_d_r_g_i_activity_title;
    TextView a_d_r_g_i_activity_comment;


    public MyGroupBatchesViewHolder(View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        a_d_r_g_i_campain_name = (TextView) itemView.findViewById(R.id.a_d_r_g_i_campain_name);
        a_d_r_g_i_time = (TextView) itemView.findViewById(R.id.a_d_r_g_i_time);
        a_d_r_g_i_activity_title = (TextView) itemView.findViewById(R.id.a_d_r_g_i_activity_title);
        a_d_r_g_i_activity_comment = (TextView) itemView.findViewById(R.id.a_d_r_g_i_activity_comment);
    }
}

class MyChildBatchViewHolder extends AbstractExpandableItemViewHolder {
    LinearLayout linearLayout;
    ImageView lead_dashboard_recycler_child_item_alert;
    ImageView lead_dashboard_recycler_child_item_email;
    ImageView lead_dashboard_recycler_child_item_sms;
    ImageView lead_dashboard_recycler_child_item_call;
    ImageView lead_dashboard_recycler_child_item_details;


    public MyChildBatchViewHolder(View itemView) {
        super(itemView);
        //linearLayout = (LinearLayout) itemView.findViewById(R.id.batches_ll_child_item);
//        lead_dashboard_recycler_child_item_alert = (ImageView) itemView.findViewById(R.id.lead_dashboard_recycler_child_item_alert);
//        lead_dashboard_recycler_child_item_email = (ImageView) itemView.findViewById(R.id.lead_dashboard_recycler_child_item_email);
//        lead_dashboard_recycler_child_item_sms = (ImageView) itemView.findViewById(R.id.lead_dashboard_recycler_child_item_sms);
//        lead_dashboard_recycler_child_item_call = (ImageView) itemView.findViewById(R.id.lead_dashboard_recycler_child_item_call);
//        lead_dashboard_recycler_child_item_details = (ImageView) itemView.findViewById(R.id.lead_dashboard_recycler_child_item_details);

    }
}