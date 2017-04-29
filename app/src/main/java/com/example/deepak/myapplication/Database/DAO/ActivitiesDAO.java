package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Database.AbstractDatabaseHelper;
import com.example.deepak.myapplication.Database.OfflineDatabaseHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public class ActivitiesDAO extends OfflineDatabaseHelper {
    public ActivitiesDAO(Context context) {
        super(context);
    }

    public Long addActivity(ActivityDTO activityDTO) {
        Log.d("rohit", "inseting value : " + activityDTO.getActivityDataJSON());
        ContentValues cv = new ContentValues();

        cv.put(ACTIVITY_TYPE_ID, activityDTO.getActvityTypeID());
        cv.put(STUDENT_ID, activityDTO.getStudentID());

        cv.put(ACTIVITY_DATE_TIME, activityDTO.getNextActionDate());
        cv.put(CREATED_ON, activityDTO.getCreatedDate());
        cv.put(UPDATED_ON, activityDTO.getModificationDate());

        cv.put(IS_DONE, activityDTO.getIsDone());

        cv.put(SEND_ACTIVITY_JSON, activityDTO.getSendActivityJSON());
        cv.put(ACTIVITY_DATA_JSON, activityDTO.getActivityDataJSON());
        cv.put(SYNC_STATUS, activityDTO.getSyncStatus());

        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(ACTIVITY_TABLE, null, cv);
        Log.d("rohit", "result  : " + result);
        db.close();
        return result;
    }

    public ArrayList<ActivityDTO> getActivities(long statingMilli, long endingMilli) {

        String sql = "SELECT " + "a." + ACTIVITY_DATA_JSON + ", " + "a." + ID + ", " + "at." + TITLE+ ", "
                + "s." + FORM_1_ENTITY_1 +", " + "s." + FORM_1_ENTITY_2+", " + "s." + FORM_1_ENTITY_3+", " + "s." + FORM_1_ENTITY_4
                + " FROM " + ACTIVITY_TABLE + " a "
                + " LEFT JOIN " + ACTIVITY_TYPE_TABLE + " at "
                + " ON a." + ACTIVITY_TYPE_ID + " = at." + ID
                + " LEFT JOIN " + STUDENT_INFO_TABLE + " s "
                + " ON a." + STUDENT_ID + " = s." + ID
                + " WHERE a." + ACTIVITY_DATE_TIME + " < '" + endingMilli + "' AND a." + ACTIVITY_DATE_TIME + " >= '"+ statingMilli + "'"
                + " ORDER BY "+ACTIVITY_DATE_TIME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<ActivityDTO> list = new ArrayList<>();

        if (null != cursor) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(cursor.getColumnIndex(ACTIVITY_DATA_JSON));
                String title = cursor.getString(cursor.getColumnIndex(TITLE));
                Long id= cursor.getLong(cursor.getColumnIndex(ID));
                String form1entity1 = cursor.getString(cursor.getColumnIndex(FORM_1_ENTITY_1));
                String form1entity2 = cursor.getString(cursor.getColumnIndex(FORM_1_ENTITY_2));
                String form1entity3 = cursor.getString(cursor.getColumnIndex(FORM_1_ENTITY_3));
                String form1entity4 = cursor.getString(cursor.getColumnIndex(FORM_1_ENTITY_4));

                ActivityDTO activity = new Gson().fromJson(student, ActivityDTO.class);
                activity.setForm1Entity1(form1entity1+" "+form1entity2);
                activity.setForm1Entity3(form1entity3);
                activity.setForm1Entity4(form1entity4);
                activity.setActivityTitle(title);
                activity.setId(id);
                list.add(activity);
            }
        }
        cursor.close();
        db.close();
        return list;

    }


    public ArrayList<ActivityDTO> getActivities(int index) {
        int i = 0;

        String sql = "SELECT " + "a." + ACTIVITY_DATA_JSON + ", " + "a." + ID + ", " + "at." + TITLE+ ", "
                + "s." + FORM_1_ENTITY_1 +", " + "s." + FORM_1_ENTITY_2+", " + "s." + FORM_1_ENTITY_3+", " + "s." + FORM_1_ENTITY_4
                + " FROM " + ACTIVITY_TABLE + " a "
                + " LEFT JOIN " + ACTIVITY_TYPE_TABLE + " at "
                + " ON a." + ACTIVITY_TYPE_ID + " = at." + ID
                + " LEFT JOIN " + STUDENT_INFO_TABLE + " s "
                + " ON a." + STUDENT_ID + " = s." + ID
                + " WHERE a." + ACTIVITY_DATE_TIME + " < '" + new Date().getTime() + "' AND "
                + " a." + ACTIVITY_TYPE_ID + " IN ('" + 3+"', '"+4+"', '"+5 + "') "
                + " ORDER BY "+ACTIVITY_DATE_TIME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<ActivityDTO> list = new ArrayList<>();

        cursor.move(index);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(cursor.getColumnIndex(ACTIVITY_DATA_JSON));
                String title = cursor.getString(cursor.getColumnIndex(TITLE));
                Long id= cursor.getLong(cursor.getColumnIndex(ID));
                String form1entity1 = cursor.getString(cursor.getColumnIndex(FORM_1_ENTITY_1));
                String form1entity2 = cursor.getString(cursor.getColumnIndex(FORM_1_ENTITY_2));
                String form1entity3 = cursor.getString(cursor.getColumnIndex(FORM_1_ENTITY_3));
                String form1entity4 = cursor.getString(cursor.getColumnIndex(FORM_1_ENTITY_4));

                ActivityDTO activity = new Gson().fromJson(student, ActivityDTO.class);
                activity.setForm1Entity1(form1entity1+" "+form1entity2);
                activity.setForm1Entity3(form1entity3);
                activity.setForm1Entity4(form1entity4);
                activity.setActivityTitle(title);
                activity.setId(id);
                list.add(activity);
                if (i > 20) {
                    return list;
                }
                i++;
            }
        }
        return list;
    }

    public ArrayList<ActivityDTO> getActivitiesForStudent(StudentDTO studentData) {
        String sql = "SELECT " + "a." + ACTIVITY_DATA_JSON + ", " + "at." + TITLE
                + " FROM " + ACTIVITY_TABLE + " a "
                + " LEFT JOIN " + ACTIVITY_TYPE_TABLE + " at "
                + " ON a." + ACTIVITY_TYPE_ID + " = at." + ID
                + " WHERE a." + STUDENT_ID + " = '" + studentData.getId() + "'";

        Log.d("rohit", "id "+studentData.getId());

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<ActivityDTO> list = new ArrayList<>();

        if (null != cursor) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(cursor.getColumnIndex(ACTIVITY_DATA_JSON));
                String title = cursor.getString(cursor.getColumnIndex(TITLE));
                ActivityDTO dto = new Gson().fromJson(student, ActivityDTO.class);
                dto.setActivityTitle(title);
                list.add(dto);
            }
            cursor.close();
        }

        db.close();
        return list;
    }


}
