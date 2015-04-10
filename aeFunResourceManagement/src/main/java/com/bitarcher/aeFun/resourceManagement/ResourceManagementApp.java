/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.resourceManagement;

import android.app.Application;
import android.content.Context;

/**
 * Created by michel on 29/01/15.
 */
public class ResourceManagementApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}