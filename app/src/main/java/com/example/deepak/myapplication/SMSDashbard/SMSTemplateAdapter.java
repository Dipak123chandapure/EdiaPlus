package com.example.deepak.myapplication.SMSDashbard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.AddStudent.DropDownDialog;
import com.example.deepak.myapplication.AddStudent.DropDownDialogRecyclerAdapter;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.SMSTemplateDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deepak on 5/2/2017.
 */

public class SMSTemplateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<SMSTemplateDTO> mLeadList;
    private static final int TYPE_ITEM = 0;
    SMSTemplateDialog dialog;


    public SMSTemplateAdapter(ArrayList<SMSTemplateDTO> mLeadList, SMSTemplateDialog dialog) {
        this.mLeadList = mLeadList;
        this.dialog = dialog;

    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_dialog_item, parent, false);
        return new LeadListViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
        Log.d("rohit", "group id"+mLeadList.get(position).getTitile());
        headingViewHolder.add_lead_coomon_code_dialog_recy_item_text.setText(mLeadList.get(position).getTitile());
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
                    mOnSMSTemplateSelected.onSMSTemplateSelected(mLeadList.get(getAdapterPosition()));

                }
            });
        }
    }
    OnSMSTemplateSelected mOnSMSTemplateSelected;

    public void setOnSMSTemplateSelected(OnSMSTemplateSelected mOnSMSTemplateSelected) {
        this.mOnSMSTemplateSelected = mOnSMSTemplateSelected;
    }


    public interface OnSMSTemplateSelected {
        void onSMSTemplateSelected(SMSTemplateDTO value);
    }

}
