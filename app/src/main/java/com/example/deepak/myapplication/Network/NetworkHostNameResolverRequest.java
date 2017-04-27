package com.example.deepak.myapplication.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class NetworkHostNameResolverRequest {

    private static NetworkHostNameResolverRequest sInstance;
    private RequestQueue mRequestQueue;
    private static Context sCtx;

    private NetworkHostNameResolverRequest(Context context) {
        sCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized NetworkHostNameResolverRequest getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NetworkHostNameResolverRequest(context);
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(sCtx.getApplicationContext(), new MyHttpStack());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}