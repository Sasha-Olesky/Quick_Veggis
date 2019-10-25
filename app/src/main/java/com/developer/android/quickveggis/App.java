package com.developer.android.quickveggis;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static final String DEVELOPER_KEY = "AIzaSyDE14XnoMMPy5hIwwnxb7gYodeqS4aCrxU";
    public static boolean bool = false;

    static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static void setTask(boolean b){
        bool = b;
    }
    public static boolean getTask(){
        return bool;
    }
}
