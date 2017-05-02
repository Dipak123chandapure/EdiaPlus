package com.example.deepak.myapplication.Settings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DAO.SMSEmailTemplateDAO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.SMSTemplateDTO;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;

public class SMSTemplateAddEditDialog extends Dialog implements View.OnClickListener, SMSTemplatesAddEditAdpter.OnDropDownItemCallback {


    EditText title_et, text_et;
    TextView add_btn, edit_btn;
    Context mContext;
    RecyclerView drop_values_recycler;
    ArrayList<SMSTemplateDTO> mList;
    SMSTemplatesAddEditAdpter adpter;
    SMSTemplateDTO selectedItem;

    public SMSTemplateAddEditDialog(Context mContext, ArrayList<SMSTemplateDTO> mList) {
        super(mContext);
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sms_template_add_edit_dialog);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = (Gravity.CENTER);
        getWindow().setAttributes(params);
        inIiView();
    }

    public void inIiView() {
        title_et = (EditText) findViewById(R.id.title_et);
        text_et = (EditText) findViewById(R.id.text_et);
        add_btn = (TextView) findViewById(R.id.add_btn);
        edit_btn = (TextView) findViewById(R.id.edit_btn);
        drop_values_recycler = (RecyclerView) findViewById(R.id.drop_values_recycler);
        add_btn.setOnClickListener(this);
        edit_btn.setOnClickListener(this);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        adpter = new SMSTemplatesAddEditAdpter(mContext, mList);
        adpter.setOnDropDownItemCallback(this);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        drop_values_recycler.setLayoutManager(manager);
        drop_values_recycler.setAdapter(adpter);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_btn:
                if (null != selectedItem) {
                    if (null != title_et.getText() && title_et.getText().toString().length() > 0
                            && text_et.getText() != null && text_et.getText().toString().length()>0) {
                        if (chekIfItemExists())
                            Toast.makeText(mContext, "Item Exists with this name", Toast.LENGTH_SHORT).show();
                        else editDropDownToDatabase();
                    } else
                        Toast.makeText(mContext, "Titel cannot be empty", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(mContext, "No item selected to edit", Toast.LENGTH_SHORT).show();

                break;

            case R.id.add_btn:
                if (null != title_et.getText() && title_et.getText().toString().length() > 0
                        && text_et.getText() != null && text_et.getText().toString().length()>0) {

                    if (chekIfItemExists())
                        Toast.makeText(mContext, "Item Exists with this name", Toast.LENGTH_SHORT).show();
                    else addDropDownToDatabase();

                } else Toast.makeText(mContext, "Titel cannot be empty", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public Boolean chekIfItemExists() {
        Boolean isItemExits = false;
        for (int i = 0; i < mList.size(); i++) {
            Log.d("rohit", "value " + mList.get(i).getTitile() + " etVAlue" + title_et.getText().toString());
            if (mList.get(i).getTitile().equals(title_et.getText().toString()))
                isItemExits = true;
        }
        return isItemExits;
    }

    private void editDropDownToDatabase() {
        selectedItem.setTitile(title_et.getText().toString());
        selectedItem.setText(text_et.getText().toString());
        selectedItem.setSystemValue(false);
        SMSEmailTemplateDAO dao = new SMSEmailTemplateDAO(mContext);
        dao.updateSMSTemplate(selectedItem);
        adpter.notifyDataSetChanged();
        title_et.setText("");
        text_et.setText("");
    }

    private void addDropDownToDatabase() {
        Log.d("rohit", "adding the database");
        SMSTemplateDTO dto = new SMSTemplateDTO();
        dto.setTitile(title_et.getText().toString());
        dto.setVirtuallyDeleted(false);
        dto.setSystemValue(false);
        dto.setText(text_et.getText().toString());

        SMSEmailTemplateDAO dao = new SMSEmailTemplateDAO(mContext);
        Long result = dao.saveSMSTemplate(dto);
        dto.setId(result);
        mList.add(dto);
        adpter.notifyDataSetChanged();
        title_et.setText("");
        text_et.setText("");
    }



    public void onItemClicked(SMSTemplateDTO value) {
        selectedItem = value;
        title_et.setText(value.getTitile());
        text_et.setText(value.getText());
    }

    public void onRemoveItemClicked(SMSTemplateDTO value) {
        if (mList.contains(value))
            mList.remove(value);
        SMSEmailTemplateDAO dao = new SMSEmailTemplateDAO(mContext);
        dao.deleteSMSTemplate(value);
        adpter.notifyDataSetChanged();
        title_et.setText("");
        text_et.setText("");
    }

}
