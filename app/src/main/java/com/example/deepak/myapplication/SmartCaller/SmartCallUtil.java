package com.example.deepak.myapplication.SmartCaller;


import com.example.deepak.myapplication.Utility.Constant;

public class SmartCallUtil {
    public static int getIDForCallType(String callType) {
        switch (callType) {
            case Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_STARTED:
                return 5;

            case Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_RECEIVED:
                return 5;

            case Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_MISSED:
                return 4;

            case Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_ENDED:
                return 5;

            case Constant.SMART_CALLER_DIAL_TYPE_OUTGOING_CALL_STARTED:
                return 3;

            case Constant.SMART_CALLER_DIAL_TYPE_OUTGOING_CALL_ENDED:
                return 3;

        }
        return 33;
    }

    public static String getCallDuration(Long callEndingTime, Long callStartingTime) {
        int duration;
        String mDuration;
        if (null != callEndingTime) {
            duration = (int) ((callEndingTime - callStartingTime) / 1600);
            if (duration / 60 > 0)
                mDuration = (duration/60)+": "+(duration%60)+"s";
            else mDuration = ""+duration+"s";
        }else mDuration = "";

        return mDuration;
    }
}
