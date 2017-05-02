package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Database.AbstractDatabaseHelper;
import com.example.deepak.myapplication.Database.OfflineDatabaseHelper;
import com.google.gson.Gson;

import java.util.ArrayList;


public class StudentDAO extends OfflineDatabaseHelper {

    public StudentDAO(Context context) {
        super(context);
    }

    public Long addStudent(StudentDTO studentDTO) {

        ContentValues cv = getContentValue(studentDTO);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(STUDENT_INFO_TABLE, null, cv);
        Log.d("rohit", "add student result " + result);

        db.close();
        return result;
    }

    public ArrayList<StudentDTO> getStudentList(String QUERY, int index) {
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] coloumns = {ID, STUDENT_DATA_JSON};
        Cursor cursor = db.query(STUDENT_INFO_TABLE, coloumns, QUERY, null, null, null, null);
        ArrayList<StudentDTO> list = new ArrayList<>();
        cursor.move(index);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                Long id = cursor.getLong(cursor.getColumnIndex(ID));
                String student = cursor.getString(cursor.getColumnIndex(STUDENT_DATA_JSON));
                StudentDTO studentData = new Gson().fromJson(student, StudentDTO.class);
                studentData.setId(id);
                list.add(studentData);
                if (i > 40) {
                    cursor.close();
                    db.close();
                    return list;
                }
                i++;
            }
            cursor.close();
        }
        db.close();
        return list;
    }


    public int getStudentListCount(String QUERY) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] coloumns = {ID, STUDENT_DATA_JSON};
        Cursor cursor = db.query(STUDENT_INFO_TABLE, coloumns, QUERY, null, null, null, null);
        ArrayList<StudentDTO> list = new ArrayList<>();
        int count = 0;
        if (null != cursor) {
            count = cursor.getCount();
            cursor.close();
        }
        db.close();
        return count;
    }

    public StudentDTO getStudentForNumber(String number) {


        String sql = "SELECT " + "s." + STUDENT_DATA_JSON + ", " + "s." + ID + ", "
                + "f21." + TITLE + ", " + "f22." + TITLE + ", " + "f23." + TITLE + ", f24." + TITLE + ", "
                + "f31." + TITLE + ", " + "f32." + TITLE + ", " + "f33." + TITLE + ", f34." + TITLE + ",  "
                + "f41." + TITLE + ", " + "f42." + TITLE + ", " + "f43." + TITLE + ", f44." + TITLE

                + " FROM " + STUDENT_INFO_TABLE + " s "
                + " LEFT JOIN " + FORM_2_ENTITY_1_TABLE + " f21 "
                + " ON s." + FORM_2_ENTITY_1_ID + " = f21." + ID
                + " LEFT JOIN " + FORM_2_ENTITY_2_TABLE + " f22 "
                + " ON s." + FORM_2_ENTITY_2_ID + " = f22." + ID
                + " LEFT JOIN " + FORM_2_ENTITY_3_TABLE + " f23 "
                + " ON s." + FORM_2_ENTITY_3_ID + " = f23." + ID
                + " LEFT JOIN " + FORM_2_ENTITY_4_TABLE + " f24 "
                + " ON s." + FORM_2_ENTITY_4_ID + " = f24." + ID

                + " LEFT JOIN " + FORM_3_ENTITY_1_TABLE + " f31 "
                + " ON s." + FORM_3_ENTITY_1_ID + " = f31." + ID
                + " LEFT JOIN " + FORM_3_ENTITY_2_TABLE + " f32 "
                + " ON s." + FORM_3_ENTITY_2_ID + " = f32." + ID
                + " LEFT JOIN " + FORM_3_ENTITY_3_TABLE + " f33 "
                + " ON s." + FORM_3_ENTITY_3_ID + " = f33." + ID
                + " LEFT JOIN " + FORM_3_ENTITY_4_TABLE + " f34 "
                + " ON s." + FORM_3_ENTITY_4_ID + " = f34." + ID

                + " LEFT JOIN " + FORM_4_ENTITY_1_TABLE + " f41 "
                + " ON s." + FORM_4_ENTITY_1_ID + " = f41." + ID
                + " LEFT JOIN " + FORM_4_ENTITY_2_TABLE + " f42 "
                + " ON s." + FORM_4_ENTITY_2_ID + " = f42." + ID
                + " LEFT JOIN " + FORM_4_ENTITY_3_TABLE + " f43 "
                + " ON s." + FORM_4_ENTITY_3_ID + " = f43." + ID
                + " LEFT JOIN " + FORM_4_ENTITY_4_TABLE + " f44 "
                + " ON s." + FORM_4_ENTITY_4_ID + " = f44." + ID

                + " WHERE s." + FORM_1_ENTITY_4 + " = '" + number + "'";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        StudentDTO dto = null;
        Log.d("rohit ", "cursor count" + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            dto = new StudentDTO();
            String studentdata = cursor.getString(cursor.getColumnIndex(STUDENT_DATA_JSON));
            Long id = cursor.getLong(1);

            String title21 = cursor.getString(2);
            String title22 = cursor.getString(3);
            String title23 = cursor.getString(4);
            String title24 = cursor.getString(5);

            String title31 = cursor.getString(6);
            String title32 = cursor.getString(7);
            String title33 = cursor.getString(8);
            String title34 = cursor.getString(9);

            String title41 = cursor.getString(10);
            String title42 = cursor.getString(11);
            String title43 = cursor.getString(12);
            String title44 = cursor.getString(13);


            dto = new Gson().fromJson(studentdata, StudentDTO.class);
            dto.setForm2Entity1(title21);
            dto.setForm2Entity2(title22);
            dto.setForm2Entity3(title23);
            dto.setForm2Entity4(title24);

            dto.setForm3Entity1(title31);
            dto.setForm3Entity2(title32);
            dto.setForm3Entity3(title33);
            dto.setForm3Entity4(title34);

            dto.setForm4Entity1(title41);
            dto.setForm4Entity2(title42);
            dto.setForm4Entity3(title43);
            dto.setForm4Entity4(title44);
            dto.setId(id);

            cursor.close();
        }
        db.close();
        return dto;
    }


    public void updateStudent(StudentDTO student) {
        ContentValues cv = getContentValue(student);
        SQLiteDatabase db = getWritableDatabase();
        int result = db.update(STUDENT_INFO_TABLE, cv, ID + " = " + student.getId(), null);

        Log.d("rohit", "add student result " + result);
        db.close();
    }


    private ContentValues getContentValue(StudentDTO studentDTO) {
        ContentValues cv = new ContentValues();
        cv.put(FORM_1_ENTITY_1, studentDTO.getForm1Entity1());
        cv.put(FORM_1_ENTITY_2, studentDTO.getForm1Entity2());
        cv.put(FORM_1_ENTITY_3, studentDTO.getForm1Entity3());
        cv.put(FORM_1_ENTITY_4, studentDTO.getForm1Entity4());

        cv.put(FORM_2_ENTITY_1_ID, studentDTO.getForm2Entity1ID());
        cv.put(FORM_2_ENTITY_2_ID, studentDTO.getForm2Entity2ID());
        cv.put(FORM_2_ENTITY_3_ID, studentDTO.getForm2Entity3ID());
        cv.put(FORM_2_ENTITY_4_ID, studentDTO.getForm2Entity4ID());

        if (null != studentDTO.getForm3Entity1ID())
            cv.put(FORM_3_ENTITY_1_ID, studentDTO.getForm3Entity1ID());
        if (null != studentDTO.getForm3Entity2ID())
            cv.put(FORM_3_ENTITY_2_ID, studentDTO.getForm3Entity2ID());
        if (null != studentDTO.getForm3Entity3ID())
            cv.put(FORM_3_ENTITY_3_ID, studentDTO.getForm3Entity3ID());
        if (null != studentDTO.getForm3Entity4ID())
            cv.put(FORM_3_ENTITY_4_ID, studentDTO.getForm3Entity4ID());

        if (null != studentDTO.getForm4Entity1ID())
            cv.put(FORM_4_ENTITY_1_ID, studentDTO.getForm4Entity1ID());
        if (null != studentDTO.getForm4Entity2ID())
            cv.put(FORM_4_ENTITY_2_ID, studentDTO.getForm4Entity2ID());
        if (null != studentDTO.getForm4Entity3ID())
            cv.put(FORM_4_ENTITY_3_ID, studentDTO.getForm4Entity3ID());
        if (null != studentDTO.getForm4Entity4ID())
            cv.put(FORM_4_ENTITY_4_ID, studentDTO.getForm4Entity4ID());

        cv.put(CREATED_ON, studentDTO.getUpdatedON());
        cv.put(CREATED_ON_MILLI, studentDTO.getCreatedOnMilli());
        cv.put(UPDATED_ON, studentDTO.getUpdatedON());
        cv.put(UPDATED_ON_MILLI, studentDTO.getUpdatedONMilli());

        cv.put(STUDENT_DATA_JSON, studentDTO.getStudentDataJSON());
        cv.put(SEND_DATA_JSON, studentDTO.getSendDataJSON());
        cv.put(SYNC_STATUS, studentDTO.getSyncStatus());

        return cv;
    }


}
