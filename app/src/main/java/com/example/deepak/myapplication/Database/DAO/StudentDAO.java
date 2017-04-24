package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Database.OfflineDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Deepak on 4/19/2017.
 */

public class StudentDAO extends OfflineDatabase {

    public StudentDAO(Context context) {
        super(context);
    }

    public void addStudent(StudentDTO studentDTO ){
        ContentValues cv = new ContentValues();
        cv.put(FORM_1_ENTITY_1, studentDTO.getForm1Entity1());
        cv.put(FORM_1_ENTITY_2, studentDTO.getForm1Entity2());
        cv.put(FORM_1_ENTITY_3, studentDTO.getForm1Entity3());
        cv.put(FORM_1_ENTITY_4, studentDTO.getForm1Entity4());

        cv.put(FORM_2_ENTITY_1_ID, studentDTO.getForm2Entity1ID());
        cv.put(FORM_2_ENTITY_2_ID, studentDTO.getForm2Entity2ID());
        cv.put(FORM_2_ENTITY_3_ID, studentDTO.getForm2Entity3ID());
        cv.put(FORM_2_ENTITY_4_ID, studentDTO.getForm2Entity4ID());

        if(0 != studentDTO.getForm3Entity1ID())
        cv.put(FORM_3_ENTITY_1_ID, studentDTO.getForm3Entity1ID());
        if(0 != studentDTO.getForm3Entity2ID())
        cv.put(FORM_3_ENTITY_2_ID, studentDTO.getForm3Entity2ID());
        if(0 != studentDTO.getForm3Entity3ID())
        cv.put(FORM_3_ENTITY_3_ID, studentDTO.getForm3Entity3ID());
        if(0 != studentDTO.getForm3Entity4ID())
        cv.put(FORM_3_ENTITY_4_ID, studentDTO.getForm3Entity4ID());

        if(0 != studentDTO.getForm4Entity1ID())
        cv.put(FORM_4_ENTITY_1_ID, studentDTO.getForm4Entity1ID());
        if(0 != studentDTO.getForm4Entity2ID())
        cv.put(FORM_4_ENTITY_2_ID, studentDTO.getForm4Entity2ID());
        if(0 != studentDTO.getForm4Entity3ID())
        cv.put(FORM_4_ENTITY_3_ID, studentDTO.getForm4Entity3ID());
        if(0 != studentDTO.getForm4Entity4ID())
        cv.put(FORM_4_ENTITY_4_ID, studentDTO.getForm4Entity4ID());

        cv.put(CREATED_ON, studentDTO.getUpdatedON());
        cv.put(CREATED_ON_MILLI, studentDTO.getCreatedOnMilli());
        cv.put(UPDATED_ON, studentDTO.getUpdatedON());
        cv.put(UPDATED_ON_MILLI, studentDTO.getUpdatedONMilli());

        cv.put(STUDENT_DATA_JSON, studentDTO.getStudentDataJSON());
        cv.put(SEND_DATA_JSON, studentDTO.getSendDataJSON());
        cv.put(SYNC_STATUS, studentDTO.getSyncStatus());

        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(STUDENT_INFO_TABLE, null, cv);
        Log.d("rohit", "add student result "+result);
        db.close();
    }

    public ArrayList<StudentDTO> getStudentList(String QUERY , int index){
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = {STUDENT_DATA_JSON};
        Cursor cursor = db.query(STUDENT_INFO_TABLE, coloumns, QUERY, null, null, null, null);
        ArrayList<StudentDTO> list = new ArrayList<>();
        cursor.move(index);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(cursor.getColumnIndex(STUDENT_DATA_JSON));
                StudentDTO studentData = new Gson().fromJson(student, StudentDTO.class);
                list.add(studentData);
                if (i > 20) {
                    return list;
                }
                i++;
            }
        }
        return list;

    }




    public ArrayList<StudentDTO> getAllStudentList(){
        int i =0;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = {ID, STUDENT_DATA_JSON};
        Cursor cursor = db.query(STUDENT_INFO_TABLE, coloumns, FORM_2_ENTITY_2_ID +" = '"+4+"'", null, null, null, null);
        ArrayList<StudentDTO> list = new ArrayList<>();
        if (null != cursor) {
            while (cursor.moveToNext()) {
                String student = cursor.getString(cursor.getColumnIndex(STUDENT_DATA_JSON));
                int id = cursor.getInt(cursor.getColumnIndex(ID));
                StudentDTO studentData = new Gson().fromJson(student, StudentDTO.class);
                studentData.setId(id);
                list.add(studentData);
                i++;
                if (i>100)
                    return list;
            }
        }
        return list;

    }

//    public StudentDTO getStudentForNumber(String number) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        String[] coloumns = {STUDENT_DATA_JSON};
//        Cursor cursor = db.query(STUDENT_INFO_TABLE, coloumns, FORM_1_ENTITY_4 + " LIKE '%" + number + "%'", null, null, null, null);
//        ArrayList<StudentDTO> list = new ArrayList<>();
//        StudentDTO studentData = null;
//        if (null != cursor) {
//            if (cursor.getCount()>0) {
//                cursor.moveToFirst();
//                String student = cursor.getString(cursor.getColumnIndex(STUDENT_DATA_JSON));
//                studentData = new Gson().fromJson(student, StudentDTO.class);
//
//
//            }
//        }
//        return  studentData;
//    }


    public StudentDTO getStudentForNumber(String number) {


        String sql = "SELECT " + "s." + STUDENT_DATA_JSON + ", "
                + "f21." + TITLE+", "+ "f22." + TITLE+", "+ "f23." + TITLE+ ", f24." + TITLE + ", "
                + "f31." + TITLE+", "+ "f32." + TITLE+", "+ "f33." + TITLE+ ", f34." + TITLE + ",  "
                + "f41." + TITLE+", "+ "f42." + TITLE+", "+ "f43." + TITLE+ ", f44." + TITLE

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
        StudentDTO dto  = new StudentDTO();
        Log.d("rohit ", "cursor count" + cursor.getCount());
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            String studentdata = cursor.getString(cursor.getColumnIndex(STUDENT_DATA_JSON));
            
            String title21 = cursor.getString(1);
            String title22 = cursor.getString(2);
            String title23 = cursor.getString(3);
            String title24 = cursor.getString(4);

            String title31 = cursor.getString(5);
            String title32 = cursor.getString(6);
            String title33 = cursor.getString(7);
            String title34 = cursor.getString(8);

            String title41 = cursor.getString(9);
            String title42 = cursor.getString(10);
            String title43 = cursor.getString(11);
            String title44 = cursor.getString(12);
            

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
            
            
        }
        return dto;
    }



}
