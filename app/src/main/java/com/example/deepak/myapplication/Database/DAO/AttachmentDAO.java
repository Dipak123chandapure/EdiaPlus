package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.AttachmentDTO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Database.OfflineDatabaseHelper;

import java.util.ArrayList;


public class AttachmentDAO extends OfflineDatabaseHelper {
    public AttachmentDAO(Context context) {
        super(context);
    }


    public void saveAttachment(StudentDTO dto, ArrayList<AttachmentDTO> list){
        SQLiteDatabase db = getWritableDatabase();
        for (int i=0; i<list.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(STUDENT_ID, dto.getId());
            contentValues.put(ATTACHMENT_URL, list.get(i).getUrl());
            contentValues.put(SECTION, list.get(i).getSection());

            Long result = db.insert(STUDENT_ATTACHMENT_TABLE, null, contentValues);
            Log.d("rohit", "student attachment result " + result);
        }
        db.close();
    }

    public void saveAttachment(BatchDTO dto, ArrayList<AttachmentDTO> list){
        SQLiteDatabase db = getWritableDatabase();
        for (int i=0; i<list.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(BATCH_ID, dto.getId());
            contentValues.put(ATTACHMENT_URL, list.get(i).getUrl());
            contentValues.put(SECTION, list.get(i).getSection());

            Long result = db.insert(BATCH_ATTACHMENT_TABLE, null, contentValues);
            Log.d("rohit", "batch attachment result " + result);
        }
        db.close();

    }

    public void saveAttachment(ActivityDTO dto, ArrayList<AttachmentDTO> list){
        SQLiteDatabase db = getWritableDatabase();
        for (int i=0; i<list.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ACTIVITY_ID, dto.getId());
            contentValues.put(ATTACHMENT_URL, list.get(i).getUrl());
            contentValues.put(SECTION, list.get(i).getSection());

            Long result = db.insert(ACTIVITIES_ATTACHMENT_TABLE, null, contentValues);
            Log.d("rohit", "actvity attachment result " + result);
        }
        db.close();

    }


    public ArrayList<String> getAttachment(StudentDTO dto){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = { ATTACHMENT_URL};
        Cursor cursor = db.query(STUDENT_ATTACHMENT_TABLE, coloumns, STUDENT_ID +" = '"+dto.getId()+"'", null, null, null, null);

        if (null != cursor && cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                String url = cursor.getString(cursor.getColumnIndex(ATTACHMENT_URL));
                list.add(url);
            }
            cursor.close();
        }

        db.close();
        return list;
    }

    public ArrayList<String> getAttachment(BatchDTO dto){
        ArrayList<String> list = new ArrayList<>();
        return list;
    }


    public ArrayList<String> getAttachment(ActivityDTO dto){
        ArrayList<String> list = new ArrayList<>();
        return list;
    }
}
