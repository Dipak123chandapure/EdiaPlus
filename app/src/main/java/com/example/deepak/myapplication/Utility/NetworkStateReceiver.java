package com.example.deepak.myapplication.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/**
 * Created by ipspl on 21/1/16.
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Utility.isConnectedToInternet = Utility.isConnectedToInternet(context);
        Log.i("Network State Changed:", String.valueOf(Utility.isConnectedToInternet));
    }
}
