package com.example.deepak.myapplication.StudentDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.ParentDropDownDTO;
import com.example.deepak.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FilterParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ParentDropDownDTO> mList;
    Context mContext;

    public FilterParentAdapter(Context mContext, ArrayList<ParentDropDownDTO> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_filter_parent_item, parent, false);
        return new LeadListViewHolder(view);

    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != mList) {
            LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
            headingViewHolder.text.setText(mList.get(position).getTitle());
        }
    }

    public int getItemCount() {
        if (null != mList) {
            return mList.size()-1;
        }
        return 0;
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;

        public LeadListViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.text);
            text.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != mOnFilterParentItemSelected) {
                mOnFilterParentItemSelected.onFilterParentItemSelected(mList.get(getAdapterPosition()));
            }
        }
    }

    OnFilterParentItemSelected mOnFilterParentItemSelected;

    public void setmOnFilterParentItemSelected(OnFilterParentItemSelected mOnFilterParentItemSelected) {
        this.mOnFilterParentItemSelected = mOnFilterParentItemSelected;
    }

    public interface OnFilterParentItemSelected {
        void onFilterParentItemSelected(ParentDropDownDTO parentDropDownDTO);
    }
}
