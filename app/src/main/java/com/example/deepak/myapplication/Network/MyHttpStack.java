package com.example.deepak.myapplication.Network;

import com.android.volley.toolbox.HurlStack;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MyHttpStack extends HurlStack {
    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.createConnection(url);
        try {
            httpsURLConnection.setHostnameVerifier(NetworkUtil.getHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpsURLConnection;
    }
}
