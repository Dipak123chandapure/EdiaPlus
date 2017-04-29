package com.example.deepak.myapplication.ActivityDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepak.myapplication.R;

import java.util.ArrayList;

public class ActivityAttchmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> mList;

    public ActivityAttchmentAdapter(Context mContext, ArrayList<String> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard_attachemnt_item, parent, false);
        return new ActivityAttchmentAdapter.LeadListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != mList) {
            LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
            String absoultePath = mList.get(position);
            //String filename= absoultePath.substring(absoultePath.lastIndexOf('/')+1, absoultePath.length());
            headingViewHolder.attachment_url.setText(absoultePath);
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder  {

        TextView attachment_url;
        public LeadListViewHolder(View v) {
            super(v);
            attachment_url = (TextView) v.findViewById(R.id.attachment_url);

            //remove_attachment_icon.setOnClickListener(this);
        }

    }
}
