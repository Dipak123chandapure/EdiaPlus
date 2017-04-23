package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Database.OfflineDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public class ActivitiesDAO extends OfflineDatabase {
    public ActivitiesDAO(Context context) {
        super(context);
    }

    public void addActivity(ActivityDTO activityDTO ){

        Log.d("rohit", "inseting value : "+activityDTO.getActivityDataJSON());
        ContentValues cv = new ContentValues();

        cv.put(FORM_1_ENTITY_3, activityDTO.getForm1Entity3());
        cv.put(FORM_1_ENTITY_4, activityDTO.getForm1Entity4());
        cv.put(ACTIVITY_TYPE_ID, activityDTO.getActvityTypeID());
        
        cv.put(ACTIVITY_DATE_TIME, activityDTO.getNextActionDate());
        cv.put(CREATED_ON, activityDTO.getCreatedDate());
        cv.put(UPDATED_ON, activityDTO.getModificationDate());
       
        cv.put(IS_DONE, activityDTO.getIsDone());

        cv.put(SEND_ACTIVITY_JSON, activityDTO.getSendActivityJSON());
        cv.put(ACTIVITY_DATA_JSON, activityDTO.getActivityDataJSON());
        cv.put(SYNC_STATUS, activityDTO.getSyncStatus());
        
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(ACTIVITY_TABLE, null, cv);
        Log.d("rohit", "result  : "+result);
        db.close();
    }

    public ArrayList<ActivityDTO> getActivities(long statingMilli, long endingMilli) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = {ACTIVITY_DATA_JSON};
        Cursor cursor = db.query(ACTIVITY_TABLE, coloumns, ACTIVITY_DATE_TIME + " < '" + endingMilli + "' AND "+ACTIVITY_DATE_TIME + " >= '" + statingMilli + "'", null, null, null, null);
        ArrayList<ActivityDTO> list = new ArrayList<>();

        if (null != cursor) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(cursor.getColumnIndex(ACTIVITY_DATA_JSON));
                ActivityDTO studentData = new Gson().fromJson(student, ActivityDTO.class);
                list.add(studentData);
            }
        }
        cursor.close();
        db.close();
        return list;

    }


    public ArrayList<ActivityDTO> getActivities(int index) {
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = {ACTIVITY_DATA_JSON};
        Cursor cursor = db.query(ACTIVITY_TABLE, coloumns, ACTIVITY_DATE_TIME +" < '"+ new Date().getTime()+"'", null, null, null, ACTIVITY_DATE_TIME+ " DESC");
        ArrayList<ActivityDTO> list = new ArrayList<>();
        cursor.move(index);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(cursor.getColumnIndex(ACTIVITY_DATA_JSON));
                ActivityDTO studentData = new Gson().fromJson(student, ActivityDTO.class);
                list.add(studentData);
                if (i > 20) {
                    return list;
                }
                i++;
            }
        }
        return list;
    }

    public ArrayList<ActivityDTO> getActivitiesForStudent(StudentDTO studentData) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = {ACTIVITY_DATA_JSON};
        Cursor cursor = db.query(ACTIVITY_TABLE, coloumns, FORM_1_ENTITY_3 + " = '" + studentData.getForm1Entity3() + "'", null, null, null, null);
        ArrayList<ActivityDTO> list = new ArrayList<>();

        if (null != cursor) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(cursor.getColumnIndex(ACTIVITY_DATA_JSON));
                ActivityDTO dto = new Gson().fromJson(student, ActivityDTO.class);
                list.add(dto);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
}
