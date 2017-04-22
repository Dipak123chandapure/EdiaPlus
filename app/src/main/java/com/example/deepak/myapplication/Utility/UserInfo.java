package com.example.deepak.myapplication.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.deepak.myapplication.Database.DTO.FormConstarins;
import com.google.gson.Gson;

import org.json.JSONArray;


public class UserInfo {
    private static final String USER_INFO = UserInfo.class.getSimpleName();
    private static final String LAST_SAVED_ID = "LAST_SAVED_ID";

    private static UserInfo sUserInfo;

    private UserInfo() {
    }

    public static UserInfo getInstance() {
        if (null == sUserInfo) {
            sUserInfo = new UserInfo();
        }
        return sUserInfo;
    }


    public void wipeUserInfo(Context mContext) {
        mContext.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit().clear().commit();
    }

    private void write(String key, String value, Context mContext) {
        SharedPreferences sharedPref = mContext.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private String read(String key, Context mContext) {
        return mContext.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).getString(key, null);
    }





    public void saveFormConstains(FormConstarins formOneConstarins, String KEY, Context mContext) {
        if (null != formOneConstarins) {
            String fomdDataString = new Gson().toJson(formOneConstarins);
            write(KEY, fomdDataString, mContext);
        }
    }

    public FormConstarins getFormConstarins(String KEY, Context mContext) {
        if (null != read(KEY, mContext))
            return new Gson().fromJson(read(KEY, mContext), FormConstarins.class);
        else return null;
    }

    public void saveFilterJSONArray(String KEY, JSONArray filterArray, Context mContext) {
        if (null != filterArray) {
            String fomdDataString = filterArray.toString();
            write(KEY, fomdDataString, mContext);
        }
    }

    public String readFilterJSONArray(String KEY, Context mContext) {
        if (null != read(KEY, mContext)) {
            return read(KEY, mContext);
        }else return null;
    }
}

