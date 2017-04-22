package com.example.deepak.myapplication.AddStudent;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class DropDownDialog extends Dialog {
    DropDownDialogRecyclerAdapter.OnAddLeadCommonCodeDialogItemSelected mOnAddLeadCommonCodeDialogItemSelected;
    ArrayList<DropDownDataDTO> mList;
    String TYPE;
    public DropDownDialog(Context context, ArrayList<DropDownDataDTO> mList,
                          String TYPE,
                          DropDownDialogRecyclerAdapter.OnAddLeadCommonCodeDialogItemSelected mOnAddLeadCommonCodeDialogItemSelected) {
        super(context);
        this.mList = mList;
        mContext = context;
        this.TYPE = TYPE;
        this.mOnAddLeadCommonCodeDialogItemSelected = mOnAddLeadCommonCodeDialogItemSelected;
    }

    Context mContext;
    RecyclerView add_lead_common_code_dialog_recycler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_lead_common_code_dialog);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = (Gravity.CENTER);
        getWindow().setAttributes(params);

        add_lead_common_code_dialog_recycler = (RecyclerView) findViewById(R.id.add_lead_common_code_dialog_recycler);
        add_lead_common_code_dialog_recycler.setLayoutManager(new LinearLayoutManager(mContext));
        DropDownDialogRecyclerAdapter adapter = new DropDownDialogRecyclerAdapter(mList, TYPE, this);
        adapter.setOnAddLeadCommonCodeDialogItemSelected(mOnAddLeadCommonCodeDialogItemSelected);
        add_lead_common_code_dialog_recycler.setAdapter(adapter);

    }
}
