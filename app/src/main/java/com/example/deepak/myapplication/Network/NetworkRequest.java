package com.example.deepak.myapplication.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by manishp on 2/3/16.
 */
class NetworkRequest {

    private static NetworkRequest sInstance;
    private RequestQueue mRequestQueue;
    private static Context sCtx;

    private NetworkRequest(Context context) {
        sCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized NetworkRequest getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NetworkRequest(context);
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(sCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}