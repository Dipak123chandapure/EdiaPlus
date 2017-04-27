package com.example.deepak.myapplication.Network;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.deepak.myapplication.Application.App;
import com.example.deepak.myapplication.Utility.Utility;

import java.util.HashMap;
import java.util.Map;

public class NetworkExecuter {
    private static final String TAG = NetworkExecuter.class.getSimpleName();

    public static void sendSms(final String mobiles, final String message, final OnNetworkResponse networkResponse) {
        if (Utility.isConnectedToInternet) {
            Log.d("rohit", "connected to internet");
            String hittingUrl = "https://control.msg91.com/api/sendhttp.php?";
            Log.i(TAG, "Hitting URL for send sms " + hittingUrl);
            final String authkey = "137912AsnrvNtZ590179dd";
            final String route = "1";
            final String senderId = "832909";

            StringRequest request = new StringRequest(Request.Method.POST, hittingUrl,
                    new Response.Listener<String>() {
                        public void onResponse(String response) {
                            Log.i(TAG, "sendSms success " + response);
                            if (null != networkResponse) {
                                networkResponse.onSuccess(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "sendSms failure ", error);
                    if (null != networkResponse) {
                        networkResponse.onFailure(error);
                    }
                }
            }) {

                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("authkey", authkey);
                    params.put("mobiles", "8229055090");
                    params.put("message", message);
                    params.put("route", route);
                    params.put("sender", senderId);
                    return params;
                }
            };
            request.setRetryPolicy(NetworkUtil.getRetryPolicy());
            NetworkHostNameResolverRequest.getInstance(App.getInstance()).addToRequestQueue(request);
        } else {
            networkResponse.onNoConnction();
        }
    }

    public interface OnNetworkResponse {
        void onSuccess(String response);
        void onFailure(VolleyError response);
        void onNoConnction();
    }
}