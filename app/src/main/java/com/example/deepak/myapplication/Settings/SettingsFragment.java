package com.example.deepak.myapplication.Settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.myapplication.Database.DAO.ParentDropDownDAO;
import com.example.deepak.myapplication.Database.DAO.SMSEmailTemplateDAO;
import com.example.deepak.myapplication.Database.DTO.EmailTemplateDTO;
import com.example.deepak.myapplication.Database.DTO.ParentDropDownDTO;
import com.example.deepak.myapplication.Database.DTO.SMSTemplateDTO;
import com.example.deepak.myapplication.EmailDashboard.EmailTemplateDialog;
import com.example.deepak.myapplication.R;

import java.util.ArrayList;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    RecyclerView drop_down_recycler;
    TextView  email_templates, sms_templates;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment_layout, container, false);
        drop_down_recycler = (RecyclerView) view.findViewById(R.id.drop_down_recycler);
        email_templates = (TextView) view.findViewById(R.id.email_templates);
        sms_templates = (TextView) view.findViewById(R.id.sms_templates);
        email_templates.setOnClickListener(this);
        sms_templates.setOnClickListener(this);
        setUpRecycler();
        return view;
    }

    private void setUpRecycler() {
        ParentDropDownDAO parentDropDownDAO = new ParentDropDownDAO(getActivity());
        ArrayList<ParentDropDownDTO> list = parentDropDownDAO.getAllParentsDropDown();
        DropDownRecyclerAdapter adpter = new DropDownRecyclerAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        drop_down_recycler.setLayoutManager(manager);
        drop_down_recycler.setAdapter(adpter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.email_templates:
                SMSEmailTemplateDAO smsEmailTemplateDAO = new SMSEmailTemplateDAO(getActivity());
                ArrayList<EmailTemplateDTO> list = smsEmailTemplateDAO.getAllEmailTemplates();
                EmailTemplateAddEditDialog dialog = new EmailTemplateAddEditDialog(getActivity(),list);
                dialog.show();
                break;

            case R.id.sms_templates:
                SMSEmailTemplateDAO smsEmailTemplateDAO1 = new SMSEmailTemplateDAO(getActivity());
                ArrayList<SMSTemplateDTO> list1 = smsEmailTemplateDAO1.getAllSMSTemplates();
                SMSTemplateAddEditDialog dialog1 = new SMSTemplateAddEditDialog(getActivity(),list1);
                dialog1.show();
                break;
        }
    }
}
