package com.example.deepak.myapplication.EmailDashboard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.deepak.myapplication.Database.DTO.EmailTemplateDTO;
import com.example.deepak.myapplication.Database.DTO.SMSTemplateDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deepak on 5/2/2017.
 */

public class EmailTemplateDialog extends Dialog {

    ArrayList<EmailTemplateDTO> mList;
    EmailTemplateAdapter.OnEmailTemplateSelected mOnEmailTemplateSelected;
    public EmailTemplateDialog(Context context, ArrayList<EmailTemplateDTO> mList, EmailTemplateAdapter.OnEmailTemplateSelected mOnEmailTemplateSelected) {
        super(context);
        this.mOnEmailTemplateSelected = mOnEmailTemplateSelected;
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
        EmailTemplateAdapter adapter = new EmailTemplateAdapter(mList, this);
        adapter.setOnEmailTemplateSelected(mOnEmailTemplateSelected);
        add_lead_common_code_dialog_recycler.setAdapter(adapter);

    }
}
