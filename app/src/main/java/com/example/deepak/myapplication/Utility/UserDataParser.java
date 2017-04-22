package com.example.deepak.myapplication.Utility;

import android.content.Context;
import android.util.Log;

import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.FormConstarins;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DeepakC on 24/03/2017.
 */

public class UserDataParser {

    public static final String ALL_FORM_CONSTRAINS = "ALL_FORM_CONSTRAINS";

    public static final String FORM_ONE_CONSTRAINS = "FORM_ONE_CONSTRAINS";
    public static final String FORM_TWO_CONSTRAINS = "FORM_TWO_CONSTRAINS";
    public static final String FORM_THREE_CONSTRAINS = "FORM_THREE_CONSTRAINS";
    public static final String FORM_FOUR_CONSTRAINS = "FORM_FOUR_CONSTRAINS";

    public static final String FORM_DROPDOWN_VALUES = "FORM_DROPDOWN_VALUES";

    public static final String FORM_ONE_CHILD_ONE_DROPDOWN_VALUES = "FORM_ONE_CHILD_ONE_DROPDOWN_VALUES";
    public static final String FORM_ONE_CHILD_TWO_DROPDOWN_VALUES = "FORM_ONE_CHILD_TWO_DROPDOWN_VALUES";
    public static final String FORM_ONE_CHILD_THREE_DROPDOWN_VALUES = "FORM_ONE_CHILD_THREE_DROPDOWN_VALUES";
    public static final String FORM_ONE_CHILD_FOUR_DROPDOWN_VALUES = "FORM_ONE_CHILD_FOUR_DROPDOWN_VALUES";

    public static final String FORM_TWO_CHILD_ONE_DROPDOWN_VALUES = "FORM_TWO_CHILD_ONE_DROPDOWN_VALUES";
    public static final String FORM_TWO_CHILD_TWO_DROPDOWN_VALUES = "FORM_TWO_CHILD_TWO_DROPDOWN_VALUES";
    public static final String FORM_TWO_CHILD_THREE_DROPDOWN_VALUES = "FORM_TWO_CHILD_THREE_DROPDOWN_VALUES";
    public static final String FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES = "FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES";

    public static final String FORM_THREE_CHILD_ONE_DROPDOWN_VALUES = "FORM_THREE_CHILD_ONE_DROPDOWN_VALUES";
    public static final String FORM_THREE_CHILD_TWO_DROPDOWN_VALUES = "FORM_THREE_CHILD_TWO_DROPDOWN_VALUES";
    public static final String FORM_THREE_CHILD_THREE_DROPDOWN_VALUES = "FORM_THREE_CHILD_THREE_DROPDOWN_VALUES";
    public static final String FORM_THREE_CHILD_FOUR_DROPDOWN_VALUES = "FORM_THREE_CHILD_FOUR_DROPDOWN_VALUES";

    public static final String FORM_FOUR_CHILD_ONE_DROPDOWN_VALUES = "FORM_FOUR_CHILD_ONE_DROPDOWN_VALUES";
    public static final String FORM_FOUR_CHILD_TWO_DROPDOWN_VALUES = "FORM_FOUR_CHILD_TWO_DROPDOWN_VALUES";
    public static final String FORM_FOUR_CHILD_THREE_DROPDOWN_VALUES = "FORM_FOUR_CHILD_THREE_DROPDOWN_VALUES";
    public static final String FORM_FOUR_CHILD_FOUR_DROPDOWN_VALUES = "FORM_FOUR_CHILD_FOUR_DROPDOWN_VALUES";

    public static final String ACTIVITY_TYPE_DROPDOWN_VALUES = "ACTIVITY_TYPE_DROPDOWN_VALUES";


    public static final String FILTER_DATA = "FILTER_DATA";
    static Context contex;
    public static void parseAllMasters(String MASTER_STRING, Context mContext) throws JSONException {
        contex = mContext;
        JSONObject jsonObject = new JSONObject(MASTER_STRING);

        JSONObject all_form_contrains = jsonObject.optJSONObject(ALL_FORM_CONSTRAINS);
        Log.d("rohit", "Parent JSON object"+all_form_contrains);

        JSONObject form_one_constarins = all_form_contrains.optJSONObject(FORM_ONE_CONSTRAINS);
        JSONObject form_two_constarins = all_form_contrains.optJSONObject(FORM_TWO_CONSTRAINS);
        JSONObject form_three_constarins = all_form_contrains.optJSONObject(FORM_THREE_CONSTRAINS);
        JSONObject form_four_constarins = all_form_contrains.optJSONObject(FORM_FOUR_CONSTRAINS);

        Log.d("rohit", "JSON object1"+form_one_constarins);
        Log.d("rohit", "JSON object2"+form_two_constarins);
        Log.d("rohit", "JSON object3"+form_three_constarins);
        Log.d("rohit", "JSON object4"+form_four_constarins);

        Gson gson = new Gson();
        FormConstarins formOneConstarins = gson.fromJson(form_one_constarins.toString(),FormConstarins.class);
        FormConstarins formTwoConstarins = gson.fromJson(form_two_constarins.toString(),FormConstarins.class);
        FormConstarins formThreeConstarins = gson.fromJson(form_three_constarins.toString(),FormConstarins.class);
        FormConstarins formFourConstarins = gson.fromJson(form_four_constarins.toString(),FormConstarins.class);

        UserInfo userInfo = UserInfo.getInstance();
        userInfo.saveFormConstains(formOneConstarins, FORM_ONE_CONSTRAINS, mContext);
        userInfo.saveFormConstains(formTwoConstarins, FORM_TWO_CONSTRAINS, mContext);
        userInfo.saveFormConstains(formThreeConstarins, FORM_THREE_CONSTRAINS, mContext);
        userInfo.saveFormConstains(formFourConstarins, FORM_FOUR_CONSTRAINS, mContext);

        JSONArray activity_type_dropDown_values = jsonObject.optJSONArray(ACTIVITY_TYPE_DROPDOWN_VALUES);

        JSONObject all_dropDown_values = jsonObject.optJSONObject(FORM_DROPDOWN_VALUES);
        JSONArray form_one_child_one_dropDown_values = all_dropDown_values.optJSONArray(FORM_ONE_CHILD_ONE_DROPDOWN_VALUES);
        JSONArray form_one_child_two_dropDown_values = all_dropDown_values.optJSONArray(FORM_ONE_CHILD_TWO_DROPDOWN_VALUES);
        JSONArray form_one_child_three_dropDown_values = all_dropDown_values.optJSONArray(FORM_ONE_CHILD_THREE_DROPDOWN_VALUES);
        JSONArray form_one_child_four_dropDown_values = all_dropDown_values.optJSONArray(FORM_ONE_CHILD_FOUR_DROPDOWN_VALUES);

        JSONArray form_two_child_one_dropDown_values = all_dropDown_values.optJSONArray(FORM_TWO_CHILD_ONE_DROPDOWN_VALUES);
        JSONArray form_two_child_two_dropDown_values = all_dropDown_values.optJSONArray(FORM_TWO_CHILD_TWO_DROPDOWN_VALUES);
        JSONArray form_two_child_three_dropDown_values = all_dropDown_values.optJSONArray(FORM_TWO_CHILD_THREE_DROPDOWN_VALUES);
        JSONArray form_two_child_four_dropDown_values = all_dropDown_values.optJSONArray(FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES);

        JSONArray form_three_child_one_dropDown_values = all_dropDown_values.optJSONArray(FORM_THREE_CHILD_ONE_DROPDOWN_VALUES);
        JSONArray form_three_child_two_dropDown_values = all_dropDown_values.optJSONArray(FORM_THREE_CHILD_TWO_DROPDOWN_VALUES);
        JSONArray form_three_child_three_dropDown_values = all_dropDown_values.optJSONArray(FORM_THREE_CHILD_THREE_DROPDOWN_VALUES);
        JSONArray form_three_child_four_dropDown_values = all_dropDown_values.optJSONArray(FORM_THREE_CHILD_FOUR_DROPDOWN_VALUES);

        JSONArray form_four_child_one_dropDown_values = all_dropDown_values.optJSONArray(FORM_FOUR_CHILD_ONE_DROPDOWN_VALUES);
        JSONArray form_four_child_two_dropDown_values = all_dropDown_values.optJSONArray(FORM_FOUR_CHILD_TWO_DROPDOWN_VALUES);
        JSONArray form_four_child_three_dropDown_values = all_dropDown_values.optJSONArray(FORM_FOUR_CHILD_THREE_DROPDOWN_VALUES);
        JSONArray form_four_child_four_dropDown_values = all_dropDown_values.optJSONArray(FORM_FOUR_CHILD_FOUR_DROPDOWN_VALUES);


//        Log.d("rohit", "form_two_child_one_dropDown_values"+form_two_child_one_dropDown_values.toString());
//        Log.d("rohit", "form_two_child_two_dropDown_values"+form_two_child_two_dropDown_values.toString());
//        Log.d("rohit", "form_two_child_three_dropDown_values"+form_two_child_three_dropDown_values.toString());
//        Log.d("rohit", "form_two_child_four_dropDown_values"+form_two_child_four_dropDown_values.toString());
        saveDropDownData(activity_type_dropDown_values, Constant.ACTIVITY_DROPDOWN_VALUES);

        saveDropDownData(form_one_child_one_dropDown_values, Constant.FORM_ONE_CHILD_ONE_COMMON_CODE);
        saveDropDownData(form_one_child_two_dropDown_values,  Constant.FORM_ONE_CHILD_TWO_COMMON_CODE);
        saveDropDownData(form_one_child_three_dropDown_values,  Constant.FORM_ONE_CHILD_THREE_COMMON_CODE);
        saveDropDownData(form_one_child_four_dropDown_values, Constant.FORM_ONE_CHILD_FOUR_COMMON_CODE);

        saveDropDownData(form_two_child_one_dropDown_values, Constant.FORM_TWO_CHILD_ONE_COMMON_CODE);
        saveDropDownData(form_two_child_two_dropDown_values, Constant.FORM_TWO_CHILD_TWO_COMMON_CODE);
        saveDropDownData(form_two_child_three_dropDown_values, Constant.FORM_TWO_CHILD_THREE_COMMON_CODE);
        saveDropDownData(form_two_child_four_dropDown_values, Constant.FORM_TWO_CHILD_FOUR_COMMON_CODE);

        saveDropDownData(form_three_child_one_dropDown_values, Constant.FORM_THREE_CHILD_ONE_COMMON_CODE);
        saveDropDownData(form_three_child_two_dropDown_values, Constant.FORM_THREE_CHILD_TWO_COMMON_CODE);
        saveDropDownData(form_three_child_three_dropDown_values, Constant.FORM_THREE_CHILD_THREE_COMMON_CODE);
        saveDropDownData(form_three_child_four_dropDown_values, Constant.FORM_THREE_CHILD_FOUR_COMMON_CODE);

        saveDropDownData(form_four_child_one_dropDown_values, Constant.FORM_FOUR_CHILD_ONE_COMMON_CODE);
        saveDropDownData(form_four_child_two_dropDown_values, Constant.FORM_FOUR_CHILD_TWO_COMMON_CODE);
        saveDropDownData(form_four_child_three_dropDown_values, Constant.FORM_FOUR_CHILD_THREE_COMMON_CODE);
        saveDropDownData(form_four_child_four_dropDown_values,  Constant.FORM_FOUR_CHILD_FOUR_COMMON_CODE);



        JSONArray filterArray = jsonObject.optJSONArray(FILTER_DATA);
        userInfo.saveFilterJSONArray(FILTER_DATA, filterArray, mContext);
    }


    public static void saveDropDownData(JSONArray jsonArray, String TYPE) throws JSONException {
        Gson gson = new Gson();
        DropDownDataDAO handler = new DropDownDataDAO(contex);
        Log.d("rohit", "value "+jsonArray.toString()+"length "+jsonArray.length());
        for (int i =0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            DropDownDataDTO data = gson.fromJson(jsonObject.toString(), DropDownDataDTO.class);
            handler.saveFormData(TYPE, data);
        }

    }
}
