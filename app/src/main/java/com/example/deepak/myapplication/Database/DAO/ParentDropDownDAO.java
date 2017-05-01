package com.example.deepak.myapplication.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.deepak.myapplication.Database.DTO.ParentDropDownDTO;
import com.example.deepak.myapplication.Database.OfflineDatabaseHelper;
import com.example.deepak.myapplication.Utility.Constant;

import java.util.ArrayList;

public class ParentDropDownDAO extends OfflineDatabaseHelper {
    private static final String FORM_1_ENTITY_1_TABLE = "FORM_1_ENTITY_1_TABLE";
    private static final String FORM_1_ENTITY_2_TABLE = "FORM_1_ENTITY_2_TABLE";
    private static final String FORM_1_ENTITY_3_TABLE = "FORM_1_ENTITY_3_TABLE";
    private static final String FORM_1_ENTITY_4_TABLE = "FORM_1_ENTITY_4_TABLE";

    private static final String FORM_3_ENTITY_5_TABLE = "FORM_3_ENTITY_5_TABLE";
    private static final String FORM_3_ENTITY_6_TABLE = "FORM_3_ENTITY_6_TABLE";
    private static final String FORM_3_ENTITY_7_TABLE = "FORM_3_ENTITY_7_TABLE";

    private static final String FORM_4_ENTITY_5_TABLE = "FORM_4_ENTITY_5_TABLE";
    private static final String FORM_4_ENTITY_6_TABLE = "FORM_4_ENTITY_6_TABLE";
    private static final String FORM_4_ENTITY_7_TABLE = "FORM_4_ENTITY_7_TABLE";


    public ParentDropDownDAO(Context context) {
        super(context);
    }

    public ArrayList<ParentDropDownDTO> getAllParentsDropDown() {
        ArrayList<ParentDropDownDTO> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] coloumns = {ID, TITLE, DETAILS, IS_SYSTEM_VALUE, IS_VIRTUALLY_DELETED, TABLE_NAME, DATABASE_KEY, IS_NEED_SHOWN, IS_COMPULSORY, IS_DROP_DOWN_TYPE};
        Cursor cursor = db.query(PARENT_DROP_DOWN_TABLE, coloumns, IS_NEED_SHOWN+" = '"+1+"' AND "+IS_DROP_DOWN_TYPE+" = '"+1+"'", null, null, null, null);

        if (null != cursor && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ParentDropDownDTO dto = new ParentDropDownDTO();
                dto.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                dto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                dto.setDetails(cursor.getString(cursor.getColumnIndex(DETAILS)));
                dto.setDatabaseKey(cursor.getString(cursor.getColumnIndex(DATABASE_KEY)));
                dto.setTableName(cursor.getString(cursor.getColumnIndex(TABLE_NAME)));
                list.add(dto);
            }
            cursor.close();
        } else {
            //addDefaultData();
            Log.d("rohit","no output found");
        }
        db.close();
        return list;
    }

    private void addDefaultData() {
        for (int i = 0; i < titles.length; i++) {
                ParentDropDownDTO dto = new ParentDropDownDTO();
                dto.setTitle(titles[i]);
                dto.setSystemValue(false);

                dto.setTableName(tables[i]);
                dto.setDatabaseKey(database_keys[i]);

                dto.setShown(isShown[i]);
                dto.setCompulsory(isCompulsory[i]);
                dto.setDropDown(isDropDown[i]);

                addParentsDropDown(dto);
           }
    }


    public ArrayList<ParentDropDownDTO> getParentsDropDown() {
        ArrayList<ParentDropDownDTO> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] coloumns = {ID, TITLE, DETAILS, IS_SYSTEM_VALUE, IS_VIRTUALLY_DELETED, TABLE_NAME, DATABASE_KEY, IS_NEED_SHOWN, IS_COMPULSORY, IS_DROP_DOWN_TYPE};
        Cursor cursor = db.query(PARENT_DROP_DOWN_TABLE, coloumns, null, null, null, null, null);

        if (null != cursor && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ParentDropDownDTO dto = new ParentDropDownDTO();
                dto.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                dto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                dto.setDetails(cursor.getString(cursor.getColumnIndex(DETAILS)));
                dto.setDatabaseKey(cursor.getString(cursor.getColumnIndex(DATABASE_KEY)));
                dto.setTableName(cursor.getString(cursor.getColumnIndex(TABLE_NAME)));

                Boolean shown = false, compulsory=false, isDropdown=false;
                if (cursor.getInt(cursor.getColumnIndex(IS_NEED_SHOWN)) == 1) shown = true;
                if (cursor.getInt(cursor.getColumnIndex(IS_COMPULSORY)) == 1) compulsory = true;
                if (cursor.getInt(cursor.getColumnIndex(IS_DROP_DOWN_TYPE)) == 1) isDropdown = true;
                dto.setShown(shown);
                dto.setCompulsory(compulsory);
                dto.setDropDown(isDropdown);
                list.add(dto);
            }
            cursor.close();
        }
        db.close();
        return list;
    }














    private void addParentsDropDown(ParentDropDownDTO dto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, dto.getTitle());
        cv.put(DETAILS, dto.getDetails());

        cv.put(IS_SYSTEM_VALUE, dto.getSystemValue());
        cv.put(IS_VIRTUALLY_DELETED, dto.getVirtuallyDeleted());

        cv.put(IS_DROP_DOWN_TYPE, dto.getDropDown());
        cv.put(IS_COMPULSORY, dto.getCompulsory());
        cv.put(IS_NEED_SHOWN, dto.getShown());

        cv.put(TABLE_NAME, dto.getTableName());
        cv.put(DATABASE_KEY, dto.getDatabaseKey());

        Long result = db.insert(PARENT_DROP_DOWN_TABLE, null, cv);
        Log.d("rohit", "result" + result);
        db.close();
    }

    String[] titles = {
            "First Name", "Laset Name", "Email Id", "Mobile No",
            "Source", "Status", "Priority", "Type",
            "Location", "Course", "form3entity3", "form3entity4", "form3entity5", "form3entity6", "form3entity7",
            "form4entity1", "form4entity2", "form4entity3", "form4entity4", "form4entity5", "form4entity6", "form4entity7",
            "Reminders"};

    String[] tables = {
            FORM_1_ENTITY_1_TABLE,
            FORM_1_ENTITY_2_TABLE,
            FORM_1_ENTITY_3_TABLE,
            FORM_1_ENTITY_4_TABLE,

            FORM_2_ENTITY_1_TABLE,
            FORM_2_ENTITY_2_TABLE,
            FORM_2_ENTITY_3_TABLE,
            FORM_2_ENTITY_4_TABLE,

            FORM_3_ENTITY_1_TABLE,
            FORM_3_ENTITY_2_TABLE,
            FORM_3_ENTITY_3_TABLE,
            FORM_3_ENTITY_4_TABLE,
            FORM_3_ENTITY_5_TABLE,
            FORM_3_ENTITY_6_TABLE,
            FORM_3_ENTITY_7_TABLE,

            FORM_4_ENTITY_1_TABLE,
            FORM_4_ENTITY_2_TABLE,
            FORM_4_ENTITY_3_TABLE,
            FORM_4_ENTITY_4_TABLE,
            FORM_4_ENTITY_5_TABLE,
            FORM_4_ENTITY_6_TABLE,
            FORM_4_ENTITY_7_TABLE,

            ACTIVITY_TYPE_TABLE};

    String[] database_keys = {
            Constant.FORM_1_CHILD_1,
            Constant.FORM_1_CHILD_2,
            Constant.FORM_1_CHILD_3,
            Constant.FORM_1_CHILD_4,

            Constant.FORM_2_CHILD_1,
            Constant.FORM_2_CHILD_2,
            Constant.FORM_2_CHILD_3,
            Constant.FORM_2_CHILD_4,

            Constant.FORM_3_CHILD_1,
            Constant.FORM_3_CHILD_2,
            Constant.FORM_3_CHILD_3,
            Constant.FORM_3_CHILD_4,
            Constant.FORM_3_CHILD_5,
            Constant.FORM_3_CHILD_6,
            Constant.FORM_3_CHILD_7,

            Constant.FORM_4_CHILD_1,
            Constant.FORM_4_CHILD_2,
            Constant.FORM_4_CHILD_3,
            Constant.FORM_4_CHILD_4,
            Constant.FORM_4_CHILD_5,
            Constant.FORM_4_CHILD_6,
            Constant.FORM_4_CHILD_7,

            Constant.ACTIVITY_DROPDOWN_VALUES};


    Boolean[] isShown = {
            true, true, true, true,
            true, true, true, true,
            true, true, false, false, false, false, false,
            false, false, false, false, false, false, false,
            false
    };

    Boolean[] isCompulsory = {
            true, true, true, true,
            true, true, true, true,
            false, false, false, false, false, false, false,
            false, false, false, false, false, false, false,
            false
    };

    Boolean[] isDropDown = {
            false, false, false, false,
            true, true, true, true,
            true, true, true, true, false, false, false,
            true, true, true, true, false, false, false,
            false
    };
}
