package com.example.deepak.myapplication.AddStudent;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;

public class DropDownDialogRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    OnAddLeadCommonCodeDialogItemSelected mOnAddLeadCommonCodeDialogItemSelected;
    private ArrayList<DropDownDataDTO> mLeadList;
    private static final int TYPE_ITEM = 0;
    DropDownDialog dialog;
    String TYPE;

    public DropDownDialogRecyclerAdapter(ArrayList<DropDownDataDTO> mLeadList, String TYPE, DropDownDialog dialog) {
        this.mLeadList = mLeadList;
        this.dialog = dialog;
        this.TYPE = TYPE;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_dialog_item, parent, false);
        return new LeadListViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
        Log.d("rohit", "group id"+mLeadList.get(position).getTitle());
        headingViewHolder.add_lead_coomon_code_dialog_recy_item_text.setText(mLeadList.get(position).getTitle());
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
                    mOnAddLeadCommonCodeDialogItemSelected.onAddLeadCommonCodeDialogItemSelected(mLeadList.get(getAdapterPosition()), TYPE);

                }
            });
        }
    }

    public void setOnAddLeadCommonCodeDialogItemSelected(OnAddLeadCommonCodeDialogItemSelected mOnAddLeadCommonCodeDialogItemSelected) {
       this.mOnAddLeadCommonCodeDialogItemSelected = mOnAddLeadCommonCodeDialogItemSelected;
    }


    public interface OnAddLeadCommonCodeDialogItemSelected {
        void onAddLeadCommonCodeDialogItemSelected(DropDownDataDTO value, String TYPE);
    }

}
