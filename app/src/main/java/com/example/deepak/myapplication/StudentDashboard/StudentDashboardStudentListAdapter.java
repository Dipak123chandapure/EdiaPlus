package com.example.deepak.myapplication.StudentDashboard;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Utility;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.ArrayList;


public class StudentDashboardStudentListAdapter extends AbstractExpandableItemAdapter<StudentDashboardStudentListAdapter.MyGroupBatchesViewHolder, StudentDashboardStudentListAdapter.MyChildBatchViewHolder>  {
    ArrayList<StudentDTO> mList;
    static Context mContext;
    OnStudentListAdapterCallback mOnStudentListAdapterCallback;

    public StudentDashboardStudentListAdapter(Context mContext, ArrayList<StudentDTO> mList) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_recycler_group_list_item, parent, false);
        return new MyGroupBatchesViewHolder(v);
    }
    public MyChildBatchViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_recycler_child_list_item, parent, false);
        return new MyChildBatchViewHolder(v);
    }
    public void onBindGroupViewHolder(MyGroupBatchesViewHolder holder, final int groupPosition, int viewType) {

        StudentDTO data = mList.get(groupPosition);
        holder.s_d_r_g_i_campaign_name.setText(data.getForm1Entity1() + " " + data.getForm1Entity2());
        holder.s_d_r_g_i_circular_text.setText(data.getForm1Entity1().charAt(0) + "");
        int colour = Utility.getCircularTextBackground(data.getForm1Entity1().charAt(0));

        holder.s_d_r_g_i_circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);
        holder.s_d_r_g_i_email_id.setText(data.getForm1Entity3());
        holder.s_d_r_g_i_mobile_num.setText(data.getForm1Entity4());

        if (groupPosition == (mList.size() - 10))
            loadMore(mList.size());

    }
    private void loadMore(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOnStudentListAdapterCallback.onLoadMore(index);
            }
        }).start();
    }

    int groupPosition;
    public void onBindChildViewHolder(MyChildBatchViewHolder holder, final int groupPosition, final int childPosition, int viewType) {
       this.groupPosition = groupPosition;
    }
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupBatchesViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }
    public void setOnStudentListAdapterCallback(OnStudentListAdapterCallback mOnStudentListAdapterCallback) {
        this.mOnStudentListAdapterCallback = mOnStudentListAdapterCallback;
    }


    public interface OnStudentListAdapterCallback {
        void onLoadMore(int index);
        void onChildItemClicekd(int child_index, StudentDTO dto);
    }



    public class MyChildBatchViewHolder extends AbstractExpandableItemViewHolder implements View.OnClickListener {

        ImageView s_d_r_c_i_image_one;
        ImageView s_d_r_c_i_image_two;
        ImageView s_d_r_c_i_image_three;
        ImageView s_d_r_c_i_image_four;
        ImageView s_d_r_c_i_image_five;
        ImageView s_d_r_c_i_image_six;

        public MyChildBatchViewHolder(View itemView) {
            super(itemView);
            s_d_r_c_i_image_one = (ImageView) itemView.findViewById(R.id.s_d_r_c_i_image_one);
            s_d_r_c_i_image_two = (ImageView) itemView.findViewById(R.id.s_d_r_c_i_image_two);
            s_d_r_c_i_image_three = (ImageView) itemView.findViewById(R.id.s_d_r_c_i_image_three);
            s_d_r_c_i_image_four = (ImageView) itemView.findViewById(R.id.s_d_r_c_i_image_four);
            s_d_r_c_i_image_five = (ImageView) itemView.findViewById(R.id.s_d_r_c_i_image_five);
            s_d_r_c_i_image_six = (ImageView) itemView.findViewById(R.id.s_d_r_c_i_image_six);

            s_d_r_c_i_image_one.setOnClickListener(this);
            s_d_r_c_i_image_two.setOnClickListener(this);
            s_d_r_c_i_image_three.setOnClickListener(this);
            s_d_r_c_i_image_four.setOnClickListener(this);
            s_d_r_c_i_image_five.setOnClickListener(this);
            s_d_r_c_i_image_six.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.s_d_r_c_i_image_one:
                    if (null != mOnStudentListAdapterCallback)
                        mOnStudentListAdapterCallback.onChildItemClicekd(0,mList.get(groupPosition));

                    break;
                case R.id.s_d_r_c_i_image_two:
                    if (null != mOnStudentListAdapterCallback)
                        mOnStudentListAdapterCallback.onChildItemClicekd(1,mList.get(groupPosition));
                    break;
                case R.id.s_d_r_c_i_image_three:
                    if (null != mOnStudentListAdapterCallback)
                        mOnStudentListAdapterCallback.onChildItemClicekd(2,mList.get(groupPosition));
                    break;
                case R.id.s_d_r_c_i_image_four:
                    break;
                case R.id.s_d_r_c_i_image_five:
                    break;
                case R.id.s_d_r_c_i_image_six:
                    break;
            }
        }
    }


    public class MyGroupBatchesViewHolder extends AbstractExpandableItemViewHolder {
        LinearLayout linearLayout;
        TextView s_d_r_g_i_circular_text;
        TextView s_d_r_g_i_campaign_name;
        TextView s_d_r_g_i_email_id;
        TextView s_d_r_g_i_mobile_num;

        public MyGroupBatchesViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            s_d_r_g_i_circular_text = (TextView) itemView.findViewById(R.id.s_d_r_g_i_circular_text);
            s_d_r_g_i_campaign_name = (TextView) itemView.findViewById(R.id.s_d_r_g_i_campaign_name);
            s_d_r_g_i_email_id = (TextView) itemView.findViewById(R.id.s_d_r_g_i_email_id);
            s_d_r_g_i_mobile_num = (TextView) itemView.findViewById(R.id.s_d_r_g_i_mobile_num);
        }
    }
}

