package com.example.deepak.myapplication.Database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class OfflineDatabaseHelper extends AbstractDatabaseHelper {

    private final String TAG = getClass().getSimpleName();

    public OfflineDatabaseHelper(Context context) {
        super(context);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d("rohit", "starting oncreate");
        try {
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
            db.execSQL(STUDENT_ATTACHMENT_TABLE_CREATE);

            db.execSQL(ACTIVITY_TYPE_TABLE_CREATE);
            db.execSQL(ACTIVITY_TABLE_CREATE);
            db.execSQL(ACTIVITIES_ATTACHMENT_TABLE_CREATE);

            db.execSQL(BATCH_TABLE_CREATE);
            db.execSQL(BATCH_STUDENT_BRIDGE_TABLE_CREATE);
            db.execSQL(BATCH_ATTACHMENT_TABLE_CREATE);

        }catch (Exception e){
            Log.d("rohit", "exception "+e.toString());
        }
        Log.d("rohit", "ending oncreate");
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
