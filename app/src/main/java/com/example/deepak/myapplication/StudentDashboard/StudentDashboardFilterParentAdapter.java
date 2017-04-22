package com.example.deepak.myapplication.StudentDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class StudentDashboardFilterParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    JSONArray jsonArray;
    ArrayList<ArrayList<DropDownDataDTO>> list;

    public StudentDashboardFilterParentAdapter(Context mContext, JSONArray jsonArray, ArrayList<ArrayList<DropDownDataDTO>> list) {
        this.jsonArray = jsonArray;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_filter_recycler_parent_list_item, parent, false);
        return new LeadListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != jsonArray) {
            LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(position);
                headingViewHolder.s_d_f_r_p_l_i_text.setText(jsonObject.optString("VALUE"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public int getItemCount() {

        if (null != jsonArray) {
            return jsonArray.length();
        }
        return 0;
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView s_d_f_r_p_l_i_text;
        public LeadListViewHolder(View v) {
            super(v);
            s_d_f_r_p_l_i_text = (TextView) v.findViewById(R.id.s_d_f_r_p_l_i_text);
            s_d_f_r_p_l_i_text.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != mOnFilterParentItemSelected){
                mOnFilterParentItemSelected.onFilterParentItemSelected(list.get(getAdapterPosition()), getAdapterPosition());
            }
        }
    }
    OnFilterParentItemSelected mOnFilterParentItemSelected;
    public void setmOnFilterParentItemSelected(OnFilterParentItemSelected mOnFilterParentItemSelected){
        this.mOnFilterParentItemSelected = mOnFilterParentItemSelected;
    }

    public interface OnFilterParentItemSelected{
        void onFilterParentItemSelected(ArrayList<DropDownDataDTO> mList, int position);
    }
}
