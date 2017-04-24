package com.example.deepak.myapplication.StudentDashboard;


import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Utility;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.ArrayList;


public class StudentAdapter extends AbstractExpandableItemAdapter<StudentAdapter.MyGroupBatchesViewHolder, StudentAdapter.MyChildBatchViewHolder>  {
    private ArrayList<StudentDTO> mList;
    private OnStudentAdapterCallback mOnStudentAdapterCallback;
    private int groupPosition;

    public StudentAdapter(ArrayList<StudentDTO> mList) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_group_item, parent, false);
        return new MyGroupBatchesViewHolder(v);
    }
    public MyChildBatchViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_child_item, parent, false);
        return new MyChildBatchViewHolder(v);
    }

    public void onBindGroupViewHolder(MyGroupBatchesViewHolder holder, final int groupPosition, int viewType) {
        StudentDTO data = mList.get(groupPosition);
        int colour = Utility.getCircularTextBackground(data.getForm1Entity1().charAt(0));

        holder.campaign_name.setText(data.getForm1Entity1() + " " + data.getForm1Entity2());
        holder.email_id.setText(data.getForm1Entity3());
        holder.mobile_num.setText(data.getForm1Entity4());
        holder.circular_text.setText(data.getForm1Entity1().charAt(0) + "");
        holder.circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);

        if (groupPosition == (mList.size() - 10))
            loadMore(mList.size());

    }
    private void loadMore(final int index) {
        new Thread(new Runnable() {
            public void run() {
                mOnStudentAdapterCallback.onLoadMore(index);
            }
        }).start();
    }


    public void onBindChildViewHolder(MyChildBatchViewHolder holder, final int groupPosition, final int childPosition, int viewType) {
       this.groupPosition = groupPosition;
    }
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupBatchesViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }
    public void setOnStudentAdapterCallback(OnStudentAdapterCallback mOnStudentAdapterCallback) {
        this.mOnStudentAdapterCallback = mOnStudentAdapterCallback;
    }


    public interface OnStudentAdapterCallback {
        void onLoadMore(int index);
        void onChildItemClicekd(int child_index, StudentDTO dto);
    }



    public class MyChildBatchViewHolder extends AbstractExpandableItemViewHolder implements View.OnClickListener {

        ImageView image_one;
        ImageView image_two;
        ImageView image_three;
        ImageView image_four;
        ImageView image_five;
        ImageView image_six;

        public MyChildBatchViewHolder(View itemView) {
            super(itemView);
            image_one = (ImageView) itemView.findViewById(R.id.image_one);
            image_two = (ImageView) itemView.findViewById(R.id.image_two);
            image_three = (ImageView) itemView.findViewById(R.id.image_three);
            image_four = (ImageView) itemView.findViewById(R.id.image_four);
            image_five = (ImageView) itemView.findViewById(R.id.image_five);
            image_six = (ImageView) itemView.findViewById(R.id.image_six);

            image_one.setOnClickListener(this);
            image_two.setOnClickListener(this);
            image_three.setOnClickListener(this);
            image_four.setOnClickListener(this);
            image_five.setOnClickListener(this);
            image_six.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_one:
                    if (null != mOnStudentAdapterCallback)
                        mOnStudentAdapterCallback.onChildItemClicekd(0,mList.get(groupPosition));

                    break;
                case R.id.image_two:
                    if (null != mOnStudentAdapterCallback)
                        mOnStudentAdapterCallback.onChildItemClicekd(1,mList.get(groupPosition));
                    break;
                case R.id.image_three:
                    if (null != mOnStudentAdapterCallback)
                        mOnStudentAdapterCallback.onChildItemClicekd(2,mList.get(groupPosition));
                    break;
                case R.id.image_four:
                    break;
                case R.id.image_five:
                    break;
                case R.id.image_six:
                    if (null != mOnStudentAdapterCallback)
                        mOnStudentAdapterCallback.onChildItemClicekd(5,mList.get(groupPosition));
                    break;
            }
        }
    }


    public class MyGroupBatchesViewHolder extends AbstractExpandableItemViewHolder {
        TextView circular_text;
        TextView campaign_name;
        TextView email_id;
        TextView mobile_num;

        public MyGroupBatchesViewHolder(View itemView) {
            super(itemView);
            circular_text = (TextView) itemView.findViewById(R.id.circular_text);
            campaign_name = (TextView) itemView.findViewById(R.id.campaign_name);
            email_id = (TextView) itemView.findViewById(R.id.email_id);
            mobile_num = (TextView) itemView.findViewById(R.id.mobile_num);
        }
    }
}

