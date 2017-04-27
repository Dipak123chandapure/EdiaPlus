package com.example.deepak.myapplication.Application;

import android.app.Activity;
import android.app.Application;

import com.example.deepak.myapplication.Utility.Utility;

/**
 * Created by Deepak on 4/26/2017.
 */

public class App extends Application {
    private static App sApplication;
    private Activity mLiveContext;


    @Override
    public void onCreate() {
        sApplication = this;
        super.onCreate();

        Utility.isConnectedToInternet = Utility.isConnectedToInternet(this);

    }

    public Activity getLiveContext() {
        return mLiveContext;
    }

    public void setLiveContext(Activity mLiveContext) {
        this.mLiveContext = mLiveContext;
    }

    public static App getInstance() {
        return sApplication;
    }

}
