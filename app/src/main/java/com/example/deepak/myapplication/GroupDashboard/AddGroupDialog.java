package com.example.deepak.myapplication.GroupDashboard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

/**
 * Created by Deepak on 4/29/2017.
 */

public class AddGroupDialog extends Dialog implements View.OnClickListener {
    public AddGroupDialog(@NonNull Context context) {
        super(context);
        mContext = context;

    }
    EditText title_et, details_et;
    TextView add_btn, cancel_btn;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_dropdownvalue_dialog);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = (Gravity.CENTER);
        getWindow().setAttributes(params);

        inIiView();
    }

    public void inIiView(){
        title_et = (EditText) findViewById(R.id.title_et);
        details_et = (EditText) findViewById(R.id.details_et);
        add_btn = (TextView) findViewById(R.id.add_btn);
        cancel_btn = (TextView) findViewById(R.id.cancel_btn);

        add_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_btn:
                dismiss();
                break;
            case R.id.add_btn:
                if (title_et.getText().toString().length()>0 && details_et.getText().toString().length()>0){
                    BatchDAO dao = new BatchDAO(mContext);
                    BatchDTO dto = new BatchDTO();
                    dto.setName(title_et.getText().toString());
                    dto.setDetails(details_et.getText().toString());
                    Long result = dao.saveBatch(dto);
                    dto.setId(result);
                    dismiss();
                    if (null != mOnBatchaAdded)
                    mOnBatchaAdded.onBatchAdded(dto);
                    Toast.makeText(mContext, "successfully added entry", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "please enter valid entries", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    OnBatchaAdded mOnBatchaAdded;

    public void setOnBatchaAdded(OnBatchaAdded mOnBatchaAdded){
        this.mOnBatchaAdded = mOnBatchaAdded;
    }


    public interface OnBatchaAdded{
        void onBatchAdded(BatchDTO batch);
    }
}

