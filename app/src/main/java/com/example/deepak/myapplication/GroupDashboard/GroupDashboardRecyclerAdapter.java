package com.example.deepak.myapplication.GroupDashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.R;

public class GroupDashboardRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;


    public GroupDashboardRecyclerAdapter() {

    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_dashbard_recycler_item, parent, false);
        return new LeadListViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
        // headingViewHolder.add_lead_coomon_code_dialog_recy_item_text.setText(mLeadList.get(position).getTitle());
    }

    public int getItemCount() {
        return 20;
    }

    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder {
        protected TextView add_lead_coomon_code_dialog_recy_item_text;

        public LeadListViewHolder(View v) {
            super(v);

        }
    }

}
