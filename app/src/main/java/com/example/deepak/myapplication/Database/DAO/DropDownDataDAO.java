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

    public void saveFormData(String TYPE, DropDownDataDTO dropDownDataDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, dropDownDataDTO.getTitle());
        cv.put(DETAILS, dropDownDataDTO.getDetails());
        cv.put(IS_SYSTEM_VALUE, dropDownDataDTO.getSystemValue());
        cv.put(IS_VIRTUALLY_DELETED, dropDownDataDTO.getVirtuallyDeleted());
        Long result = -1L;

        switch (TYPE) {
            case Constant.FORM_TWO_CHILD_ONE_COMMON_CODE:
                result = db.insert(FORM_2_ENTITY_1_TABLE, null, cv);
                break;

            case Constant.FORM_TWO_CHILD_TWO_COMMON_CODE:
                result = db.insert(FORM_2_ENTITY_2_TABLE, null, cv);
                break;

            case Constant.FORM_TWO_CHILD_THREE_COMMON_CODE:
                result = db.insert(FORM_2_ENTITY_3_TABLE, null, cv);
                break;

            case Constant.FORM_TWO_CHILD_FOUR_COMMON_CODE:
                result = db.insert(FORM_2_ENTITY_4_TABLE, null, cv);
                break;


            case Constant.FORM_THREE_CHILD_ONE_COMMON_CODE:
                result = db.insert(FORM_3_ENTITY_1_TABLE, null, cv);
                break;

            case Constant.FORM_THREE_CHILD_TWO_COMMON_CODE:
                result = db.insert(FORM_3_ENTITY_2_TABLE, null, cv);
                break;

            case Constant.FORM_THREE_CHILD_THREE_COMMON_CODE:
                result = db.insert(FORM_3_ENTITY_3_TABLE, null, cv);
                break;

            case Constant.FORM_THREE_CHILD_FOUR_COMMON_CODE:
                result = db.insert(FORM_3_ENTITY_4_TABLE, null, cv);
                break;


            case Constant.FORM_FOUR_CHILD_ONE_COMMON_CODE:
                result = db.insert(FORM_4_ENTITY_1_TABLE, null, cv);
                break;

            case Constant.FORM_FOUR_CHILD_TWO_COMMON_CODE:
                result = db.insert(FORM_4_ENTITY_2_TABLE, null, cv);
                break;

            case Constant.FORM_FOUR_CHILD_THREE_COMMON_CODE:
                result = db.insert(FORM_4_ENTITY_3_TABLE, null, cv);
                break;

            case Constant.FORM_FOUR_CHILD_FOUR_COMMON_CODE:
                result = db.insert(FORM_4_ENTITY_4_TABLE, null, cv);
                break;

            case Constant.ACTIVITY_DROPDOWN_VALUES:
                result = db.insert(ACTIVITY_TYPE_TABLE, null, cv);
                break;
        }
        Log.d("rohit", "result "+result);
        db.close();
    }

    public ArrayList<DropDownDataDTO> getFormData(String TYPE){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;

        switch (TYPE){
            case Constant.FORM_TWO_CHILD_ONE_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_2_ENTITY_1_TABLE, null);
                break;

            case Constant.FORM_TWO_CHILD_TWO_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_2_ENTITY_2_TABLE, null);
                break;

            case Constant.FORM_TWO_CHILD_THREE_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_2_ENTITY_3_TABLE, null);
                break;

            case Constant.FORM_TWO_CHILD_FOUR_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_2_ENTITY_4_TABLE, null);
                break;



            case Constant.FORM_THREE_CHILD_ONE_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_3_ENTITY_1_TABLE, null);
                break;

            case Constant.FORM_THREE_CHILD_TWO_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_3_ENTITY_2_TABLE, null);
                break;

            case Constant.FORM_THREE_CHILD_THREE_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_3_ENTITY_3_TABLE, null);
                break;

            case Constant.FORM_THREE_CHILD_FOUR_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_3_ENTITY_4_TABLE, null);
                break;



            case Constant.FORM_FOUR_CHILD_ONE_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_4_ENTITY_1_TABLE, null);
                break;

            case Constant.FORM_FOUR_CHILD_TWO_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_4_ENTITY_2_TABLE, null);
                break;

            case Constant.FORM_FOUR_CHILD_THREE_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_4_ENTITY_3_TABLE, null);
                break;

            case Constant.FORM_FOUR_CHILD_FOUR_COMMON_CODE:
                cursor = db.rawQuery("SELECT * FROM "+FORM_4_ENTITY_4_TABLE, null);
                break;

            case Constant.ACTIVITY_DROPDOWN_VALUES:
                cursor = db.rawQuery("SELECT * FROM "+ACTIVITY_TYPE_TABLE, null);
                break;
        }
        ArrayList<DropDownDataDTO> list = new ArrayList<>();

        if (cursor!= null){
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                do {
                    DropDownDataDTO dropDownDataDTO = new DropDownDataDTO();
                    dropDownDataDTO.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                    dropDownDataDTO.setDetails(cursor.getString(cursor.getColumnIndex(DETAILS)));
                    dropDownDataDTO.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                    dropDownDataDTO.setVirtuallyDeleted(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_SYSTEM_VALUE))));
                    dropDownDataDTO.setVirtuallyDeleted(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_VIRTUALLY_DELETED))));
                    list.add(dropDownDataDTO);
                }while (cursor.moveToNext());

            }

            cursor.close();
        }

        db.close();
        return list;
    }
}
