package com.example.deepak.myapplication.Database.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.AbstractDatabaseHelper;
import com.example.deepak.myapplication.Database.OfflineDatabaseHelper;
import com.example.deepak.myapplication.Utility.Constant;

import java.util.ArrayList;


public class DropDownDataDAO extends OfflineDatabaseHelper {
    public DropDownDataDAO(Context context) {
        super(context);
    }

    public Long saveFormData(String TYPE, DropDownDataDTO dropDownDataDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, dropDownDataDTO.getTitle());
        cv.put(DETAILS, dropDownDataDTO.getDetails());
        cv.put(IS_SYSTEM_VALUE, dropDownDataDTO.getSystemValue());
        cv.put(IS_VIRTUALLY_DELETED, dropDownDataDTO.getVirtuallyDeleted());
        Long result = db.insert(getTableName(TYPE), null, cv);
        Log.d("rohit", "result "+result);
        db.close();
        return result;
    }

    public ArrayList<DropDownDataDTO> getFormData(String TYPE){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+getTableName(TYPE)+" WHERE "+IS_SYSTEM_VALUE+" = '"+0+"'", null);
        ArrayList<DropDownDataDTO> list = new ArrayList<>();
        if (cursor!= null){
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                do {
                    DropDownDataDTO dropDownDataDTO = new DropDownDataDTO();
                    dropDownDataDTO.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                    dropDownDataDTO.setDetails(cursor.getString(cursor.getColumnIndex(DETAILS)));
                    dropDownDataDTO.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                    dropDownDataDTO.setSystemValue(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_SYSTEM_VALUE))));
                    dropDownDataDTO.setVirtuallyDeleted(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_VIRTUALLY_DELETED))));
                    list.add(dropDownDataDTO);
                }while (cursor.moveToNext());

            }

            cursor.close();
        }

        db.close();
        return list;
    }

    public void deleteFormData(String TYPE, DropDownDataDTO value) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(getTableName(TYPE), ID+" = '"+value.getId()+"'", null);
        Log.d("rohit", "result "+result);
        db.close();
    }

    public void updateFormData(String type, DropDownDataDTO selectedItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, selectedItem.getTitle());
        cv.put(DETAILS, selectedItem.getDetails());
        cv.put(IS_SYSTEM_VALUE, selectedItem.getSystemValue());
        cv.put(IS_VIRTUALLY_DELETED, selectedItem.getVirtuallyDeleted());
        int result = db.update(getTableName(type), cv, ID+" = '"+selectedItem.getId()+"'", null);
        Log.d("rohit", "result "+result);
    }

    private String getTableName(String TYPE) {
        switch (TYPE) {
            case Constant.FORM_2_CHILD_1:
                return FORM_2_ENTITY_1_TABLE;

            case Constant.FORM_2_CHILD_2:
                return FORM_2_ENTITY_2_TABLE;

            case Constant.FORM_2_CHILD_3:
                return FORM_2_ENTITY_3_TABLE;

            case Constant.FORM_2_CHILD_4:
                return  FORM_2_ENTITY_4_TABLE;


            case Constant.FORM_3_CHILD_1:
                return FORM_3_ENTITY_1_TABLE;

            case Constant.FORM_3_CHILD_2:
                return FORM_3_ENTITY_2_TABLE;

            case Constant.FORM_3_CHILD_3:
                return FORM_3_ENTITY_3_TABLE;

            case Constant.FORM_3_CHILD_4:
                return FORM_3_ENTITY_4_TABLE;


            case Constant.FORM_4_CHILD_1:
                return  FORM_4_ENTITY_1_TABLE;

            case Constant.FORM_4_CHILD_2:
                return  FORM_4_ENTITY_2_TABLE;

            case Constant.FORM_4_CHILD_3:
                return  FORM_4_ENTITY_3_TABLE;

            case Constant.FORM_4_CHILD_4:
                return  FORM_4_ENTITY_4_TABLE;

            case Constant.ACTIVITY_DROPDOWN_VALUES:
                return ACTIVITY_TYPE_TABLE;

            default:return null;
        }
    }



}
