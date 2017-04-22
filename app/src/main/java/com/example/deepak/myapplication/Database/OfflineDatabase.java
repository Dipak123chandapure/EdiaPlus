package com.example.deepak.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class OfflineDatabase extends SQLiteOpenHelper {

    //database
    protected static final String DATABASE_NAME = "STUDENT_DATABASE_DB";
    protected static final int DATABASE_VERSION = 1;
    protected final String TAG = getClass().getSimpleName();

    protected static final String ID = "ID";
    protected static final String SYNC_STATUS = "SYNC_STATUS";
    protected static final String FORM_1_ENTITY_1 = "FORM_1_ENTITY_1";
    protected static final String FORM_1_ENTITY_2 = "FORM_1_ENTITY_2";
    public static final String FORM_1_ENTITY_3 = "FORM_1_ENTITY_3";
    protected static final String FORM_1_ENTITY_4 = "FORM_1_ENTITY_4";

    protected static final String CREATED_ON = "CREATED_ON";
    protected static final String UPDATED_ON = "UPDATED_ON";
    protected static final String CREATED_ON_MILLI = "CREATED_ON_MILLI";
    protected static final String UPDATED_ON_MILLI = "UPDATED_ON_MILLI";

    //statusTable, source table, priority table
    protected static final String FORM_2_ENTITY_1_TABLE = "STATUS_TABLE";
    protected static final String FORM_2_ENTITY_2_TABLE = "SOURCE_TABLE";
    protected static final String FORM_2_ENTITY_3_TABLE = "PRIORITY_TABLE";
    protected static final String FORM_2_ENTITY_4_TABLE = "FORM_2_ENTITY_1_TABLE";

    protected static final String FORM_3_ENTITY_1_TABLE = "FORM_3_ENTITY_1_TABLE";
    protected static final String FORM_3_ENTITY_2_TABLE = "FORM_3_ENTITY_2_TABLE";
    protected static final String FORM_3_ENTITY_3_TABLE = "FORM_3_ENTITY_3_TABLE";
    protected static final String FORM_3_ENTITY_4_TABLE = "FORM_3_ENTITY_4_TABLE";

    protected static final String FORM_4_ENTITY_1_TABLE = "FORM_4_ENTITY_1_TABLE";
    protected static final String FORM_4_ENTITY_2_TABLE = "FORM_4_ENTITY_2_TABLE";
    protected static final String FORM_4_ENTITY_3_TABLE = "FORM_4_ENTITY_3_TABLE";
    protected static final String FORM_4_ENTITY_4_TABLE = "FORM_4_ENTITY_4_TABLE";

    protected static final String TITLE = "TITLE";
    protected static final String DETAILS = "DETAILS";
    protected static final String IS_SYSTEM_VALUE = "IS_SYSTEM_VALUE";
    protected static final String IS_VIRTUALLY_DELETED = "IS_VIRTUALLY_DELETED";


    protected static final String FORM_2_ENTITY_1_TABLE_CREATE = "create table "
            + FORM_2_ENTITY_1_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_2_ENTITY_2_TABLE_CREATE = "create table "
            + FORM_2_ENTITY_2_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_2_ENTITY_3_TABLE_CREATE = "create table "
            + FORM_2_ENTITY_3_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_2_ENTITY_4_TABLE_CREATE = "create table "
            + FORM_2_ENTITY_4_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_3_ENTITY_1_TABLE_CREATE = "create table "
            + FORM_3_ENTITY_1_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_3_ENTITY_2_TABLE_CREATE = "create table "
            + FORM_3_ENTITY_2_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_3_ENTITY_3_TABLE_CREATE = "create table "
            + FORM_3_ENTITY_3_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_3_ENTITY_4_TABLE_CREATE = "create table "
            + FORM_3_ENTITY_4_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_4_ENTITY_1_TABLE_CREATE = "create table "
            + FORM_4_ENTITY_1_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_4_ENTITY_2_TABLE_CREATE = "create table "
            + FORM_4_ENTITY_2_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_4_ENTITY_3_TABLE_CREATE = "create table "
            + FORM_4_ENTITY_3_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";

    protected static final String FORM_4_ENTITY_4_TABLE_CREATE = "create table "
            + FORM_4_ENTITY_4_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text not null, "
            + IS_SYSTEM_VALUE + " text not null,"
            + IS_VIRTUALLY_DELETED + " text" + ");";


    //actionTypeTable
    protected static final String ACTIVITY_TYPE_TABLE = "ACTIVITY_TYPE_TABLE";

    protected static final String ACTIVITY_TYPE_TABLE_CREATE = "create table "
            + ACTIVITY_TYPE_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DETAILS + " text, "
            + IS_SYSTEM_VALUE + " text,"
            + IS_VIRTUALLY_DELETED + " text" + ");";



    //activityTable
    protected static final String ACTIVITY_TABLE = "ACTIVITY_TABLE";
    protected static final String ACTIVITY_TYPE_ID = "ACTIVITY_TYPE_ID";
    protected static final String ACTIVITY_DATE_TIME = "ACTIVITY_DATE_TIME";
    protected static final String IS_DONE = "IS_DONE";
    protected static final String STUDENT_ID = "STUDENT_ID";
    protected static final String ACTIVITY_DATA_JSON = "ACTIVITY_DATA_JSON";
    protected static final String SEND_ACTIVITY_JSON = "SEND_ACTIVITY_JSON";


    protected static final String ACTIVITY_DATA = "ACTIVITY_DATA";

    protected static final String ACTIVITY_TABLE_CREATE = "create table "
            + ACTIVITY_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + ACTIVITY_TYPE_ID + " integer, "
            + IS_DONE + " text,"

            + FORM_1_ENTITY_3 + " text,"
            + FORM_1_ENTITY_4 + " text,"

            + ACTIVITY_DATE_TIME + " text, "
            + CREATED_ON + " text, "
            + UPDATED_ON + " text, "

            + SEND_ACTIVITY_JSON + " text, "
            + ACTIVITY_DATA_JSON + " text, "
            + SYNC_STATUS + " text, "

            + " FOREIGN KEY ("+ACTIVITY_TYPE_ID+") REFERENCES "+ACTIVITY_TYPE_TABLE+"("+ID+")"
            + ");";




    //studentTable
    protected static final String STUDENT_INFO_TABLE = "STUDENT_INFO_TABLE";


    protected static final String FORM_2_ENTITY_1_ID = "FORM_2_ENTITY_1_ID";
    protected static final String FORM_2_ENTITY_2_ID = "FORM_2_ENTITY_2_ID";
    protected static final String FORM_2_ENTITY_3_ID = "FORM_2_ENTITY_3_ID";
    protected static final String FORM_2_ENTITY_4_ID = "FORM_2_ENTITY_4_ID";

    protected static final String FORM_3_ENTITY_1_ID = "FORM_3_ENTITY_1_ID";
    protected static final String FORM_3_ENTITY_2_ID = "FORM_3_ENTITY_2_ID";
    protected static final String FORM_3_ENTITY_3_ID = "FORM_3_ENTITY_3_ID";
    protected static final String FORM_3_ENTITY_4_ID = "FORM_3_ENTITY_4_ID";

    protected static final String FORM_4_ENTITY_1_ID = "FORM_4_ENTITY_1_ID";
    protected static final String FORM_4_ENTITY_2_ID = "FORM_4_ENTITY_2_ID";
    protected static final String FORM_4_ENTITY_3_ID = "FORM_4_ENTITY_3_ID";
    protected static final String FORM_4_ENTITY_4_ID = "FORM_4_ENTITY_4_ID";


    protected static final String STUDENT_DATA_JSON = "STUDENT_DATA_JSON";
    protected static final String SEND_DATA_JSON = "SEND_DATA_JSON";



    protected static final String STUDENT_TABLE_CREATE = "create table "
            + STUDENT_INFO_TABLE + " ("
            + ID + " integer primary key autoincrement, "

            + FORM_1_ENTITY_1 + " text not null, "
            + FORM_1_ENTITY_2 + " text, "
            + FORM_1_ENTITY_3 + " text not null,"
            + FORM_1_ENTITY_4 + " text not null,"

            + FORM_2_ENTITY_1_ID + " integer, "
            + FORM_2_ENTITY_2_ID + " integer, "
            + FORM_2_ENTITY_3_ID + " integer, "
            + FORM_2_ENTITY_4_ID + " integer, "

            + FORM_3_ENTITY_1_ID + " integer, "
            + FORM_3_ENTITY_2_ID + " integer, "
            + FORM_3_ENTITY_3_ID + " integer, "
            + FORM_3_ENTITY_4_ID + " integer, "

            + FORM_4_ENTITY_1_ID + " integer, "
            + FORM_4_ENTITY_2_ID + " integer, "
            + FORM_4_ENTITY_3_ID + " integer, "
            + FORM_4_ENTITY_4_ID + " integer, "

            + CREATED_ON + " text not null, "
            + CREATED_ON_MILLI + " text not null, "
            + UPDATED_ON + " text not null, "
            + UPDATED_ON_MILLI + " text not null, "

            + STUDENT_DATA_JSON + " text,"
            + SEND_DATA_JSON + " text, "
            + SYNC_STATUS + " text,"

            + " FOREIGN KEY ("+FORM_2_ENTITY_1_ID+") REFERENCES "+FORM_2_ENTITY_1_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_2_ENTITY_2_ID+") REFERENCES "+FORM_2_ENTITY_2_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_2_ENTITY_3_ID+") REFERENCES "+FORM_2_ENTITY_3_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_2_ENTITY_4_ID+") REFERENCES "+FORM_2_ENTITY_4_TABLE+"("+ID+"), "

            + " FOREIGN KEY ("+FORM_3_ENTITY_1_ID+") REFERENCES "+FORM_3_ENTITY_1_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_3_ENTITY_2_ID+") REFERENCES "+FORM_3_ENTITY_1_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_3_ENTITY_3_ID+") REFERENCES "+FORM_3_ENTITY_1_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_3_ENTITY_4_ID+") REFERENCES "+FORM_3_ENTITY_4_TABLE+"("+ID+"), "

            + " FOREIGN KEY ("+FORM_4_ENTITY_1_ID+") REFERENCES "+FORM_4_ENTITY_1_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_4_ENTITY_2_ID+") REFERENCES "+FORM_4_ENTITY_1_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_4_ENTITY_3_ID+") REFERENCES "+FORM_4_ENTITY_1_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+FORM_4_ENTITY_4_ID+") REFERENCES "+FORM_4_ENTITY_4_TABLE+"("+ID+") "
            + ");";


    //batch table
    protected static final String BATCH_TABLE = "BATCH_TABLE";
    protected static final String BATCH_NAME = "BATCH_NAME";
    protected static final String BATCH_DETAILS = "BATCH_DETAILS";



    protected static final String BATCH_TABLE_CREATE = "create table "
            + BATCH_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + BATCH_NAME + " text not null, "
            + BATCH_DETAILS + " text not null "
            + ");";

    //batch-student-bridge table
    protected static final String BATCH_STUDENT_BRIDGE_TABLE = "BATCH_STUDENT_BRIDGE_TABLE";
    protected static final String BATCH_ID = "BATCH_TABLE";


    protected static final String BATCH_STUDENT_BRIDGE_TABLE_CREATE = "create table "
            + BATCH_STUDENT_BRIDGE_TABLE + " ("
            + BATCH_ID + " integer, "
            + STUDENT_ID + " integer, "
            + " FOREIGN KEY ("+BATCH_ID+") REFERENCES "+BATCH_TABLE+"("+ID+"), "
            + " FOREIGN KEY ("+STUDENT_ID+") REFERENCES "+STUDENT_INFO_TABLE+"("+ID+")"
            + ");";



    public OfflineDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "starting oncreate");
        try {

            db.execSQL(ACTIVITY_TYPE_TABLE_CREATE);
            db.execSQL(ACTIVITY_TABLE_CREATE);

            db.execSQL(FORM_2_ENTITY_1_TABLE_CREATE);
            db.execSQL(FORM_2_ENTITY_2_TABLE_CREATE);
            db.execSQL(FORM_2_ENTITY_3_TABLE_CREATE);
            db.execSQL(FORM_2_ENTITY_4_TABLE_CREATE);

            db.execSQL(FORM_3_ENTITY_1_TABLE_CREATE);
            db.execSQL(FORM_3_ENTITY_2_TABLE_CREATE);
            db.execSQL(FORM_3_ENTITY_3_TABLE_CREATE);
            db.execSQL(FORM_3_ENTITY_4_TABLE_CREATE);

            db.execSQL(FORM_4_ENTITY_1_TABLE_CREATE);
            db.execSQL(FORM_4_ENTITY_2_TABLE_CREATE);
            db.execSQL(FORM_4_ENTITY_3_TABLE_CREATE);
            db.execSQL(FORM_4_ENTITY_4_TABLE_CREATE);

            db.execSQL(STUDENT_TABLE_CREATE);

            db.execSQL(BATCH_TABLE_CREATE);
            db.execSQL(BATCH_STUDENT_BRIDGE_TABLE_CREATE);
        }catch (Exception e){
            Log.d("rohiti", "exception "+e.toString());
        }
        Log.d("rohiti", "ending oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
