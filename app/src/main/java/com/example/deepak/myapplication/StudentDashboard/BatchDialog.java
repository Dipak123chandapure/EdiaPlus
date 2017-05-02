package com.example.deepak.myapplication.StudentDashboard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class BatchDialog extends Dialog {
    BatchDialogRecyclerAdapter.OnBatchItemSelected mOnBatchItemSelected;
    ArrayList<BatchDTO> mList;
    StudentDTO student;

    public BatchDialog(Context context, ArrayList<BatchDTO> mList, StudentDTO student,
                       BatchDialogRecyclerAdapter.OnBatchItemSelected mOnBatchItemSelected) {
        super(context);
        this.mList = mList;
        mContext = context;
        this.mOnBatchItemSelected = mOnBatchItemSelected;
        this.student = student;
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
        BatchDialogRecyclerAdapter adapter = new BatchDialogRecyclerAdapter(mList,this, student);
        adapter.setOOnBatchItemSelected(mOnBatchItemSelected);
        add_lead_common_code_dialog_recycler.setAdapter(adapter);

    }
}
