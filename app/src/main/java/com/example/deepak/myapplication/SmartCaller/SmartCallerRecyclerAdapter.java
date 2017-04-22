package com.example.deepak.myapplication.SmartCaller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class SmartCallerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> mList;
    String[] names = {"Name", "Staus", "Group","Source","Type","Priority","Created On"};
    Context mContext;

    public SmartCallerRecyclerAdapter(Context mContext, ArrayList<String> mList) {
        this.mList = mList;
        Log.d("rohit", "size" + mList.size());
        this.mContext = mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.smart_caller_dial_recyler_item, parent, false);
        return new LeadListViewHolder(view);

    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
        headingViewHolder.smart_caller_dial_recyler_item_heading_text.setText(names[position]);
        headingViewHolder.smart_caller_dial_recyler_item_subheading_text.setText(mList.get(position));
    }


    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder {
        TextView smart_caller_dial_recyler_item_heading_text;
        TextView smart_caller_dial_recyler_item_subheading_text;
        public LeadListViewHolder(View v) {
            super(v);
            smart_caller_dial_recyler_item_heading_text = (TextView) v.findViewById(R.id.smart_caller_dial_recyler_item_heading_text);
            smart_caller_dial_recyler_item_subheading_text = (TextView) v.findViewById(R.id.smart_caller_dial_recyler_item_subheading_text);
        }

    }
}
