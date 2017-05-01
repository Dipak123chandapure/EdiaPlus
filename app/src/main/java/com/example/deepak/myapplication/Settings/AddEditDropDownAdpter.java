package com.example.deepak.myapplication.Settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deepak on 4/30/2017.
 */

public class AddEditDropDownAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<DropDownDataDTO> mList;
    Context mContext;

    public AddEditDropDownAdpter(Context mContext, ArrayList<DropDownDataDTO> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_down_child_item, parent, false);
        return new LeadListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != mList) {
            LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
            headingViewHolder.attachment_url.setText(mList.get(position).getTitle());
        }
    }


    public int getItemCount() {
        return mList.size();
    }
    OnDropDownItemCallback mOnDropDownItemCallback;
    public void setOnDropDownItemCallback (OnDropDownItemCallback mOnDropDownItemCallback){
        this.mOnDropDownItemCallback = mOnDropDownItemCallback;
    }

    public interface OnDropDownItemCallback{
        void onItemClicked(DropDownDataDTO value);
        void onRemoveItemClicked(DropDownDataDTO value);
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView attachment_url;
        ImageView cancel_icon;

        public LeadListViewHolder(View v) {
            super(v);
            attachment_url = (TextView) v.findViewById(R.id.attachment_url);
            cancel_icon = (ImageView) v.findViewById(R.id.cancel_icon);
            attachment_url.setOnClickListener(this);
            cancel_icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.attachment_url:
                    if (null != mOnDropDownItemCallback)
                        mOnDropDownItemCallback.onItemClicked(mList.get(getAdapterPosition()));
                    break;
                case R.id.cancel_icon:
                    if (null != mOnDropDownItemCallback)
                        mOnDropDownItemCallback.onRemoveItemClicked(mList.get(getAdapterPosition()));
                    break;
            }
        }
    }
}
