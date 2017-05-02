package com.example.deepak.myapplication.StudentDashboard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;

public class BatchDialogRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    OnBatchItemSelected mOnBatchItemSelected;
    private ArrayList<BatchDTO> mLeadList;
    private static final int TYPE_ITEM = 0;
    BatchDialog dialog;
    StudentDTO student;

    public BatchDialogRecyclerAdapter(ArrayList<BatchDTO> mLeadList, BatchDialog dialog, StudentDTO student) {
        this.mLeadList = mLeadList;
        this.dialog = dialog;
        this.student = student;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_dialog_item, parent, false);
        return new LeadListViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
        Log.d("rohit", "group id"+mLeadList.get(position).getName());
        headingViewHolder.add_lead_coomon_code_dialog_recy_item_text.setText(mLeadList.get(position).getName());
    }

    public int getItemCount() {
        return mLeadList.size();
    }

    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder {
        protected TextView add_lead_coomon_code_dialog_recy_item_text;
        public LeadListViewHolder(View v) {
            super(v);
            add_lead_coomon_code_dialog_recy_item_text = (TextView) v.findViewById(R.id.add_lead_coomon_code_dialog_recy_item_text);
            add_lead_coomon_code_dialog_recy_item_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mOnBatchItemSelected.onBatchItemSelected(mLeadList.get(getAdapterPosition()), student);

                }
            });
        }
    }

    public void setOOnBatchItemSelected(OnBatchItemSelected mOnBatchItemSelected) {
       this.mOnBatchItemSelected = mOnBatchItemSelected;
    }


    public interface OnBatchItemSelected {
        void onBatchItemSelected(BatchDTO value, StudentDTO student);
    }

}
