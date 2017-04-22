package com.example.deepak.myapplication.SmartCaller;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.deepak.myapplication.Utility.Constant;

import java.util.Date;

public class SmartCallerReceiver extends AbstractSmartCallerReceiver {

    protected void onIncomingCallStarted(Context ctx, String number, Date start, Intent intent) {
        Log.d("rohit", "incoming call started");
        Intent mIntent = new Intent(ctx, SmartCallerService.class);
        mIntent.putExtra(Constant.SMART_CALLER_DIAL_NUMBER, number);
        mIntent.putExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE, Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_STARTED);
        ctx.startService(mIntent);
    }

    protected void onIncomingCallAnswered(Context ctx, String number, Date start) {
        Log.d("rohit", "incoming call Answred");
        Intent mIntent = new Intent();
        mIntent.setAction(Constant.SMART_CALLER_DIAL_BROAD_CAST_RECEIVER);
        mIntent.putExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE, Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_RECEIVED);
        ctx.sendBroadcast(mIntent);
    }

    protected void onOutgoingCallStarted(Context ctx, String number, Date start, Intent intent) {
        Log.d("rohit", "outgoing call started" + number);
        Intent mIntent = new Intent(ctx, SmartCallerService.class);
        mIntent.putExtra(Constant.SMART_CALLER_DIAL_NUMBER, number);
        mIntent.putExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE, Constant.SMART_CALLER_DIAL_TYPE_OUTGOING_CALL_STARTED);
        ctx.startService(mIntent);
    }

    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.d("rohit", "incoming call ended");
        Intent mIntent = new Intent();
        mIntent.setAction(Constant.SMART_CALLER_DIAL_BROAD_CAST_RECEIVER);
        mIntent.putExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE, Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_ENDED);
        ctx.sendBroadcast(mIntent);
        //ctx.startService(new Intent(ctx, SmartCallerService.class).putExtra("OPERATION", "STOP"));
    }

    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end, Intent i) {
        Log.d("rohit", "outgoing call ended");
        Intent mIntent = new Intent();
        mIntent.setAction(Constant.SMART_CALLER_DIAL_BROAD_CAST_RECEIVER);
        mIntent.putExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE, Constant.SMART_CALLER_DIAL_TYPE_OUTGOING_CALL_ENDED);
        ctx.sendBroadcast(mIntent);
    }

    protected void onMissedCall(Context ctx, String number, Date start) {
        Log.d("rohit", "missed call");
        Intent mIntent = new Intent();
        mIntent.setAction(Constant.SMART_CALLER_DIAL_BROAD_CAST_RECEIVER);
        mIntent.putExtra(Constant.SMART_CALLER_DIAL_BROAD_CAST_TYPE, Constant.SMART_CALLER_DIAL_TYPE_INCOMING_CALL_MISSED);
        ctx.sendBroadcast(mIntent);
    }

    protected void onDataCame(Context ctx, Intent intent) {
        Log.d("rohit", "data came");
    }
}
