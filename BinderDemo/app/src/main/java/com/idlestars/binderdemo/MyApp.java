package com.idlestars.binderdemo;

import android.app.Application;

public class MyApp extends Application {
    private static String processName;
    private static String appName;

    @Override
    public void onCreate() {
        super.onCreate();

        appName = getPackageName();
        processName = getApplicationInfo().processName;
    }

    public static String getProcessName() {
        return processName;
    }

    public static String getAppName() {
        return appName;
    }

    public static String getAppDescription() {
        return "Process = " + MyApp.getProcessName()
                + ", AppName = " + MyApp.getAppName();
    }
}
