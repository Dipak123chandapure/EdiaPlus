package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Database.OfflineDatabaseHelper;
import com.google.gson.Gson;
import java.util.ArrayList;


public class BatchDAO extends OfflineDatabaseHelper {
    public BatchDAO(Context context) {
        super(context);
    }


    public Long saveBatch(BatchDTO dto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BATCH_NAME, dto.getName());
        contentValues.put(BATCH_DETAILS, dto.getDetails());

        SQLiteDatabase db = getWritableDatabase();
        Long result = db.insert(BATCH_TABLE, null, contentValues);
        Log.d("rohit", "result " + result);
        db.close();
        return result;
    }


    public ArrayList<BatchDTO> getBatchs() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = {ID, BATCH_NAME, BATCH_DETAILS};
        Cursor cursor = db.query(BATCH_TABLE, coloumns, null, null, null, null, null);
        ArrayList<BatchDTO> list = new ArrayList<>();

        if (null != cursor && cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                BatchDTO dto = new BatchDTO();
                dto.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                dto.setName(cursor.getString(cursor.getColumnIndex(BATCH_NAME)));
                dto.setDetails(cursor.getString(cursor.getColumnIndex(BATCH_DETAILS)));
                list.add(dto);
            }
            cursor.close();
        }

        db.close();
        return list;

    }

    public void saveToStudentBatchBridge(Long id, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_ID, id);
        contentValues.put(BATCH_ID, i);

        SQLiteDatabase db = getWritableDatabase();
        Long result = db.insert(BATCH_STUDENT_BRIDGE_TABLE, null, contentValues);
        Log.d("rohit", "result " + result);
    }

    public ArrayList<StudentDTO> getStudentsForBatch(Long batchID, int index) {
        int i = 0;
        String sql = "SELECT " + "b." + STUDENT_ID + ", " + "s." + STUDENT_DATA_JSON
                + " FROM " + BATCH_STUDENT_BRIDGE_TABLE + " b "
                + " JOIN " + STUDENT_INFO_TABLE + " s "
                + " ON b." + STUDENT_ID + " = s." + ID
                + " WHERE b." + BATCH_ID + " = '" + batchID + "'";


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<StudentDTO> list = new ArrayList<>();
        cursor.move(index);

        if (null != cursor && cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(STUDENT_ID));
                String json = cursor.getString(cursor.getColumnIndex(STUDENT_DATA_JSON));
                StudentDTO dto = new Gson().fromJson(json, StudentDTO.class);
                list.add(dto);
                if (i > 20) {
                    return list;
                }
                i++;
            }
            cursor.close();
        }

        db.close();
        return list;
    }


    public ArrayList<StudentDTO> getStudentsForBatch(Long batchID) {
        String sql = "SELECT " + "b." + STUDENT_ID + ", " + "s." + STUDENT_DATA_JSON
                + " FROM " + BATCH_STUDENT_BRIDGE_TABLE + " b "
                + " JOIN " + STUDENT_INFO_TABLE + " s "
                + " ON b." + STUDENT_ID + " = s." + ID
                + " WHERE b." + BATCH_ID + " = '" + batchID + "'";


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<StudentDTO> list = new ArrayList<>();

        if (null != cursor && cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(STUDENT_ID));
                String json = cursor.getString(cursor.getColumnIndex(STUDENT_DATA_JSON));
                StudentDTO dto = new Gson().fromJson(json, StudentDTO.class);
                list.add(dto);
            }
            cursor.close();
        }

        db.close();
        return list;
    }
}
