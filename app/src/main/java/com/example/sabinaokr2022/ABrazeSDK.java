package com.example.sabinaokr2022;

import android.app.Application;
import android.util.Log;

import com.braze.BrazeActivityLifecycleCallbackListener;
import com.braze.support.BrazeLogger;

public class ABrazeSDK extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BrazeLogger.setLogLevel(Log.VERBOSE);
        registerActivityLifecycleCallbacks(new BrazeActivityLifecycleCallbackListener());
    }
}
