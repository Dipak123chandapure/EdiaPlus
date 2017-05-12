package com.example.deepak.myapplication;

/**
 * Created by Deepak on 5/9/2017.
 */

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.microsoft.windowsazure.messaging.NotificationHub;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";

    private NotificationHub hub;

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String resultString = null;
        String regID = null;

        try {
            String token = intent.getStringExtra("TOKEN");

            // Storing the registration id that indicates whether the generated token has been
            // sent to your server. If it is not stored, send the token to your server,
            // otherwise your server should have already received the token.
            if ((regID=sharedPreferences.getString("registrationID", null)) == null) {
                NotificationHub hub = new NotificationHub(NotificationSettings.HubName,
                        NotificationSettings.HubListenConnectionString, this);
                Log.i("rohit", "Attempting to register with NH using token : " + token);

                regID = hub.register(token).getRegistrationId();

                // If you want to use tags...
                // Refer to : https://azure.microsoft.com/en-us/documentation/articles/notification-hubs-routing-tag-expressions/
                // regID = hub.register(token, "tag1", "tag2").getRegistrationId();

                resultString = "Registered Successfully - RegId : " + regID;
                sharedPreferences.edit().putString("registrationID", regID ).apply();
                Log.d("rohit", "registartion result "+resultString);
            } else {

                Log.d("rohit", "Previously Registered Successfully - RegId");
            }
        } catch (Exception e) {
            Log.e("rohit", "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
        }

    }
}
