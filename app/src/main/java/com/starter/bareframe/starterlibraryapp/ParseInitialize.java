package com.starter.bareframe.starterlibraryapp;

import android.app.Application;

import com.parse.Parse;

public class ParseInitialize extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize Parse with "Application_ID" and "Master_Key"
        Parse.initialize(this, "APPLICATION_ID", "MASTER_KEY");
    }

}