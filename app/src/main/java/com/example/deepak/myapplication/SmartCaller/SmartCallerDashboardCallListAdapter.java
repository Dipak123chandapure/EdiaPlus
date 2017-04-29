package com.example.deepak.myapplication.SmartCaller;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Utility;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class SmartCallerDashboardCallListAdapter extends AbstractExpandableItemAdapter<MyGroupBatchesViewHolder, MyChildBatchViewHolder> implements View.OnClickListener {
    ArrayList<ActivityDTO> mList;
    Context mContext;

    public SmartCallerDashboardCallListAdapter(Context mContext, ArrayList<ActivityDTO> mList) {
        this.mContext = mContext;
        this.mList = mList;
        setHasStableIds(true);
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return 1;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public MyGroupBatchesViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.smart_caller_dashboard_recycler_group_item, parent, false);
        return new MyGroupBatchesViewHolder(v);
    }

    @Override
    public MyChildBatchViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.smart_caller_dashboard_recycler_child_item, parent, false);
        return new MyChildBatchViewHolder(v);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("EEE, dd-MMM-yyyy");

    public void onBindGroupViewHolder(MyGroupBatchesViewHolder holder, final int groupPosition, int viewType) {
        if (groupPosition != 0) {
            if (sdf1.format(mList.get(groupPosition).getCreatedDate()).equals(sdf1.format(mList.get(groupPosition - 1).getCreatedDate()))) {
                holder.heading_text.setVisibility(View.GONE);
            } else {
                holder.heading_text.setVisibility(View.VISIBLE);
                holder.heading_text.setText(sdf1.format(mList.get(groupPosition).getCreatedDate()));
            }
        } else {
            holder.heading_text.setVisibility(View.VISIBLE);
            holder.heading_text.setText(sdf1.format(mList.get(groupPosition).getCreatedDate()));

        }

        int colour;
        if (null != mList.get(groupPosition).getForm1Entity1()) {
            holder.circular_text.setText(mList.get(groupPosition).getForm1Entity1().charAt(0) + "");
            colour = Utility.getCircularTextBackground(mList.get(groupPosition).getForm1Entity1().charAt(0));
        } else {
            holder.circular_text.setText("U");
            colour = Utility.getCircularTextBackground('U');
        }
        if (mList.get(groupPosition).getActvityTypeID() == 4) {
            holder.student_name.setTextColor(mContext.getResources().getColor(R.color.Red_400));
            holder.mob_no.setTextColor(mContext.getResources().getColor(R.color.Red_300));
            holder.time_duartion.setTextColor(mContext.getResources().getColor(R.color.Red_200));
        }else {
            holder.student_name.setTextColor(mContext.getResources().getColor(R.color.Grey_700));
            holder.mob_no.setTextColor(mContext.getResources().getColor(R.color.Grey_600));
            holder.time_duartion.setTextColor(mContext.getResources().getColor(R.color.Grey_500));
        }

        holder.circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);
        holder.student_name.setText(mList.get(groupPosition).getForm1Entity1());
        holder.mob_no.setText(mList.get(groupPosition).getForm1Entity4());
        holder.time_duartion.setText(sdf.format(mList.get(groupPosition).getCreatedDate()) + " (" + mList.get(groupPosition).getSmartCallDuration() + ")");

        holder.call_type_image.setImageResource(getIconForCallType(mList.get(groupPosition).getActvityTypeID()));
        if (groupPosition == (mList.size() - 10))
            loadMore(mList.size());

    }

    private int getIconForCallType(int actvityTypeID) {
        switch (actvityTypeID) {
            case 3:
                return R.mipmap.call_made_grey;
            case 4:
                return R.mipmap.call_missed_grey;

            case 5:
                return R.mipmap.call_received_grey;

            default:
                return 0;
        }
    }

    private void loadMore(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOnSmartCallerListAdapterCallback.onLoadMore(index);
            }
        }).start();
    }

    
    @Override
    public void onBindChildViewHolder(MyChildBatchViewHolder holder, final int groupPosition, final int childPosition, int viewType) {
        holder.image_one.setOnClickListener(new ChilClickListener(groupPosition, 0));
        holder.image_two.setOnClickListener(new ChilClickListener(groupPosition, 1));
        holder.image_three.setOnClickListener(new ChilClickListener(groupPosition, 2));
        holder.image_four.setOnClickListener(new ChilClickListener(groupPosition, 3));
        holder.image_five.setOnClickListener(new ChilClickListener(groupPosition, 4));
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupBatchesViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }

    @Override
    public void onClick(View v) {

    }
    public class ChilClickListener implements View.OnClickListener {
        int childPosition, groupPosition;

        public ChilClickListener(int groupPosition, int childPosition) {
            this.childPosition = childPosition;
            this.groupPosition = groupPosition;
        }

        public void onClick(View v) {
            if (null != mOnSmartCallerListAdapterCallback) {
                ActivityDTO activity = mList.get(groupPosition);
                StudentDTO student = new StudentDAO(mContext).getStudentForNumber(activity.getForm1Entity4());
                mOnSmartCallerListAdapterCallback.onChildItemClicked(childPosition, student);
            }
        }
    }

    OnSmartCallerListAdapterCallback mOnSmartCallerListAdapterCallback;

    public void setOnSmartCallerListAdapterCallback(OnSmartCallerListAdapterCallback mOnSmartCallerListAdapterCallback) {
        this.mOnSmartCallerListAdapterCallback = mOnSmartCallerListAdapterCallback;
    }


    public interface OnSmartCallerListAdapterCallback {
        void onLoadMore(int index);
        void onChildItemClicked(int position, StudentDTO sto);
    }
}

class MyGroupBatchesViewHolder extends AbstractExpandableItemViewHolder {
    LinearLayout linearLayout;
    TextView heading_text;
    TextView circular_text;
    TextView student_name;
    TextView mob_no;
    TextView time_duartion;
    ImageView call_type_image;

    public MyGroupBatchesViewHolder(View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        heading_text = (TextView) itemView.findViewById(R.id.heading_text);
        circular_text = (TextView) itemView.findViewById(R.id.circular_text);
        student_name = (TextView) itemView.findViewById(R.id.student_name);
        mob_no = (TextView) itemView.findViewById(R.id.mob_no);
        time_duartion = (TextView) itemView.findViewById(R.id.time_duartion);
        call_type_image = (ImageView) itemView.findViewById(R.id.call_type_image);
    }
}

class MyChildBatchViewHolder extends AbstractExpandableItemViewHolder {

    ImageView image_one;
    ImageView image_two;
    ImageView image_three;
    ImageView image_four;
    ImageView image_five;


    public MyChildBatchViewHolder(View itemView) {
        super(itemView);

        image_one = (ImageView) itemView.findViewById(R.id.image_one);
        image_two = (ImageView) itemView.findViewById(R.id.image_two);
        image_three = (ImageView) itemView.findViewById(R.id.image_three);
        image_four = (ImageView) itemView.findViewById(R.id.image_four);
        image_five = (ImageView) itemView.findViewById(R.id.image_five);

    }
}