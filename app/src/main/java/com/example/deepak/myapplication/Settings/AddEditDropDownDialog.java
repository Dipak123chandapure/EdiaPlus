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
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Settings.AddEditDropDownAdpter;

import java.util.ArrayList;

public class AddEditDropDownDialog extends Dialog implements View.OnClickListener, AddEditDropDownAdpter.OnDropDownItemCallback {
    public AddEditDropDownDialog(@NonNull Context context, String TYPE, int position) {
        super(context);
        mContext = context;
        this.TYPE = TYPE;
    }

    EditText title_et;
    TextView add_btn, edit_btn;
    Context mContext;
    String TYPE;
    RecyclerView drop_values_recycler;
    ArrayList<DropDownDataDTO> mList;
    AddEditDropDownAdpter adpter;

    public AddEditDropDownDialog(Context mContext, ArrayList<DropDownDataDTO> mList, String TYPE) {
        super(mContext);
        this.mContext = mContext;
        this.TYPE = TYPE;
        this.mList = mList;
    }

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

    public void inIiView() {
        title_et = (EditText) findViewById(R.id.title_et);

        add_btn = (TextView) findViewById(R.id.add_btn);
        edit_btn = (TextView) findViewById(R.id.edit_btn);
        drop_values_recycler = (RecyclerView) findViewById(R.id.drop_values_recycler);
        add_btn.setOnClickListener(this);
        edit_btn.setOnClickListener(this);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        adpter = new AddEditDropDownAdpter(mContext, mList);
        adpter.setOnDropDownItemCallback(this);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        drop_values_recycler.setLayoutManager(manager);
        drop_values_recycler.setAdapter(adpter);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_btn:
                if (null != selectedItem) {
                    if (null != title_et.getText() && title_et.getText().toString().length() > 0) {
                        if (chekIfItemExists())
                            Toast.makeText(mContext, "Item Exists with this name", Toast.LENGTH_SHORT).show();
                        else editDropDownToDatabase();
                    } else
                        Toast.makeText(mContext, "Titel cannot be empty", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(mContext, "No item selected to edit", Toast.LENGTH_SHORT).show();

                break;

            case R.id.add_btn:
                if (null != title_et.getText() && title_et.getText().toString().length() > 0) {

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
            Log.d("rohit", "value " + mList.get(i).getTitle() + " etVAlue" + title_et.getText().toString());
            if (mList.get(i).getTitle().equals(title_et.getText().toString()))
                isItemExits = true;
        }
        return isItemExits;
    }

    private void editDropDownToDatabase() {
        selectedItem.setTitle(title_et.getText().toString());
        selectedItem.setSystemValue(false);
        DropDownDataDAO dao = new DropDownDataDAO(mContext);
        dao.updateFormData(TYPE, selectedItem);
        adpter.notifyDataSetChanged();
        title_et.setText("");
    }

    private void addDropDownToDatabase() {
        Log.d("rohit", "adding the database");
        DropDownDataDTO dto = new DropDownDataDTO();
        dto.setTitle(title_et.getText().toString());
        dto.setVirtuallyDeleted(false);
        dto.setSystemValue(false);
        dto.setDetails("");

        DropDownDataDAO dao = new DropDownDataDAO(mContext);
        Long result = dao.saveFormData(TYPE, dto);
        dto.setId(result);
        mList.add(dto);
        adpter.notifyDataSetChanged();
        title_et.setText("");
    }

    DropDownDataDTO selectedItem;

    public void onItemClicked(DropDownDataDTO value) {
        selectedItem = value;
        title_et.setText(value.getTitle());
    }

    public void onRemoveItemClicked(DropDownDataDTO value) {
        if (mList.contains(value))
            mList.remove(value);
        DropDownDataDAO dao = new DropDownDataDAO(mContext);
        dao.deleteFormData(TYPE, value);
        adpter.notifyDataSetChanged();
        title_et.setText("");
    }
}
