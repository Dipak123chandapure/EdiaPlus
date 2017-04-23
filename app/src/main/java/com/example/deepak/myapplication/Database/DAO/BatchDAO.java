package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.OfflineDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Deepak on 4/22/2017.
 */

public class BatchDAO extends OfflineDatabase {
    public BatchDAO(Context context) {
        super(context);
    }


    public void saveBatch(BatchDTO dto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BATCH_NAME, dto.getName());
        contentValues.put(BATCH_DETAILS, dto.getDetails());

        SQLiteDatabase db = getWritableDatabase();
        Long result = db.insert(BATCH_TABLE, null, contentValues);
        Log.d("rohit", "result " + result);
    }


    public ArrayList<BatchDTO> getBatchs() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = {ID, BATCH_NAME, BATCH_DETAILS};
        Cursor cursor = db.query(BATCH_TABLE, coloumns, null, null, null, null, null);
        ArrayList<BatchDTO> list = new ArrayList<>();

        if (null != cursor) {
            while (cursor.moveToNext()) {
                BatchDTO dto = new BatchDTO();
                dto.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                dto.setName(cursor.getString(cursor.getColumnIndex(BATCH_NAME)));
                dto.setDetails(cursor.getString(cursor.getColumnIndex(BATCH_DETAILS)));
                list.add(dto);
            }
        }
        cursor.close();
        db.close();
        return list;

    }
}
