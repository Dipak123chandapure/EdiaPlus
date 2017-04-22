package com.example.deepak.myapplication.Utility;

/**
 * Created by DeepakC on 24/03/2017.
 */

public class ModalData {



    public String getAllMasters(){
      return sampleData;
    };
    public String sampleData = "{\n" +
            "\t\"ALL_FORM_CONSTRAINS\": {\n" +
            "\t\t\"FORM_ONE_CONSTRAINS\": {\n" +
            "\t\t\t\"TITLE\": \"Basic Information\",\n" +
            "\t\t\t\"IS_VISIBLE\": true,\n" +
            "\n" +
            "\t\t\t\"CHILD_ONE_TITLE\": \"First Name\",\n" +
            "\t\t\t\"IS_CHILD_ONE_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_ONE_COMPULSORY\": true,\n" +
            "\n" +
            "\t\t\t\"CHILD_TWO_TITLE\": \"Last Name\",\n" +
            "\t\t\t\"IS_CHILD_TWO_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_TWO_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_THREE_TITLE\": \"Email id\",\n" +
            "\t\t\t\"IS_CHILD_THREE_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_THREE_COMPULSORY\": true,\n" +
            "\n" +
            "\t\t\t\"CHILD_FOUR_TITLE\": \"Mobile Num\",\n" +
            "\t\t\t\"IS_CHILD_FOUR_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_FOUR_COMPULSORY\": true\n" +
            "\t\t},\n" +
            "\n" +
            "\n" +
            "\t\t\"FORM_TWO_CONSTRAINS\": {\n" +
            "\t\t\t\"TITLE\": \"Lead Information\",\n" +
            "\t\t\t\"IS_VISIBLE\": true,\n" +
            "\n" +
            "\t\t\t\"CHILD_ONE_TITLE\": \"Lead source\",\n" +
            "\t\t\t\"IS_CHILD_ONE_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_ONE_COMPULSORY\": true,\n" +
            "\n" +
            "\t\t\t\"CHILD_TWO_TITLE\": \"Lead status\",\n" +
            "\t\t\t\"IS_CHILD_TWO_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_TWO_COMPULSORY\": true,\n" +
            "\n" +
            "\t\t\t\"CHILD_THREE_TITLE\": \"Lead priority\",\n" +
            "\t\t\t\"IS_CHILD_THREE_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_THREE_COMPULSORY\": true,\n" +
            "\n" +
            "\t\t\t\"CHILD_FOUR_TITLE\": \"Lead group\",\n" +
            "\t\t\t\"IS_CHILD_FOUR_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_FOUR_COMPULSORY\": true\n" +
            "\t\t},\n" +
            "\n" +
            "\t\t\"FORM_THREE_CONSTRAINS\": {\n" +
            "\t\t\t\"TITLE\": \"More Information\",\n" +
            "\t\t\t\"IS_VISIBLE\": true,\n" +
            "\n" +
            "\t\t\t\"CHILD_ONE_TITLE\": \"Location\",\n" +
            "\t\t\t\"IS_CHILD_ONE_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_ONE_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_TWO_TITLE\": \"Course\",\n" +
            "\t\t\t\"IS_CHILD_TWO_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_TWO_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_THREE_TITLE\": \"Graduation\",\n" +
            "\t\t\t\"IS_CHILD_THREE_VISIBLE\": true,\n" +
            "\t\t\t\"IS_CHILD_THREE_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_FOUR_TITLE\": \"Test\",\n" +
            "\t\t\t\"IS_CHILD_FOUR_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_FOUR_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_FIVE_TITLE\": \"Test\",\n" +
            "\t\t\t\"IS_CHILD_FIVE_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_FIVE_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_SIX_TITLE\": \"Test\",\n" +
            "\t\t\t\"IS_CHILD_SIX_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_SIX_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_SEVEN_TITLE\": \"Test\",\n" +
            "\t\t\t\"IS_CHILD_SEVEN_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_SEVEN_COMPULSORY\": false\n" +
            "\t\t},\n" +
            "\n" +
            "\t\t\"FORM_FOUR_CONSTRAINS\": {\n" +
            "\t\t\t\"TITLE\": \"Test\",\n" +
            "\t\t\t\"IS_VISIBLE\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_ONE_TITLE\": \"Test1\",\n" +
            "\t\t\t\"IS_CHILD_ONE_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_ONE_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_TWO_TITLE\": \"Test2\",\n" +
            "\t\t\t\"IS_CHILD_TWO_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_TWO_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_THREE_TITLE\": \"Test3\",\n" +
            "\t\t\t\"IS_CHILD_THREE_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_THREE_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_FOUR_TITLE\": \"Test4\",\n" +
            "\t\t\t\"IS_CHILD_FOUR_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_FOUR_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_FIVE_TITLE\": \"Test\",\n" +
            "\t\t\t\"IS_CHILD_FIVE_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_FIVE_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_SIX_TITLE\": \"Test\",\n" +
            "\t\t\t\"IS_CHILD_SIX_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_SIX_COMPULSORY\": false,\n" +
            "\n" +
            "\t\t\t\"CHILD_SEVEN_TITLE\": \"Test\",\n" +
            "\t\t\t\"IS_CHILD_SEVEN_VISIBLE\": false,\n" +
            "\t\t\t\"IS_CHILD_SEVEN_COMPULSORY\": false\n" +
            "\t\t}\n" +
            "\n" +
            "\t},\n" +
            "\n" +
            "\n" +
            "\t\"FORM_DROPDOWN_VALUES\": {\n" +
            "\n" +
            "\t\t\"FORM_ONE_CHILD_ONE_DROPDOWN_VALUES\": [],\n" +
            "\t\t\"FORM_ONE_CHILD_TWO_DROPDOWN_VALUES\": [],\n" +
            "\t\t\"FORM_ONE_CHILD_THREE_DROPDOWN_VALUES\": [],\n" +
            "\t\t\"FORM_ONE_CHILD_FOUR_DROPDOWN_VALUES\": [],\n" +
            "\n" +
            "\t\t\"FORM_TWO_CHILD_ONE_DROPDOWN_VALUES\": [{\n" +
            "\t\t\t\t\"title\": \"Facebook\",\n" +
            "\t\t\t\t\"id\": 1,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Shiksha\",\n" +
            "\t\t\t\t\"id\": 2,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Aluminia Referral\",\n" +
            "\t\t\t\t\"id\": 4,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Google Adv\",\n" +
            "\t\t\t\t\"id\": 5,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\n" +
            "\t\t\"FORM_TWO_CHILD_TWO_DROPDOWN_VALUES\": [{\n" +
            "\t\t\t\t\"title\": \"New Open\",\n" +
            "\t\t\t\t\"id\": 1,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"New Callback\",\n" +
            "\t\t\t\t\"id\": 2,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"FollowUp Callback\",\n" +
            "\t\t\t\t\"id\": 3,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Enrolled\",\n" +
            "\t\t\t\t\"id\": 4,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Closed\",\n" +
            "\t\t\t\t\"id\": 5,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\n" +
            "\t\t\"FORM_TWO_CHILD_THREE_DROPDOWN_VALUES\": [{\n" +
            "\t\t\t\t\"title\": \"Cold\",\n" +
            "\t\t\t\t\"id\": 1,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Warm\",\n" +
            "\t\t\t\t\"id\": 2,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Hot\",\n" +
            "\t\t\t\t\"id\": 3,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"High\",\n" +
            "\t\t\t\t\"id\": 4,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Medium\",\n" +
            "\t\t\t\t\"id\": 5,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\n" +
            "\t\t\"FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES\": [{\n" +
            "\t\t\t\t\"title\": \"Android Group\",\n" +
            "\t\t\t\t\"id\": 1,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"JAVA Group\",\n" +
            "\t\t\t\t\"id\": 2,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"React.js Group\",\n" +
            "\t\t\t\t\"id\": 3,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"English Group\",\n" +
            "\t\t\t\t\"id\": 4,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Marathi Group\",\n" +
            "\t\t\t\t\"id\": 5,\n" +
            "\t\t\t\t\"details\": \"FORM_TWO_CHILD_FOUR_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\n" +
            "\n" +
            "\t\t\"FORM_THREE_CHILD_ONE_DROPDOWN_VALUES\": [{\n" +
            "\t\t\t\t\"title\": \"Mumbai\",\n" +
            "\t\t\t\t\"id\": 1,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Pune\",\n" +
            "\t\t\t\t\"id\": 2,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Alahabad\",\n" +
            "\t\t\t\t\"id\": 3,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Jaipur\",\n" +
            "\t\t\t\t\"id\": 4,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Tiruanantpuram\",\n" +
            "\t\t\t\t\"id\": 5,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_ONE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\n" +
            "\t\t\"FORM_THREE_CHILD_TWO_DROPDOWN_VALUES\": [{\n" +
            "\t\t\t\t\"title\": \"Android Development\",\n" +
            "\t\t\t\t\"id\": 1,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Core Java\",\n" +
            "\t\t\t\t\"id\": 2,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Objective C\",\n" +
            "\t\t\t\t\"id\": 3,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"IOS development\",\n" +
            "\t\t\t\t\"id\": 4,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Linear Algebra\",\n" +
            "\t\t\t\t\"id\": 5,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_TWO_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\n" +
            "\t\t\"FORM_THREE_CHILD_THREE_DROPDOWN_VALUES\": [{\n" +
            "\t\t\t\t\"title\": \"B.tech\",\n" +
            "\t\t\t\t\"id\": 1,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"BSC\",\n" +
            "\t\t\t\t\"id\": 2,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"BE Engineering\",\n" +
            "\t\t\t\t\"id\": 3,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"MSC\",\n" +
            "\t\t\t\t\"id\": 4,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"BCOM\",\n" +
            "\t\t\t\t\"id\": 5,\n" +
            "\t\t\t\t\"details\": \"FORM_THREE_CHILD_THREE_DROPDOWN_VALUES\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\n" +
            "\t\t\"FORM_THREE_CHILD_FOUR_DROPDOWN_VALUES\": [],\n" +
            "\n" +
            "\t\t\"FORM_FOUR_CHILD_ONE_DROPDOWN_VALUES\": [],\n" +
            "\t\t\"FORM_FOUR_CHILD_TWO_DROPDOWN_VALUES\": [],\n" +
            "\t\t\"FORM_FOUR_CHILD_THREE_DROPDOWN_VALUES\": [],\n" +
            "\t\t\"FORM_FOUR_CHILD_FOUR_DROPDOWN_VALUES\": []\n" +
            "\t},\n" +
            "\n" +
            "\n" +
            "    \"FILTER_DATA\":[\n" +
            "\n" +
            "            {\n" +
            "                \"KEY\":\"FORM_TWO_CHILD_ONE_COMMON_CODE\",\n" +
            "                \"DATABASE_KEY\": \"FORM_2_ENTITY_1_ID\",\n" +
            "                \"VALUE\":\"Sources\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"KEY\":\"FORM_TWO_CHILD_TWO_COMMON_CODE\",\n" +
            "                \"DATABASE_KEY\": \"FORM_2_ENTITY_2_ID\",\n" +
            "                \"VALUE\":\"Status\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"KEY\":\"FORM_TWO_CHILD_THREE_COMMON_CODE\",\n" +
            "                \"DATABASE_KEY\": \"FORM_2_ENTITY_3_ID\",\n" +
            "                \"VALUE\":\"Priorities\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"KEY\":\"FORM_TWO_CHILD_FOUR_COMMON_CODE\",\n" +
            "                \"DATABASE_KEY\": \"FORM_2_ENTITY_4_ID\",\n" +
            "                \"VALUE\":\"Groups\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"KEY\":\"FORM_THREE_CHILD_ONE_COMMON_CODE\",\n" +
            "                \"DATABASE_KEY\": \"FORM_3_ENTITY_1_ID\",\n" +
            "                \"VALUE\":\"Locations\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"KEY\":\"FORM_THREE_CHILD_TWO_COMMON_CODE\",\n" +
            "                \"DATABASE_KEY\": \"FORM_3_ENTITY_2_ID\",\n" +
            "                \"VALUE\":\"Courses\"\n" +
            "            }, \n" +
            "            {\n" +
            "                \"KEY\":\"FORM_THREE_CHILD_THREE_COMMON_CODE\",\n" +
            "                \"DATABASE_KEY\": \"FORM_3_ENTITY_3_ID\",\n" +
            "                \"VALUE\":\"Graduation\"\n" +
            "            }\n" +
            "           ],\n" +
            "\n" +
            "\n" +
            "\t\t   \"ACTIVITY_TYPE_DROPDOWN_VALUES\": [{\n" +
            "\t\t\t\t\"title\": \"Send SMS\",\n" +
            "\t\t\t\t\"id\": 1,\n" +
            "\t\t\t\t\"details\": \"Details of send sms\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Receive SMS\",\n" +
            "\t\t\t\t\"id\": 2,\n" +
            "\t\t\t\t\"details\": \"Details of send sms\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Dialed Call\",\n" +
            "\t\t\t\t\"id\": 3,\n" +
            "\t\t\t\t\"details\": \"Details of send sms\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Missed Call\",\n" +
            "\t\t\t\t\"id\": 4,\n" +
            "\t\t\t\t\"details\": \"Details of send sms\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"title\": \"Received Call\",\n" +
            "\t\t\t\t\"id\": 5,\n" +
            "\t\t\t\t\"details\": \"Details of send sms\",\n" +
            "\t\t\t\t\"isSystemValue\": false\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\n" +
            "}";
}
