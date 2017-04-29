package com.example.deepak.myapplication.ActivityDashboard;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DAO.AttachmentDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class ActivityDashboardActivityListAdapter extends AbstractExpandableItemAdapter<ActivityDashboardActivityListAdapter.MyGroupBatchesViewHolder, ActivityDashboardActivityListAdapter.MyChildBatchViewHolder> {
    ArrayList<ActivityDTO> mList;
    Context mContext;

    OnActivityListCallBachk mOnActivityListCallBachk;

    public ActivityDashboardActivityListAdapter(Context mContext, ArrayList<ActivityDTO> mList) {
        Log.d("rohit", "size " + mList.size());
        this.mContext = mContext;
        this.mList = mList;
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
            holder.a_d_r_g_i_activity_title.setText(data.getActivityTitle());
            if (null != data.getActivityComment()) {
                holder.a_d_r_g_i_activity_comment.setVisibility(View.VISIBLE);
                holder.a_d_r_g_i_activity_comment.setText(data.getActivityComment());
            } else holder.a_d_r_g_i_activity_comment.setVisibility(View.GONE);
        }

    }


    public void onBindChildViewHolder(final MyChildBatchViewHolder holder, final int groupPosition, final int childPosition, int viewType) {
        final AttachmentDAO dao = new AttachmentDAO(mContext);
        ArrayList<String> list = dao.getAttachment(mList.get(groupPosition));
        final ActivityDTO activity = mList.get(groupPosition);
        if (list.size() > 0) {
            holder.attachment_recycler.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            ActivityAttchmentAdapter adapter = new ActivityAttchmentAdapter(mContext, list);
            holder.attachment_recycler.setLayoutManager(manager);
            holder.attachment_recycler.setAdapter(adapter);
        } else holder.attachment_recycler.setVisibility(View.GONE);

        if (null != activity.getActivityComment() && activity.getActivityComment().length() > 30) {
            holder.activity_comment.setVisibility(View.VISIBLE);
            holder.activity_comment.setText(mList.get(groupPosition).getActivityComment());
        } else holder.activity_comment.setVisibility(View.GONE);

        holder.image_one.setOnClickListener(new ChilClickListener(groupPosition, 0));
        holder.image_two.setOnClickListener(new ChilClickListener(groupPosition, 1));
        holder.image_three.setOnClickListener(new ChilClickListener(groupPosition, 2));
        holder.image_four.setOnClickListener(new ChilClickListener(groupPosition, 3));
        holder.image_five.setOnClickListener(new ChilClickListener(groupPosition, 4));

    }

    public class ChilClickListener implements View.OnClickListener {
        int childPosition, groupPosition;

        public ChilClickListener(int groupPosition, int childPosition) {
            this.childPosition = childPosition;
            this.groupPosition = groupPosition;
        }

        public void onClick(View v) {
            if (null != mOnActivityListCallBachk) {
                ActivityDTO activity = mList.get(groupPosition);
                StudentDTO student = new StudentDAO(mContext).getStudentForNumber(activity.getForm1Entity4());
                mOnActivityListCallBachk.onChildItemClicked(childPosition, student);
            }
        }
    }

    public boolean onCheckCanExpandOrCollapseGroup(MyGroupBatchesViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }


    public interface OnActivityListCallBachk {
        void onChildItemClicked(int position, StudentDTO dto);
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
        ImageView image_one;
        ImageView image_two;
        ImageView image_three;
        ImageView image_four;
        ImageView image_five;
        RecyclerView attachment_recycler;
        TextView activity_comment;


        public MyChildBatchViewHolder(View itemView) {
            super(itemView);
            image_one = (ImageView) itemView.findViewById(R.id.image_one);
            image_two = (ImageView) itemView.findViewById(R.id.image_two);
            image_three = (ImageView) itemView.findViewById(R.id.image_three);
            image_four = (ImageView) itemView.findViewById(R.id.image_four);
            image_five = (ImageView) itemView.findViewById(R.id.image_five);

            attachment_recycler = (RecyclerView) itemView.findViewById(R.id.attachment_recycler);
            activity_comment = (TextView) itemView.findViewById(R.id.activity_comment);
        }

    }


    public void setmOnActivityListCallBachk(OnActivityListCallBachk mOnActivityListCallBachk) {
        this.mOnActivityListCallBachk = mOnActivityListCallBachk;
    }

}

