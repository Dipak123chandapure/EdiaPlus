package com.example.deepak.myapplication.SmartCaller;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
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
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

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

        holder.circular_text.setText(mList.get(groupPosition).getForm1Entity1().charAt(0) + "");
        int colour = Utility.getCircularTextBackground(mList.get(groupPosition).getForm1Entity1().charAt(0));
        holder.circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);

        holder.student_name.setText(mList.get(groupPosition).getForm1Entity1());
        holder.mob_no.setText(mList.get(groupPosition).getForm1Entity4());

        holder.time_duartion.setText(sdf.format(mList.get(groupPosition).getCreatedDate()) + " (" + mList.get(groupPosition).getSmartCallDuration() + ")");

        if (groupPosition == (mList.size() - 10))
            loadMore(mList.size());

    }

    private void loadMore(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOnSmartCallerListAdapterCallback.onLoadMore(index);
            }
        }).start();
    }


    int mGroup_position;

    @Override
    public void onBindChildViewHolder(MyChildBatchViewHolder holder, final int groupPosition, final int childPosition, int viewType) {
        this.mGroup_position = groupPosition;
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupBatchesViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }

    @Override
    public void onClick(View v) {

    }


    OnSmartCallerListAdapterCallback mOnSmartCallerListAdapterCallback;

    public void setOnSmartCallerListAdapterCallback(OnSmartCallerListAdapterCallback mOnSmartCallerListAdapterCallback) {
        this.mOnSmartCallerListAdapterCallback = mOnSmartCallerListAdapterCallback;
    }


    public interface OnSmartCallerListAdapterCallback {
        void onLoadMore(int index);
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
    ImageView sim_type_image;

    public MyGroupBatchesViewHolder(View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        heading_text = (TextView) itemView.findViewById(R.id.heading_text);
        circular_text = (TextView) itemView.findViewById(R.id.circular_text);
        student_name = (TextView) itemView.findViewById(R.id.student_name);
        mob_no = (TextView) itemView.findViewById(R.id.mob_no);
        time_duartion = (TextView) itemView.findViewById(R.id.time_duartion);
        call_type_image = (ImageView) itemView.findViewById(R.id.call_type_image);
        sim_type_image = (ImageView) itemView.findViewById(R.id.sim_type_image);
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