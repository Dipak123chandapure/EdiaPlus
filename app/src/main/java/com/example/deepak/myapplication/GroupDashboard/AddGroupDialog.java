package com.example.deepak.myapplication.GroupDashboard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Settings.AddEditDropDownAdpter;
import com.example.deepak.myapplication.Settings.AddEditDropDownDialog;

import java.util.ArrayList;

/**
 * Created by Deepak on 4/29/2017.
 */

public class AddGroupDialog extends Dialog implements View.OnClickListener, AddEditBatchAdpter.OnDropDownItemCallback {
    public AddGroupDialog(@NonNull Context context, ArrayList<BatchDTO> mList) {
        super(context);
        mContext = context;
        this.mList = mList;
    }

    ArrayList<BatchDTO> mList;
    EditText title_et;
    TextView add_btn, edit_btn, cancel_btn;
    Context mContext;
    RecyclerView drop_values_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_batch_layout);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = (Gravity.CENTER);
        getWindow().setAttributes(params);

        inIiView();
    }

    public void inIiView() {
        title_et = (EditText) findViewById(R.id.title_et);
        add_btn = (TextView) findViewById(R.id.add_btn);
        cancel_btn = (TextView) findViewById(R.id.cancel_btn);
        edit_btn = (TextView) findViewById(R.id.edit_btn);

        edit_btn.setOnClickListener(this);
        add_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);

        drop_values_recycler = (RecyclerView) findViewById(R.id.drop_values_recycler);
        setUpRecycler();
    }

    AddEditBatchAdpter adapter;

    private void setUpRecycler() {
        adapter = new AddEditBatchAdpter(mContext, mList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        adapter.setOnDropDownItemCallback(this);
        drop_values_recycler.setLayoutManager(manager);
        drop_values_recycler.setAdapter(adapter);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn:
                dismiss();
                break;
            case R.id.add_btn:
                if (title_et.getText().toString().length() > 0) {
                    if (!checkForBatch()) {
                        BatchDAO dao = new BatchDAO(mContext);
                        BatchDTO dto = new BatchDTO();
                        dto.setName(title_et.getText().toString());
                        dto.setDetails("test");
                        Long result = dao.saveBatch(dto);
                        dto.setId(result);
                        if (null != mOnBatchaAdded)
                            mOnBatchaAdded.onBatchAdded(dto);

                        dismiss();
                        Toast.makeText(mContext, "successfully added entry", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(mContext, "Batch already exists", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(mContext, "please enter valid entries", Toast.LENGTH_SHORT).show();

                break;

            case R.id.edit_btn:
                if (currentBatch != null) {
                    if (!checkForBatch()) {
                        currentBatch.setName(title_et.getText().toString());
                        new BatchDAO(mContext).updateBatch(currentBatch);
                        if (null != mOnBatchaAdded)
                            mOnBatchaAdded.onBatchEdited(currentBatch);
                        dismiss();

                    } else
                        Toast.makeText(mContext, "Batch already exists", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(mContext, "Please select Batch to edit", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkForBatch() {
        Boolean isBatchExists = false;
        for (int i = 0; i < mList.size(); i++) {
            if (title_et.getText().toString().equals(mList.get(i).getName()))
                isBatchExists = true;
        }
        return isBatchExists;
    }

    OnBatchaAdded mOnBatchaAdded;

    public void setOnBatchaAdded(OnBatchaAdded mOnBatchaAdded) {
        this.mOnBatchaAdded = mOnBatchaAdded;
    }

    BatchDTO currentBatch;

    @Override
    public void onItemClicked(BatchDTO value) {
        currentBatch = value;
        title_et.setText(value.getName());
    }

    @Override
    public void onRemoveItemClicked(BatchDTO value) {
        mList.remove(value);
        adapter.notifyDataSetChanged();
    }


    public interface OnBatchaAdded {
        void onBatchAdded(BatchDTO batch);
        void onBatchEdited(BatchDTO batch);
    }
}

