package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.EmailTemplateDTO;
import com.example.deepak.myapplication.Database.DTO.SMSTemplateDTO;
import com.example.deepak.myapplication.Database.OfflineDatabaseHelper;
import java.util.ArrayList;


public class SMSEmailTemplateDAO extends OfflineDatabaseHelper {

    public SMSEmailTemplateDAO(Context context) {
        super(context);
    }

    public Long saveSMSTemplate(SMSTemplateDTO smsTemplate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, smsTemplate.getTitile());
        cv.put(TEXT, smsTemplate.getText());
        cv.put(IS_SYSTEM_VALUE, smsTemplate.getSystemValue());
        cv.put(IS_VIRTUALLY_DELETED, smsTemplate.getVirtuallyDeleted());
        Long result = db.insert(SMS_TEMPLATE_TABLE, null, cv);
        Log.d("rohit", "result "+result);
        db.close();
        return result;
    }

    public void updateSMSTemplate(SMSTemplateDTO smsTemplate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, smsTemplate.getTitile());
        cv.put(TEXT, smsTemplate.getText());
        cv.put(IS_SYSTEM_VALUE, smsTemplate.getSystemValue());
        cv.put(IS_VIRTUALLY_DELETED, smsTemplate.getVirtuallyDeleted());
        int result = db.update(SMS_TEMPLATE_TABLE, cv, ID + " = '" + smsTemplate.getId() + "'", null);
        db.close();

    }

    public Long saveEmailTemplate(EmailTemplateDTO emailTemplate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, emailTemplate.getTitile());
        cv.put(SUBJECT, emailTemplate.getSubject());
        cv.put(BODY, emailTemplate.getBody());
        cv.put(IS_SYSTEM_VALUE, emailTemplate.getSystemValue());
        cv.put(IS_VIRTUALLY_DELETED, emailTemplate.getVirtuallyDeleted());
        Long result = db.insert(EMAIL_TEMPLATE_TABLE, null, cv);
        Log.d("rohit", "result "+result);
        db.close();
        return result;

    }

    public void updateEmailTemplate(EmailTemplateDTO emailTemplate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, emailTemplate.getTitile());
        cv.put(SUBJECT, emailTemplate.getSubject());
        cv.put(BODY, emailTemplate.getBody());
        cv.put(IS_SYSTEM_VALUE, emailTemplate.getSystemValue());
        cv.put(IS_VIRTUALLY_DELETED, emailTemplate.getVirtuallyDeleted());
        int result = db.update(EMAIL_TEMPLATE_TABLE, cv, ID + " = '" + emailTemplate.getId() + "'", null);
        db.close();
    }

    public ArrayList<SMSTemplateDTO> getAllSMSTemplates() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SMSTemplateDTO> mList = new ArrayList<>();
        String[] coloumns = {ID, TITLE, TEXT, IS_SYSTEM_VALUE, IS_VIRTUALLY_DELETED};
        Cursor cursor = db.query(SMS_TEMPLATE_TABLE, coloumns, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                SMSTemplateDTO smsTemplate = new SMSTemplateDTO();
                smsTemplate.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                smsTemplate.setText(cursor.getString(cursor.getColumnIndex(TEXT)));
                smsTemplate.setTitile(cursor.getString(cursor.getColumnIndex(TITLE)));
                smsTemplate.setSystemValue(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_SYSTEM_VALUE))));
                smsTemplate.setVirtuallyDeleted(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_VIRTUALLY_DELETED))));
                mList.add(smsTemplate);
            }
            cursor.close();
        }
        db.close();
        return mList;
    }

    public ArrayList<EmailTemplateDTO> getAllEmailTemplates() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<EmailTemplateDTO> mList = new ArrayList<>();
        String[] coloumns = {ID, TITLE, SUBJECT, BODY, IS_SYSTEM_VALUE, IS_VIRTUALLY_DELETED};
        Cursor cursor = db.query(EMAIL_TEMPLATE_TABLE, coloumns, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                EmailTemplateDTO emailTemplate = new EmailTemplateDTO();
                emailTemplate.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                emailTemplate.setTitile(cursor.getString(cursor.getColumnIndex(TITLE)));
                emailTemplate.setSubject(cursor.getString(cursor.getColumnIndex(SUBJECT)));
                emailTemplate.setBody(cursor.getString(cursor.getColumnIndex(BODY)));
                emailTemplate.setSystemValue(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_SYSTEM_VALUE))));
                emailTemplate.setVirtuallyDeleted(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_VIRTUALLY_DELETED))));
                mList.add(emailTemplate);
            }
            cursor.close();
        }
        db.close();
        return mList;
    }

    public void deleteSMSTemplate(SMSTemplateDTO value) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(SMS_TEMPLATE_TABLE, ID+" = '"+value.getId()+"'", null);
        Log.d("rohit", "result "+result);
        db.close();
    }

    public void deleteEmailTemplate(EmailTemplateDTO value) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(EMAIL_TEMPLATE_TABLE, ID+" = '"+value.getId()+"'", null);
        Log.d("rohit", "result "+result);
        db.close();
    }
}
