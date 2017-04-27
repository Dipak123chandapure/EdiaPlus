package com.example.deepak.myapplication.Network;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


public class NetworkUtil {

    public static String BASE_URL = "http://androidapi.extraaedge.com/";
    public static String LOGIN_ATHENTICATION = BASE_URL + "LoginAuthentication";
    private static final int SOCKET_TIME_OUT = 8000;//30 seconds
    public static final int NO_INTERNET_CONNECTION = 00;


    public static String getNetworkError(VolleyError e) {
        //String error = App.getInstance().getString(R.string.something_went_wrong);

        if (null != e && null != e.networkResponse) {
            NetworkResponse errorResponse = e.networkResponse;
            switch (errorResponse.statusCode) {
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    ///error = App.getInstance().getString(R.string.error_credentials);
                    break;
                case NO_INTERNET_CONNECTION:
                    //error = App.getInstance().getString(R.string.error_no_interner);
            }
        }
        return "";
    }

    public static void handleNoInternetConnection(final NetworkExecuter.OnNetworkResponse networkResponse) {
        VolleyError v = new VolleyError(new NetworkResponse(NetworkUtil.NO_INTERNET_CONNECTION, null, null, false));
        if (null != networkResponse) {
            networkResponse.onFailure(v);
        }
    } 
    
    public static void handleJsonParsingException(final NetworkExecuter.OnNetworkResponse networkResponse) {
        VolleyError v = new VolleyError(new NetworkResponse(null));
        if (null != networkResponse) {
            networkResponse.onFailure(v);
        }
    }

    public static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                //return true;
//                MyHttpStack hv = HttpsURLConnection.getDefaultHostnameVerifier();
//                return hv.verify("localhost", session);
                return true;
            }
        };
    }
    
    public static RetryPolicy getRetryPolicy(){
        return new DefaultRetryPolicy(SOCKET_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

}
