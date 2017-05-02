package com.example.deepak.myapplication.SMSDashbard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.deepak.myapplication.AddStudent.DropDownDialogRecyclerAdapter;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.SMSTemplateDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deepak on 5/2/2017.
 */

public class SMSTemplateDialog extends Dialog {

    ArrayList<SMSTemplateDTO> mList;
    SMSTemplateAdapter.OnSMSTemplateSelected mOnSMSTemplateSelected;
    public SMSTemplateDialog(Context context, ArrayList<SMSTemplateDTO> mList, SMSTemplateAdapter.OnSMSTemplateSelected mOnSMSTemplateSelected) {
        super(context);
        this.mOnSMSTemplateSelected = mOnSMSTemplateSelected;
        this.mList = mList;
        mContext = context;

    }

    Context mContext;
    RecyclerView add_lead_common_code_dialog_recycler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dropdownvalue_dialog);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = (Gravity.CENTER);
        getWindow().setAttributes(params);

        add_lead_common_code_dialog_recycler = (RecyclerView) findViewById(R.id.add_lead_common_code_dialog_recycler);
        add_lead_common_code_dialog_recycler.setLayoutManager(new LinearLayoutManager(mContext));
        SMSTemplateAdapter adapter = new SMSTemplateAdapter(mList, this);
        adapter.setOnSMSTemplateSelected(mOnSMSTemplateSelected);
        add_lead_common_code_dialog_recycler.setAdapter(adapter);

    }
}
