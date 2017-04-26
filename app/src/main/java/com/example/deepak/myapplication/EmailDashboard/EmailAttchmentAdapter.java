package com.example.deepak.myapplication.EmailDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;


import java.util.ArrayList;

public class EmailAttchmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> mList;

    public EmailAttchmentAdapter(Context mContext, ArrayList<String> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emai_dashboard_attachemnt_item, parent, false);
        return new EmailAttchmentAdapter.LeadListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != mList) {
            LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
            String absoultePath = mList.get(position);
            //String filename= absoultePath.substring(absoultePath.lastIndexOf('/')+1, absoultePath.length());
            headingViewHolder.file_name.setText(absoultePath);
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    OnAttchmentRemoved mOnAttchmentRemoved;

    public void setOnAttchmentRemoved(OnAttchmentRemoved mOnAttchmentRemoved) {
        this.mOnAttchmentRemoved = mOnAttchmentRemoved;
    }

    public interface OnAttchmentRemoved {
        void onAttchmentRemoved(int poition);
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView file_name;
        ImageView remove_attachment_icon;

        public LeadListViewHolder(View v) {
            super(v);
            file_name = (TextView) v.findViewById(R.id.file_name);
            remove_attachment_icon = (ImageView) v.findViewById(R.id.remove_attachment_icon);

            remove_attachment_icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (null != mOnAttchmentRemoved)
                        mOnAttchmentRemoved.onAttchmentRemoved(getAdapterPosition());
                }
            }).start();
        }

    }
}
