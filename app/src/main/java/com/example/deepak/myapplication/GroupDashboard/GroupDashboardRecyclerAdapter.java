package com.example.deepak.myapplication.GroupDashboard;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Utility;

import java.util.ArrayList;

public class GroupDashboardRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    ArrayList<BatchDTO> mList;
    Context mContext;

    public GroupDashboardRecyclerAdapter(Context mContext, ArrayList<BatchDTO> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_dashbard_recycler_item, parent, false);
        return new LeadListViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
        BatchDTO data = mList.get(position);
        if (null != data) {
            headingViewHolder.circular_text.setText(data.getName().charAt(0) + "");
            int colour = Utility.getCircularTextBackground(data.getName().charAt(0));
            headingViewHolder.circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);
            headingViewHolder.name.setText(data.getName());
        }
    }

    public int getItemCount() {
        return mList.size();
    }

    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder {
        protected TextView circular_text;
        protected TextView name;
        protected TextView activity_date;
        protected TextView student_name;
        protected ImageView menu;


        public LeadListViewHolder(View v) {
            super(v);
            circular_text = (TextView) v.findViewById(R.id.circular_text);
            name = (TextView) v.findViewById(R.id.name);
            activity_date = (TextView) v.findViewById(R.id.activity_date);
            student_name = (TextView) v.findViewById(R.id.student_name);
            menu = (ImageView) v.findViewById(R.id.menu);

        }
    }
}
